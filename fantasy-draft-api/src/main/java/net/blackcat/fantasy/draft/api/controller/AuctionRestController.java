/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.AuctionFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.AuctionBidsDto;
import net.blackcat.fantasy.draft.integration.facade.dto.AuctionPhaseResultsDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TeamAuctionStatusDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for exposing auction related operations.
 * 
 * @author Chris Hern
 * 
 */
@RestController
@RequestMapping("/fantasydraft/auction")
public class AuctionRestController {

	// @formatter:off

	private AuctionFacade auctionFacade;

    @Autowired
    public AuctionRestController(final AuctionFacade auctionFacade) {
        this.auctionFacade = auctionFacade;
    }

    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/{leagueId}")
    @ResponseStatus(value = HttpStatus.OK) 
    public void startAuction(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	auctionFacade.startAuction(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/phase/{leagueId}")
    @ResponseStatus(value = HttpStatus.OK) 
    public void openAuctionPhase(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	auctionFacade.openAuctionPhase(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/phase/{leagueId}/close")
    @ResponseStatus(value = HttpStatus.OK) 
    public void closeAuctionPhase(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	auctionFacade.closeAuctionPhase(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/team/status")
    public @ResponseBody TeamAuctionStatusDto getTeamAuctionStatus() throws FantasyDraftIntegrationException {

        final String loggedInUser = SecurityUtils.getLoggedInUser();

        return auctionFacade.getTeamAuctionStatus(loggedInUser);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void makeBids(@RequestBody final AuctionBidsDto bids) throws FantasyDraftIntegrationException {

        auctionFacade.makeBids(bids);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/results/{leagueId}")
    public @ResponseBody List<AuctionPhaseResultsDto> getLeagueAuctionPhaseResults(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	return auctionFacade.getLeagueAuctionPhaseResults(leagueId);
    }
    
    // @formatter:on
    
}
