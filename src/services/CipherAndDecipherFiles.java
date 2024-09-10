package services;

import java.io.*;

public class CipherAndDecipherFiles {

    public String encipherFile(String filename) {
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
