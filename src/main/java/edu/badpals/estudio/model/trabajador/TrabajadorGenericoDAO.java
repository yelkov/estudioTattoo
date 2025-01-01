package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrabajadorGenericoDAO<T> {
    private final Class<T> tipo;

    public TrabajadorGenericoDAO(Class<T> tipo) {
        this.tipo = tipo;
    }

    public void create(T entidad) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    public void update(T entidad) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(T entidad) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        entidad = em.merge(entidad);
        em.remove(entidad);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<T> findById(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        T entidad = em.find(tipo, id);
        em.close();
        return Optional.ofNullable(entidad);
    }


    public Optional<T> findByAttribute(String atributo, Object value) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        try {
            String queryString = "SELECT e FROM " + tipo.getSimpleName() + " e WHERE e." + atributo + " = :value";
            Query query = em.createQuery(queryString, tipo);
            query.setParameter("value", value);

            T result = (T) query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }


    public List<T> findAll() {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM "+tipo.getSimpleName()+" t");
        List<T> entidad = query.getResultList();
        em.close();
        return entidad;
    }


    public <T> List<T> findStartByName(String name) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        List<T> results = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT t FROM " + tipo.getSimpleName() + " t WHERE t.nombre LIKE :name", tipo);
            query.setParameter("name", name + "%");
            results.addAll(query.getResultList());
        } finally {
            em.close();
        }
        return results;
    }

    public <T> List<T> findByNameContaining(String name) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        List<T> results = new ArrayList<>();
        Query query = em.createQuery("SELECT t FROM " + tipo.getSimpleName() + " t WHERE t.nombre LIKE :name", tipo);
        query.setParameter("name", "%" + name + "%");
        results.addAll(query.getResultList());
        em.close();
        return results;
    }

}
