package net.blackcat.fantasy.draft.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Return 401-unauthorized for every unauthorized request. The client side
 * catches this and handles login itself.
 */
@Component(value = "unauthorisedEntryPoint")
public class UnauthorisedEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}

}
