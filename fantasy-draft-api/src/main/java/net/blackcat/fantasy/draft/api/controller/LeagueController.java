/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.league.League;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller class which provides league related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/leagues")
public class LeagueController {

	@Autowired
	@Qualifier(value = "leagueIntegrationController")
	private  net.blackcat.fantasy.draft.integration.controller.LeagueController leagueIntegrationController;
	
	/**
	 * Get the table for a given league
	 * 
	 * @param leagueId The ID of the league to get the table for.
	 * 
	 * @return {@link League} object containing the table for the required league.
	 * @throws FantasyDraftIntegrationException If the given league can't be found.
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/table/{leagueId}", method = RequestMethod.GET)
	public @ResponseBody League getPlayers(@PathVariable int leagueId) throws FantasyDraftIntegrationException {
		return leagueIntegrationController.getLeagueTable(leagueId);
	}
}
