/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.player.Player;
import net.blackcat.fantasy.draft.team.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller class which provides team related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/teams")
public class TeamController {

	@Autowired
	@Qualifier(value = "teamIntegrationController")
	private  net.blackcat.fantasy.draft.integration.controller.TeamController teamIntegrationController;
	
	/**
	 * Get all {@link Player} objects within the game of a given position.
	 * 
	 * @param position The position of the players wanted.
	 * 
	 * @return JSON containing all players of the desired position.
	 * @throws FantasyDraftIntegrationException 
	 */
	@RequestMapping(value = "/{leagueId}", method = RequestMethod.GET)
	public @ResponseBody List<Team> getPlayers(@PathVariable int leagueId) throws FantasyDraftIntegrationException {
		return teamIntegrationController.getCompleteTeams(leagueId);
	}
}
