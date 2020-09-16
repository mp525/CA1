package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private JokeFacade() {

    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> tq = em.createQuery("SELECT j from Joke j", Joke.class);
        List<Joke> jokes = tq.getResultList();
        List<JokeDTO> dtos = new ArrayList();
        jokes.forEach((Joke joke) -> {
            dtos.add(new JokeDTO(joke));
        });
        return dtos;
    }
    
    public long getJokeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long jokeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return jokeCount;
        }finally{
            em.close();
        }
    }
    
    public JokeDTO getJokeByID(int id) {
        EntityManager em = emf.createEntityManager();
        Joke j = em.find(Joke.class, id);
        return new JokeDTO(j);
    }
    
    public static JokeDTO getRandomJoke() {
        Random ran = new Random();
        
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> tq = em.createQuery("select j from Joke j", Joke.class);
        List<Joke> jokes = tq.getResultList();
        List<JokeDTO> dtos = new ArrayList();
        jokes.forEach((Joke joke) -> {
            dtos.add(new JokeDTO(joke));
        });
        int arrSize = jokes.size();
        int ranNum = ran.nextInt(arrSize);
        JokeDTO ranJoke = dtos.get(ranNum);
        return ranJoke;
        //dtos[size];
//        Joke j = em.find(Joke.class, size);
//        return new JokeDTO(j);
    }
    
    public static void main(String[] args) {

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(new Joke("What did the Skeleton mobster say to my dog?", "I've got a bone to pick with you! *laugh track* "));
            em.persist(new Joke("Why did the chicken cross the road?", "PLEASE OH GOD TELL ME WHY!!! *laugh track* "));
            em.persist(new Joke("What did the Skeleton math teacher say to me?", "Today we will be working with ankles! *laugh track* "));

            em.getTransaction().commit();
        } finally {
            em.close();
        }


    }

}
