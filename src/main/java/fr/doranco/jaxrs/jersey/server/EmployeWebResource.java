/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.jersey.server;


import fr.doranco.jaxrs.dao.EmployeDAO;
import fr.doranco.jaxrs.jersey.entity.Employe;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Boule
 */
@Path("employes")
@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
public class EmployeWebResource implements IEmployeWebResource {

    private final static String CHARSET = ";charset=UTF-8";

    public EmployeWebResource() {
    }

    private final String employeFromXmlToString(Employe employe) {
        String employeXML = null;
        try {
            //Créer un contexte JaxB
            JAXBContext jaxbContext = JAXBContext.newInstance(Employe.class);
            //Créer un marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Formatage
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //Imprimer le string dans la console
            StringWriter sw = new StringWriter();
            //Ecrire le xml dans le string writer
            jaxbMarshaller.marshal(employe, sw);
            //Retourner la string
            employeXML = sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employeXML;
    }

    private final String employeFromJsonToString(Employe employe) {
        String employeJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            employeJson = mapper.writeValueAsString(employe);
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            mapper.setDateFormat(date);
            //System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeJson;
    }

    @Override
    @GET
    public String getInfo() {
        return "Vous venez de récupérer les informations de l'employé via le service internet REST."; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @GET
    @Path("employe-{id}-XML")
    @Produces(MediaType.APPLICATION_XML + CHARSET)
    public String getEmployeAuFormatXmlString(@PathParam("id") @DefaultValue("1") Integer id) {
        EmployeDAO employeDao = new EmployeDAO();
        Employe employe = null;
        try {
            employe = employeDao.getEmployeById(id);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employeFromXmlToString(employe);
    }

    @Override
    @GET
    @Path("employe-{id}-JSON")
    @Produces(MediaType.APPLICATION_JSON + CHARSET)
    public String getEmployeAuFormatJsonString(@PathParam("id") Integer id) {
//        Employe employe = new Employe(id, "BADAD", "Chérif", "Gros con");

        EmployeDAO employeDao = new EmployeDAO();
        Employe employe = null;
        try {
            employe = employeDao.getEmployeById(id);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employeFromJsonToString(employe);
    }

    @Override
    @GET
    @Path("employe-{id}")
    @Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
    public Response getEmployeJSONtoResponse(@PathParam("id") Integer id) {
        EmployeDAO employeDao = new EmployeDAO();
        Employe employe = null;
        try {
            employe = employeDao.getEmployeById(id);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok().entity(employe).build();
    }

    @Override
    @GET
    @Path("liste")
    @Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
    public List<Employe> getEmployesByList() {
        EmployeDAO employeDao = new EmployeDAO();
        List<Employe> listeEmployes = null;
        try {
            listeEmployes = employeDao.getListeEmploye();
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeEmployes;
    }

    @Override
    @POST
    @Path("ajouter")
    @Produces({MediaType.APPLICATION_XML + CHARSET,MediaType.APPLICATION_JSON + CHARSET})
    public Response addEmploye(Employe employe) throws URISyntaxException {
        EmployeDAO employeDAO = new EmployeDAO();
        try {
            employe = employeDAO.addEmploye(employe);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return Response.status(Status.OK).entity(employe).build();
    }

    @Override
    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_XML + CHARSET,MediaType.APPLICATION_JSON + CHARSET})
    public Response updateEmploye(Employe employe) throws URISyntaxException {
        EmployeDAO employeDAO = new EmployeDAO();
        try {
            employeDAO.updateEmploye(employe);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return Response.status(Status.OK).entity(employe).build();
    }
    
    @Override
    @DELETE
    @Path("effacer-{id}")
    @Produces({MediaType.APPLICATION_XML + CHARSET,MediaType.APPLICATION_JSON + CHARSET})
    public Response removeEmploye(@PathParam("id")Integer id) throws URISyntaxException {
        EmployeDAO employeDAO = new EmployeDAO();
        Employe employe = null;
        try {
//            employe.setId(id);
            employeDAO.removeEmploye(id);
        } catch (Exception ex) {
            Logger.getLogger(EmployeWebResource.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return Response.status(Status.OK).entity(employe).build();
    }

}
