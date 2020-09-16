/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import dtos.GroupMemberDTO;
import static dtos.GroupMemberDTO.toDTO;
import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    private GroupMemberFacade() {

    }

    public static GroupMemberFacade getGMPFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }
    public static List<GroupMemberDTO> getAll(){
        
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<GroupMember> query = 
                       em.createQuery("Select m from GroupMember m", GroupMember.class);
            List <GroupMember>members= query.getResultList();
            System.out.println(members.get(0).getName());
            
            List<GroupMemberDTO>membersDTO=toDTO(members);
            System.out.println(membersDTO.get(0).getName());
            return membersDTO;
        }finally {
            em.close();}}

        
    
    
    
    public static void main(String[] args) {

        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {
            
            em.getTransaction().begin();
            
            em.createQuery("DELETE from GroupMember").executeUpdate();
            //ændre min hobby senere haha
            em.persist( new GroupMember("Matti Hansen", "cph-mh829", "Curlyfries", "The FellowShip of the Ring", "Tinder"));
            em.persist( new GroupMember("Nikolaj Trankjaer", "cph-nt105", "Rebaked Potatoes", "Neil Breen: Twisted Pair", "Tabletop Wargaming"));
            
            em.getTransaction().commit();
            getAll();
        } finally {
            em.close();
        }

    }

}
