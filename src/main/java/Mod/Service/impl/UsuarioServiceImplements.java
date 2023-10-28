package Mod.Service.impl;

import Mod.Entity.Usuario;
import Mod.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioServiceImplements implements UserDetailsService{
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Transactional
        public Usuario salvar(Usuario usuario){
            return usuarioRepository
                    .save(usuario);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = usuarioRepository
                    .findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encotnrado na base de dados"));

            return User
                    .builder()
                    .username(usuario.getLogin())
                    .password(usuario.getSenha())
                    .roles(usuario.getRoles())
                    .build();
        }
}
