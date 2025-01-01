package edu.badpals.estudio.model.cliente;

import edu.badpals.estudio.model.InterfaceDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements InterfaceDAO<Cliente> {

    @Override
    public void create(Cliente cliente) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Cliente cliente) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Cliente cliente) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        cliente = em.merge(cliente);
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Cliente> findById(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return Optional.ofNullable(cliente);
    }

    public Optional<Cliente> findByDni(String dni) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();

        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.dni = :dni");
        query.setParameter("dni", dni);
        try{
            Cliente cliente = (Cliente) query.getSingleResult();
            em.close();
            return Optional.ofNullable(cliente);
        }catch(NoResultException e){
            em.close();
            return Optional.empty();
        }
    }

    public Optional<Cliente> findByTelefono(String telefono) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();

        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono");
        query.setParameter("telefono", telefono);
        try{
            Cliente cliente = (Cliente) query.getSingleResult();
            em.close();
            return Optional.ofNullable(cliente);
        }catch(NoResultException e){
            em.close();
            return Optional.empty();
        }
    }


    public List<Cliente> findByNameContaining(String name) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :name");
        query.setParameter("name", "%"+name+"%");
        clientes.addAll(query.getResultList());
        em.close();
        return clientes;
    }

    public List<Cliente> findAll() {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.getResultList();
        em.close();
        return clientes;
    }
}
