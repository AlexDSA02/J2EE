package apirest.user;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

        @PersistenceContext
        private EntityManager entityManager;

        @Resource
        UserTransaction userTransaction;

        public List<User> getUsers(){
            return entityManager.createQuery("select u from User u", User.class).getResultList();
        }
        public User getUser(String mailUser){
            return entityManager.createQuery("select u from User u where u.mail = '"+mailUser+"'", User.class).getSingleResult();
        }

    public boolean createUser(User user){
        try{
            userTransaction.begin();
            entityManager.persist(user);
            userTransaction.commit();
            return true;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user, String idUser){
        //entityManager.createQuery("update u from User u  where u.id = "+ user.getId() +" set password = 'testfinal'", User.class).executeUpdate();
        //entityManager.createQuery("update User set password = 'testfinal' where User.id = 6").executeUpdate();
        try {
            userTransaction.begin();
            User oldUser = entityManager.createQuery("select u from User u where u.id = '" + idUser + "'", User.class).getSingleResult();
            oldUser.setPassword(user.getPassword());
            oldUser.setMail(user.getMail());
            entityManager.persist(oldUser);
            userTransaction.commit();
            return true;
        }catch (Exception e){
        Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
        return false;
        }
    }

    public boolean deleteUser(String idUser){
        try {
            userTransaction.begin();
            User oldUser = entityManager.createQuery("select u from User u where u.id = '" + idUser + "'", User.class).getSingleResult();
            entityManager.remove(entityManager.contains(oldUser) ? oldUser : entityManager.merge(oldUser));
            userTransaction.commit();
            return true;
        }catch (Exception e){
        Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
        return false;
        }
    }
}
