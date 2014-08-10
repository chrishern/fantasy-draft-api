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
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void makeBids(@RequestBody final TeamBids teamBids) throws FantasyDraftIntegrationException {
		draftRoundIntegrationController.makeBids(teamBids);
	}
}
