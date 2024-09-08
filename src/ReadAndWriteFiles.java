import java.io.*;
import java.nio.file.Files;

public class ReadAndWriteFiles {

    public String readFile(String filename) {
        String line = "";
        try (FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()){
                line = bufferedReader.readLine();
                //cipher(line) вызвать шифрование?!

            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        return line;
    }
}
