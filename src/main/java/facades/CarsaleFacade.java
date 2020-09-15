package facades;

import dtos.CarsaleDTO;
import entities.Carsale;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
    
    public List<CarsaleDTO> getAllCarsales() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Carsale> query = em.createQuery("SELECT c FROM Carsale c", Carsale.class);
            List<Carsale> carsales = query.getResultList();
            List<CarsaleDTO> listDTO = CarsaleDTO.toDTO(carsales);
            return listDTO;
        } finally {
            em.close();
        }
    }
    
}
