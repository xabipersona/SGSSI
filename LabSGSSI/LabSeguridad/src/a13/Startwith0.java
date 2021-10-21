package a13;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Startwith0 {

	public static String getSHA(String p) throws IOException, NoSuchAlgorithmException {
		byte[] array = Files.readAllBytes(Paths.get(p));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(array);

		String hashHex = bytesToHex(hash);
		return hashHex;
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

	public static String SHAConCerosMasLargo(String s1, String s2) {
		int i = 0;
		while (s2.charAt(i) == '0') {
			if (s1.charAt(i) != 0) {
				return s2;
			}
		}
		return s1;
	}

	public static String getSaltString() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() <= 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		System.out.println("                      "+saltStr);			
		return saltStr;

	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		String path2 ="";
		String pathFinal ="";
		
		String c2 = "999999999999";
		String c1 = "";
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);

		//c2.charAt(0)!='0' || c2.charAt(1)!='0' || c2.charAt(2)!='0' || c2.charAt(3)!='0' || c2.charAt(4)!='0'
		while (System.nanoTime() < endTime) {

			String path1 = "files/SGSSI-21.CB.04.txt";
			

			try (Stream<String> lines = Files.lines(Paths.get(path1))) {

				String content = lines.collect(Collectors.joining(System.lineSeparator()));

				content += "\n"+getSaltString()+" G0816";
				System.out.println(content);
				path2 = "files/zerosOutput.txt";

				try {

					Files.write(Paths.get(path2), content.getBytes());

				} catch (IOException e) {
					e.printStackTrace();
				}

				try (Stream<String> lines2 = Files.lines(Paths.get(path2))) {
					//String content2 = lines2.collect(Collectors.joining(System.lineSeparator()));

					c1 = getSHA(path2);
					System.out.println("La cadena NUEVA es: "+c1);
					c2 = SHAConCerosMasLargo(c1, c2);
					System.out.println("La cadena con más ceros es: "+c2);
					
					if (c1.equals(c2)) {
						pathFinal = content;
					
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Files.write(Paths.get(path2), pathFinal.getBytes());
			//System.out.println("FIN");
		}
		
		Files.write(Paths.get(path2), pathFinal.getBytes());
	}
}
