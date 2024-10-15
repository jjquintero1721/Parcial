package org.juan.aplication;



import org.juan.domain.Mascota;
import org.juan.aplication.Service.MascotaService;
import org.juan.aplication.Service.MascotaServiceImpl;
import org.juan.infraestructure.repositoryImpl.MascotaRepositoryImpl;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MascotaService mascotaService = new MascotaServiceImpl(new MascotaRepositoryImpl());

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> registrarMascota();
                case 2 -> obtenerMascotaPorId();
                case 3 -> listarTodasLasMascotas();
                case 4 -> actualizarMascota();
                case 5 -> eliminarMascota();
                case 6 -> {
                    System.out.println("Saliendo...");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Registrar mascota");
        System.out.println("2. Buscar mascota por ID");
        System.out.println("3. Listar mascotas");
        System.out.println("4. Actualizar mascota");
        System.out.println("5. Eliminar mascota");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void registrarMascota() {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();

        Mascota mascota = new Mascota(nombre, edad);
        mascotaService.registrarMascota(mascota);
        System.out.println("Mascota registrada.");
    }

    private static void obtenerMascotaPorId() {
        System.out.print("Ingrese el ID: ");
        int id = scanner.nextInt();

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            System.out.println(mascota);
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }

    private static void listarTodasLasMascotas() {
        mascotaService.obtenerTodasLasMascotas().forEach(System.out::println);
    }

    private static void actualizarMascota() {
        System.out.print("Ingrese el ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva edad: ");
        int edad = scanner.nextInt();

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            mascota.setNombre(nombre);
            mascota.setEdad(edad);
            mascotaService.modificarMascota(mascota);
            System.out.println("Mascota actualizada.");
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }

    private static void eliminarMascota() {
        System.out.print("Ingrese el ID: ");
        int id = scanner.nextInt();

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            mascotaService.eliminarMascota(id);
            System.out.println("Mascota eliminada.");
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }
}
