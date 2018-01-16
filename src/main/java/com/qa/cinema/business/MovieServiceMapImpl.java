package com.qa.cinema.business;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.cinema.persistence.Movie;
import com.qa.cinema.util.JSONUtil;

@ApplicationScoped
@Transactional(SUPPORTS)
@Alternative
public class MovieServiceMapImpl implements MovieService {
	
	private Map<Long, Movie> movieMap;
	
	@Inject
	private JSONUtil json;

	public MovieServiceMapImpl() {
		movieMap = new HashMap<Long, Movie>();
	}

	@Override
	public String getAllMovies() {
		return json.getJSONForObject(movieMap);
	}

	@Override
	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		Movie aMovie = json.getObjectForJSON(movie, Movie.class);				
		movieMap.put(aMovie.getId(), aMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateMovie(Long id, String movie) {
		Movie updatedMovie = json.getObjectForJSON(movie, Movie.class);
		Movie movieIn = movieMap.get(id);
		if (movieIn != null) {
			movieMap.put(id, updatedMovie);
			}
		return "{\"message\": \"movie sucessfully updated\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movie movieIn = movieMap.get(id);
		if (movieIn != null) {
			movieMap.remove(id);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

}
