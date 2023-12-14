package co.escuelaing.edu.ieti;
import co.escuelaing.edu.ieti.security.JwtAuthenticationEntryPoint;
import co.escuelaing.edu.ieti.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.servlet.Filter;


@SpringBootApplication
public class App {    
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configura tu proveedor de autenticación (puede ser una base de datos, servicio LDAP, etc.)
        // Por ejemplo, puedes utilizar un proveedor de autenticación basado en memoria para pruebas:
        auth.inMemoryAuthentication()
            .withUser("user1").password("{noop}password1").roles("USER")
            .and()
            .withUser("admin1").password("{noop}admin1").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // En un entorno de producción, usa un PasswordEncoder seguro
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Deshabilita la protección CSRF y permite solicitudes a /authenticate
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Añade un filtro para validar el token en cada solicitud
        httpSecurity.addFilter((Filter) jwtRequestFilter);
    }
}

}

