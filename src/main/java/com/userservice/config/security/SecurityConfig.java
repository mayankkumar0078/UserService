package com.userservice.config.security;

/**
 * @author Mayank
 * 
 * This class is used for configuring Security related configuration.
 * 
 */
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.userservice.dao.DatabaseUserAcountRepository;
import com.userservice.dao.UserAccountRepository;
import com.userservice.repository.policy.PasswordPolicyFactory;
import com.userservice.repository.policy.PasswordPolicyRepository;
import com.userservice.repository.policy.PasswordPolicyRepositoryFileImpl;
import com.userservice.service.StatelessAuthenticationFilter;
import com.userservice.service.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired(required = true)
	private TokenAuthenticationService tokenAuthenticationService = null;

	public SecurityConfig() {
		super(true);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {/*
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/userModule/Users/**", "/aa/**", "/rp/**","/userModule/BookShelves/**","/user/shelf/**","/user/notes/**","/http://openlibrary.org/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(
						new StatelessAuthenticationFilter(
								tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
		
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry security=http
				.csrf()
				.disable().authorizeRequests().antMatchers("/").authenticated().antMatchers("/userModule/Users/**").permitAll();
		//security.anyRequest().authenticated();
		security.and().addFilterBefore(new StatelessAuthenticationFilter(
								tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
		
	*/
		
		http.csrf().disable();
		http.exceptionHandling()
				.and()
				.anonymous()
				.and()
				.servletApi()
				.and()
				.headers()
				.cacheControl()
				.and()
				.authorizeRequests()
				.antMatchers("/userModule/Users/**", "/aa/**", "/rp/**","/userModule/BookShelves/**","/user/shelf/**","/user/notes/**","/http://openlibrary.org/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(
						new StatelessAuthenticationFilter(
								tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				passwordEncoder());
	}

	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserAccountRepository userDetailsService() {
		return new DatabaseUserAcountRepository();
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return new TokenAuthenticationService("tooManySecrets",
				userDetailsService());
	}
	
	/*@Bean
    public FilterSecurityInterceptor filterSecurityInterceptor()
            throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor
                .setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor
        .setAccessDecisionManager(accessDecisionManager());
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        List<ConfigAttribute> configs = new ArrayList<ConfigAttribute>();
        configs.add(new org.springframework.security.access.SecurityConfig("isAuthenticated()"));
        requestMap.put(new AntPathRequestMatcher("/**"), configs);
        FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource = new ExpressionBasedFilterInvocationSecurityMetadataSource(
                requestMap, new DefaultWebSecurityExpressionHandler());
        filterSecurityInterceptor
                .setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
        filterSecurityInterceptor.afterPropertiesSet();

        return filterSecurityInterceptor;
    }*/
	
	/*public AffirmativeBased accessDecisionManager() throws Exception {
        List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        voters.add(new WebExpressionVoter());
        voters.add(new RoleVoter());
        AffirmativeBased affirmativeBased = new AffirmativeBased(voters);
        affirmativeBased.setAllowIfAllAbstainDecisions(false);
        affirmativeBased.afterPropertiesSet();

        return affirmativeBased;
    }*/

	/*
	 * @Bean public TokenHandler tokenHandler() { return new
	 * TokenHandler("tooManySecrets", userDetailsService()); }
	 */

	/*
	 * @Bean public UserSignUpService userSignUpService(){ return new
	 * UserSignUpService(); }
	 */

	@Bean
	public PasswordPolicyRepositoryFileImpl getPasswordPolicyRepositoryFileImpl() {
		return new PasswordPolicyRepositoryFileImpl();
	}

	@Bean
	public Map<String, PasswordPolicyRepository> getPasswordPolicyFactoryMap() {
		Map<String, PasswordPolicyRepository> map = new HashMap<String, PasswordPolicyRepository>();
		map.put("FilePasswordPolicy", getPasswordPolicyRepositoryFileImpl());
		return map;
	}

	@Bean(name = "passwordPolicyFactory")
	public PasswordPolicyFactory getPasswordPolicyFactory() {
		PasswordPolicyFactory passwordPolicyFactory = new PasswordPolicyFactory();
		passwordPolicyFactory
				.setPasswordPoliciesMap(getPasswordPolicyFactoryMap());
		return passwordPolicyFactory;
	}
}