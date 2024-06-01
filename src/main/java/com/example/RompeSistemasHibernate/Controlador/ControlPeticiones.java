package com.example.RompeSistemasHibernate.Controlador;

import java.time.LocalDate;
import java.util.Scanner;

public class ControlPeticiones {
    private Scanner scanner;

    public ControlPeticiones() {
        this.scanner = new Scanner(System.in);
    }

    public ControlPeticiones(ControlPeticiones cPeticiones) {
        this.scanner = cPeticiones.getScanner();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String pedirString(String peticion) {
        System.out.print(peticion);
        String resp = scanner.nextLine().trim();
        return resp.isEmpty() ? " " : resp;
    }

    public int pedirEntero(String peticion, int min, int max) {
        int i = Integer.MIN_VALUE;
        while (i < min || i > max) {
            System.out.print(peticion);
            try {
                i = Integer.parseInt(scanner.nextLine().trim());
                if (i < min || i > max) {
                    System.out.println("El valor no es válido. Debe ser un número entre " + min + " y " + max + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("** ERROR ** -> Solo puedes insertar números.\n");
            }
        }
        return i;
    }

    public LocalDate pedirFecha(String peticion, LocalDate minFecha, LocalDate maxFecha) {
        int dia, mes, ano;
        boolean fechaValida = false;
        LocalDate fecha = null;

        System.out.println(peticion);
        while (!fechaValida) {
            ano = pedirEntero("Introduzca el año (aaaa): ", minFecha.getYear(), maxFecha.getYear());
            mes = pedirEntero("Introduzca el mes (mm): ", 1, 12);
            dia = pedirEntero("Introduzca el día (dd): ", 1, 31);

            try {
                fecha = LocalDate.of(ano, mes, dia);
                if (fecha.isBefore(minFecha) || fecha.isAfter(maxFecha)) {
                    System.out.println("La fecha no puede ser anterior a " + minFecha + " ni posterior a " + maxFecha + ".");
                } else {
                    fechaValida = true;
                }
            } catch (Exception e) {
                System.out.println("Fecha inválida. Por favor, introduzca una fecha válida.");
            }
        }
        return fecha;
    }

    public float pedirFloat(String peticion, float min, float max) {
        float f = Float.MIN_VALUE;
        while (f < min || f > max) {
            System.out.print(peticion);
            try {
                f = Float.parseFloat(scanner.nextLine().trim());
                if (f < min || f > max) {
                    System.out.println("El valor no puede ser menor que " + min + " ni mayor que " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("** ERROR ** -> Solo puedes insertar números.\n");
            }
        }
        return f;
    }

    public String pedirNIF() {
        String nif = "";
        boolean resultado = false;

        System.out.print("Introduce el NIF del socio: ");
        while (!resultado) {
            nif = scanner.nextLine().trim();
            if (nif.length() == 9 && nif.substring(0, 8).chars().allMatch(Character::isDigit) && Character.isLetter(nif.charAt(8))) {
                resultado = true;
            } else {
                System.out.println("El NIF debe tener 8 dígitos y una letra. Inténtalo de nuevo.");
            }
        }
        return nif;
    }

    public boolean pedirConfirmacion(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                return true;
            } else if (respuesta.equals("N")) {
                return false;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduzca 'S' para sí o 'N' para no.");
            }
        }
    }
}
