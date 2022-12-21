package pe.edu.upeu.sigrysmuc.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@CrossOrigin(origins= "*")
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

        return http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeRequests()
                // API PUBLICAS
                .antMatchers(HttpMethod.POST, "/api/usuarios/crear-usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/api/cargoJunta/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/nivelOrganizacion/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/tipoOrganizacion/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/zonaUbicacion/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/gerencia/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/gerencia/registrar-resolucion-orgSocial/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/gerencia/enviarNotificacion/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/mesaDePartes/listarSolicitudesMesaDePartes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/mesaDePartes/actualizarEstado/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/solicitud/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/solicitud/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuarios/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subGerencia/buscarSoli/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/mesaDePartes/registrarCodigo-NrExpediente/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/subGerencia/crear-informe-tecnico/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subGerencia/listar-informes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subGerencia/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/vistaPublica/listarRequsitos").permitAll()
                // API PRIVADAS

                //.antMatchers(HttpMethod.GET, "/api/categoria/get-all").access("hasAuthority('ADMIN_ROLE') or hasAuthority('USER_ROLE') ")
                .antMatchers(HttpMethod.GET, "/api/tipoOrganizacions/**").hasAuthority("ADMIN_ROLE")
                .antMatchers(HttpMethod.POST, "/api/solicitud/crearSolicitud").hasAuthority("SUB_GERENCIA_ROLE")
                .antMatchers(HttpMethod.POST, "/api/subGerencia/crear-informe-tecnico").hasAuthority("SUB_GERENCIA_ROLE")
                .antMatchers(HttpMethod.GET, "/api/subGerencia/listar-informes").hasAuthority("USER_ROLE")
                .antMatchers(HttpMethod.POST, "/api/solicitud/crearSolicitudJuntaDirectiva").hasAuthority("USER_ROLE")
                //.antMatchers(HttpMethod.GET, "/api/mesaDePartes/listarSolicitudesMesaDePartes").hasAuthority("MESA_PARTE_ROLE")


                /*
                 * USER_ROLE
                 * ADMIN_ROLE
                 * MESA_PARTE_ROLE
                 * GERENCIA_ROLE
                 * SUB_GERENCIA_ROLE
                 */
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//	@Bean
//	UserDetailsService userDetailService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles()
//				.build());
//		return manager;
//	}

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
