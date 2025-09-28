package just.ddingdong.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * CORS(Cross-Origin Resource Sharing) 설정을 관리하는 구성 클래스
 *
 * <p>프론트엔드 애플리케이션이 다른 도메인에서 API를 호출할 수 있도록 허용하는 정책을 정의합니다. 환경별로 다른 Origin을 허용할 수 있도록 설정되어 있습니다.
 */
@Configuration
public class CorsConfig {

  @Value("${app.cors.allowed-origins:http://localhost:3000,http://localhost:5173}")
  private List<String> allowedOrigins;

  /**
   * CORS 정책을 정의하는 CorsConfigurationSource Bean을 생성합니다.
   *
   * <p>개발 환경에서는 localhost의 다양한 포트를 허용하고, 운영 환경에서는 application.properties에서 설정된 도메인만 허용합니다.
   *
   * @return CorsConfigurationSource CORS 설정이 적용된 소스
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(allowedOrigins);
    configuration.setAllowedMethods(
        Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
