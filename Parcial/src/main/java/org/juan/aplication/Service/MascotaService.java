package org.juan.aplication.Service;

import org.juan.domain.Mascota;


import java.util.List;

public interface MascotaService {
    void registrarMascota(Mascota mascota);
    void modificarMascota(Mascota mascota);
    void eliminarMascota(int id);
    Mascota buscarMascota(int id);
    List<Mascota> listarMascotas();
}



