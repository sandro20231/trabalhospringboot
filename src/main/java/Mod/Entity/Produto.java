package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="nome")
    @NotEmpty(message="{produto.nome.obrigatorio}")
    private String nome;
    @Column(name="marca")
    @NotEmpty(message="{produto.marca.obrigatorio}")
    private String marca;
    @Column(name="preco")
    @NotNull(message="{produto.preco.obrigatorio}")
    private double preco;
    @Column(name="estoque")
    @NotNull(message="{produto.estoque.obrigatorio}")
    private Integer estoque;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    @NotNull(message="{produto.categoria.obrigatorio}")
    private Categoria categoria;
}
