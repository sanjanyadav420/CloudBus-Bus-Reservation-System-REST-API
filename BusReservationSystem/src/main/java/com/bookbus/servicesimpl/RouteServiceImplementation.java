package com.bookbus.servicesimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookbus.exceptions.LogException;
import com.bookbus.exceptions.RouteException;
import com.bookbus.models.Bus;
import com.bookbus.models.CurrentAdminSession;
import com.bookbus.models.Route;
import com.bookbus.repositories.AdminLogRepo;
import com.bookbus.repositories.RouteRepo;
import com.bookbus.services.RouteService;

@Service
public class RouteServiceImplementation implements RouteService {

	@Autowired
	private RouteRepo routeRepo;

	@Autowired
	private AdminLogRepo alRepo;

	@Override
	public Route addRoute(Route route, Integer id) throws RouteException, LogException {

		Optional<CurrentAdminSession> cas = alRepo.findById(id);

		if (cas.isPresent()) {
			List<Bus> buses = routeRepo.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());

			route.setBusList(buses);

			if (buses != null)
				return routeRepo.save(route);
			else
				throw new RouteException("No buses is present for this route.");

		} else
			throw new LogException("Admins can only add routes if you please logIn or check AdminId.");

	}

	@Override
	public Route updateRoute(Route route, Integer id) throws RouteException, LogException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(id);

		if (cas.isPresent())
		{
			Optional<Route> existedRoute = routeRepo.findById(route.getRouteId());

			if (existedRoute.isPresent()) {		
				Route savedRoute = routeRepo.save(route);
				return savedRoute;

			}
			throw new RouteException("Route not exited with this RouteId" + route.getRouteId());
		}
		else
			throw new LogException("Admins can only update routes if you are admin please logIn or check AdminId.");

	}

	@Override
	public Route deleteRoute(Integer routeId, Integer adminId) throws RouteException, LogException {
		
		Optional<CurrentAdminSession> cas = alRepo.findById(adminId);

		if (cas.isPresent())
		{
			Optional<Route> route = routeRepo.findById(routeId);
			if (route.isPresent()) {
				Route existingRoute = route.get();
				routeRepo.delete(existingRoute);
				return existingRoute;
			} else {
				throw new RouteException("No Route found with this routeId" + routeId);
			}
		}
		else
			throw new LogException("Admins can only update routes if you are admin please logIn or check AdminId.");
	}

	@Override
	public Route viewRoute(int routeId) throws RouteException {
		Optional<Route> route = routeRepo.findById(routeId);

		if (route.isPresent()) {
			Route fetchedRoute = route.get();
			return fetchedRoute;
		} else {
			throw new RouteException("No Route found with this routeId" + routeId);
		}
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();
		if (allRoutes.size() > 0) {
			return allRoutes;
		} else {
			throw new RouteException("No Routes Time");
		}
	}

}
