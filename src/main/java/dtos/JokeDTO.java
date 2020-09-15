/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Joke;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author SJUBE
 */

public class JokeDTO {


    private int id;
    private String setup;
    private String punch;

//     Probably never need, but keep for funzies
//        public JokeDTO(int id, String setup, String punch) {
//        this.id = id;
//        this.setup = setup;
//        this.punch = punch;
//    }
        
           
    public JokeDTO(Joke jokee) {
        this.id = jokee.getId();
        this.setup = jokee.getSetup();
        this.punch = jokee.getPunch();
    }
    
    public JokeDTO() {
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunch() {
        return punch;
    }

    public void setPunch(String punch) {
        this.punch = punch;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
