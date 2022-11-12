package com.bookbus.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbus.exceptions.RouteException;
import com.bookbus.models.Bus;
import com.bookbus.models.Route;
import com.bookbus.repositories.RouteRepo;
import com.bookbus.services.RouteService;


@Service
public class RouteServiceImplementation implements RouteService {
	
	@Autowired
	private RouteRepo  routeRepo;

	@Override
	public Route addRoute(Route route) throws RouteException {
		
		 Route existingRoute =   routeRepo.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
	
		 if(existingRoute!=null) {
			 throw new RouteException("Route  is Your Finding In Already Present");
		 }
		 
		  if(route!=null) {
			  List<Bus> buses = route.getBusList();
//			  for(int i=0;i<buses.size();i++) {
//				  Bus b = buses.get(i);
//				  b.setRoute(route);
//			  }
             
			route.setBusList(buses);
			Route savedRoute = routeRepo.save(route);
			return savedRoute;
			 
		  }
		  else {
			  throw new RouteException("Please give route details properly");
		  }
		
		
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
		      Optional<Route> existedRoute = routeRepo.findById(route.getRouteId());
		      
		      if(existedRoute.isPresent()) {
		    	  Route presentRoute = existedRoute.get();
		    	  
		    	  List<Bus> busesList = presentRoute.getBusList();
		    	  if(busesList.size()>0) {
		    		  throw new RouteException(" Bus is already scheduled So cannot Update");
		    	  }
		    	  else {
		    		Route savedRoute =   routeRepo.save(route);
		    		return savedRoute;
		    	  }
		    	  
		      }
		      throw new RouteException("Route not exited with this RouteId" + route.getRouteId());
	}

	@Override
	public Route deleteRoute(int routeId) throws RouteException {
		   Optional<Route> route =  routeRepo.findById(routeId);
		   if(route.isPresent()) {
			   Route existingRoute = route.get();
			   routeRepo.delete(existingRoute);
			   return existingRoute;
		   }
		   else {
			   throw new RouteException("No Route found with this routeId"+ routeId);
		   }
	}

	@Override
	public Route viewRoute(int routeId) throws RouteException {
		   Optional<Route> route =  routeRepo.findById(routeId);
		   
		   if(route.isPresent()) {
			   Route fetchedRoute = route.get();
			   return fetchedRoute;
		   }
		   else {
			   throw new RouteException("No Route found with this routeId"+ routeId);
		   }
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		       List<Route> allRoutes =     routeRepo.findAll();
		       if(allRoutes.size()>0) {
		    	   return allRoutes;
		       }
		       else {
		    	   throw new RouteException("No Routes Time");
		       }
	}

}
