/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.integration.exception.FantasyDraftIntegrationException;
import net.blackcat.fantasy.draft.transfer.LeagueTransferWindowSummary;
import net.blackcat.fantasy.draft.transfer.Transfer;
import net.blackcat.fantasy.draft.transfer.TransferSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/**
	 * Add a given transfer to a window.
	 * 
	 * @param transfer Transfer to add.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addTransfer(@RequestBody final Transfer transfer) throws FantasyDraftIntegrationException {
		transferWindowIntegrationController.addTransfer(transfer);
	}
	
	/**
	 * Sell a given player from a given team to the pot.
	 * 
	 * @param teamId The ID of the team selling the player to the pot.
	 * @param playerId The ID of the player being sold to the pot.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/potSale", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK) 
	public void sellPlayerToPot(@RequestParam(value = "teamId", required = true) final int teamId, 
			@RequestParam(value = "playerId", required = true) final int playerId) throws FantasyDraftIntegrationException {
		transferWindowIntegrationController.sellPlayerToPot(teamId, playerId);
	}
	
	/**
	 * Get the transfers for a given team in the open transfer window.
	 * 
	 * @param teamId ID of the team we want the transfers for.
	 * @return Transfers for the given team.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/transfers", method = RequestMethod.GET)
	public @ResponseBody List<TransferSummary> getTransfersForTeamInOpenWindow(@RequestParam(value = "teamId", required = true) final int teamId) throws FantasyDraftIntegrationException {
		return transferWindowIntegrationController.getTransfersForTeamInOpenWindow(teamId);
	}
	
	/**
	 * Move the transfer window status onto auction phase.  This involves moving all players in and out of the
	 * respective teams based on all transfers in the window.
	 * 
	 * @param leagueId ID of the league to move the transfer window phase on for.
	 * @throws FantasyDraftIntegrationException
	 */
	@RequestMapping(value = "/startAuction", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK) 
	public void moveTransferWindowOntoAuction(@RequestParam(value = "leagueId", required = true) final int leagueId) throws FantasyDraftIntegrationException {
		transferWindowIntegrationController.moveTransferWindowOntoAuction(leagueId);
	}
	
	/**
	 * Get the transfer window summary for a given league.
	 * 
	 * @param leagueId ID of the league to get the transfer window summary for.
	 * @return Transfer window summary for the league,
	 */
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public @ResponseBody LeagueTransferWindowSummary getLeagueTransferWindowSummary(@RequestParam(value = "leagueId", required = true) int leagueId) throws FantasyDraftIntegrationException {
		return transferWindowIntegrationController.getLeagueTransferWindowSummary(leagueId);
	}
}
