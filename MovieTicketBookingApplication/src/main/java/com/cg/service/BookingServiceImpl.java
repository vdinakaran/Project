package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IBookingRepository;
import com.cg.dao.ICustomerRepository;
import com.cg.dao.IMovieRepository;
import com.cg.dao.IShowRepository;
import com.cg.dao.ITicketRepository;
import com.cg.dao.QueryClass;
import com.cg.exception.BookingNotFoundException;
import com.cg.model.Customer;
import com.cg.model.Seat;
import com.cg.model.Show;
import com.cg.model.Ticket;
import com.cg.model.TicketBooking;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;
	@Autowired
	IMovieRepository moviesRepository;
	@Autowired
	IShowRepository showRepository;
	@Autowired
	ICustomerRepository custoRepository;
	@Autowired
	ITicketRepository ticketRepository;
	@Autowired
	QueryClass query;

	@Override
	public TicketBooking addBooking(TicketBooking booking, Integer customerId, Integer showId) throws BookingNotFoundException {
		Customer customer = new Customer();
		Show show=new Show();
		/*
		 * if (bookingRepository.existsById(booking.getTransactionId())) { throw new
		 * BookingNotFoundException("Booking with this transcationId is already done");
		 * } else {}
		 */
		if(showId!=null) {
				customer = custoRepository.getOne(customerId);
				show=showRepository.findById(showId).get();
				show.setBooking(booking);
				booking.setCustomer(customer);
				booking.setShow(show);
		}
			bookingRepository.saveAndFlush(booking);
			showRepository.saveAndFlush(show);
			custoRepository.saveAndFlush(customer);
		return booking;
	}

	@Override
	public TicketBooking updateBooking(TicketBooking booking) throws BookingNotFoundException {
		bookingRepository.saveAndFlush(booking);
		return bookingRepository.getOne(booking.getTransactionId());
	}

	@Override
	public TicketBooking cancelBooking(int bookingid) throws BookingNotFoundException {
		TicketBooking b = bookingRepository.getOne(bookingid);
		bookingRepository.delete(b);
		return b;
	}

	@Override
	public List<TicketBooking> showAllBookings(int movieid) throws BookingNotFoundException {
		List<TicketBooking> bk = query.getAllByMovieId(movieid);
		/*
		 * if (bk.size() == 0) throw new BookingNotFoundException("No bookings found");
		 */
		return bk;
	}

	@Override
	public List<TicketBooking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException {
		List<TicketBooking> bkList = new ArrayList<>();
		for (TicketBooking booking : bookingRepository.findAll()) {
			if (booking.getBookingDate() != null && booking.getBookingDate().isEqual(bookingdate)) {
				bkList.add(booking);
			}
		}
		if (bkList.size() == 0)
			throw new BookingNotFoundException("No bookings found");
		else {
			return bkList;
		}
	}

	@Override
	public double calculateTotalCost(int bookingid) {
		List<Ticket> tickets = ticketRepository.findAll();
		Set<Seat> Seats = new HashSet<>();
		for (Ticket ticket : tickets) {
			if (bookingid == ticket.getTicketBooking().getTransactionId()) {
				Seats.addAll(ticket.getSeats());
			}
		}
		double amount = 0;
		for (Seat seat : Seats) {
			amount = amount + seat.getPrice();
		}
		TicketBooking booking = bookingRepository.getOne(bookingid);
		booking.setTotalCost(amount);
		bookingRepository.saveAndFlush(booking);
		return amount;
	}

	@Override
	public List<TicketBooking> viewBookingList() throws BookingNotFoundException {
		List<TicketBooking> bk = bookingRepository.findAll();
		/*
		 * if (bk.size() == 0) throw new BookingNotFoundException("No bookings found");
		 */
		return bk;
	}

	@Override
	public TicketBooking viewBooking(int bookingid) throws BookingNotFoundException {
		return bookingRepository.findById(bookingid).get();
	}

}
