package com.qa.cinema.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.cinema.intergration.MovieEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class MovieEndPointTest {

	@InjectMocks
	private MovieEndpoint endPoint;

	@Mock
	private MovieService movieService;

	@Test
	public void testCreateMovie() {
		endPoint.setService(movieService);
		Mockito.when(movieService.createMovie("test")).thenReturn("test output");
		Assert.assertEquals("test output", endPoint.addMovie("test"));
		Mockito.verify(movieService).createMovie("test");
		Mockito.verify(movieService, Mockito.never()).deleteMovie(1L);
	}

}
