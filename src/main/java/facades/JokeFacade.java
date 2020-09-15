
package facades;

import DTO.JokeDTO;
import DTO.MembersDTO;
import entities.Joke;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class JokeFacade {
    
    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private JokeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public JokeDTO getJokeByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke joke = em.find(Joke.class, id);
            return new JokeDTO(joke);
        } finally {
            em.close();
        }
    }
    
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        List<Joke> listen = new ArrayList();
        List<JokeDTO> listJokes = new ArrayList();
        try {
            TypedQuery<Joke> query = em.createQuery("SELECT m FROM Joke m", Joke.class);
            listen = query.getResultList();
            for (Joke joke : listen) {
                listJokes.add(new JokeDTO(joke));
            }
            return listJokes;
        } finally {
            em.close();
        }
    }
    
}
