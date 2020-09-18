/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.GroupMemberDTO;
import entities.GroupMember;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author matti
 */
public class GroupMemberFacadeIT {
        private static EntityManagerFactory emf;
        private static GroupMemberFacade facade;

    public GroupMemberFacadeIT() {
            
                    
    }
    
    @BeforeAll
    public static void setUpClass() throws InterruptedException {

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        facade=GroupMemberFacade.getGMPFacade(emf);
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from GroupMember").executeUpdate();
            GroupMember m1 = new GroupMember("Matti Hansen", "cph-mh829", "Curlyfries", "The FellowShip of the Ring", "Tinder");
            
            //ændre min hobby senere haha
            em.persist( m1);   
            Thread.sleep(2000);
            GroupMember m2 = new GroupMember("Bob Hansen", "cph-ok829", "fries", "The FellowShip of the Ring", "Tinder");
            em.persist( m2); 
            Thread.sleep(2000);
            GroupMember m3 = new GroupMember("bent bob Hansen", "cph-sd829", "fries", "The FellowShip of the Ring", "Tinder");

            em.persist( m3);  
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    

    /**
     * Test of getAll method, of class GroupMemberFarcade.
     */
    @Test
    public void testGetAll() {
        List<GroupMemberDTO> result = GroupMemberFacade.getAll();   

        assertThat(result, everyItem(hasProperty("name")));
        assertThat(result, hasItems( // or contains or containsInAnyOrder 
                Matchers.<GroupMemberDTO>hasProperty("name", is("Matti Hansen")),
                Matchers.<GroupMemberDTO>hasProperty("name", is("Bob Hansen")),
                Matchers.<GroupMemberDTO>hasProperty("name", is("bent bob Hansen"))
        )
        );
        
        
    }

    
    
}
