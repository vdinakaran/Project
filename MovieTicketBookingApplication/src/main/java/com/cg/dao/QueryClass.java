package com.cg.dao;

import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import com.cg.model.User;
import com.cg.exception.UserNotFoundException;
import com.cg.model.TicketBooking;

@Repository
public class QueryClass {
	@PersistenceContext
	EntityManager eManager;

	public List<TicketBooking> getAllByMovieId(int movieId) {
		TypedQuery<TicketBooking> qry = eManager
				.createQuery("select b from Booking b join b.show s where s.movie.movieId = :id", TicketBooking.class);
		qry.setParameter("id", movieId);
		return qry.getResultList();
	}

	public User findByUserName(String username) throws UserNotFoundException {
		TypedQuery<User> qry = eManager.createQuery("select u from User u where u.username like :name", User.class);
		qry.setParameter("name", username);
		List<User> user = qry.getResultList();
		if (user.size() == 0)
			throw new UserNotFoundException("User Not Available !!");
		return user.get(0);
	}

}
