package org.juan.aplication.Service;



import org.juan.domain.Mascota;
import org.juan.interfaces.MascotaRepository;
import java.util.List;

public class MascotaServiceImpl implements MascotaService {
    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public void registrarMascota(Mascota mascota) {
        mascotaRepository.guardar(mascota);
    }

    @Override
    public Mascota obtenerMascotaPorId(int id) {
        return mascotaRepository.buscarPorId(id);
    }

    @Override
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.listarTodas();
    }

    @Override
    public void modificarMascota(Mascota mascota) {
        mascotaRepository.actualizar(mascota);
    }

    @Override
    public void eliminarMascota(int id) {
        mascotaRepository.eliminar(id);
    }
}



