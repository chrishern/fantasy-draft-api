/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.round.TeamBids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * REST controller class which provides draft round related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/draft")
public class DraftRoundController {

	@Autowired
	@Qualifier(value = "draftRoundIntegrationController")
	private net.blackcat.fantasy.draft.integration.controller.DraftRoundController draftRoundIntegrationController;
	
	/**
	 * Submit a selection of bids for a team in an auction.
	 * 
	 * @param teamBids Bids to place. 
	 * @throws FantasyDraftIntegrationException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/bids", method = RequestMethod.POST)
	public void makeBids(@RequestBody final TeamBids teamBids) throws FantasyDraftIntegrationException {
		draftRoundIntegrationController.makeBids(teamBids);
	}

	/**
	 * Start a draft round for a given team.
	 * 
	 * @param leagueId ID of the league to start the draft round for.
	 * @param phaseNumber The phase of the round to start.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/round/start", method = RequestMethod.PUT)
	public void startDraftRound(@RequestParam(value = "leagueId", required = true) final int leagueId, 
			@RequestParam(value = "phaseNumber", required = true) final int phaseNumber) throws FantasyDraftIntegrationException {
		draftRoundIntegrationController.startAuctionPhase(leagueId, phaseNumber);
	}
}
