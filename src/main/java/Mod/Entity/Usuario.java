package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="login")
    @NotEmpty(message="{usuario.login.obrigatorio}")
    private String login;
    @Column
    @NotEmpty(message="{usuario.senha.obrigatorio}")
    private String senha;
    @Column
    private String roles;

}
