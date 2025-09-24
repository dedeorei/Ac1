package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor; // Relacionamento: muitos filmes para um diretor
}
