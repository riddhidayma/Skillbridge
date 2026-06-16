package com.skillbridge.service;

import com.skillbridge.entity.User;
import com.skillbridge.entity.Workshop;
import com.skillbridge.exception.BadRequestException;
import com.skillbridge.exception.ForbiddenException;
import com.skillbridge.exception.ResourceNotFoundException;
import com.skillbridge.repository.UserRepository;
import com.skillbridge.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final UserRepository userRepository;

    // Injecting both repositories
    public WorkshopService(WorkshopRepository workshopRepository, UserRepository userRepository) {
        this.workshopRepository = workshopRepository;
        this.userRepository = userRepository;
    }

    // CREATE WORKSHOP
    public Workshop createWorkshop(Long mentorId, Workshop workshop) {
        // 1. Find the user by ID
        User mentor = userRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with ID: " + mentorId));

        if (!"MENTOR".equalsIgnoreCase(mentor.getRole())) {
            throw new ForbiddenException("Only users with the MENTOR role can create workshops.");
        }

        if (workshop.getCategory() == null || workshop.getCategory().isBlank()) {
            workshop.setCategory("General");
        }

        if (workshop.getTitle() == null || workshop.getTitle().isBlank()) {
            throw new BadRequestException("Workshop title is required.");
        }
        if (workshop.getPrice() == null || workshop.getPrice().signum() < 0) {
            throw new BadRequestException("Workshop price must be zero or greater.");
        }
        if (workshop.getAvailableSeats() == null || workshop.getAvailableSeats() < 1) {
            throw new BadRequestException("Available seats must be at least 1.");
        }
        if (workshop.getScheduledDate() == null) {
            throw new BadRequestException("Scheduled date is required.");
        }

        workshop.setMentor(mentor);
        return workshopRepository.save(workshop);
    }

    // GET ALL WORKSHOPS
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    public List<Workshop> getWorkshopsByMentor(Long mentorId) {
        userRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with ID: " + mentorId));
        return workshopRepository.findByMentorId(mentorId);
    }

    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found with ID: " + id));
    }
}