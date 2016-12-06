package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.fpl.integration.facade.PlayerDataFacade;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/fantasydraft/admin")
public class AdminRestController {

private PlayerDataFacade playerDataFacade;
	
	@Autowired
	public AdminRestController(final PlayerDataFacade playerDataFacade) {
		
		this.playerDataFacade = playerDataFacade;
	}
	
	@RequestMapping(value = "/players/statistics", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK) 
    public void updateExistingPlayerStatistics() throws FantasyDraftIntegrationException {
    	
    	playerDataFacade.updateExistingPlayerStatistics();
    }
	
	@RequestMapping(value = "/players/price", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK) 
    public void updateExistingPlayerPriceData() throws FantasyDraftIntegrationException {
    	
    	playerDataFacade.updateExistingPlayerPriceData();
    }
}
