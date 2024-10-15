package org.juan.aplication;



import org.juan.domain.Mascota;
import org.juan.aplication.Service.MascotaService;
import org.juan.aplication.Service.MascotaServiceImpl;
import org.juan.infraestructure.repositoryImpl.MascotaRepositoryImpl;
import org.juan.interfaces.MascotaRepository;


import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        MascotaService mascotaService = new MascotaServiceImpl(mascotaRepository);
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar nueva mascota");
            System.out.println("2. Modificar mascota");
            System.out.println("3. Eliminar mascota");
            System.out.println("4. Buscar mascota por ID");
            System.out.println("5. Listar todas las mascotas");
            System.out.println("6. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de la mascota:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el tipo de la mascota:");
                    String tipo = scanner.nextLine();
                    Mascota nuevaMascota = new Mascota(nombre, tipo);
                    mascotaService.registrarMascota(nuevaMascota);
                    break;
                case 2:
                    System.out.println("Ingrese el ID de la mascota a modificar:");
                    int idModificar = scanner.nextInt();
                    scanner.nextLine();
                    Mascota mascota = mascotaService.buscarMascota(idModificar);
                    if (mascota != null) {
                        System.out.println("Ingrese el nuevo nombre:");
                        mascota.setNombre(scanner.nextLine());
                        System.out.println("Ingrese el nuevo tipo:");
                        mascota.setTipo(scanner.nextLine());
                        mascotaService.modificarMascota(mascota);
                    } else {
                        System.out.println("Mascota no encontrada.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el ID de la mascota a eliminar:");
                    int idEliminar = scanner.nextInt();
                    mascotaService.eliminarMascota(idEliminar);
                    break;
                case 4:
                    System.out.println("Ingrese el ID de la mascota:");
                    int idBuscar = scanner.nextInt();
                    Mascota buscada = mascotaService.buscarMascota(idBuscar);
                    System.out.println(buscada != null ? buscada : "Mascota no encontrada.");
                    break;
                case 5:
                    List<Mascota> mascotas = mascotaService.listarMascotas();
                    mascotas.forEach(System.out::println);
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}

