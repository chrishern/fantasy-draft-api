/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.integration.controller.ManagerController;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.manager.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	@Qualifier(value = "managerIntegrationController")
	private  ManagerController managerIntegrationController;
	
	@RequestMapping(value = "/authenticated/retrieve/", method = RequestMethod.GET)
	public @ResponseBody Manager authenticatedUser() throws FantasyDraftIntegrationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		final String emailAddress = ((UserDetails)authentication.getPrincipal()).getUsername();
		System.out.println("USER: " + emailAddress);
		return managerIntegrationController.getManager(emailAddress);
	}
	
	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	public void authenticate() {
		// endpoint for the basic authentication request to pass
	}
}
