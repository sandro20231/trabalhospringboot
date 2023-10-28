package Mod.Controller;

import Mod.Entity.Pedido;
import Mod.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * JSON
 * {
 *     "cliente":{"id":1},
 *     "dataPedido":"aaaa-mm-dd",
 *     "Status":"texto"
 * }
 */
@RestController
@RequestMapping("/moda/pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    @GetMapping("/tudo")
    public List<Pedido> tudo(){
        return pedidoRepository
                .findAll();
    }
    @GetMapping("/busca/{id}")
    public Pedido porid(@PathVariable Integer id){
        return pedidoRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"{pedido.get.inexistente}"));
    }
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvar(@RequestBody @Valid Pedido pedido){
        return pedidoRepository
                .save(pedido);
    }
    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pedido atualizar(@PathVariable Integer id,
                            @RequestBody @Valid Pedido pedido){
        Pedido p = pedidoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"{pedido.put.inexistente}"));

        if(p!=null){
            p.setCliente(pedido.getCliente());
            p.setDataPedido(pedido.getDataPedido());
            p.setStatus_pedido(pedido.getStatus_pedido());
            return pedidoRepository.save(p);
        }

        return null;
    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        pedidoRepository
                .deleteById(id);
    }
}
