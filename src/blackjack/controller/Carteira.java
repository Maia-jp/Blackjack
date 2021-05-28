package blackjack.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class Carteira {
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
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
            //System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
	
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
	public String gerarCarteira(Map<String, Integer> fichas , String jogador) {
		//Padrão de string encriptada -> |Fichas de 1,5,10,20,50,100 no formato 0x0000 unsigned||nome do jogador|
		
		//Mensagem a ser criptografada
		String msg = "";
		
		msg = msg + converterHex(fichas.get("1"));
		msg = msg + converterHex(fichas.get("5"));
		msg = msg + converterHex(fichas.get("10"));
		msg = msg + converterHex(fichas.get("20"));
		msg = msg + converterHex(fichas.get("50"));
		msg = msg + converterHex(fichas.get("100"));
		msg = msg + "s0" + jogador;
		
		
		return encrypt(msg,jogador);
	}
	
	public Map<String, Integer> validarCarteira(String endereço,String jogador){
		String msg = decrypt(endereço,jogador);
		
		if(!validarNome(msg,jogador)) {
			return null;
		}
		
		msg = msg.substring(0,msg.indexOf("s0"));
		
		Map<String, Integer> fichasJogador = new HashMap<String, Integer>();
		
		try {
			fichasJogador.put("1", converterInt(msg.substring(2, 6)));
			fichasJogador.put("5", converterInt(msg.substring(8, 12)));
			fichasJogador.put("10", converterInt(msg.substring(14, 18)));
			fichasJogador.put("20", converterInt(msg.substring(20, 24)));
			fichasJogador.put("50", converterInt(msg.substring(26, 30)));
			fichasJogador.put("100", converterInt(msg.substring(32, 36)));
		
			
		}catch(Exception e){
			return null;
		
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
