package com.example.utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Autor;
import com.example.entities.Libro;
import com.example.services.AutorService;
import com.example.services.LibroService;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(LibroService libroService, AutorService autorService) {

        return data -> {

            // crear autores
            Autor autor1 = Autor.builder()
                .nombre("Isabel")
                .build();

            Autor autor2 = Autor.builder()
                .nombre("Andrea")
                .build();

            Autor autor3 = Autor.builder()
                .nombre("Coty")
                .build();

            Autor autor4 = Autor.builder()
                .nombre("Celia")
                .build();

            // guardar autores
            autor1 = autorService.save(autor1);
            autor2 = autorService.save(autor2);
            autor3 = autorService.save(autor3);
            autor4 = autorService.save(autor4);

            // crear libros
            Libro libro1 = libroService.save(Libro.builder()
                .titulo("El arte de hacerse bocadillos de lomo")
                .date(LocalDate.of(2023, 8, 10))
                .build());

            Libro libro2 = libroService.save(Libro.builder()
                .titulo("Conviertete en Sailor Moon en 3 sencillos pasos")
                .date(LocalDate.of(2023, 9, 10))
                .build());

            Libro libro3 = libroService.save(Libro.builder()
                .titulo("El asesino #1: EL GLUTEN")
                .date(LocalDate.of(2023, 9, 10))
                .build());

            Libro libro4 = libroService.save(Libro.builder()
                .titulo("Cómo sobrevivir a un bootcamp de Java")
                .date(LocalDate.of(2024, 8, 3))
                .build());

            // relacionar libros con autores
            libro1.getAutores().add(autor1);
            libro2.getAutores().add(autor2);
            libro3.getAutores().add(autor3);

            libro4.getAutores().add(autor4);
            libro4.getAutores().add(autor3);
            libro4.getAutores().add(autor2);
            libro4.getAutores().add(autor1);

            // guardar libros
            libroService.save(libro1);
            libroService.save(libro2);
            libroService.save(libro3);
            libroService.save(libro4);

        };
    }

}
