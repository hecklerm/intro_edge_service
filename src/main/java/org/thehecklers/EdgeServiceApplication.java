package org.thehecklers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class EdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}
}

@RestController
class QuoteController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/quotorama")
    public Quote getRandomQuote() {
//        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://QUOTE-SERVICE/random", Quote.class);
//        return new Quote(1L, "Test quote.", "Testquoticus");
    }
}

class Quote {
    private Long id;
    private String text;
    private String source;

    Quote() {
    }

    public Quote(Long id, String text, String source) {
        this.id = id;
        this.text = text;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}