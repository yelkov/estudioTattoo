package edu.badpals.estudio.model.stock;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class StockDAO {

    public void create(Stock stock) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(stock);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Stock stock) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        stock = em.merge(stock);
        em.remove(stock);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Stock stock) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(stock);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<Stock> findById(int i) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Stock stock = em.find(Stock.class, i);
        em.close();
        return Optional.ofNullable(stock);
    }


    public Optional<Producto> findProducto(int idProducto) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Producto producto = em.find(Producto.class, idProducto);
        em.close();
        return Optional.ofNullable(producto);
    }

    public Optional<Cabina> findCabina(int idCabina) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        Cabina cabina = em.find(Cabina.class, idCabina);
        em.close();
        return Optional.ofNullable(cabina);
    }


}
