/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.TransferWindowFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.OpenTransferWindowTeamTransfersDto;
import net.blackcat.fantasy.draft.integration.facade.dto.SellPlayerToPotDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TransferBidDto;

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
    
    // @formatter:on
}
