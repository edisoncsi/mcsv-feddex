package eco.com.spring.mcsv.feddex.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brands")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public long getId() {
        return id;
    }

}
