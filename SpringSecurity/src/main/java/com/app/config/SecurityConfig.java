package com.app.config;

import com.app.service.UserDetailServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity //Habilita la seguridad web
@EnableMethodSecurity //Permite usar anotaciones propias de SB
public class SecurityConfig {

    @Bean //Todos los filtos pasan por httpSecurity y lo modifican
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults()) //Porque solo está con user y contraseña, cuando es con tokens es diferente
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //No crea objeto de sesión (las que caducan cada x tiempo)
                //El poder de sesión del usuario logeado depende de la expiración del token
                /*.authorizeHttpRequests(http ->{

                        //Configura los endpoints públicos
                        http.requestMatchers(HttpMethod.GET, "/auth/get").permitAll();

                        //Configura los endpoints privados
                        http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyRole("ADMIN", "DEVELOPER");
                        http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyRole( "DEVELOPER", "INVITED");


                    //Configura los endpoints no especificados, rechaza el acceso
                        http.anyRequest().denyAll();
                       //O también se puede poner así: http.anyRequest().authenticated(); en este caso, lo deja pasar si está autenticado

                })*/
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }




    /*

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("Mel")
                .password("1227")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());

        userDetailsList.add(User.withUsername("Jula")
                .password("1234")
                .roles("User")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
       //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(); //Esto encripta las contraseñas
    }

}
