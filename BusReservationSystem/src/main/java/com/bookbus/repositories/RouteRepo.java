package com.bookbus.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookbus.models.Bus;
import com.bookbus.models.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

	@Query("select b from Bus b where b.routeFrom=?1 and b.routeTo=?2")
	public List<Bus> findByRouteFromAndRouteTo(String from, String to);

	
}
