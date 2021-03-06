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
 * @author Mathias
 */
@Entity
public class Carsale implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private String make;
    private String model;
    private int price;
    private String buyerName;
    //private String owner;

    public Carsale() {
    }

    public Carsale(int year, String make, String model, int price, String buyerName) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
        this.buyerName = buyerName;
        //this.owner = owner;
    }

    public String getSeller() {
        return buyerName;
    }

    public void setSeller(String buyerName) {
        this.buyerName = buyerName;
    }

    
    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.Carsale[ id=" + id + " ]";
    }
    
}
