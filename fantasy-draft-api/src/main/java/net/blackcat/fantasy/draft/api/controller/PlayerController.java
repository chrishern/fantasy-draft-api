/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.player.Player;
import net.blackcat.fantasy.draft.player.types.PlayerSelectionStatus;
import net.blackcat.fantasy.draft.player.types.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller class which provides player related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/players")
public class PlayerController {

	@Autowired
	@Qualifier(value = "playerIntegrationController")
	private  net.blackcat.fantasy.draft.integration.controller.PlayerController playerIntegrationController;
	
	/**
	 * Get all {@link Player} objects within the game.
	 * 
	 * @return JSON containing all players.
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Player> getPlayers() {
		return playerIntegrationController.getPlayers();
	}
	
	/**
	 * Get all {@link Player} objects within the game of a given position.
	 * 
	 * @param position The position of the players wanted.
	 * 
	 * @return JSON containing all players of the desired position.
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{position}", method = RequestMethod.GET)
	public @ResponseBody List<Player> getPlayers(@PathVariable String position) {
		return playerIntegrationController.getPlayers(Position.fromRestApiValue(position));
	}
	
	/**
	 * Get all available {@link Player} objects within the game of a given position.
	 * 
	 * @param position The position of the players wanted.
	 * 
	 * @return JSON containing all available players of the desired position.
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/available/{position}", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAvailablePlayers(@PathVariable String position) {
		return playerIntegrationController.getPlayers(Position.fromRestApiValue(position), PlayerSelectionStatus.NOT_SELECTED);
	}
	
	/**
	 * Get all available {@link Player} objects within the game of a given position.
	 * 
	 * @param position The position of the players wanted.
	 * 
	 * @return JSON containing all available players of the desired position.
	 * @throws FantasyDraftIntegrationException 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/available/{position}/{teamId}", method = RequestMethod.GET)
	public @ResponseBody List<Player> getAvailablePlayers(@PathVariable String position, @PathVariable int teamId) throws FantasyDraftIntegrationException {
		return playerIntegrationController.getPlayers(Position.fromRestApiValue(position), PlayerSelectionStatus.NOT_SELECTED, teamId);
	}
}
