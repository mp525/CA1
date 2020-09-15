/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import entities.GroupMember;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class GroupMemberFarcade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    private GroupMemberFarcade() {

    }

    public static void main(String[] args) {

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            
            em.createQuery("DELETE from GroupMember").executeUpdate();
            //ændre min hobby senere haha
            em.persist( new GroupMember("Matti Hansen", "cph-mh829", "Curlyfries", "The FellowShip of the Ring", "Tinder"));
            
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
