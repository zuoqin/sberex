import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.SecretKey;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */
public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
 
    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }
 
    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }
 
    private static void doCrypto(int cipherMode, String key, File inputFile,
            File outputFile) throws CryptoException {
        try {

	    //System.out.println(inputFile);
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	    byte[] salt = new byte[] {1, 2, 3, 4, 5};
            char[] password = new char[] {'1', '2', '3', '4', '5'};
            /* Derive the key, given password and salt. */

            //SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");//(key);
            //KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
            //SecretKey tmp = factory.generateSecret(spec);
            //SecretKey secretKey= new SecretKeySpec(tmp.getEncoded(), ALGORITHM);


            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             

            //System.out.println(inputFile);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
             
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
             
            inputStream.close();
            outputStream.close();
             
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex 
           //| java.security.spec.InvalidKeySpecException ex
            ) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}