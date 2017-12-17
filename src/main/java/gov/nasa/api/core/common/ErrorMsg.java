package gov.nasa.api.core.common;

public class ErrorMsg {

    private ErrorMsg() {
    }

    public static String forNoMatch(final String element) {
        return String.format("'%s' do not match", element);
    }
}