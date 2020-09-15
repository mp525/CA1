package dtos;

import entities.Carsale;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mathias
 */
public class CarsaleDTO {

    private int id;
    private int year;
    private String make;
    private String model;
    private int price;

    public CarsaleDTO(Carsale cs) {
        this.id = cs.getId();
        this.year = cs.getYear();
        this.make = cs.getMake();
        this.model = cs.getModel();
        this.price = cs.getPrice();
    }
    
    public static List<CarsaleDTO> toDTO(List<Carsale> carsaleList){
        List<CarsaleDTO> listDTO = new ArrayList();
        for (Carsale cs : carsaleList) {
                listDTO.add(new CarsaleDTO(cs));
        } //Måske lambda expression?
        return listDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    

}
