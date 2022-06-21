package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.exception.BookingNotFoundException;
import com.cg.model.TicketBooking;

public interface IBookingService {
	public TicketBooking addBooking(TicketBooking booking, Integer customerId,Integer showId) throws BookingNotFoundException;

	public List<TicketBooking> viewBookingList() throws BookingNotFoundException;

	public TicketBooking updateBooking(TicketBooking booking) throws BookingNotFoundException;

	public TicketBooking cancelBooking(int bookingid) throws BookingNotFoundException;

	public List<TicketBooking> showAllBookings(int movieid) throws BookingNotFoundException;
	public TicketBooking viewBooking(int bookingid) throws BookingNotFoundException;
	public List<TicketBooking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException;

	public double calculateTotalCost(int bookingid);

}
