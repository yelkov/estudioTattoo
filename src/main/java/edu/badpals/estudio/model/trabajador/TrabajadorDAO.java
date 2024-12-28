package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrabajadorDAO implements InterfaceDAO<Trabajador>{
    @Override
    public void create(Trabajador trabajador) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(trabajador);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Trabajador trabajador) {

    }

    @Override
    public void delete(Trabajador trabajador) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        trabajador = em.merge(trabajador);
        em.remove(trabajador);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Trabajador> findById(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Trabajador trabajador = em.find(Trabajador.class, id);
        em.close();
        return Optional.ofNullable(trabajador);
    }


    public Optional<Trabajador> findByNif(String nif) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Trabajador t WHERE t.nif = :nif");
        query.setParameter("nif", nif);
        try{
            Trabajador trabajador = (Trabajador) query.getSingleResult();
            em.close();
            return Optional.ofNullable(trabajador);
        }catch (NoResultException e){
            em.close();
            return Optional.empty();
        }
    }


    public List<Trabajador> findAll() {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Trabajador t");
        List<Trabajador> trabajadores = query.getResultList();
        em.close();
        return trabajadores;
    }

    public Optional<Trabajador> findByNss(String nss) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Trabajador t WHERE t.nss = :nss");
        query.setParameter("nss", nss);
        try{
            Trabajador trabajador = (Trabajador) query.getSingleResult();
            em.close();
            return Optional.ofNullable(trabajador);
        }catch (NoResultException e){
            em.close();
            return Optional.empty();
        }
    }

    public List<Trabajador> findStartByName(String name) {
        List<Trabajador> trabajadores = new ArrayList<>();
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Trabajador t WHERE t.nombre LIKE :name", Trabajador.class);
        query.setParameter("name", name + "%");
        trabajadores.addAll(query.getResultList());
        em.close();
        return trabajadores;
    }

    public List<Trabajador> findByNameContaining(String name) {
        List<Trabajador> trabajadores = new ArrayList<>();
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT t FROM Trabajador t WHERE t.nombre LIKE :name", Trabajador.class);
        query.setParameter("name","%"+name+"%");
        trabajadores.addAll(query.getResultList());
        em.close();
        return trabajadores;
    }

}
