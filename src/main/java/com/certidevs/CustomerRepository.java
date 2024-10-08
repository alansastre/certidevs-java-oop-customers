package com.certidevs;

import java.util.ArrayList;
import java.util.Optional;

/*
Repositorio de objetos Customer
Equivale a una tabla Customer en base de datos
No usa base de datos, usa un ArrayList en memoria
Tiene las operaciones CRUD sobre Customer.
 */
public class CustomerRepository {

    // atributos
    private ArrayList<Customer> customers = new ArrayList<>();

    // constructores
    public CustomerRepository() {
        // Datos ficticios opcionales:
        customers.add(new Customer(1L, "c1", "garcia", "c1@gmail.com", 30));
        customers.add(new Customer(2L, "c2", "perez", "c2@gmail.com", 40));
        customers.add(new Customer(3L, "c3", "melgar", "c3@gmail.com", 50));
    }

    public ArrayList<Customer> findAll() {
        // devolver un clon para evitar que
        // lo modifiquen desde afuera.
        return new ArrayList<>(customers);
    }

    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public boolean save(Customer customer) {
        try {
            return customers.add(customer);
        } catch (Exception e) {
            return false;
        }
    }

    // update con RESTRICCIONES
    public void update(Long id, Customer customer) {
        Customer cliente = findById(id).orElse(null);
        if (cliente == null)
            return;

        if (customer.getNombre() != null
                && customer.getNombre().length() < 50)
            cliente.setNombre(customer.getNombre());


        if (customer.getApellido() != null
        && customer.getApellido().length() < 50) {
            cliente.setApellido(customer.getApellido());
        }

        if (customer.getEdad() >= 18 && customer.getEdad() <= 110) {
            cliente.setEdad(customer.getEdad());
        }
    }


    // remove
    public boolean removeById(Long id) {
        return customers
                .removeIf(c -> c.getId().equals(id));
    }

    public void removeAll() {
        customers.clear();
    }

}
