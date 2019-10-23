package test;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class EncodeTest {
    public static void main(String[] args) throws Exception {
//        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiLCJ3ZWIiXSwiZXhwIjoxNTcxODgwNjQxLCJhdXRob3JpdGllcyI6WyJhYWEiXSwianRpIjoiOGY5NDEwMjgtYWEwOS00ZjMwLWFkNmYtNWE4MjhlMmY0YzdiIiwiY2xpZW50X2lkIjoiY2xpZW50In0.LrN2UpxvJIK-GqeE_sCohuAxtSf5EvN9hQQDP8QzNCjS5jZrDBOffQyyLUkq3Fjm-hwBdPWPd3UH9xJnsTUFQlT-cJs0m-4AKqoaNXKTwaxPhvcRJYWnS0IJhnxgOoUee-d8sFIu9rqG1NKICgYf9E3u8aLNCrn1jn5SRrpSiu9ZIMUSg1Pd3d5qX_1kn2WnpFjhJDUxuHl7KLSyEOtBanyniKY6b2Nx9F7hZW7TrznfEHz3gaEFG73nXLy99zswBWw1ftdZpE0L6qVq_yQu57PTa9fLWWShwdJN78769TaluLf_ojWCH5GHvhWFeDqLhqJ90dxBv3hBW_wNj49NTQ";
//
//        Resource resource = new ClassPathResource("public.txt");
//        String publicKeyStr = inputStream2String(resource.getInputStream());
//        System.out.println(parseTokenWithSpring(jwt,publicKeyStr));
//
//        Resource resource2 = new ClassPathResource("public2.txt");
//        String publicKeyStr2 = inputStream2String(resource2.getInputStream());
//        System.out.println(parseToken(jwt,publicKeyStr2));

        String key ="test-secret";
        String fairJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiLCJ3ZWIiXSwiZXhwIjoxNTcxODg1OTQ4LCJhdXRob3JpdGllcyI6WyJhYWEiXSwianRpIjoiYjJhNGJhNDYtNTFhZi00MWI2LTllMmUtYjZhZTdjN2I1OWZjIiwiY2xpZW50X2lkIjoiY2xpZW50In0.0oHqsV1ajl5QQkA4thNVCPhKjlqEkA_BpuddFoKhTBo";
        System.out.println(parseTokenFairWithSpring(fairJwt,key));
        System.out.println(parseTokenFair(fairJwt,key));


    }

    /**
     * 此方式需要去除publicKey 的头尾
     * @param token
     * @param key
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String parseTokenFair(String token,String key) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(new SecretKeySpec(key.getBytes(), "HMACSHA256"))
                .parseClaimsJws(token).getBody();
        return JSON.toJSONString(claims);
    }

    /**
     * 此方式需要去除publicKey 的头尾
     * @param token
     * @param key
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String parseTokenFairWithSpring(String token,String key) throws Exception {
        Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner(key));
        //Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner(new SecretKeySpec(key.getBytes(), "HMACSHA256")));
        String claimsStr = jwt.getClaims();
        return claimsStr;

    }

    /**
     * 此方式需要去除publicKey 的头尾
     * @param token
     * @param key
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String parseToken(String token,String key) throws Exception {
        PublicKey publicKey = getPublicKey(key);
        Claims claims = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token).getBody();
        return JSON.toJSONString(claims);
    }

    /**
     * spring方式解析,不需要去除publicKey 的头尾。jwtAccessConverter配置中就是用此种方式。
     * @param token
     * @param key
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static String parseTokenWithSpring(String token,String key) throws Exception {
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(key));
        return jwt.getClaims();
    }

    public static String inputStream2String(InputStream in) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(in, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static PublicKey getPublicKey(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyBase64));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        return publicKey;
    }
}
