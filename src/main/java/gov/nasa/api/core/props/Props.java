package gov.nasa.api.core.props;

import org.aeonbits.owner.ConfigFactory;

public class Props {

    public static final Configuration CONFIG = ConfigFactory.create(Configuration.class, System.getProperties());

    private Props() {
    }
}