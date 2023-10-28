package Mod.Controller;

import Mod.Entity.Endereco;
import Mod.Repository.EnderecoRepository;
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
 *     "rua":"rua",
 *     "numero":"numero",
 *     "complemento":"complemento",
 *     "cidade":"cidade",
 *     "estado":"estado",
 *     "cep":05791050
 * }
 */

@RestController
@RequestMapping("/moda/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    @GetMapping("/tudo")
    public List<Endereco> tudo(){
        return enderecoRepository
                .findAll();
    }
    @GetMapping("/busca/{id}")
    public Endereco porid(@PathVariable Integer id) {
        return enderecoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"{endereco.get.inexistente}"));
    }
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody @Valid Endereco endereco){
        return enderecoRepository
                .save(endereco);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Endereco atualizar(@PathVariable Integer id,
                              @RequestBody @Valid Endereco endereco){
        Endereco e = enderecoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "{endereco.put.inexistente}"));

        if(e!=null){
            e.setCliente(endereco.getCliente());
            e.setRua(endereco.getRua());
            e.setNumero(endereco.getNumero());
            e.setComplemento(endereco.getComplemento());
            e.setCidade(endereco.getCidade());
            e.setEstado(endereco.getEstado());
            e.setCep(endereco.getCep());
            return enderecoRepository.save(e);
        }

        return null;
    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        enderecoRepository
                .deleteById(id);
    }
}
