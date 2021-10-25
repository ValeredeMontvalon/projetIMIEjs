/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ImiesException.SelectFormerException;
import BeansBdd.Ecf;
import BeansBdd.Formation;
import BeansBdd.Former;
import BeansBdd.Note;
import BeansBdd.Promotion;
import BeansBdd.School;
import BeansBdd.Student;
import HibernateTools.HibernateUtil;
import ImiesException.AddException;
import ImiesException.DeleteException;
import ImiesException.SelectCityNameException;
import ImiesException.SelectDateBeginException;
import ImiesException.SelectDateEndException;
import ImiesException.SelectEcfException;
import ImiesException.SelectFormationException;
import ImiesException.SelectFormationIdByNameException;
import ImiesException.SelectFormerWithValidLoginException;
import ImiesException.SelectNoteException;
import ImiesException.SelectPromoException;
import ImiesException.SelectSchoolException;
import ImiesException.SelectSchoolIdByNameException;
import ImiesException.SelectStudentException;
import ImiesException.SelectSchoolNameException;
import ImiesException.UpdateException;
import java.sql.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

/**
 *
 * @author lor.cdi02
 */
public class DaoHibernateImiesProject {

    public DaoHibernateImiesProject() {
    }

    public static DaoHibernateImiesProject getInstance() {
        return DaoHibernateImiesProjectHolder.INSTANCE;
    }

    public List<Ecf> selectEcf(Ecf ecf) throws SelectEcfException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Ecf.class);
            if (ecf.getEcfName() != null) {
                criteria.add(Restrictions.like("ecfName", ecf.getEcfName() + "%"));
            }
            if (ecf.getEcfId() != null) {
                criteria.add(Restrictions.like("ecfId", ecf.getEcfId()));
            }
            if (ecf.getEcfDate() != null) {
                criteria.add(Restrictions.like("ecfDate", ecf.getEcfDate()));
            }

            List<Ecf> listEcf = criteria.list();
            session.close();
            return listEcf;
        } catch (Exception e) {
            throw new SelectEcfException("Erreur lors de la selection des ecf");
        }
    }

     public List<Note> selectEcfByStudent(Student student) throws SelectNoteException{
                try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String queryString= "FROM Note where id_student = "+ student.getStudentId();
            Query query = session.createQuery(queryString);
            List<Note> listNote = query.list();
            session.close();
            return listNote;
        } catch (Exception e) {
            throw new SelectNoteException("Erreur lors de la selection des notes.");
        }
    }

    public Note selectNotebyEcf(Note note)throws SelectNoteException { 
        try {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String queryString = "From Note where id_student = "+ note.getIdStudent().getStudentId()+" and id_ecf = "+ note.getIdEcf().getEcfId();
        Query query = s.createQuery(queryString);
        List<Note> listNote = query.list();
        Note n = listNote.get(0);
        s.close();
        return n;       
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SelectNoteException("Erreur lors de la selection des notes.");
        }
    
    }



    private static class DaoHibernateImiesProjectHolder {

        private static final DaoHibernateImiesProject INSTANCE = new DaoHibernateImiesProject();
    }

    public void addBdd(Object o) throws AddException {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            s.save(o);
            t.commit();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AddException("Erreur lors de l'ajout");
        }
    }

    public void updateBdd(Object o) throws UpdateException {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            s.update(o);
            t.commit();
            s.close();
        } catch (Exception e) {
            throw new UpdateException("Erreur lors de l'update");
        }
    }

    public void deleteBdd(Object o) throws DeleteException {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            s.delete(o);
            t.commit();
            s.close();
        } catch (Exception e) {
            throw new DeleteException("Erreur lors de la supression");
        }
    }

    public List<Promotion> selectPromo(Promotion promo) throws SelectPromoException {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stringQuery = "";
            if (promo.getIdSchool() != null) {
                //stringQuery += "from Promotion school where school.city_name = "+promo.getIdSchool().getSchoolCity() +" and school.school_id = promotion.id_school";
                stringQuery += "from Promotion as promotion  where promotion.idSchool.schoolCity like  '" + promo.getIdSchool().getSchoolCity() + "'";
            } else {
                stringQuery = "from Promotion where 1=1 ";
            }
            if (promo.getPromotionName() != null) {
                stringQuery += "and promotion_name =" + promo.getPromotionName() + "%";
            }
            if (promo.getPromotionDateBegin() != null) {
                String str = new LocalDate(promo.getPromotionDateBegin()).toString();
                stringQuery += "and promotion_date_begin ='" + str + "'";
            }
            if (promo.getPromotionDateEnd() != null) {
                String str = new LocalDate(promo.getPromotionDateEnd()).toString();
                stringQuery += "and promotion_date_end ='" + str + "'";
            }
            if (promo.getIdFormation() != null) {
                stringQuery += "and id_formation=" + ((Formation) promo.getIdFormation()).getFormationId();
            }
            stringQuery += "ORDER BY promotion_name";
            Query query = s.createQuery(stringQuery);
            List<Promotion> listpromo = query.list();
            s.close();
            return listpromo;
        } catch (Exception e) {
            String t = e.getMessage();
            throw new SelectPromoException("Erreur lors de la selection d'une promotion");
        }
    }

    public School selectSchoolIdbyName(String string) throws SelectSchoolIdByNameException {
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            String stringQuery = "";
            if (string != null) {
                stringQuery += "from School where school_name='" + string + "'";
            } else {
                stringQuery = "from School where 1=1 ";
            }
            Query q = session.createQuery(stringQuery);
            List<School> listSchool = q.list();
            School s = new School();
            s.setSchoolId(listSchool.get(0).getSchoolId());
            s.setSchoolCity(listSchool.get(0).getSchoolCity());
            s.setSchoolName(listSchool.get(0).getSchoolName());
            s.setPromotionCollection(listSchool.get(0).getPromotionCollection());
            session.close();
            return s;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SelectSchoolIdByNameException("Erreur lors de la selection de l'id de l'école par son nom");
        }
    }

    public String findPromotionNumber(Formation formation, School school) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String nb = "";
        int promotionExistante = 0;
        int i = 0;

        String stringQuery = "";
        if (formation != null && school != null) {
            stringQuery += "from Promotion where id_school =" + school.getSchoolId() + " and id_formation =" + formation.getFormationId() + " order by promotion_number desc";
        } else {
            stringQuery = "from Promotion where 1 = 1";
        }
        Query query = session.createQuery(stringQuery);
        List<Promotion> listPromotion = query.list();
        for (Promotion p : listPromotion) {
            promotionExistante++;
        }
        if (promotionExistante == 0) {
            nb = "01";
        } else {
            i = Integer.parseInt(listPromotion.get(0).getPromotionNumber());
            i++;
            if (i < 10) {
                nb = "0" + Integer.toString(i);
            } else {
                nb = Integer.toString(i);
            }
        }

        session.close();
        return nb;
    }

    public Formation selectFormationIdbyName(String string) throws SelectFormationIdByNameException {
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            String stringQuery = "";
            if (string != null) {
                stringQuery += "from Formation where formation_name='" + string + "'";
            } else {
                stringQuery = "from Formation where 1=1 ";
            }
            Query q = session.createQuery(stringQuery);
            List<Formation> listFormation = q.list();
            Formation f = new Formation();
            f.setFormationId(listFormation.get(0).getFormationId());
            f.setFormationName(listFormation.get(0).getFormationName());
            f.setPromotionCollection(listFormation.get(0).getPromotionCollection());
            session.close();
            return f;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SelectFormationIdByNameException("Erreur lors de la selection de l'id de la formation par son nom");
        }
    }

    public List<School> selectSchool(School s) throws SelectSchoolException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(School.class);
        if (s.getSchoolName() != null) {
            criteria.add(Restrictions.like("schoolName", s.getSchoolName() + "%"));
        }
        if (s.getSchoolCity() != null) {
            criteria.add(Restrictions.like("schoolCity", s.getSchoolCity()));
        }
        criteria.addOrder(Order.asc("schoolName"));
        List<School> listSchool = criteria.list();
        session.close();
        return listSchool;
    }

    public List<Formation> selectFormation(Formation f) throws SelectFormationException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Formation.class);
            if (f.getFormationName() != null) {
                criteria.add(Restrictions.like("formationName", f.getFormationName() + "%"));
            }
            if (f.getFormationId() != null) {
                criteria.add(Restrictions.like("formationId", f.getFormationId()));
            }
            criteria.addOrder(Order.asc("formationName"));
            List<Formation> listFormation = criteria.list();
            session.close();
            return listFormation;
        } catch (Exception e) {
            throw new SelectFormationException("Erreur lors de la selection d'une promotion");
        }
    }

    public List<String> selectSchoolName() throws SelectSchoolNameException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(School.class);
            criteria.setProjection(Projections.distinct(Projections.property("schoolName")));
            List<String> listSchoolName = criteria.list();
            session.close();
            return listSchoolName;
        } catch (Exception e) {
            throw new SelectSchoolNameException("Erreur lors de la selection de l'école");
        }
    }

    public List<String> selectCity() throws SelectCityNameException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(School.class);
            criteria.setProjection(Projections.distinct(Projections.property("schoolCity")));
            List<String> listSchool = criteria.list();

            session.close();
            return listSchool;
        } catch (Exception e) {
            throw new SelectCityNameException("Erreur lors de la selection d'une ville");
        }
    }

    public List<Date> selectDateBegin() throws SelectDateBeginException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Promotion.class);
            criteria.setProjection(Projections.distinct(Projections.property("promotionDateBegin")));
            List<Date> listDate = criteria.list();

            session.close();
            return listDate;
        } catch (Exception e) {
            throw new SelectDateBeginException("Erreur lors de la selection d'une date de début");
        }
    }

    public List<Date> selectDateEnd() throws SelectDateEndException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Promotion.class);
            criteria.setProjection(Projections.distinct(Projections.property("promotionDateEnd")));
            List<Date> listDate = criteria.list();

            session.close();
            return listDate;
        } catch (Exception e) {
            throw new SelectDateEndException("Erreur lors de la selection d'une date de fin");
        }
    }

    public List<Former> selectFormerWhithValidLogin(Former f) throws SelectFormerWithValidLoginException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Former.class);
            if (f.getFormerLogin() != null) {
                criteria.add(Restrictions.like("formerLogin", f.getFormerLogin()));
            }
            if (f.getFormerPassword() != null) {
                criteria.add(Restrictions.like("formerPassword", f.getFormerPassword() + "%"));
            }
            List<Former> listFormer = criteria.list();
            session.close();
            return listFormer;
        } catch (Exception e) {
            throw new SelectFormerWithValidLoginException("Erreur lors de la selection d'un formateur avec le bon couple login mot de passe");
        }
    }

    public List<Student> selectStudent(Student student) throws SelectStudentException {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stringQuery = "";
            if (student.getStudentId() != null) {
                stringQuery += "from Student as student where student.id_student like " + student.getStudentId() + "' ";
            } else {
                stringQuery = "from Student where 1=1 ";
            }
            if (student.getStudentFirstName() != null) {
                stringQuery += "and student_first_name = " + student.getStudentFirstName() + " ";
            }
            if (student.getStudentLastName() != null) {
                stringQuery += "and student_last_name = " + student.getStudentLastName() + " ";
            }
            if (student.getStudentMail() != null) {
                stringQuery += "and student_mail = " + student.getStudentMail() + " ";
            }
            if (student.getStudentAge() != null) {
                stringQuery += "and student_age = " + student.getStudentAge() + " ";
            }
            if (student.getStudentRetard() != null) {
                stringQuery += "and student_retard = " + student.getStudentRetard() + " ";
            }
            if (student.getStudentCommentary() != null) {
                stringQuery += "and student_commentary = " + student.getStudentCommentary() + " ";
            }
            if (student.getStudentTelephonNumber() != null) {
                stringQuery += "and student_telephon_number = " + student.getStudentTelephonNumber() + " ";
            }
            if (student.getIdPromotion() != null) {
                stringQuery += "and id_promotion = " + student.getIdPromotion().getPromotionId() + " ";
            }
            if (student.getIdFormer() != null) {
                stringQuery += "and id_former = " + (student.getIdFormer().getFormerId()) + " ";
            }
            if (student.getIdOldPromotion() != null) {
                stringQuery += "and id_old_promotion = " + (student.getIdOldPromotion() + " ");
            }
            stringQuery += " ORDER BY student_last_name, student_first_name";
            Query query = s.createQuery(stringQuery);
            List<Student> listStudent = query.list();
            s.close();

            return listStudent;
        } catch (Exception e) {
            String t = e.getMessage();
            throw new SelectStudentException("Erreur lors de la selection d'un etudiant" + t);
        }

    }

    public List<Former> selectFormer(Former former) throws SelectFormerException {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String stringQuery = "";
        if (former.getFormerId() != null) {
            stringQuery += "from Former as former where former.id_former like " + former.getFormerId() + "'";
        } else {
            stringQuery = "from Former where 1=1";
        }
        if (former.getFormerFirstName() != null) {
            stringQuery += "and former_first_name =" + former.getFormerFirstName() + "%";
        }
        if (former.getFormerLastName() != null) {
            stringQuery += "and former_last_name =" + former.getFormerLastName() + "%";
        }
        if (former.getFormerLesson() != null) {
            stringQuery += "and former_lesson = " + former.getFormerLesson() + "%";
        }
        if (former.getFormerLogin() != null) {
            stringQuery += "and former_login = " + former.getFormerLogin() + "%";
        }
        if (former.getFormerPassword() != null) {
            stringQuery += "and former_password = " + former.getFormerPassword() + "%";
        }
        stringQuery += "ORDER BY former_first_name, former_last_name";
        Query query = s.createQuery(stringQuery);
        List<Former> listFormer = query.list();
        s.close();

        return listFormer;
    }
    
    public List<Former> selectAllFormer(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String strQuery= "from Former";
        Query q = s.createQuery(strQuery);
        List<Former> listFormer = q.list();
        s.close();
        
        return listFormer;
    }
    
    public List<Promotion> selectAllPromotion(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String strQuery= "from Promotion";
        Query q = s.createQuery(strQuery);
        List<Promotion> listPromotion = q.list();
        s.close();
        
        return listPromotion;
    }
    
    public List<School> selectAllSchool(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String strQuery= "from School";
        Query q = s.createQuery(strQuery);
        List<School> listSchool = q.list();
        s.close();
        
        return listSchool;
    }
    
    public List<Formation> selectAllFormation(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String strQuery= "from Formation";
        Query q = s.createQuery(strQuery);
        List<Formation> listFormation = q.list();
        s.close();
        
        return listFormation;
    }
    
}
