package controllers;

import models.Location;
import play.libs.Json;
import play.mvc.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public Result index() {
        final String ip = request().remoteAddress();
        return ok(Json.toJson(new Location(ip, getHostname(ip))));
    }

    /**
     * map ip address to hostname
     *
     * @param ip  ip address
     *
     * @return String  hostname
     */
    private String getHostname(String ip) {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(ip);
            return addr.getHostName();
        } catch (UnknownHostException e) {
            // no such hostname
            return ip;
        }
    }

}
