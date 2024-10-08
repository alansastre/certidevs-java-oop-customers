package com.certidevs;

import java.util.Scanner;

public class Main {

    public static final String MENU = """
            Bienvenidos a la app. Selecciona una opción:
            1 - Imprimir todos los clientes
            2 - Imprimir un cliente por su id
            3 - Crear un nuevo cliente
            4 - Modificar un cliente
            5 - Borrar un cliente
            6 - Borrar todos los clientes
            7 - Salir
            """;
    static CustomerRepository customersRepo = new CustomerRepository();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println(MENU);
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> imprimirClientes();
                case 2 -> imprimirCliente();
                case 3 -> crearCliente();
                case 4 -> actualizarCliente();
                case 5 -> borrarCliente();
                case 6 -> borrarClientes();
                case 7 -> exit = true;
                default -> System.out.println("NO es una opción válida.");
            }
        }
    }

    private static void borrarClientes() {
        customersRepo.removeAll();
    }

    private static void borrarCliente() {
        System.out.println("Ingrese el ID del cliente a modificar: ");
        long id = scanner.nextLong();

        if (customersRepo.removeById(id))
            System.out.println("Cliente borrado con éxito, MUY BIEN.");
    }

    private static void actualizarCliente() {
        System.out.println("Ingrese el ID del cliente a modificar: ");
        long id = scanner.nextLong();
        System.out.println("Ingrese la propiedad a modificar: ");
        scanner.nextLine();
        String atributo = scanner.nextLine();
        Customer cliente = new Customer();
        if (atributo.equals("nombre")) {
            System.out.println("Introduce el nuevo nombre: ");
            cliente.setNombre(scanner.nextLine());
        } else if (atributo.equals("apellido")) {
            System.out.println("Introduce el nuevo apellido: ");
            cliente.setApellido(scanner.nextLine());
        } else if (atributo.equals("edad")) {
            System.out.println("Introduce la nueva edad: ");
            cliente.setEdad(scanner.nextInt());
        }
        customersRepo.update(id, cliente);
    }

    private static void crearCliente() {
        System.out.println("Ingrese el ID: ");
        long id = scanner.nextLong();
        System.out.println("Ingrese el Nombre: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el Apellido: ");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el Email: ");
        String email = scanner.nextLine();
        System.out.println("Ingrese la Edad: ");
        int edad = scanner.nextInt();

        Customer customer = new Customer(id, nombre, apellido, email, edad);
        boolean saved = customersRepo.save(customer);
        if (saved) System.out.println("Cliente guardado OK");
    }

    private static void imprimirCliente() {
        System.out.println("Introduce ID de cliente a buscar");
        long id = scanner.nextLong();
        customersRepo
                .findById(id)
                .ifPresent(customer -> System.out.println(customer));
    }

    private static void imprimirClientes() {
        System.out.println(customersRepo.findAll());
    }
}