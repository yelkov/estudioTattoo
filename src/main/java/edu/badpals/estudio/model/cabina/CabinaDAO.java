package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class CabinaDAO {

    public void create(Cabina cabina) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(cabina);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Cabina cabina) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        cabina = em.merge(cabina);
        em.remove(cabina);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Cabina cabina) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(cabina);
        em.getTransaction().commit();
        em.close();
    }

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

    public Optional<Cabina> findByUbicacion(String ubicacion) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cabina c WHERE c.ubicacion = :ubicacion");
        query.setParameter("ubicacion", ubicacion.toUpperCase());
        try{
            Cabina cabina = (Cabina) query.getSingleResult();
            em.close();
            return Optional.ofNullable(cabina);
        }catch(NoResultException e){
            em.close();
            return Optional.empty();
        }
    }
}
