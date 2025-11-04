package co.edu.local.parcial2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import co.edu.local.parcial2.services.UsuarioService;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**","/js/**","/images/**","/","/login","/registro","/registroAdmin").permitAll()
                .requestMatchers("/rector/**").hasAuthority("RECTOR")
                .requestMatchers("/docente/**").hasAuthority("DOCENTE")
                .requestMatchers("/estudiante/**").hasAuthority("ESTUDIANTE")
                .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
            .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return http.build();
    }
}