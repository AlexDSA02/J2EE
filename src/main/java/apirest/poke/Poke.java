package apirest.poke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Poke implements Serializable {
        @Column
        private String name;
        @Column
        private String type;
        @Id
        @GeneratedValue
        private Long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
}
