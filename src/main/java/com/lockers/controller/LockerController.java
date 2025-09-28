package com.lockers.controller;

import com.lockers.repository.LockerRepository;
import com.lockers.service.LockerService;

import java.util.Scanner;

public class LockerController {
    public static void main(String[] args) {
        LockerRepository repo = new LockerRepository();
        LockerService service = new LockerService(repo);
        Scanner sc = new Scanner(System.in);

        // Inicializamos 5 lockers por defecto
        for (int i = 1; i <= 5; i++) {
            service.registrarLocker(i);
        }

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE LOCKERS =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Alquilar locker");
            System.out.println("3. Liberar locker");
            System.out.println("4. Ver lockers");
            System.out.println("5. Ver historial de alquileres");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                sc.next(); // limpia entrada inválida
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Documento: ");
                    String doc = sc.nextLine();
                    service.registrarEstudiante(nombre, doc);
                    break;

                case 2:
                    System.out.print("Documento estudiante: ");
                    String docEst = sc.nextLine();
                    System.out.print("ID Locker: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    service.alquilarLocker(docEst, id);
                    break;

                case 3:
                    System.out.print("ID Locker: ");
                    int idL = sc.nextInt();
                    sc.nextLine();
                    service.liberarLocker(idL);
                    break;

                case 4:
                    service.mostrarLockers();
                    break;

                case 5:
                    service.mostrarAlquileres();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
