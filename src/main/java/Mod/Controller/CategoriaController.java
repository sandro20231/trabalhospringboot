package Mod.Controller;

import Mod.Entity.Categoria;
import Mod.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * JSON
 * {
 *     "nome":"exemplonome"
 * }
 */

@RestController
@RequestMapping("/moda/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/tudo")
    public List<Categoria> tudo(){
        return categoriaRepository
                .findAll();
    }

    @GetMapping("/busca/{id}")
    public Categoria porid(@PathVariable Integer id){
        return categoriaRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "{categoria.get.inexistente}"));
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria salvar(@RequestBody @Valid Categoria categoria){
        return categoriaRepository
                .save(categoria);
    }
    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Categoria atualizar(@PathVariable Integer id,
                               @RequestBody @Valid Categoria categoria){
        Categoria c = categoriaRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "{categoria.put.inexistente}"));

        if(c!=null){
            c.setNome(categoria.getNome());
            return  categoriaRepository.save(c);
        }

        return null;

    }
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        categoriaRepository
                .deleteById(id);
    }
}
