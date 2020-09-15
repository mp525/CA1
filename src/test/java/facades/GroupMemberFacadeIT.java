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
    public static void setUpClass() {

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        facade=GroupMemberFacade.getGMPFacade(emf);
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
    
    

    /**
     * Test of getAll method, of class GroupMemberFarcade.
     */
    @Test
    public void testGetAll() {
        List<GroupMemberDTO> expResult = new ArrayList();
        GroupMember m = new GroupMember("Matti Hansen", "cph-mh829", "Curlyfries", "The FellowShip of the Ring", "Tinder");
        GroupMemberDTO dto = new GroupMemberDTO(m);
        expResult.add(dto);

        System.out.println(m);
        System.out.println(dto);
        //nullpointer
        List<GroupMemberDTO> result = GroupMemberFacade.getAll();   
        System.out.println(result);


//
        assertEquals(expResult.get(0).getName(), result.get(0).getName());
        
    }

    
    
}
