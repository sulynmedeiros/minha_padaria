package br.com.minhapadaria.dao;

import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceUtil {

    private static final String PERSISTENCE_FILE = "resources/configuration";
    private static final String PERSISTENCE_UNIT_NAME = "persistence_united";
    private static String nomeDaUnidadeDePersistencia;
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    public PersistenceUtil() {
    }

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PERSISTENCE_FILE);
        nomeDaUnidadeDePersistencia = resourceBundle.getString(PERSISTENCE_UNIT_NAME);
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(nomeDaUnidadeDePersistencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        try {
            if (entityManager == null || !entityManager.isOpen()) {
                entityManager = entityManagerFactory.createEntityManager();
            }
            return entityManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
