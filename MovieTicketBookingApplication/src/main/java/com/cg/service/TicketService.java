package com.cg.service;

import java.util.List;

import com.cg.exception.TicketNotFoundException;
import com.cg.model.Ticket;

public interface TicketService {
	public Ticket addTicket(Ticket ticket,Integer bookingId) throws TicketNotFoundException;

	public Ticket findTicket(int ticketId);

	List<Ticket> viewTicketList() throws TicketNotFoundException;

}
