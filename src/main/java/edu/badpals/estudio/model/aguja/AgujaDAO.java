package edu.badpals.estudio.model.aguja;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class AgujaDAO {

    public void create(Aguja aguja) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(aguja);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Aguja aguja) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        aguja = em.merge(aguja);
        em.remove(aguja);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Aguja aguja) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(aguja);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Aguja> findById(int i) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Aguja aguja = em.find(Aguja.class, i);
        em.close();
        return Optional.ofNullable(aguja);
    }

    public List<Aguja> findAll(){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Aguja c");
        List<Aguja> agujas = query.getResultList();
        em.close();
        return agujas;
    }


}
