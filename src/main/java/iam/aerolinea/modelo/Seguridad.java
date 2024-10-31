/*

package iam.aerolinea.modelo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

    @Configuration
    @EnableWebSecurity
    public class Seguridad extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll() // Permite acceso a login
                    .antMatchers(HttpMethod.GET, "/public/**").permitAll() // Acceso público a rutas públicas
                    .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                    .and()
                    .sessionManagement().disable(); // Desactiva la gestión de sesiones si usas JWT
        }
    }

}

 */
