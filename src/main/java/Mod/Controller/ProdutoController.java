package Mod.Controller;

import Mod.Entity.Produto;
import Mod.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * JSON
 * {
 *     "nome":"texto",
 *     "marca":"texto,
 *     "preco":5.00,
 *     "estoque":1000,
 *     "categoria": {"id":1}
 * }
 */

@RestController
@RequestMapping("/moda/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/tudo")
    public List<Produto> tudo(){
        return produtoRepository
                .findAll();
    }

    @GetMapping("/busca/{id}")
    public Produto busca(@PathVariable Integer id){
        return produtoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "{produto.get.inexistente}"));
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody @Valid Produto produto){
        return produtoRepository
                .save(produto);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto atu(@PathVariable Integer id,
                       @RequestBody @Valid Produto produto){
        Produto p = produtoRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"{produto.put.inexistente}"));

        if(p!=null){
            p.setCategoria(produto.getCategoria());
            p.setNome(produto.getNome());
            p.setMarca(produto.getMarca());
            p.setPreco(produto.getPreco());
            p.setEstoque(produto.getEstoque());
            return produtoRepository.save(p);
        }

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        produtoRepository
                .deleteById(id);
    }

}
