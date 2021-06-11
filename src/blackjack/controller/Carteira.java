/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

//Modulos de criptografia
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class Carteira {
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	//Cria uma chave secreta utilizando o padrão AES/SHA-1
	private static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
	
	//Encripta a mensagem usando uma chave
	private static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
           return null;
        }

    }
	
	//Utilizado para decrptar uma mensagem
	private static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            //System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
	
	//Metodos especificos
	String gerarCarteira(int fichas , String jogador) {
		
		//Mensagem a ser criptografada
		String msg = "";
		
		msg = msg + converterHex(fichas);
		msg = msg + "s0" + jogador; // padding
		
	
		return encrypt(msg,jogador);
	}
	
	
	 int validarCarteira(String endereco,String jogador){
		String msg = decrypt(endereco,jogador);
		
		//Se nao for possivel validar retorna -1
		if(!validarNome(msg,jogador)) {
			return -1;
		}
		
		
		msg = msg.substring(2,msg.indexOf("s0"));
		
		int fichasJogador = -1;

		try {
			fichasJogador = converterInt(msg);
		
			
		}catch(Exception e){
			return -1;
		
		}
		
		return fichasJogador;
	}
	
	
	//Metodos auxiliares
	private String converterHex(int val) {
		String hex = Integer.toHexString(val);
		String resultado = "0x";
		
		for(int i = 0; i < 4-hex.length();i++) {
			resultado = resultado + "0";
		}
		
		
		return resultado+hex;
	}
	
	//Validaão é feita conferindo a existencia de certos "marcos" na string
	private boolean validarNome(String msg,String Jogador) {
		boolean[] testes = {false,false,false};
		if(msg==null) {
			return false;
		}
		if(msg.indexOf(Jogador) != -1)
			testes[0] = true;
		if(msg.indexOf("s0") != -1)
			testes[1] = true;
		if(msg.indexOf("0x") == 0)
			testes[2] = true;
		
		if(testes[0]&&testes[1]&&testes[2])
			return true;
		
		return false;
	}
	
	private int converterInt(String hex) {
		return Integer.valueOf(hex, 16).intValue();
	}
 
}
