package org.juan.aplication;

import org.juan.infraestructure.repositoryImpl.MascotaRepositoryImpl;
import org.juan.aplication.Service.MascotaService;
import org.juan.aplication.Service.MascotaServiceImpl;
import org.juan.domain.Mascota;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MascotaService mascotaService = new MascotaServiceImpl(new MascotaRepositoryImpl());

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1 -> registrarMascota();
                case 2 -> obtenerMascotaPorId();
                case 3 -> listarTodasLasMascotas();
                case 4 -> actualizarMascota();
                case 5 -> eliminarMascota();
                case 6 -> {
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Gestión de Mascotas ---");
        System.out.println("1. Registrar mascota");
        System.out.println("2. Obtener mascota por ID");
        System.out.println("3. Listar todas las mascotas");
        System.out.println("4. Actualizar mascota");
        System.out.println("5. Eliminar mascota");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarMascota() {
        System.out.print("Ingrese el ID de la mascota: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese el nombre de la mascota: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad de la mascota: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = new Mascota(id, nombre, edad);
        mascotaService.registrarMascota(mascota);
        System.out.println("Mascota registrada exitosamente.");
    }

    private static void obtenerMascotaPorId() {
        System.out.print("Ingrese el ID de la mascota a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            System.out.println("Mascota encontrada: " + mascota);
        } else {
            System.out.println("No se encontró una mascota con el ID proporcionado.");
        }
    }

    private static void listarTodasLasMascotas() {
        List<Mascota> mascotas = mascotaService.obtenerTodasLasMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            System.out.println("Listado de todas las mascotas:");
            mascotas.forEach(System.out::println);
        }
    }

    private static void actualizarMascota() {
        System.out.print("Ingrese el ID de la mascota a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            System.out.print("Ingrese el nuevo nombre de la mascota: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese la nueva edad de la mascota: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            mascota.setNombre(nombre);
            mascota.setEdad(edad);
            mascotaService.modificarMascota(mascota);

            System.out.println("Mascota actualizada exitosamente.");
        } else {
            System.out.println("No se encontró una mascota con el ID proporcionado.");
        }
    }

    private static void eliminarMascota() {
        System.out.print("Ingrese el ID de la mascota a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            mascotaService.eliminarMascota(id);
            System.out.println("Mascota eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una mascota con el ID proporcionado.");
        }
    }
}


