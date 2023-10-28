package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    @NotNull(message="{endereco.cliente.obrigatorio}")
    private Cliente cliente;
    @Column(name="rua")
    @NotEmpty(message="{endereco.rua.obrigatorio}")
    private String rua;
    @Column(name="numero")
    @NotEmpty(message="{endereco.numero.obrigatorio}")
    private String numero;
    @Column(name="complemento")
    private String complemento;
    @Column(name="cidade")
    @NotEmpty(message="{endereco.cidade.obrigatorio}")
    private String cidade;
    @Column(name="estado")
    @NotEmpty(message="{endereco.estado.obrigatorio}")
    private String estado;
    @Column(name="cep")
    @Pattern(regexp="[0-9]{5}-[0-9]{3}", message="{endereco.cep.formato}")
    @NotNull(message="{endereco.cep.obrigatorio}")
    private String cep;
}
