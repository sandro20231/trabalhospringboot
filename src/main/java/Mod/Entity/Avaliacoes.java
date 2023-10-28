package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="avaliacao")
public class Avaliacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    @NotNull(message = "{avaliacoes.cliente.obrigatorio}")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="produto_id")
    @NotNull(message = "{avaliacoes.produto.obrigatorio}")
    private Produto produto;
    @Column(name="nota")
    @Min(value = 1, message ="{avaliacoes.nota.minima}")
    @Max(value = 5, message ="{avaliacoes.nota.maxima}")
    private Integer nota;
    @Column(name="comentario")
    @NotEmpty(message="{avalicaoes.comentario.obrigatorio}")
    private String comentario;

    @Column(name="data_avaliacao")
    private LocalDate dataAvaliacao;
}
