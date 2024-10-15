package org.juan.interfaces;

import org.juan.domain.Mascota;

import java.util.List;

public interface MascotaRepository {
    void guardar(Mascota mascota);
    Mascota buscarPorId(int id);
    List<Mascota> listarTodas();
    void actualizar(Mascota mascota);
    void eliminar(int id);
}

