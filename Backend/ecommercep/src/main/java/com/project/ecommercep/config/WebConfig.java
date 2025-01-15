package com.project.ecommercep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Herhangi bir kaynaktan (localhost:3000 gibi) gelen istekleri kabul et
        registry.addMapping("/**")  // Tüm yollar için
                .allowedOrigins("http://localhost:3000")  // İzin verilen kaynak (frontend adresi)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // İzin verilen HTTP metodları
                .allowedHeaders("*")  // İzin verilen başlıklar
                .allowCredentials(true);  // Kimlik doğrulama izinleri
    }
}
