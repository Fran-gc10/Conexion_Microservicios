package com.formaciondbi.springboot.app.gateway.filters.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

    Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

    public EjemploGatewayFilterFactory() {
        super(Configuracion.class);
    }

    @Override
    public GatewayFilter apply(Configuracion config) {
        return (exchange, chain) -> {

            logger.info("Ejecutando pre gateway filter factory: " + config.mensaje);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.cookieValor).ifPresent(value -> {
                   exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, value).build());
                });

                logger.info("Ejecutando post gateway filter factory " + config.mensaje);
            }));
        };
    }


    //De esta manera podemos definir los atrubutos en el ".YML" de igual manera que en "StripPrefix"
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("mensaje", "cookieNombre", "cookieValor");
    }

    public static class Configuracion {

        //Los valores de los atributos se definen en el  ".YML"

        private String mensaje;
        private String cookieNombre;
        private String cookieValor;


        public String getMensaje() {
            return mensaje;
        }


        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getCookieValor() {
            return cookieValor;
        }

        public void setCookieValor(String cookieValor) {
            this.cookieValor = cookieValor;
        }

        public String getCookieNombre() {
            return cookieNombre;
        }

        public void setCookieNombre(String cookieNombre) {
            this.cookieNombre = cookieNombre;
        }
    }
}
