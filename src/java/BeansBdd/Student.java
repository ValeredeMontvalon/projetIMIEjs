/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansBdd;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author demontvalon.cdi02
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByStudentId", query = "SELECT s FROM Student s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "Student.findByStudentFirstName", query = "SELECT s FROM Student s WHERE s.studentFirstName = :studentFirstName"),
    @NamedQuery(name = "Student.findByStudentLastName", query = "SELECT s FROM Student s WHERE s.studentLastName = :studentLastName"),
    @NamedQuery(name = "Student.findByStudentMail", query = "SELECT s FROM Student s WHERE s.studentMail = :studentMail"),
    @NamedQuery(name = "Student.findByStudentAge", query = "SELECT s FROM Student s WHERE s.studentAge = :studentAge"),
    @NamedQuery(name = "Student.findByStudentRetard", query = "SELECT s FROM Student s WHERE s.studentRetard = :studentRetard"),
    @NamedQuery(name = "Student.findByStudentCommentary", query = "SELECT s FROM Student s WHERE s.studentCommentary = :studentCommentary"),
    @NamedQuery(name = "Student.findByStudentTelephonNumber", query = "SELECT s FROM Student s WHERE s.studentTelephonNumber = :studentTelephonNumber")})
public class Student implements Serializable, Comparator<Student> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "student_first_name")
    private String studentFirstName;
    @Column(name = "student_last_name")
    private String studentLastName;
    @Column(name = "student_mail")
    private String studentMail;
    @Column(name = "student_age")
    private Integer studentAge;
    @Column(name = "student_retard")
    private Integer studentRetard;
    @Column(name = "student_commentary")
    private String studentCommentary;
    @Column(name = "student_image")
    private byte[] studentImage;
    @Column(name = "student_telephon_number")
    private String studentTelephonNumber;
    @JoinColumn(name = "id_former", referencedColumnName = "former_id")
    @ManyToOne
    private Former idFormer;
    @JoinColumn(name = "id_promotion", referencedColumnName = "promotion_id")
    @ManyToOne
    private Promotion idPromotion;
    @JoinColumn(name = "id_old_promotion", referencedColumnName = "promotion_id")
    @ManyToOne
    private Promotion idOldPromotion;

    public Student() {
    }

    public Student(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public Integer getStudentRetard() {
        return studentRetard;
    }

    public void setStudentRetard(Integer studentRetard) {
        this.studentRetard = studentRetard;
    }

    public String getStudentCommentary() {
        return studentCommentary;
    }

    public void setStudentCommentary(String studentCommentary) {
        this.studentCommentary = studentCommentary;
    }

    public byte[] getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(byte[] studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentTelephonNumber() {
        return studentTelephonNumber;
    }

    public void setStudentTelephonNumber(String studentTelephonNumber) {
        this.studentTelephonNumber = studentTelephonNumber;
    }

    public Former getIdFormer() {
        return idFormer;
    }

    public void setIdFormer(Former idFormer) {
        this.idFormer = idFormer;
    }

    public Promotion getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Promotion idPromotion) {
        this.idPromotion = idPromotion;
    }

    public Promotion getIdOldPromotion() {
        return idOldPromotion;
    }

    public void setIdOldPromotion(Promotion idOldPromotion) {
        this.idOldPromotion = idOldPromotion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClassBdd.Student[ studentId=" + studentId + " ]";
    }

    @Override
    public int compare(Student t, Student t1) {
        if (t.getStudentFirstName().compareTo(t1.getStudentFirstName()) == 0) {
            return t.getStudentLastName().compareTo(t1.getStudentLastName());
        } else {
            return t.getStudentFirstName().compareTo(t1.getStudentFirstName());
        }

    }
}
