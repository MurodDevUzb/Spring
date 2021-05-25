package uz.dev.insta.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String SECRET = "SecretKeyGenJWT";

    public static final String TOKEN_PREFIX = "uzdev ";

    public static final String HEADER = "Authorization";

    public static final String CONTENT_TYPE = "application/json";

    public static final long  EXPIRATION_TIME = 600_000;


}
