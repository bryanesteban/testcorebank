package ec.com.corebank.banquito.filters;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ec.com.corebank.banquito.models.entities.Cliente;

import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private SecretKey secretKeySpec = null;
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;

    private String salt = "PlataformaBI_Salt";



    public EncryptionService(@Value("${encryption.key}") String base64Key) {


        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(base64Key.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);   
            this.secretKeySpec =secretKeySpec; 

        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("Error de variables de encriptacion:"+e);
        }

            
        

    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }



     //Encripta clientes 
    public Cliente  encryptCliente (Cliente cliente){

        try {
            cliente.setNombre(encrypt(cliente.getNombre()));
            cliente.setTelefono(encrypt(cliente.getTelefono()));
            cliente.setDireccion(encrypt(cliente.getDireccion()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;

    } 
    //Desencripta clientes
    public Cliente decryptCliente (Cliente cliente) {

        try {
            cliente.setNombre(decrypt(cliente.getNombre()));
            cliente.setTelefono(decrypt(cliente.getTelefono()));
            cliente.setDireccion(decrypt(cliente.getDireccion()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }
}

