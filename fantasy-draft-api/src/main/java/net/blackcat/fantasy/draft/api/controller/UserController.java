/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller class which provides user related operations.
 * 
 * @author Chris
 *
 */
@RestController
@RequestMapping("/fantasydraft/users")
public class UserController {

	@RequestMapping(value = "/authenticated/retrieve/", method = RequestMethod.GET)
	public @ResponseBody UserDetails authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		System.out.println("USER: " + ((UserDetails)authentication.getPrincipal()).getUsername());
		return (UserDetails)authentication.getPrincipal();
	}
	
	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	public void authenticate() {
		// endpoint for the basic authentication request to pass
	}
}
