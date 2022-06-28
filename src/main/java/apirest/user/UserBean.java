package apirest.user;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.util.List;
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserBean {
        @Inject
        private UserDAO userDAO;

        public List<User> getUsers() {
            return userDAO.getUsers();
        }

        public User getUser(String mailUser) {
            return userDAO.getUser(mailUser);
        }

        public boolean createUser(User user) {
            return userDAO.createUser(user);
        }

        public boolean updateUser(User user, String idUser) {
            return userDAO.updateUser(user, idUser);
        }
        public boolean deleteUser(String idUser) {
            return userDAO.deleteUser(idUser);
        }

}
