/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.TransferWindowFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.AuctionBidsDto;
import net.blackcat.fantasy.draft.integration.facade.dto.OpenTransferWindowTeamTransfersDto;
import net.blackcat.fantasy.draft.integration.facade.dto.SellPlayerToPotDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TeamAuctionStatusDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TransferBidDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TransferWindowSummaryDto;

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
 * REST controller for exposing players operations.
 * 
 * @author Chris Hern
 * 
 */
@RestController
@RequestMapping("/fantasydraft/transferWindow")
public class TransferWindowRestController {

    private TransferWindowFacade transferWindowFacade;

    @Autowired
    public TransferWindowRestController(final TransferWindowFacade transferWindowFacade) {
        this.transferWindowFacade = transferWindowFacade;
    }

    // @formatter:off

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/sellToPot", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void sellPlayerToPot(@RequestBody final SellPlayerToPotDto sellPlayerToPotDto) throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();
    	
    	sellPlayerToPotDto.setManagerEmailAddress(loggedInUser);

    	transferWindowFacade.sellPlayerToPot(sellPlayerToPotDto);

    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bid", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void makeTransferBid(@RequestBody final TransferBidDto transferBidDto) throws FantasyDraftIntegrationException {

    	transferWindowFacade.makeTransferBid(transferBidDto);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bid/{transferBidId}/accept", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void acceptTransferBid(@PathVariable final int transferBidId) throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();
    	
    	transferWindowFacade.acceptTransferBid(loggedInUser, transferBidId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/bid/{transferBidId}/reject", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void rejectTransferBid(@PathVariable final int transferBidId) throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();
    	
    	transferWindowFacade.rejectTransferBid(loggedInUser, transferBidId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/transferDetail")
    public @ResponseBody OpenTransferWindowTeamTransfersDto getTransferWindowTeamTransferDetail() throws FantasyDraftIntegrationException {
    	
    	final String loggedInUser = SecurityUtils.getLoggedInUser();
    	
    	return transferWindowFacade.getTeamTransferWindowTransferDetail(loggedInUser);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/auctionPhase/{leagueId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK) 
    public void moveOntoAuctionPhase(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	transferWindowFacade.moveTransferWindowOntoAuction(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/auctionPhase/{leagueId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK) 
    public void openAuctionPhase(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	transferWindowFacade.startAuctionPhase(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/auctionPhase/{leagueId}/close", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK) 
    public void closeAuctionPhase(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	transferWindowFacade.closeAuctionPhase(leagueId);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/auctionPhase/team/status")
    public @ResponseBody TeamAuctionStatusDto getTeamAuctionStatus() throws FantasyDraftIntegrationException {

        final String loggedInUser = SecurityUtils.getLoggedInUser();

        return transferWindowFacade.getTeamAuctionStatus(loggedInUser);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/auctionPhase/bid", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void makeBids(@RequestBody final AuctionBidsDto bids) throws FantasyDraftIntegrationException {

    	transferWindowFacade.makeBids(bids);
    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/summary/{leagueId}")
    public @ResponseBody TransferWindowSummaryDto getTransferWindowSummary(@PathVariable final int leagueId) throws FantasyDraftIntegrationException {
    	
    	return transferWindowFacade.getTransferWindowSummary(leagueId);
    }
    
    // @formatter:on
}
