/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to expose 
 * 
 * @author Chris
 *
 */
@RestController
@RequestMapping(value = "fantasydraft/user")
public class UserRestController {

	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK) 
     public void authenticate() throws FantasyDraftIntegrationException {
     // endpoint for the basic authentication request to pass
    
     }
	
	@RequestMapping(value = "/authenticated/retrieve", method = RequestMethod.GET, produces = "application/json")
	public UserDetails authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		return (UserDetails)authentication.getPrincipal();
	}
}
