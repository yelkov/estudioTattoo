package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.InterfaceDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HuecoDAO{

    public void update(Hueco hueco) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(hueco);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Hueco> findById(HuecoId id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Hueco hueco = em.find(Hueco.class, id);
        em.close();
        return Optional.ofNullable(hueco);
    }

    public List<Hueco> findAll(){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT h FROM Hueco h");
        List<Hueco> huecos = query.getResultList();
        em.close();
        return huecos;
    }

    public List<Hueco> findAllFromCabina(Cabina cabina){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT h FROM Hueco h WHERE h.cabina =:cabina ");
        query.setParameter("cabina", cabina);
        List<Hueco> huecos = query.getResultList();
        em.close();
        return huecos;
    }

    public List<Hueco> findHuecosLibres(LocalDate fechaInicio, LocalDate fechaFin){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT h FROM Hueco h WHERE h.cita IS NULL AND  h.dia BETWEEN :fechaInicio AND :fechaFin");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<Hueco> huecos = query.getResultList();
        em.close();
        return huecos;
    }

    public List<Hueco> findHuecosLibresCabina(Cabina cabina, LocalDate fechaInicio, LocalDate fechaFin){
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT h FROM Hueco h WHERE h.cabina = :cabina AND h.cita IS NULL AND  h.dia BETWEEN :fechaInicio AND :fechaFin");
        query.setParameter("cabina", cabina);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<Hueco> huecos = query.getResultList();
        em.close();
        return huecos;
    }

}
