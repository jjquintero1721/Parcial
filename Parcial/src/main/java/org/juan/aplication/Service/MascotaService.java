package org.juan.aplication.Service;

import org.juan.domain.Mascota;

import java.util.List;

public interface MascotaService {
    void registrarMascota(Mascota mascota);
    Mascota obtenerMascotaPorId(int id);
    List<Mascota> obtenerTodasLasMascotas();
    void modificarMascota(Mascota mascota);
    void eliminarMascota(int id);
}
