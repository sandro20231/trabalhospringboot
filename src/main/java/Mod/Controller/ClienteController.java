package Mod.Controller;

import Mod.Entity.Cliente;
import Mod.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * JSON
 * {
 *     "nome":"nome",
 *     "email":"email",
 *     "telefone":123456789
 * }
 */

@RestController
@RequestMapping("/moda/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @GetMapping("/tudo")
    public List<Cliente> tudo(){
        return clienteRepository
                .findAll();
    }
    @GetMapping("/busca/{id}")
    public Cliente porid(@PathVariable Integer id){
        return clienteRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"{cliente.get.inexistente}"));
    }
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return clienteRepository
                .save(cliente);
    }
    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente atualizar(@PathVariable Integer id,
                             @RequestBody @Valid Cliente cliente){
        Cliente c = clienteRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "{cliente.put.inexistente}"));

        if(c!=null){
            c.setNome(cliente.getNome());
            c.setEmail(cliente.getEmail());
            c.setTelefone(cliente.getTelefone());
            return clienteRepository.save(c);
        }

        return null;
    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        clienteRepository
                .deleteById(id);
    }

}
