/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class JokeFacade {

    private static JokeFacade intance;
    private static EntityManagerFactory emf;

    private JokeFacade() {

    }

    public static void main(String[] args) {

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist( new Joke("What did the Skeleton mobster say to my dog?","I've got a bone to pick with you! *laugh track* "));
            em.persist( new Joke("Why did the chicken cross the road?","PLEASE OH GOD TELL ME WHY!!! *laugh track* "));
            em.persist( new Joke("What did the Skeleton math teacher say to me?","Today we will be working with ankles! *laugh track* "));
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
