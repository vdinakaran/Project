package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IBookingRepository;
import com.cg.dao.ISeatRepository;
import com.cg.dao.ITicketRepository;
import com.cg.exception.TicketNotFoundException;
import com.cg.model.Ticket;
import com.cg.model.TicketBooking;

@Service
public class TicketServiceImpl implements TicketService {

	private ITicketRepository ticketRepository;

	public TicketServiceImpl(ITicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	
	@Autowired
	private ISeatRepository seatRepository;
	
	@Autowired
	private IBookingRepository bookingRepository;
	
	@Override
	public Ticket addTicket(Ticket ticket,Integer transactionId) throws TicketNotFoundException {
		TicketBooking booking=new TicketBooking();
		if(transactionId!=null) {
			booking=bookingRepository.findById(transactionId).get();
			booking.setTransactionStatus("Completed");
			ticket.setTicketBooking(booking);
		}
		ticketRepository.saveAndFlush(ticket);
		/*
		 * for(Seat s:ticket.getSeats()) { s.setTickett(ticket);
		 * seatRepository.saveAndFlush(s); }
		 */
		return ticket;
	}

	@Override
	public List<Ticket> viewTicketList() throws TicketNotFoundException {
		List<Ticket> ti = ticketRepository.findAll();
		if (ti.size() == 0)
			throw new TicketNotFoundException("No tickets are avaliable");
		return ti;
	}

	@Override
	public Ticket findTicket(int ticketId) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(ticketId).get();
	}

}
