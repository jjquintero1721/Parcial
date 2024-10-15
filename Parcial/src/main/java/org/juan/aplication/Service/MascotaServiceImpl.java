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
    public void modificarMascota(Mascota mascota) {
        mascotaRepository.actualizar(mascota);
    }

    @Override
    public void eliminarMascota(int id) {
        mascotaRepository.eliminar(id);
    }

    @Override
    public Mascota buscarMascota(int id) {
        return mascotaRepository.obtenerPorId(id);
    }

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepository.obtenerTodas();
    }
}
