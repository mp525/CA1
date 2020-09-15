package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String setup;
    private String punch;

    public Joke(int id, String setup, String punch) {
        this.id = id;
        this.setup = setup;
        this.punch = punch;
    }

    public Joke() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Joke{" + "id=" + id + ", setup=" + setup + ", punch=" + punch + '}';
    }
    
    

    
}
