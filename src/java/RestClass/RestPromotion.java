/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;

import BeansBdd.Promotion;
import ImiesException.SelectStudentException;
import Model.DaoHibernateImiesProject;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Stateless
@Path("/promotion")
public class RestPromotion {
    
    @GET
    @Produces("application/json")
    @Path("/dataPromotion")
    public List<Promotion> getDataPromtion() {
        System.out.println(DaoHibernateImiesProject.getInstance().selectAllPromotion());
        return DaoHibernateImiesProject.getInstance().selectAllPromotion();
    }
    

}
