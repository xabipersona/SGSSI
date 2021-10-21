package a23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		byte[] entrada = Files.readAllBytes(Paths.get("files/SGSSI-21.CB.04.txt"));
		byte[] salida = Files.readAllBytes(Paths.get("files/salida.txt"));
		System.out.println(check(entrada,salida));

	}
	
	public static boolean check(byte[] fileEntrada, byte[] fileSalida) throws NoSuchAlgorithmException{
		for(int i=0;i<fileEntrada.length;i++){
			if(fileEntrada[i]!=fileSalida[i]){
				return false;
			}
		
		}
//		byte[] hashEntrada = getHash(fileEntrada);
//		int j = fileSalida.toString().length();
//		for(int i=hashEntrada.toString().length();i>=0;i--){
//			System.out.println(hashEntrada[i]);
//			System.out.println(fileSalida[j]);
//			if(hashEntrada[i]!=fileSalida[j])
//				return false;
//			j--;
//		}
		return true;
		
	}

	public static byte[] getHash(byte[] array) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(array);
		return hash;
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

}