package facades;


import DTO.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CarFacade {
    
    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
     public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        List<Car> carList = new ArrayList();
        List<CarDTO> listCars = new ArrayList();
        try {
            TypedQuery<Car> query = em.createQuery("SELECT m FROM Car m", Car.class);
            carList = query.getResultList();
            for (Car car : carList) {
                listCars.add(new CarDTO(car));
            }
            return listCars;
        } finally {
            em.close();
        }
    }
    
    
    
}
