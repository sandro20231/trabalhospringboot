package Mod.Controller;

import Mod.Entity.Avaliacoes;
import Mod.Repository.AvaliacaoRepository;
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
 *     "produto":{"id":1},
 *     "nota":1,
 *     "comentario":"comentario",
 *     "dataAvaliacao":"aaaa-mm-dd"
 *     "
 * }
 *
 */

@RestController
@RequestMapping("/moda/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }
    @GetMapping("/tudo")
    public List<Avaliacoes> tudo(){
        return avaliacaoRepository
                .findAll();
    }

    @GetMapping("/busca/{id}")
    public Avaliacoes porid(@PathVariable Integer id){
        return avaliacaoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "{avaliacoes.get.inexistente}"));
    }
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacoes salvar(@RequestBody @Valid Avaliacoes avaliacoes){
        return avaliacaoRepository
                .save(avaliacoes);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Avaliacoes atualizar(@PathVariable Integer id,
                                @RequestBody @Valid Avaliacoes avaliacoes){
        Avaliacoes a = avaliacaoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "{}"));

        if(a!=null){
            a.setCliente(avaliacoes.getCliente());
            a.setNota(avaliacoes.getNota());
            a.setComentario(avaliacoes.getComentario());
            a.setProduto(avaliacoes.getProduto());
            a.setDataAvaliacao(avaliacoes.getDataAvaliacao());
            return avaliacaoRepository.save(a);
        }

        return null;
    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        avaliacaoRepository
                .deleteById(id);
    }

}
