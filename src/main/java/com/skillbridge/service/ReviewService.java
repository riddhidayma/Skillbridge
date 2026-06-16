package com.skillbridge.service;

import com.skillbridge.entity.Review;
import com.skillbridge.entity.User;
import com.skillbridge.entity.Workshop;
import com.skillbridge.exception.BadRequestException;
import com.skillbridge.exception.ConflictException;
import com.skillbridge.exception.ResourceNotFoundException;
import com.skillbridge.repository.ReviewRepository;
import com.skillbridge.repository.UserRepository;
import com.skillbridge.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final WorkshopRepository workshopRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, WorkshopRepository workshopRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.workshopRepository = workshopRepository;
    }

    public Review addReview(Long learnerId, Long workshopId, Review review) {
        User learner = userRepository.findById(learnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found"));
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found"));

        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new BadRequestException("Rating must be between 1 and 5");
        }

        if (reviewRepository.existsByLearnerIdAndWorkshopId(learnerId, workshopId)) {
            throw new ConflictException("You have already reviewed this workshop.");
        }

        review.setLearner(learner);
        review.setWorkshop(workshop);

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForWorkshop(Long workshopId) {
        return reviewRepository.findByWorkshopId(workshopId);
    }
}