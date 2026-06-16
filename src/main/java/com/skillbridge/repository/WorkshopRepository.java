package com.skillbridge.repository;

import com.skillbridge.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

    // Custom query to find all workshops hosted by a specific mentor
    List<Workshop> findByMentorId(Long mentorId);

    @Modifying
    @Query("update Workshop w set w.availableSeats = w.availableSeats - 1 where w.id = :workshopId and w.availableSeats > 0")
    int decrementAvailableSeatsIfPossible(@Param("workshopId") Long workshopId);
}