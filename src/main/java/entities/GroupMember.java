/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author matti
 */
@Entity
public class GroupMember implements Serializable {

    private static final int serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String studentID;
    private String favouritePotatoForm;
    private String favouriteMovie;
    private String hobby;

    public GroupMember(String name, String studentID, String favouritePotatoForm, String favouriteMovie, String hobby) {
        this.name = name;
        this.studentID = studentID;
        this.favouritePotatoForm = favouritePotatoForm;
        this.favouriteMovie = favouriteMovie;
        this.hobby = hobby;
    }

    public GroupMember() {
    }

    
    public int getId() {
        return id;
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
