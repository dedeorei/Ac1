package com.example.demo;

import com.example.demo.model.Diretor;
import com.example.demo.model.Filme;
import com.example.demo.repository.DiretorRepository;
import com.example.demo.repository.FilmeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Ac1Application implements CommandLineRunner {

    private final FilmeRepository filmeRepository;
    private final DiretorRepository diretorRepository;

    public Ac1Application(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ac1Application.class, args);
    }

    @Override
    public void run(String... args) {

        // Criando diretores
        Diretor d1 = new Diretor();
        d1.setNome("Christopher Nolan");

        Diretor d2 = new Diretor();
        d2.setNome("Quentin Tarantino");

        // Criando filmes e associando diretores
        Filme f1 = new Filme(null, "Interestelar", 169, d1);
        Filme f2 = new Filme(null, "A Origem", 148, d1);
        Filme f3 = new Filme(null, "Pulp Fiction", 154, d2);

        d1.setFilmes(Arrays.asList(f1, f2));
        d2.setFilmes(Arrays.asList(f3));

        // Salvando no banco
        diretorRepository.saveAll(Arrays.asList(d1, d2));

        // ---------- Testando métodos FilmeRepository ----------
        System.out.println("\nFilmes com duração > 150:");
        filmeRepository.findByDuracaoGreaterThan(150).forEach(System.out::println);

        System.out.println("\nFilmes com duração <= 150:");
        filmeRepository.findByDuracaoLessThanEqual(150).forEach(System.out::println);

        System.out.println("\nFilmes que começam com 'A':");
        filmeRepository.findByTituloStartingWith("A").forEach(System.out::println);

        // ---------- Testando métodos DiretorRepository ----------
        System.out.println("\nDiretores que começam com 'C':");
        diretorRepository.findByNomeStartingWith("C").forEach(System.out::println);

        System.out.println("\nDiretor com filmes pelo ID:");
        diretorRepository.findByIdWithFilmes(d1.getId())
                .ifPresent(diretor -> {
                    System.out.println(diretor.getNome());
                    diretor.getFilmes().forEach(filme -> System.out.println(" - " + filme.getTitulo()));
                });
    }
}
