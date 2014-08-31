/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import net.blackcat.fantasy.draft.fpl.integration.controller.CalculateGameweekPointsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST controller class which provides team related operations.
 * 
 * @author Chris
 *
 */
@Controller
@RequestMapping("/fantasydraft/gameweek")
public class GameweekScoreController {

	@Autowired
	@Qualifier(value = "calculateGameweekPointsController")
	private CalculateGameweekPointsController gameweekPointsController;
	
	/**
	 * Calculate the scores for a given gameweek.
	 */
	@RequestMapping(value = "/score", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void calculateGameweekScores() {
		gameweekPointsController.calculateGameweekPoints();
	}
}
