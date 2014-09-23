/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST controller class which provides transfer round related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/transferwindow")
public class TransferWindowController {

	@Autowired
	@Qualifier(value = "transferWindowIntegrationController")
	private net.blackcat.fantasy.draft.integration.controller.TransferWindowController transferWindowIntegrationController;
	
	/**
	 * Start a transfer window for a given league.
	 * 
	 * @param leagueId ID of the league to start the transfer window for.
	 * @param windowNumber The number of the window to start.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/start", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK) 
	public void startDraftRound(@RequestParam(value = "leagueId", required = true) final int leagueId, 
			@RequestParam(value = "windowNumber", required = true) final int windowNumber) throws FantasyDraftIntegrationException {
		transferWindowIntegrationController.startTransferWindow(leagueId, windowNumber);
	}
}
