package apirest.poke;

import apirest.user.User;
import apirest.user.UserDAO;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PokeBean {
        @Inject
        private PokeDAO pokeDAO;

        public List<Poke> getPokes() {
            return pokeDAO.getPokes();
        }

        public Poke getPoke(String namePoke) {
            return pokeDAO.getPoke(namePoke);
        }

        public boolean createPoke(Poke poke) {
            return pokeDAO.createPoke(poke);
        }

        public boolean updatePoke(Poke poke, String idPoke) {
            return pokeDAO.updatePoke(poke, idPoke);
        }
        public boolean deletePoke(String idPoke) {
            return pokeDAO.deletePoke(idPoke);
        }

}
