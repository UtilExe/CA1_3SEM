package facades;

import DTO.MembersDTO;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MembersFacade {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MembersFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMembersCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long MembersCount = (long)em.createQuery("SELECT COUNT(m) FROM Members m").getSingleResult();
            return MembersCount;
        }finally{  
            em.close();
        }
    }
    
    //Jannich
    public List<MembersDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        List<Members> listen = new ArrayList();
        List<MembersDTO> listMembers = new ArrayList();
        try {
            TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
            listen = query.getResultList();
            for (Members member : listen) {
                listMembers.add(new MembersDTO(member));
            }
            return listMembers;
        } finally {
            em.close();
        }
    }

}
