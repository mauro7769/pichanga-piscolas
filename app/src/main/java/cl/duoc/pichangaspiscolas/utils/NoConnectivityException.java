package cl.duoc.pichangaspiscolas.utils;

import java.io.IOException;



public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No Internet access";
    }
}
