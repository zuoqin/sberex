import java.io.File;
 
/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoExec {
    public static void main(String[] args) {
        String key = args[1];
        
        File encryptedFile = new File("document.encrypted");
        
         
        try {

            if( new String(args[0]).equals("e") ){
               File inputFile = new File(args[2]);


	       CryptoUtils.encrypt(key, inputFile, encryptedFile);
               System.out.println(args[0]);
               System.out.println(args[1]);
	    }
            else{
               File decryptedFile = new File("document.decrypted");
               CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
            }
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}