package a22;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main22 {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {


		byte[] array = Files.readAllBytes(Paths.get("files/SGSSI-21.CB.04.txt"));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(array);

		String hashHex = bytesToHex(hash);
		System.out.println("el resumen es: "+hashHex);

		try {
			File myObj = new File("files/salida.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				try (FileOutputStream fos = new FileOutputStream("files/salida.txt")) {
					fos.write(array);
				}
				FileWriter fr = new FileWriter(myObj, true);
				fr.write(hashHex);
				fr.close();
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


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