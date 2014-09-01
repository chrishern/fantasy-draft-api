/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.LoggedInUser;
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
	
	/**
	 * Retrieve authenticated user details.
	 * 
	 * @return Username of the logged in user.
	 */
	@RequestMapping(value = "/authenticated/retrieve/", method = RequestMethod.GET)
	public @ResponseBody String authenticatedUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		final String emailAddress = ((UserDetails)authentication.getPrincipal()).getUsername();
		System.out.println("USER: " + emailAddress);
		return emailAddress;
	}
	
	/**
	 * Endpoint used for BA authentication.
	 * @throws FantasyDraftIntegrationException 
	 */
	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	public @ResponseBody LoggedInUser authenticate() throws FantasyDraftIntegrationException {
		// endpoint for the basic authentication request to pass
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}
		
		final String emailAddress = ((UserDetails)authentication.getPrincipal()).getUsername();
		
		return managerIntegrationController.getLoggedInUser(emailAddress);
	}
	
	/**
	 * Get basic user logged in details.
	 * 
	 * @return JSON serialisable object containing logged in details.
	 * @throws FantasyDraftIntegrationException If any error occurs when obtaining the logged in user details.
	 */
	@RequestMapping(value = "/authenticate/retrieve/userdetails", method = RequestMethod.GET)
	public @ResponseBody LoggedInUser getLoggedInUserDetails() throws FantasyDraftIntegrationException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String emailAddress = ((UserDetails)authentication.getPrincipal()).getUsername();
		
		return managerIntegrationController.getLoggedInUser(emailAddress);
	}
	
	/**
	 * Get full manager details.
	 * 
	 * @return JSON serialisable object containing manager details.
	 * @throws FantasyDraftIntegrationException If any error occurs when obtaining the logged in user details.
	 */
	@RequestMapping(value = "/authenticate/retrieve/managerdetails", method = RequestMethod.GET)
	public @ResponseBody Manager getFullManagerDetails() throws FantasyDraftIntegrationException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String emailAddress = ((UserDetails)authentication.getPrincipal()).getUsername();
		
		return managerIntegrationController.getManager(emailAddress);
	}
}
