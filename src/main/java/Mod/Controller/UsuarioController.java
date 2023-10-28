package Mod.Controller;

import Mod.Entity.Usuario;
import Mod.Repository.UsuarioRepository;
import Mod.Service.impl.UsuarioServiceImplements;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/moda/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final UsuarioServiceImplements usuarioServiceImplements;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senha=passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        return usuarioServiceImplements.salvar(usuario);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Integer id){
        usuarioRepository.deleteById(id);
    }
}
