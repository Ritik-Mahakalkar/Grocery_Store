package com.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    // This method will configure the Stripe API key
    @Bean
    public void configureStripe() {
        // Use the API key from the application properties file
        Stripe.apiKey = stripeApiKey;
    }
}
