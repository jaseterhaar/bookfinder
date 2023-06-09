package nl.bookfinder.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import nl.bookfinder.controller.BookController;

@ControllerAdvice
public class CustomExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@ExceptionHandler(ResponseStatusException.class)
	public ModelAndView handleResponseStatusException(ResponseStatusException ex) {
		logger.error("Er is een fout opgetreden met status: {}", ex.getStatusCode(), ex);
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", "Er is een fout opgetreden met status: " + ex.getStatusCode());
		return modelAndView;
	}
}
