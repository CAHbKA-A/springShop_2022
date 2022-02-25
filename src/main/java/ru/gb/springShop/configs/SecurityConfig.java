package ru.gb.springShop.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtRequestFilter jwtRequestFilter;

    //настройка правил
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //выключем scrf токены. не неужны вообще
                .csrf().disable()
                .cors().disable()
                //даем доступ ко всем точкам
                .authorizeRequests()
                //доступ к эндпоинту только для авторизованных
                .antMatchers("/auth_check").authenticated()  //доступ к эндпоинту проверки авторизации только для авторизованных
                .antMatchers("/api/v1/orders").authenticated()  //доступ к эндпоинту проверки авторизации только для авторизованных
                 .anyRequest().permitAll()
                .and()
                //не используем сессию дла rest
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //сохроянем досуп к консоли Н2
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                //если ломится неавторизованный - то ошибка 401
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
