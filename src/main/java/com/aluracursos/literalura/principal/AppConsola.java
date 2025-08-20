package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.GutendexService;
import com.aluracursos.literalura.service.LibroService;
import com.aluracursos.literalura.util.Menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppConsola {
    private final GutendexService gutendexServicio;
    private final LibroService libroServicio;
    private final AutorService autorServicio;

    public AppConsola(GutendexService gutendexServicio, LibroService libroServicio, AutorService autorServicio) {
        this.gutendexServicio = gutendexServicio;
        this.libroServicio = libroServicio;
        this.autorServicio = autorServicio;
    }

    public void iniciarApp(){
        Scanner in = new Scanner(System.in);
        int opcion = -1;
        while (true){
            Menu.mostrarMenu("""
            Bienvenid@, te ofrecemos una variada selección de libros y en muchos idiomas.
            ¿Qué deseas hacer? Ingresa el dígito de la opción elegida
            [1] Buscar libro por título
            [2] Listar libros registrados
            [3] Listar autores registrados
            [4] Listar autores vivos en un determinado año
            [5] Listar libros por idioma
            
            [0] Salir de la aplicación
            """);

            opcion = Menu.seleccionarOpcion(in,6);
            switch (opcion){
                case 1: buscarLibro(in); break;
                case 2: listarLibros(); break;
                case 3: listarAutores(); break;
                case 4: listarAutoresVivos(in); break;
                case 5: listarLibrosPorIdioma(in); break;
                case 0: salir(); break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void buscarLibro(Scanner in) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = in.nextLine();

        Optional<Libro> libro = gutendexServicio.buscarLibroPorTitulo(titulo);

        if (libro.isPresent()) {
            System.out.println("✅ Libro encontrado: ");
            System.out.printf("""
                ----------------------------------------------------------------------------
                    Título ............: %s
                    Autor .............: %s
                    Idioma ............: %s
                    Número de descargas: %d
                ----------------------------------------------------------------------------
                """,
                libro.get().getTitulo(),
                libro.get().getAutor()==null?"Sin Autor":libro.get().getAutor(),
                libro.get().getIdioma(),
                libro.get().getNumeroDescargas());
        } else {
            System.out.println("❌ No se pudo encontrar el libro.");
        }
        Menu.solicitarContinuar();
    }

    private void listarLibros() {
        List<Libro> libros = libroServicio.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("\n=== Libros Registrados ===");
        System.out.println("----------------------------------------------------------------------------");
        libros.forEach(l -> System.out.printf("""
                    Título ............: %s
                    Autor .............: %s
                    Idioma ............: %s
                    Número de descargas: %d
                ----------------------------------------------------------------------------
                """,
                l.getTitulo(),
                l.getAutor()==null?"Sin Autor":l.getAutor(),
                l.getIdioma(),
                l.getNumeroDescargas())
        );
    }

    private void listarAutores() {
        List<Autor> autores = autorServicio.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }
        System.out.println("\n=== Autores Registrados ===");
        System.out.println("----------------------------------------------------------------------------");
        autores.forEach(a -> System.out.printf("""
                    Nombre ..........: %s
                    Año nacimiento ..: %s
                    Año fallecimiento: %s
                ----------------------------------------------------------------------------
                """,
                a.getNombre(),
                (a.getAnioNacimiento() != null ? String.valueOf(a.getAnioNacimiento()): "desconocido"),
                (a.getAnioFallecimiento() != null ? String.valueOf(a.getAnioFallecimiento()): "desconocido"))
        );
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese el año a consultar: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorServicio.listarAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
            return;
        }

        System.out.println("\n=== Autores vivos en " + anio + " ===");
        System.out.println("----------------------------------------------------------------------------");
        autores.forEach(a -> System.out.printf("""
                    Nombre ..........: %s
                    Año nacimiento ..: %s
                ----------------------------------------------------------------------------
                """,
                a.getNombre(),
                (a.getAnioNacimiento() != null ? String.valueOf(a.getAnioNacimiento()): "desconocido"))
        );
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (ej: en, es, fr, pt): ");
        String idioma = scanner.nextLine();

        List<Libro> libros = libroServicio.listarLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma: " + idioma);
            return;
        }

        System.out.println("\n=== Libros en idioma: " + idioma + " ===");
        System.out.println("----------------------------------------------------------------------------");
        libros.forEach(l -> System.out.printf("""
                    Título ............: %s
                    Autor .............: %s
                    Idioma ............: %s
                    Número de descargas: %d
                ----------------------------------------------------------------------------
                """,
                l.getTitulo(),
                l.getAutor()==null?"Sin Autor":l.getAutor(),
                l.getIdioma(),
                l.getNumeroDescargas())
        );
    }

    private void salir(){
        System.out.println("Gracias por usar la aplicación. ¡Hasta pronto!");
        System.exit(0);
    }
}
