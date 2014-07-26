/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller class which provides player related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/players")
public class PlayerController {

	@Autowired
	@Qualifier(value = "playerIntegrationController")
	private  net.blackcat.fantasy.draft.integration.controller.PlayerController playerIntegrationController;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Player> getPlayers() {
		return playerIntegrationController.getPlayers();
	}
}
