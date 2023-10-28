package Mod.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="itempedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    @NotNull(message="{itempedido.pedido.obrigatorio}")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name="produto_id")
    @NotNull(message="{itempedido.produto.obrigatorio}")
    private Produto produto;
    @Column(name="quantidade")
    private Integer quantidade;
    @Column(name="preco_unitario")
    private double preco_unitario;
}
