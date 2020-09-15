package facades;

import dtos.CarsaleDTO;
import entities.Carsale;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Mathias
 */
public class CarsaleFacade {
    private static CarsaleFacade instance;
    private static EntityManagerFactory emf;

    public CarsaleFacade() {
    }
    
     public static CarsaleFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsaleFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getCarsaleCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carsaleCount = (long) em.createQuery("SELECT COUNT(c) FROM Carsale c").getSingleResult();
            return carsaleCount;
        } finally {
            em.close();
        }

    }
    
    public List<CarsaleDTO> getAllCarsales() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Carsale> query = em.createQuery("SELECT c FROM Carsale c", Carsale.class);
            List<Carsale> carsales = query.getResultList();
            List<CarsaleDTO> listDTO = CarsaleDTO.toDTO(carsales);
            return listDTO;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Carsale").executeUpdate();
           
            em.persist(new Carsale(1997, "Ford", "E350", 3000, "Hans Pilgaard"));
            em.persist(new Carsale(1999, "Chevy", "Venture", 4900, "James Bond"));
            em.persist(new Carsale(2000, "Chevy", "Venture", 5000, "Kim Larsen"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
