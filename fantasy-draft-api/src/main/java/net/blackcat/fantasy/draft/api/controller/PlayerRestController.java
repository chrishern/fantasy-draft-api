/**
 * 
 */
package net.blackcat.fantasy.draft.api.controller;

import java.util.List;

import net.blackcat.fantasy.draft.integration.facade.PlayerFacade;
import net.blackcat.fantasy.draft.integration.facade.dto.PlayerDto;
import net.blackcat.fantasy.draft.integration.model.types.player.PlayerSelectionStatus;
import net.blackcat.fantasy.draft.integration.model.types.player.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for exposing players operations.
 * 
 * @author Chris Hern
 * 
 */
@RestController
@RequestMapping("/fantasydraft/players")
public class PlayerRestController {

    private PlayerFacade playerFacade;

    @Autowired
    public PlayerRestController(final PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    // @formatter:off

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("")
    public @ResponseBody List<PlayerDto> getPlayers(@RequestParam(value="position") final Position position, 
            @RequestParam(value="selectionStatus") final PlayerSelectionStatus selectionStatus) {

        // @formatter:on

        return playerFacade.getPlayers(position, selectionStatus);

    }
}
