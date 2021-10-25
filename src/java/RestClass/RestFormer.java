/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;

import BeansBdd.Formation;
import BeansBdd.Former;
import ImiesException.AddException;
import Model.DaoHibernateImiesProject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;

/**
 *
 * @author bouessay.cdi02
 */
@Stateless
@Path("/former")
public class RestFormer {
    


    @Context
    private UriInfo context;


    /**
     * Creates a new instance of RestStudents
     */
    public RestFormer() {
    }

    /**
     * Retrieves representation of an instance of RestClass.RestStudents
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RestStudents
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
        
    }
    
    @POST
    @Produces("application/json")
    public void postAddAction(String content) {
        System.out.println(content);
        try {
        JSONObject jsonObject = new JSONObject(content);
        String lastname = jsonObject.getString("lastname");
        String firstname = jsonObject.getString("firstname");
        String lesson = jsonObject.getString("lesson");
        String login = jsonObject.getString("login");
        String password = jsonObject.getString("password");
        Former f = new Former();
        f.setFormerLastName(lastname);
        f.setFormerFirstName(firstname);
        f.setFormerLesson(lesson);
        f.setFormerLogin(login);
        f.setFormerPassword(password);
        DaoHibernateImiesProject dao = new DaoHibernateImiesProject(); 
        System.out.println(f);
        dao.addBdd(f);
        } catch (AddException ex) {
            Logger.getLogger(RestFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Produces("application/json")
    @Path("/dataFormer")
    public List<Former> getDataFormer() {
        System.out.println(DaoHibernateImiesProject.getInstance().selectAllFormer());
        return DaoHibernateImiesProject.getInstance().selectAllFormer();
    }
}