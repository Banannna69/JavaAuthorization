package jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.Strings;

import java.time.Duration;
import java.util.Date;

public class JwtUtil {
    /**
     * 防止JWT被篡改的密钥
     */
    private final static String SECRET_KEY = "secretK@y";

    private final static Duration EXPIRATION = Duration.ofHours(2);


    /**
     * 生成JWT
     * @param userName 用户名
     * @return JWt
     */
    public static String generate(String userName){
        //过期时间
        Date expirDate = new Date(System.currentTimeMillis() + EXPIRATION.toMillis());
        return Jwts.builder()
                .setSubject(userName) //将 userName 放进 JWT
                .setIssuedAt(new Date()) //设置JWT签发时间
                .setExpiration(expirDate) //设置过期时间
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY) //设置加密算法和密钥
                .compact();
    }

    /**
     * 解析JWT
     * @param token
     * @return
     */
    public static Claims parse(String token){
        //如果是空字符串则返回null
        if(Strings.isEmpty(token)){
            return null;
        }

        //Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败会抛出异常。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            System.out.println("解析失败！");
        }

        return claims;
    }
}
