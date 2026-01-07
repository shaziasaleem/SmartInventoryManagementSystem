package inventory.common_security.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /** Base64-encoded secret for HS256 (must be >= 256 bits) */
    private String secret;
    /** Token expiration in milliseconds */
    private long expirationMs = 900_000; // default 15 minutes

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationMs() {
        return expirationMs;
    }

    public void setExpirationMs(long expirationMs) {
        this.expirationMs = expirationMs;
    }
}

