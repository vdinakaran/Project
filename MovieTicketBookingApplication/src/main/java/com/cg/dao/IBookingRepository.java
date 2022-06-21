package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.TicketBooking;

@Repository
public interface IBookingRepository extends JpaRepository<TicketBooking, Integer> {

}
