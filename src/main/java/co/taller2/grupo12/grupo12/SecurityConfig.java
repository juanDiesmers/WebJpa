package co.taller2.grupo12.grupo12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import co.taller2.grupo12.grupo12.Filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements ISecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Override
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.ignoringRequestMatchers(ignoreSpecificRequests()));
        return http.build();
    }

    private RequestMatcher ignoreSpecificRequests() {
        return new OrRequestMatcher(
                // new AntPathRequestMatcher("/indicadoressuim/api/autenticacion"),
                // new AntPathRequestMatcher("/indicadoressuim/api/peticion-mes"),
                new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/jwt/security/autenticar/**", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/arrendadores/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/arrendadores/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/arrendadores/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/arrendadores/**\"", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/fincas/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/fincas/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/fincas/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/fincas/**", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/arrendatarios", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/arrendatarios", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/arrendatarios", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/arrendatarios", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/solicitudes/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/solicitudes/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/solicitudes/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/solicitudes/**", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/tipoIngreso/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/tipoIngreso/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/tipoIngreso/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/tipoIngreso/**", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/estadosolicitud/**", HttpMethod.GET.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/estadosolicitud/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/estadosolicitud/**", HttpMethod.PUT.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/estadosolicitud/**", HttpMethod.DELETE.name()),
                new AntPathRequestMatcher("/api/grupo1_6/proyecto1/solicitudes/updateCalificacion/**",
                        HttpMethod.PUT.name()));
    }
}