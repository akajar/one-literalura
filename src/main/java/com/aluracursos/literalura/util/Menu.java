package com.aluracursos.literalura.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static void imprimirCabecera(){
        System.out.println("""
            +-------------------------------------------------------------------------------+
            |                     CONVERSOR DE MONEDAS - EXCHANGERATE                       |
            +-------------------------------------------------------------------------------+""");
    }
    public static void mostrarMenu(String menu){
        imprimirCabecera();
        System.out.println(menu);
    }

    public static void limpiarPantalla(){
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola.");
        }
    }

    public static int seleccionarOpcion(Scanner in, int maxOpcion) {
        int opcion = -1;
        while (true) {
            try {
                System.out.print("Ingresa una opción válida: ");
                opcion = in.nextInt();
                in.nextLine();
                if (opcion >= 0 && opcion <= maxOpcion) {
                    return opcion;
                } else {
                    System.out.println("La opción debe estar entre 0 y " + maxOpcion + ".");
                }
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Opción no válida, prueba con una de las opciones mostradas :)");
            }
        }
    }

    public static void solicitarContinuar(){
        System.out.print("Presiona cualquier tecla para continuar ... ");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error al esperar entrada del usuario.");
        }
    }
}
