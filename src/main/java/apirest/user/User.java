package apirest.user;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class User implements Serializable {
        @Column
        private String mail;
        @Column
        private String password;
        @Column
        private String token;
        @Id
        @GeneratedValue
        private Long id;

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
}
