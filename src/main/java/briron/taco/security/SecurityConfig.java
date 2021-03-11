package briron.taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* inMemory 방식
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}password1")
                .authorities("ROLE_USER")
                .and()
                .withUser("user2")
                .password("{noop}password2")
                .authorities("ROLE_USER");
         */

        /* jdbc 기반 사용자 스토어
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users " +
                        "where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities " +
                        "where username = ?")
                .passwordEncoder(new NoEncodingPasswordEncoder());
         */

        /* LDAP 기반 사용자 스토어
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .contextSource()
                .root("dc=tacocloud,dc=com")
                .ldif("classpath:users.ldif")
                .and()
                .passwordCompare()
                .passwordEncoder(new NoEncodingPasswordEncoder())
                .passwordAttribute("userPasscode");
         */

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());


    }
}
