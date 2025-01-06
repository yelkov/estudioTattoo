package edu.badpals.estudio.model.producto;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class ProductoDAO {

    public void create(Producto producto) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Producto producto) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        producto = em.merge(producto);
        em.remove(producto);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Producto producto) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Producto> findById(int i) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Producto producto = em.find(Producto.class, i);
        em.close();
        return Optional.ofNullable(producto);
    }

    public Optional<Producto> findByName(String nombre) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre");
        query.setParameter("nombre", nombre.toLowerCase());

        try {

            Producto producto = (Producto) query.getSingleResult();

            em.close();
            return Optional.ofNullable(producto);

        } catch (NoResultException e) {
            em.close();
            return Optional.empty();

        }

    }

    public List<Producto> getAll(){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT p FROM Producto p");
        List<Producto> productos = query.getResultList();
        em.close();
        return productos;
    }



}
