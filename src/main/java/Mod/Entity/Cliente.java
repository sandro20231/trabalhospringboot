package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="nome")
    @NotEmpty(message="{cliente.nome.obrigatorio}")
    private String nome;
    @Column(name="email")
    @Email(message="{cliente.email.valido}")
    private String email;
    @Column(name="telefone")
    @Pattern(regexp="[0-9]{2}-[0-9]{5}-[0-9]{4}", message="{cliente.telefone.formato}")
    private String telefone;
}
