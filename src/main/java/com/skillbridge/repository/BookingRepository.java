package com.skillbridge.repository;

import com.skillbridge.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByLearnerId(Long learnerId);

    boolean existsByLearnerIdAndWorkshopId(Long learnerId, Long workshopId);
}