package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Members.deleteAllRows", query = "DELETE from Members")
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="student_id", length=6, nullable = false)
    private String studentID;
    @Column(name="name", length=128, nullable = false)
    private String name;
    @Column(name="favorite_tv_series", nullable = false)
    private String favoriteTVSeries;
    
    public Members() {
    }
        
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteTVSeries() {
        return favoriteTVSeries;
    }

    public void setFavoriteTVSeries(String favoriteTVSeries) {
        this.favoriteTVSeries = favoriteTVSeries;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
   
}
