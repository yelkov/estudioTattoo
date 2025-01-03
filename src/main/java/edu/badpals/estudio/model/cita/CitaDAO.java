package edu.badpals.estudio.model.cita;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class CitaDAO {

    public void create(Cita cita) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(cita);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Cita cita) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        cita = em.merge(cita);
        em.remove(cita);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Cita cita) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(cita);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Cita> findById(int i) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Cita cita = em.find(Cita.class, i);
        em.close();
        return Optional.ofNullable(cita);
    }

    public List<Cita> findAll(){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c");
        List<Cita> citas = query.getResultList();
        em.close();
        return citas;
    }

    public List<Cita> findByTipo(Tipo tipo) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.tipo = :tipo");
        query.setParameter("tipo", tipo);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public List<Cita> findByTatuador(int id) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.tatuador.id = :id");
        query.setParameter("id", id);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public List<Cita> findByAnillador(int id) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.anillador.id = :id");
        query.setParameter("id", id);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public List<Cita> findByCabina(int id) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.cabina.id = :id");
        query.setParameter("id", id);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public Optional<Tatuador> findTatuador(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Tatuador t WHERE t.id = :id");
        query.setParameter("id", id);
        try {
            Tatuador tatuador = (Tatuador) query.getSingleResult();
            return Optional.ofNullable(tatuador);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public Optional<Anillador> findAnillador(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Anillador t WHERE t.id = :id");
        query.setParameter("id", id);
        try {
            Anillador anillador = (Anillador) query.getSingleResult();
            return Optional.ofNullable(anillador);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public Optional<Cabina> findCabina(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cabina c WHERE c.id = :id");
        query.setParameter("id", id);
        try {
            Cabina cabina = (Cabina) query.getSingleResult();
            return Optional.ofNullable(cabina);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }







}
