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
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class GroupMemberFarcade {

    private static GroupMemberFarcade instance;
    private static EntityManagerFactory emf;

    private GroupMemberFarcade() {

    }

    
    public static ArrayList<GroupMemberDTO> getAll(){
        
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<GroupMember> query = 
                       em.createQuery("Select m from GroupMember m", GroupMember.class);
            ArrayList members=(ArrayList) query.getResultList();
            ArrayList<GroupMemberDTO>membersDTO=new ArrayList();
            System.out.println(members.size());
            for (int i = 0; i < members.size(); i++) {
               GroupMember member=(GroupMember) members.get(i);
               GroupMemberDTO e = new GroupMemberDTO(member);
               membersDTO.add(e);
            }
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
            
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
