package edu.badpals.estudio.model.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {
    private static EntityManagerFactory emf;

    public static void initialize(String persistenceUnitName) {
        if(emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory no está creado. Ejecuta initialize() primero.");
        }
        return emf;
    }
}
