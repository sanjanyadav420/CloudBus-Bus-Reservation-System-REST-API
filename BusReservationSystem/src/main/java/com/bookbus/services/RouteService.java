package com.bookbus.services;


import java.util.List;

import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.RouteException;
import com.bookbus.models.Route;

public interface RouteService {
	
	public Route addRoute(Route route, Integer id) throws RouteException, LogException ;
	public Route updateRoute(Route route, Integer id) throws RouteException, LogException ;
	public  Route deleteRoute(Integer routeId, Integer adminId) throws RouteException, LogException;
    public Route viewRoute(int routeId) throws RouteException;
    public List<Route> viewAllRoute() throws RouteException;
    
}