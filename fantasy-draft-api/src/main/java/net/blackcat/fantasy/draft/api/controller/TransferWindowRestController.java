/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.TransferWindowFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.SellPlayerToPotDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    // @formatter:on
}
