package org.jboss.aerogear.controller.demo.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;

import javax.servlet.ServletException;

//TODO to be moved to aerogear security
public class AeroGearSecurityProvider implements SecurityProvider {

    @Override
    public boolean isRouteAllowed(Route route) throws ServletException {
        String[] roles = route.getRoles();
        for (String role : roles) {
            System.out.println(role);
        }
        return false;
    }
}
