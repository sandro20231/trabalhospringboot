package Mod.Config;

import Mod.Service.impl.UsuarioServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServiceImplements usuarioServiceImplements;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServiceImplements)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/moda/avaliacao/**")
                .hasAnyRole( "GER", "ADMIN")
                .antMatchers("/moda/categoria/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/moda/produto/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/moda/endereco/**")
                .hasAnyRole("OPER", "GER", "ADMIN")
                .antMatchers("/moda/cliente/**")
                .hasAnyRole("OPER","GER","ADMIN")
                .antMatchers("/moda/pedido/**")
                .hasAnyRole("OPER","GER","ADMIN")
                .antMatchers("/moda/itempedido/**")
                .hasAnyRole("OPER","GER","ADMIN")
                .antMatchers("/moda/usuario/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger/resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }
}
