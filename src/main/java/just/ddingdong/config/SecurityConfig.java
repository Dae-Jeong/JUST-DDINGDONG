package just.ddingdong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Spring Security의 보안 정책을 정의하는 구성 클래스
 *
 * API 엔드포인트별 접근 권한을 설정하고, CSRF 보호를 비활성화하며,
 * CORS 정책을 적용합니다. 개발 편의성을 위해 Swagger UI와 헬스체크 엔드포인트는
 * 인증 없이 접근할 수 있도록 허용합니다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    /**
     * HTTP 보안 필터 체인을 구성합니다.
     *
     * Swagger 문서와 헬스체크는 인증 없이 접근 가능하고,
     * 나머지 모든 요청은 인증이 필요하도록 설정합니다.
     * API 서버 특성상 CSRF 보호는 비활성화하고 CORS 정책을 적용합니다.
     *
     * @param http HttpSecurity 구성 객체
     * @return SecurityFilterChain 보안 필터 체인
     * @throws Exception 보안 설정 중 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/api-docs/**",
                    "/v3/api-docs/**",
                    "/actuator/health"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}