/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClass;

import BeansBdd.Promotion;
import BeansBdd.Student;
import ImiesException.AddException;
import ImiesException.SelectStudentException;
import Model.DaoHibernateImiesProject;
import com.google.gson.Gson;
import java.text.ParseException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author lor.cdi02
 */
@Path("students")
public class RestStudents {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestStudents
     */
    public RestStudents() {
    }

    /**
     * Retrieves representation of an instance of RestClass.RestStudents
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Student> getAllStudent() throws SelectStudentException {
        DaoHibernateImiesProject dao = DaoHibernateImiesProject.getInstance();
        List<Student> listEtudiant = dao.selectStudent(new Student());
        for (Student listEtudiant1 : listEtudiant) {
            listEtudiant1.setStudentImage(null);
        }
        return listEtudiant;
    }
    
    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Student> findById(@PathParam("id") String id) throws SelectStudentException {
         DaoHibernateImiesProject dao = DaoHibernateImiesProject.getInstance();
        List<Student> listEtudiant = dao.selectStudent(new Student(Integer.valueOf(id)));
        for (Student listEtudiant1 : listEtudiant) {
            listEtudiant1.setStudentImage(null);
        }
        return listEtudiant;
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
            Student s = new Student();
            Promotion p = new Promotion();
            s.setStudentLastName(jsonObject.getString("lastname"));
            s.setStudentFirstName(jsonObject.getString("firstname"));
            s.setStudentMail(jsonObject.getString("mail"));
            s.setStudentAge(Integer.parseInt(jsonObject.getString("age")));
            //s.setStudentImage(Integer.parseInt(jsonObject.getString("image")));
            s.setStudentTelephonNumber(jsonObject.getString("telephone"));
            p.setPromotionId(Integer.parseInt(jsonObject.getString("promotion")));
            s.setIdPromotion(p);

            DaoHibernateImiesProject dao = new DaoHibernateImiesProject();
            dao.addBdd(s);
            System.out.println(s);
        } catch (AddException ex) {
            Logger.getLogger(RestEcf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
