/**
 * 
 */
package net.blackcat.fantasy.draft.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Security utilities.
 * 
 * @author Chris Hern
 * 
 */
public final class SecurityUtils {

    /**
     * Get the email address of the currently logged in user.
     * 
     * @return The email address of the currently logged in user.
     */
    public static String getLoggedInUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        final String emailAddress = ((UserDetails) authentication.getPrincipal()).getUsername();
        System.out.println("USER: " + emailAddress);
        return emailAddress;
    }
}
