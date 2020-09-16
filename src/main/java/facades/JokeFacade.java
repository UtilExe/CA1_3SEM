
package facades;

import DTO.JokeDTO;
import DTO.MembersDTO;
import entities.Joke;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        List<Joke> jokeList = new ArrayList();
        List<JokeDTO> listJokes = new ArrayList();
        try {
            TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j", Joke.class);
            jokeList = query.getResultList();
            for (Joke joke : jokeList) {
                listJokes.add(new JokeDTO(joke));
            }
            return listJokes;
        } finally {
            em.close();
        }
    }
    
    public static long getJokeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long JokeCount = (long)em.createQuery("SELECT COUNT(m) FROM Joke m").getSingleResult();
            return JokeCount;
        }finally{  
            em.close();
        }
    }
    
    public JokeDTO getRandomJoke(){
        long jokeCount = getJokeCount();
        EntityManager em = emf.createEntityManager();
        Random number = new Random();
        Long randomNumber = (long)number.nextInt((int) jokeCount);
        randomNumber += 1;
        
        try{
            Joke joke = em.find(Joke.class, randomNumber);
            return new JokeDTO(joke);
            
        }finally{
            em.close();
        } 
    }
    
}
