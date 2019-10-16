package test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class EncodeTest {
    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        //System.out.println(new BCryptPasswordEncoder().encode("secret"));

        /*String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiLCJ3ZWIiXSwiZXhwIjoxNTcxMTgzMTc1LCJhdXRob3JpdGllcyI6WyJhYWEiXSwianRpIjoiZDBhZDEwNDQtZjI4Ni00N2E2LWE1MzgtYzU4N2ZkMzJkMjNjIiwiY2xpZW50X2lkIjoiY2xpZW50In0.RPC-Y_s6Ex4quFKVXBtqmMN0FoEC17mz7wqIgBkuYec02w-to5_AIAkVHjJrEAnh34JeIUYoazMxfkTtBHV83J5yvXESMFDV2IsORCLfN6zrjbVV2WSPfOUsBTJ7vg_pU4SKU0ZzbcnKcHpbuB69sdVF5GNhjD-Eu_jgJ-d6X4qCJePzvgUFKXHkHzf79TbT_h2k4cfbDr0AUclYruvUpCHaY88GpMt9uEoc0O0z5Q0g_VV9BPUMBMd-PqcqQfJubTZ9GRI3pBEYujC7EVW5JSSqh1KjM8dmoAHUSs4grOgAFLLwFrPF8skH9oYJqMIojBv6GeE3WzJKx1AztXrwSw";
        Resource resource = new ClassPathResource("public.txt");
        String publicKeyStr = inputStream2String(resource.getInputStream());
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Claims claims = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(jwt).getBody();*/

        String key ="test-secret";
        String fairJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFwcCIsIndlYiJdLCJleHAiOjE1NzEyNzQyNjEsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiZjM2MTFiZjYtZGUyZS00MGJkLThmYTctNGVkNmE4YjU3NjVmIiwiY2xpZW50X2lkIjoiY2xpZW50IiwidXNlcm5hbWUiOiJhZG1pbiJ9.6KkjHHybRK9lfZ-T22Ozri7R_qLkocKqfiR1f1iCGl0";
        /*Jwt jwt = JwtHelper.decodeAndVerify(fairJwt, new MacSigner(new SecretKeySpec(key.getBytes(), "HMACSHA256")));
        String claimsStr = jwt.getClaims();*/

        Claims claims = Jwts.parser().setSigningKey(new SecretKeySpec(key.getBytes(), "HMACSHA256")).parseClaimsJws(fairJwt).getBody();
        System.out.println("zzzz");

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
//        String pem = publicKeyBase64
//                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
//                .replaceAll("\\-*END.*KEY\\-*", "");
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyBase64));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        System.out.println(publicKey);
        return publicKey;
    }
}
