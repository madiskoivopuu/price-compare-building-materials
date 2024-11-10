package priceCompare.backend.utils;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.core.util.BufferRecyclers;

import java.nio.charset.StandardCharsets;

public class UserInputEscaper {
    /**
     * Escapes characters that interfere with JSON
     * @param input Untrusted user input
     * @return Escaped user input that can be used in JSON
     */
    public static String escapeForJson(String input) {
        JsonStringEncoder encoder = BufferRecyclers.getJsonStringEncoder();
        return new String(encoder.quoteAsUTF8(input), StandardCharsets.UTF_8);
    }
}
