package org.juan.infraestructure.repositoryImpl;

import jakarta.persistence.*;
import org.juan.domain.Mascota;
import org.juan.interfaces.MascotaRepository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MascotaRepositoryImpl implements MascotaRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("mascotasPU");

    @Override
    public void guardar(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void actualizar(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(mascota);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Mascota mascota = em.find(Mascota.class, id);
        if (mascota != null) {
            em.getTransaction().begin();
            em.remove(mascota);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Mascota obtenerPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Mascota mascota = em.find(Mascota.class, id);
        em.close();
        return mascota;
    }

    @Override
    public List<Mascota> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        List<Mascota> mascotas = em.createQuery("from Mascota", Mascota.class).getResultList();
        em.close();
        return mascotas;
    }
}


