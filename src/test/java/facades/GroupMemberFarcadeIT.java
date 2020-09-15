/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.GroupMemberDTO;
import entities.GroupMember;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author matti
 */
public class GroupMemberFarcadeIT {
                private static EntityManagerFactory emf;

    public GroupMemberFarcadeIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {

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
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class GroupMemberFarcade.
     */
    @Test
    public void testGetAll() {
//        System.out.println("getAll");
//        ArrayList<GroupMemberDTO> expResult = null;
//        ArrayList<GroupMemberDTO> result = GroupMemberFarcade.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    
}
