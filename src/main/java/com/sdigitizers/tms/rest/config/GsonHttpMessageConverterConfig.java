
package com.sdigitizers.tms.rest.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

/**
 *
 * @author Shriram Prajapat
 */
@Configuration
public class GsonHttpMessageConverterConfig {

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson());
        return converter;
    }

    private Gson gson() {
        final GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'");
        builder.registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter());
        return builder.create();
    }
}