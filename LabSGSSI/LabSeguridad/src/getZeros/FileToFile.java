package getZeros;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileToFile {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NoSuchAlgorithmException {

		String path = "C:\\Users\\ehu\\Desktop\\SGSSI-21.CB.01.txt";

        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            // Formatting like \r\n will be lost
            // String content = lines.collect(Collectors.joining());

            // UNIX \n, WIndows \r\n
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            
            Main m = new Main();
            String c = m.getSHA(path);
            content += "\n"+c;
            System.out.println(content);

			// File to List
            //List<String> list = lines.collect(Collectors.toList());

     
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
