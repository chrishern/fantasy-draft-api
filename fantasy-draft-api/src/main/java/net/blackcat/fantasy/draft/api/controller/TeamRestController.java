/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.TeamFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.SquadDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for exposing players operations.
 * 
 * @author Chris Hern
 * 
 */
@RestController
@RequestMapping("/fantasydraft/team")
public class TeamRestController {

    private TeamFacade teamFacade;

    @Autowired
    public TeamRestController(final TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }

    // @formatter:off

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("")
    public @ResponseBody SquadDto getSquadDetails() throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();

    	return teamFacade.getSquadDetails(loggedInUser);

    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("league")
    public @ResponseBody List<SquadDto> getSquadDetailsForLeague() throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();

    	return teamFacade.getSquadDetailsForLeague(loggedInUser);

    }

    // @formatter:on
}
