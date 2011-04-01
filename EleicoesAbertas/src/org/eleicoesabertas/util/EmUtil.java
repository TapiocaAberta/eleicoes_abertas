package org.eleicoesabertas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmUtil {
	static EntityManager em;
	static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("default");
		em = emf.createEntityManager();
	}

	public static synchronized EntityManager getEntityManager() {
		return em;
	}

}
