/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.jersey.main;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;





/**
 *
 * @author Boule
 */
public class JerseyResourceLauncher {
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/jersey/").port(9991).build();
    public static void main(String[] args) {
        
        try{
            ResourceConfig config = new PackagesResourceConfig("fr.doranco.jaxrs.jersey.server");
            System.err.println("Démarrage du serveur : "+BASE_URI);
            HttpServer server = HttpServerFactory.create(BASE_URI, config);
            server.start();
            Thread.sleep(2000);
            
            System.err.println("Serveur Jersey démarré avec succès. Bravo. Vous pouvez choper le WADL ici : "+BASE_URI+"\n");
            System.err.println("Veuillez taper : "+BASE_URI+"application.wadl --afin d'acc&der au service web.");
            
            System.in.read();
            
            System.err.println("Arrêt du serveur démarré.");
            server.stop(0);
            Thread.sleep(2000);
            System.err.println("Serveur arrêté avec ZzZZZZ.....");
        }catch(Exception e){
            System.err.println("Démerdez-vous avec cette erreur : "+e);
        }
    }
}
