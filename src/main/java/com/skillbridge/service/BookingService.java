package com.skillbridge.service;

import com.skillbridge.entity.Booking;
import com.skillbridge.entity.User;
import com.skillbridge.entity.Workshop;
import com.skillbridge.exception.BadRequestException;
import com.skillbridge.exception.ConflictException;
import com.skillbridge.exception.ForbiddenException;
import com.skillbridge.exception.ResourceNotFoundException;
import com.skillbridge.repository.BookingRepository;
import org.springframework.dao.DataIntegrityViolationException;
import com.skillbridge.repository.UserRepository;
import com.skillbridge.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final WorkshopRepository workshopRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, WorkshopRepository workshopRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.workshopRepository = workshopRepository;
    }

    // @Transactional is crucial here! If saving the booking fails,
    // the seat reduction is automatically undone.
    @Transactional
    public Booking createBooking(Long learnerId, Long workshopId) {
        User learner = userRepository.findById(learnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found"));

        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found"));

        if (!"LEARNER".equalsIgnoreCase(learner.getRole())) {
            throw new ForbiddenException("Only learners can enroll in workshops.");
        }

        boolean alreadyBooked = bookingRepository.findByLearnerId(learnerId).stream()
                .anyMatch(existing -> existing.getWorkshop() != null
                        && workshopId.equals(existing.getWorkshop().getId()));
        if (alreadyBooked) {
            throw new ConflictException("You have already enrolled in this workshop.");
        }

        Integer availableSeats = workshop.getAvailableSeats();
        if (availableSeats == null || availableSeats <= 0) {
            throw new BadRequestException("Workshop is completely sold out!");
        }

        // 1. Reduce available seats atomically to avoid stale writes.
        int updatedRows = workshopRepository.decrementAvailableSeatsIfPossible(workshopId);
        if (updatedRows == 0) {
            throw new ConflictException("Workshop seats were just taken. Please try again.");
        }

        // 2. Create the booking record
        Booking booking = new Booking();
        booking.setLearner(learner);
        booking.setWorkshop(workshop);
        booking.setStatus("CONFIRMED");

        try {
            return bookingRepository.save(booking);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("You have already enrolled in this workshop.");
        }
    }

    public List<Booking> getBookingsByLearner(Long learnerId) {
        return bookingRepository.findByLearnerId(learnerId);
    }
}