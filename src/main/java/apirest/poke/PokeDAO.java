package apirest.poke;


import apirest.poke.Poke;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeDAO {

        @PersistenceContext
        private EntityManager entityManager;

        @Resource
        UserTransaction userTransaction;

        public List<Poke> getPokes(){
            return entityManager.createQuery("select p from Poke p", Poke.class).getResultList();
        }
        public Poke getPoke(String namePoke){
            return entityManager.createQuery("select p from Poke p where p.name = '"+namePoke+"'", Poke.class).getSingleResult();
        }

    public boolean createPoke(Poke poke){
        try{
            userTransaction.begin();
            entityManager.persist(poke);
            userTransaction.commit();
            return true;
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean updatePoke(Poke poke, String idPoke){
        //entityManager.createQuery("update u from User u  where u.id = "+ user.getId() +" set password = 'testfinal'", User.class).executeUpdate();
        //entityManager.createQuery("update User set password = 'testfinal' where User.id = 6").executeUpdate();
        try {
            userTransaction.begin();
            Poke oldPoke = entityManager.createQuery("select p from Poke p where p.id = '" + idPoke + "'", Poke.class).getSingleResult();
            oldPoke.setType(poke.getType());
            oldPoke.setName(poke.getName());
            entityManager.persist(oldPoke);
            userTransaction.commit();
            return true;
        }catch (Exception e){
        Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
        return false;
        }
    }

    public boolean deletePoke(String idPoke){
        try {
            userTransaction.begin();
            Poke oldPoke = entityManager.createQuery("select p from Poke p where p.id = '" + idPoke + "'", Poke.class).getSingleResult();
            entityManager.remove(entityManager.contains(oldPoke) ? oldPoke : entityManager.merge(oldPoke));
            userTransaction.commit();
            return true;
        }catch (Exception e){
        Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
        return false;
        }
    }
}
