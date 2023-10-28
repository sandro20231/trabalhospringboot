package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    @NotNull(message="{pedido.cliente.obrigatorio}")
    private Cliente cliente;
    @Column(name="data_pedido")
    private LocalDate dataPedido;
    @Column(name="status_pedido")
    private String status_pedido;
    @OneToMany
    List<ItemPedido> itens;

}
