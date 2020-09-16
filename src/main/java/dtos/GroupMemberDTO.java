/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.GroupMember;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matti
 */
public class GroupMemberDTO {

   
    private String name;
    private String studentID;
    private String favouritePotatoForm;
    private String favouriteMovie;
    private String hobby;

    public GroupMemberDTO(GroupMember m) {
        this.name = m.getName();
        this.studentID = m.getStudentID();
        this.favouritePotatoForm = m.getFavouritePotatoForm();
        this.favouriteMovie = m.getFavouriteMovie();
        this.hobby = m.getHobby();
    }

    public GroupMemberDTO() {
    }

    public static List<GroupMemberDTO> toDTO(List<GroupMember> groupList){
        List<GroupMemberDTO> listDTO = new ArrayList();
        for (GroupMember gm : groupList) {
                listDTO.add(new GroupMemberDTO(gm));
        } //Måske lambda expression?
        return listDTO;
    }    
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFavouritePotatoForm() {
        return favouritePotatoForm;
    }

    public void setFavouritePotatoForm(String favouritePotatoForm) {
        this.favouritePotatoForm = favouritePotatoForm;
    }

    public String getFavouriteMovie() {
        return favouriteMovie;
    }

    public void setFavouriteMovie(String favouriteMovie) {
        this.favouriteMovie = favouriteMovie;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    
    
}

