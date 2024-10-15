package org.juan.infraestructure.repositoryImpl;

import org.juan.domain.Mascota;
import org.juan.interfaces.MascotaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaRepositoryImpl implements MascotaRepository {
    private static final String ARCHIVO = "mascotas.dat";

    @Override
    public void guardar(Mascota mascota) {
        List<Mascota> mascotas = listarTodas();
        mascotas.add(mascota);
        guardarEnArchivo(mascotas);
    }

    @Override
    public Mascota buscarPorId(int id) {
        for (Mascota mascota : listarTodas()) {
            if (mascota.getId() == id) {
                return mascota;
            }
        }
        return null;
    }

    @Override
    public List<Mascota> listarTodas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Mascota>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void actualizar(Mascota mascota) {
        List<Mascota> mascotas = listarTodas();
        for (int i = 0; i < mascotas.size(); i++) {
            if (mascotas.get(i).getId() == mascota.getId()) {
                mascotas.set(i, mascota);
                break;
            }
        }
        guardarEnArchivo(mascotas);
    }

    @Override
    public void eliminar(int id) {
        List<Mascota> mascotas = listarTodas();
        mascotas.removeIf(m -> m.getId() == id);
        guardarEnArchivo(mascotas);
    }

    private void guardarEnArchivo(List<Mascota> mascotas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(mascotas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

