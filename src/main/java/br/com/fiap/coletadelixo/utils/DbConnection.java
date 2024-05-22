package br.com.fiap.coletadelixo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbConnection {
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("coleta-de-lixo");

    public static EntityManager getEntityManger() {
        return EMF.createEntityManager();
    }
}
