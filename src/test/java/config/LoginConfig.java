package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:testdata.properties")
public interface LoginConfig extends Config {
    String username();

    String password();

    String token();
}