package Mod.Controller;

import Mod.Entity.ItemPedido;
import Mod.Repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * JSON
 * {
 *     "pedido":{"id":1},
 *     "produto":{"id":1},
 *     "quantidade":500,
 *     "preco_unitario":5.00
 *
 * }
 *
 */

@RestController
@RequestMapping("/moda/itempedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoController(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }
    @GetMapping("/tudo")
    public List<ItemPedido> tudo(){
        return itemPedidoRepository
                .findAll();
    }
    @GetMapping("/busca/{id}")
    public ItemPedido porid(@PathVariable Integer id){
        return itemPedidoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"{itempedido.get.inexistente}"));
    }
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPedido salvar(@RequestBody @Valid ItemPedido itemPedido){
        return itemPedidoRepository
                .save(itemPedido);
    }
    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ItemPedido atualizar(@PathVariable Integer id,
                                @RequestBody @Valid ItemPedido itemPedido){
        ItemPedido i = itemPedidoRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"{itempedido.put.inexistente}"));

        if(i!=null){
            i.setPedido(itemPedido.getPedido());
            i.setProduto(itemPedido.getProduto());
            i.setQuantidade(itemPedido.getQuantidade());
            i.setPreco_unitario(itemPedido.getPreco_unitario());
            return itemPedidoRepository.save(i);
        }

        return null;
    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        itemPedidoRepository
                .deleteById(id);
    }
}
