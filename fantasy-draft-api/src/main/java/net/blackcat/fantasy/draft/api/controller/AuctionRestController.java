/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.AuctionFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.TeamAuctionStatusDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private AuctionFacade auctionFacade;

    @Autowired
    public AuctionRestController(final AuctionFacade auctionFacade) {
        this.auctionFacade = auctionFacade;
    }

    // @formatter:off

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/team/status")
    public @ResponseBody TeamAuctionStatusDto getTeamAuctionStatus() throws FantasyDraftIntegrationException {

        // @formatter:on

        final String loggedInUser = SecurityUtils.getLoggedInUser();

        return auctionFacade.getTeamAuctionStatus(loggedInUser);

    }
}
