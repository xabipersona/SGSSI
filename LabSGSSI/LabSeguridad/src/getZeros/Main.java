package getZeros;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//*
// Codigo de Endika Varas.

// Github: https://github.com/endrato/SGSSI_MINER/blob/main/SGSSI_LAB03/src/sha256Digest/Main.java
//

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		String p1 = "files/SGSSI-21.CB.04.txt";
		String p2 = "files/salida.txt";
		
		System.out.println(comprobarArchivos(p1,p2));
	}

	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public static String getSHA(String p) throws IOException, NoSuchAlgorithmException {
		byte[] array = Files.readAllBytes(Paths.get(p));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(array);
		
		String hashHex = bytesToHex(hash);
		return hashHex;
	}
	
	public static String getSHA2(String p) throws IOException, NoSuchAlgorithmException {
		byte[] array = Files.readAllBytes(Paths.get(p));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(array);
		
		String hashHex = bytesToHex(hash);
		return hashHex;
	}
	
	private static boolean comprobarArchivos(String path1,String path2) throws IOException, NoSuchAlgorithmException {
		BufferedReader br1 = new BufferedReader(new FileReader(path1));
		BufferedReader br2 = new BufferedReader(new FileReader(path2));
		
		String line1 = br1.readLine();
		String line2 = br2.readLine();
		String sha=sha256Digest(path1);
		
		while((line1 != null) && (line2 != null)) {
			if(!line1.equals(line2)) {
				System.out.println("No son iguales");
				return false;
			}
			line1=br1.readLine();
			line2=br2.readLine();
		}
		
		if(line1 == null) {
			if(!line2.equals(sha)) {
				System.out.println("No son iguales");
				return false;
			} else {
				System.out.println("Son iguales");
			}
		}
		
		br1.close();
		br2.close();
		
		return true;
	}

	private static String sha256Digest(String path) throws IOException, NoSuchAlgorithmException {
		File Archivo = new File(path);
		FileInputStream fis= new FileInputStream(Archivo);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
		
		byte[] encodedhash = digest.digest();
		
		StringBuilder hexString = new StringBuilder();
	    for (int i = 0; i < encodedhash.length; i++) {
	        String hex = Integer.toHexString(0xff & encodedhash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
		return hexString.toString();
	}

}