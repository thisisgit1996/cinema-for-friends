package com.qa.cinema.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.cinema.business.MovieService;

@Path("/cinema")
public class MovieEndpoint {
	
	private static Logger logger = Logger.getLogger("leoLog");

	@Inject
	private MovieService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		logger.info("Returning all movies. Get request");
		return service.getAllMovies();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String movie) {
		logger.info("Adding movie. Post request.");
		return service.createMovie(movie);
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateMovie(@PathParam("id") Long id, String movie) {
		logger.info("Updating movie. Put request.");
		return service.updateMovie(id, movie);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteMovie(@PathParam("id") Long id) {
		logger.info("Deleting movie. Delete request");
		return service.deleteMovie(id);

	}

	public MovieService getService() {
		return service;
	}

	public void setService(MovieService service) {
		this.service = service;
	}

}
