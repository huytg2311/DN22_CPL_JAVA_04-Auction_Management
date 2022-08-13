package java4.auction_management.config;

import java4.auction_management.service.impl.AccountService;
import java4.auction_management.service.impl.CustomOAuth2User;
import java4.auction_management.service.impl.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    AccountService accountService;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        // Sét đặt dịch vụ để tìm kiếm User trong Database.
//        // Và sét đặt PasswordEncoder.
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

//       admin
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/edit/{id}").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/edit").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/categories").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/detail/{id}").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/editCategory/{id}").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/editCategory").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/createCategory").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/changeEnable/{username}").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/waitingAuctions").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/editStatusAuction/{auctionID}").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/editAuctionStatus").access("hasRole('ROLE_ADMIN')");

        //  auction
        http.authorizeRequests().antMatchers("/auctions/my-auctions").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/auctions/detail-auction/{id}").permitAll();
        http.authorizeRequests().antMatchers("/auctions/get-winner-cartDetail/{auctionId}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/auctions/auction-result/{auctionId}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/auctions/auctions/new-bid-alert/{auctionId}").permitAll();

        //  bid
        http.authorizeRequests().antMatchers("/bid/cart/{username}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/bid/createBid").access("hasRole('ROLE_USER')");

        //  cart
        http.authorizeRequests().antMatchers("/cart").access("hasRole('ROLE_USER')");

        //  e-wallet
        http.authorizeRequests().antMatchers("/myEwallet").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/deposit").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/updateDeposit").access("hasRole('ROLE_USER')");

        //  forgot-password
        http.authorizeRequests().antMatchers("/forgot_password").permitAll();
        http.authorizeRequests().antMatchers("/reset_password").permitAll();

        //  home
        http.authorizeRequests().antMatchers("/view-profile/{username}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/edit-profile").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/changePassword/{username}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/savePassword").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/changeAvatar").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/savePassword").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/savePassword").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/403", "/register", "/about", "/guide").permitAll();

        //  login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/signUp", "/success", "/logoutSuccessful").permitAll();

        //  payment
        http.authorizeRequests().antMatchers("/paynow").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/pay").access("hasRole('ROLE_USER')");

        //  product
        http.authorizeRequests().antMatchers("/products").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/products/create").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/products/auction").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/products/productDetail").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/products/edit/{id}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/products/edit").access("hasRole('ROLE_USER')");

        // page user
        http.authorizeRequests().antMatchers("/user/edit").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/edit/{id}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/categories").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/detail/{id}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/detailAuction/{productId}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/wallet/{username}").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/user/bidding/{username}").access("hasRole('ROLE_USER')");



        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/welcome")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");



        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h


//        http.authorizeRequests()
//                .antMatchers( "/oauth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .oauth2Login()
//                .loginPage("/login1")
//                .userInfoEndpoint()
//                .userService(oauthUserService);

        http.oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

                        accountService.processOAuthPostLogin(oauthUser.getEmail());

                        response.sendRedirect("/index");
                    }
                });

    }



    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}