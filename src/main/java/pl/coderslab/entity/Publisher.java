package pl.coderslab.entity;

import javax.persistence.*;

@Entity
@Table(name="publishers")
public class Publisher {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private String name;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
