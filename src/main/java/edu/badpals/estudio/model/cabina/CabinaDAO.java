package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class CabinaDAO {

    public Optional<Cabina> findById(int i) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Cabina cabina = em.find(Cabina.class, i);
        em.close();
        return Optional.ofNullable(cabina);
    }

    public List<Cabina> findAll(){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cabina c");
        List<Cabina> cabinas = query.getResultList();
        em.close();
        return cabinas;
    }
}
