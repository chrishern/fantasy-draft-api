/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.api.security.SecurityUtils;
import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.integration.facade.LeagueFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.LeagueTableDto;
import net.blackcat.fantasy.draft.integration.facade.dto.TeamSummaryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for exposing league operations.
 * 
 * @author Chris
 *
 */
@RestController
@RequestMapping("/fantasydraft/league")
public class LeagueRestController {

	private LeagueFacade leagueFacade;

    @Autowired
    public LeagueRestController(final LeagueFacade leagueFacade) {
        this.leagueFacade = leagueFacade;
    }

    // @formatter:off

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/table")
    public @ResponseBody LeagueTableDto getLeagueTable() throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();

    	return leagueFacade.getLeagueTable(loggedInUser);

    }
    
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/team/summaries")
    public @ResponseBody List<TeamSummaryDto> getTeamSummaries() throws FantasyDraftIntegrationException {

    	final String loggedInUser = SecurityUtils.getLoggedInUser();

    	return leagueFacade.getTeamSummaries(loggedInUser);

    }
    
    // @formatter:on
}
