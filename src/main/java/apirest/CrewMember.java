package apirest;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CrewMember implements Serializable {
    @Column
    private String name;
    @Column
    private String job;
    @Id
    @GeneratedValue
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}