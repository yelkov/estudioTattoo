package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class TatuadorDAO implements InterfaceDAO<Tatuador> {
    private final TrabajadorGenericoDAO genericoDAO;

    public TatuadorDAO() {
        this.genericoDAO = new TrabajadorGenericoDAO(Tatuador.class);
    }

    @Override
    public void create(Tatuador tatuador) {
        genericoDAO.create(tatuador);
    }

    @Override
    public void update(Tatuador tatuador) {
        genericoDAO.update(tatuador);
    }

    @Override
    public void delete(Tatuador tatuador) {
        genericoDAO.delete(tatuador);
    }

    @Override
    public Optional<Tatuador> findById(int id) {
        return genericoDAO.findById(id);
    }

    public List<Tatuador> findAll() {
        return genericoDAO.findAll();
    }

    public Optional<Tatuador> findByNif(String nif) {
        return genericoDAO.findByAttribute("nif",nif);
    }

    public Optional<Tatuador> findByNss(String nss) {
        return genericoDAO.findByAttribute("nss", nss);
    }
    public List<Tatuador> findStartByName(String nombre){
        return genericoDAO.findStartByName(nombre);
    }

    public List<Tatuador> findByNameContaining(String nombre){
        return genericoDAO.findByNameContaining(nombre);
    }

    public Optional<Tatuador> findWithDiseños(int id) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        try{
            Query query = em.createQuery("SELECT t FROM Tatuador t LEFT JOIN FETCH t.diseños WHERE t.id = :id");
            query.setParameter("id", id);
            Tatuador tatuador = (Tatuador) query.getSingleResult();
            return Optional.ofNullable(tatuador);
        }catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
}
