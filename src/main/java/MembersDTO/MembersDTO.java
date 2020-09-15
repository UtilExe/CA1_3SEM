
package MembersDTO;

import entities.Members;

public class MembersDTO {

    private Long Id;
    private String studentID;
    private String name;
    private String favoriteTVSeries;
    
    public MembersDTO(Members membersDTO) {
        this.Id = membersDTO.getId();
        this.studentID = membersDTO.getStudentID();
        this.name = membersDTO.getName();
        this.favoriteTVSeries = membersDTO.getFavoriteTVSeries();
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
