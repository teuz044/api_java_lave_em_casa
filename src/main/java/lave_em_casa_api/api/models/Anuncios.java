package lave_em_casa_api.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "anuncios")
public class Anuncios {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String titulo;

   private String descricao;

   private String cidade;

   @Column(name = "valor_lavagem")
   private Double valorLavagem;

   private Boolean disponivel;

   @JsonIgnore
   @ManyToOne
   @JoinColumn(name = "proprietario_id")
   private UsuariosProprietarios proprietario;

   // Construtores, getters e setters

}
