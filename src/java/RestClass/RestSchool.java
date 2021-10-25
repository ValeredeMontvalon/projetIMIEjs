/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;

import BeansBdd.Formation;
import BeansBdd.School;
import ClassCustom.ListCities;
import ImiesException.SelectCityNameException;
import Model.DaoHibernateImiesProject;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author lor.cdi02
 */
@Stateless
@Path("school")
public class RestSchool {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestStudents
     */
    public RestSchool() {
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
    
    @GET
    @Produces("application/json")
    @Path("/dataSchool")
    public ListCities getDataSchool() throws SelectCityNameException {
        ListCities listCities = new ListCities();
        System.out.println(DaoHibernateImiesProject.getInstance().selectCity());
        listCities.setList(DaoHibernateImiesProject.getInstance().selectCity()); 
        return listCities;
    }
}
