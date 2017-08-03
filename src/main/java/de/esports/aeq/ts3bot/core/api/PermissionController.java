package de.esports.aeq.ts3bot.core.api;

import de.esports.aeq.ts3bot.command.permission.CommandPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Lukas on 27.07.2017.
 */
@RestController()
@RequestMapping("/commands")
public class PermissionController {

    @RequestMapping(method = RequestMethod.GET)
    public Collection<String> getCommands() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, name = "/{prefix}")
    public CommandPermissions getPermissionsForCommand(@PathVariable String prefix) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, name = "/{prefix}")
    public void setPermissionsForCommand(@PathVariable String prefix, @RequestBody CommandPermissions permissions) {

    }
}
