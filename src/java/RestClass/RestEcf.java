/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;

import BeansBdd.Ecf;
import ImiesException.AddException;
import Model.DaoHibernateImiesProject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author lor.cdi02
 */
@Stateless
@Path("/ecf")
public class RestEcf {

    @Context
    private UriInfo context;


    /**
     * Creates a new instance of RestStudents
     */
    public RestEcf() {
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
            String name = jsonObject.getString("name");
            String dateStr = jsonObject.getString("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            Ecf ecf = new Ecf(name, date);
            DaoHibernateImiesProject dao = new DaoHibernateImiesProject();
            dao.addBdd(ecf);
            System.out.println(ecf);
        } catch (AddException ex) {
            Logger.getLogger(RestEcf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RestEcf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
