/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;


import BeansBdd.Ecf;
import BeansBdd.Formation;
import ImiesException.AddException;
import Model.DaoHibernateImiesProject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
=======
import BeansBdd.Formation;
import Model.DaoHibernateImiesProject;
import java.util.List;
import javax.ws.rs.GET;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author demontvalon.cdi02
>>>>>>> 20a1555a89fccb937246360727de24e77f53e95a
 */
@Stateless
@Path("/formation")
public class RestFormation {


    @Context
    private UriInfo context;


    /**
     * Creates a new instance of RestStudents
     */
    public RestFormation() {
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
        Formation formation = new Formation();
        formation.setFormationName(name);
        DaoHibernateImiesProject dao = new DaoHibernateImiesProject(); 
        System.out.println(formation);
        dao.addBdd(formation);
        } catch (AddException ex) {
            Logger.getLogger(RestFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Produces("application/json")
    @Path("/dataFormation")
    public List<Formation> getDataPromtion() {
        System.out.println(DaoHibernateImiesProject.getInstance().selectAllFormation());
        return DaoHibernateImiesProject.getInstance().selectAllFormation();
    }
}

   

