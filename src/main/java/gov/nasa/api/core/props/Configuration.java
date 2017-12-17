package gov.nasa.api.core.props;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface Configuration extends Config {

    @Key("host")
    String host();

    @Key("api.key")
    String apiKey();
}