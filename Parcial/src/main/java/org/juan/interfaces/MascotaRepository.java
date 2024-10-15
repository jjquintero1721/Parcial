package org.juan.interfaces;


import org.juan.domain.Mascota;
import java.util.List;

public interface MascotaRepository {
    void guardar(Mascota mascota);
    void actualizar(Mascota mascota);
    void eliminar(int id);
    Mascota obtenerPorId(int id);
    List<Mascota> obtenerTodas();
}

