/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.fpl.integration.facade.PlayerDataFacade;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for gameweek operations.
 * 
 * @author Chris
 *
 */
@RestController
@RequestMapping("/fantasydraft/gameweek")
public class GameweekRestController {

	// @formatter:off
	
	private PlayerDataFacade playerDataFacade;
	
	@Autowired
	public GameweekRestController(final PlayerDataFacade playerDataFacade) {
		
		this.playerDataFacade = playerDataFacade;
	}
	
	@RequestMapping(value = "/scores", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK) 
    public void calculateCurrentGameweekScores() throws FantasyDraftIntegrationException {
    	
    	playerDataFacade.calculateCurrentGameweekScores();
    }
	
	@RequestMapping(value = "/scores/{gameweek}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK) 
    public void calculateGameweekScores(@PathVariable final int gameweek) throws FantasyDraftIntegrationException {
    	
    	playerDataFacade.calculateGameweekScores(gameweek);
    }
	
	// @formatter:on
	
}
