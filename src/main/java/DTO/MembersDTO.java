
package DTO;

import entities.Members;

public class MembersDTO {

    private Long Id;
    private String studentID;
    private String name;
    private String favoriteTVSeries;
    
    public MembersDTO(Members member) {
        this.Id = member.getId();
        this.studentID = member.getStudentID();
        this.name = member.getName();
        this.favoriteTVSeries = member.getFavoriteTVSeries();
    }

    public Long getId() {
        return Id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteTVSeries() {
        return favoriteTVSeries;
    }
    
}
