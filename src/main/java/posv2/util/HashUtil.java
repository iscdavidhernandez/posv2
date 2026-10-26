package posv2.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class HashUtil {

    private HashUtil() {
    }

    public static String sha256(
            String texto) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance(
                            "SHA-256"
                    );

            byte[] hash =
                    digest.digest(
                            texto.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            StringBuilder hex =
                    new StringBuilder();

            for(byte b : hash) {

                hex.append(
                        String.format(
                                "%02x",
                                b
                        )
                );
            }

            return hex.toString();

        } catch(Exception e) {

            throw new RuntimeException(
                    "Error generando hash SHA-256.",
                    e
            );
        }
    }
}