package edu.badpals.estudio.model.cita;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.*;

public class CitaDAO {

    public void create(Cita cita) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Set<Cliente> clientesMerged = new HashSet<>();
        for(Cliente cliente : cita.getClientes()){
            Cliente clienteMerged = em.merge(cliente);
            clientesMerged.add(clienteMerged);
        }
        cita.setClientes(clientesMerged);

        em.persist(cita);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Cita cita) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Cita citaMerged = em.find(Cita.class, cita.getId());

        if (citaMerged != null) {
            citaMerged.getClientes().clear();
            em.remove(citaMerged);
        }

        em.getTransaction().commit();
        em.close();
    }

    public void reservarHuecos(Cita cita, Set<Hueco> huecos){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Cita citaMerged = em.merge(cita);
        for (Hueco hueco : huecos){
            Hueco huecoMerged = em.merge(hueco);
            huecoMerged.setCita(citaMerged);
        }
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

    public List<Cita> findByTatuador(int idTatuador) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.tatuador.id = :idTatuador");
        query.setParameter("idTatuador", idTatuador);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public List<Cita> findByAnillador(int idAnillador) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.anillador.id = :idAnillador");
        query.setParameter("idAnillador", idAnillador);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public List<Cita> findByCabina(int idCabina) {

        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.cabina.id = :idCabina");
        query.setParameter("idCabina", idCabina);
        try {

            List<Cita> citas = query.getResultList();
            return citas;

        } finally {
            em.close();
        }
    }

    public Optional<Cita> findByDescripcionCabina(String descripcion, int idCabina) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.descripcion = :descripcion AND c.cabina.id = :idCabina");
        query.setParameter("descripcion", descripcion);
        query.setParameter("idCabina", idCabina);
        try{
            Cita cita = (Cita) query.getSingleResult();
            em.close();
            return Optional.ofNullable(cita);
        }catch (NoResultException e){
            em.close();
            return Optional.empty();
        }
    }

    public List<Cita> findByEstado(Estado estado) {
        List<Cita> citas = new ArrayList<>();
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.estado = :estado");
        query.setParameter("estado", estado);
        citas.addAll(query.getResultList());
        em.close();
        return citas;
    }

    public List<Cita> findByDescripcionContaining(String descripcion) {
        List<Cita> citas = new ArrayList<>();
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE c.descripcion LIKE :descripcion");
        query.setParameter("descripcion", "%"+descripcion+"%");
        citas.addAll(query.getResultList());
        em.close();
        return citas;
    }

    public List<Cita> findByCliente(Cliente cliente) {
        List<Cita> citas = new ArrayList<>();
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cita c WHERE :cliente MEMBER OF c.clientes");
        query.setParameter("cliente", cliente);
        citas.addAll(query.getResultList());
        em.close();
        return citas;
    }

}
