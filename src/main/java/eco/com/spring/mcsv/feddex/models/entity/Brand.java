package eco.com.spring.mcsv.feddex.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brands")
public class Brand{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String grupo;

    public Brand(long id) {
        this.id = id;
    }

    public Brand(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
