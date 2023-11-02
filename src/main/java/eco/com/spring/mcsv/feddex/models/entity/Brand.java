package eco.com.spring.mcsv.feddex.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String grupo;

    public long getId() {
        return id;
    }

}
