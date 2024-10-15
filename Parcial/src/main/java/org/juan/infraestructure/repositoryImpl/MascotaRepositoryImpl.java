package org.juan.infraestructure.repositoryImpl;

import jakarta.persistence.*;
import org.juan.domain.Mascota;
import org.juan.interfaces.MascotaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MascotaRepositoryImpl implements MascotaRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MascotaPU");

    @Override
    public void guardar(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(mascota);
        tx.commit();
        em.close();
    }

    @Override
    public Mascota buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Mascota mascota = em.find(Mascota.class, id);
        em.close();
        return mascota;
    }

    @Override
    public List<Mascota> listarTodas() {
        EntityManager em = emf.createEntityManager();
        List<Mascota> mascotas = em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
        em.close();
        return mascotas;
    }

    @Override
    public void actualizar(Mascota mascota) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(mascota);
        tx.commit();
        em.close();
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Mascota mascota = em.find(Mascota.class, id);
        if (mascota != null) {
            em.remove(mascota);
        }
        tx.commit();
        em.close();
    }
}
