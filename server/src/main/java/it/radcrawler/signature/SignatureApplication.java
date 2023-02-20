package it.radcrawler.signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SignatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignatureApplication.class, args);
    }
    private final String SECRET_KEY = "MY_SECRET_KEY";

    @PostMapping("/signature")
    public String signaturePost(@RequestHeader("Signature") String signature, @RequestBody String body) throws NoSuchAlgorithmException {
        String expectedSignature = signBody(body);
        if (signature.equals(expectedSignature)) {
            return "the signature is valid";
        } else {
            return "the signature isn't valid";
        }
    }

    private String signBody(String body) throws NoSuchAlgorithmException  {
        MessageDigest digest = MessageDigest.getInstance("MD5");
 
        byte[] signature = digest.digest((body + SECRET_KEY).getBytes());
  
         return Base64.getEncoder().encodeToString(signature);
    }
}
