package services;

import java.io.*;
import java.util.List;

public class CipherAndDecipherFiles {
    private final List<Character> alphabet;

    public CipherAndDecipherFiles(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public void encipherFile(String pathToFile, int key, String pathToCipheredFile) {
        try (FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(pathToCipheredFile)) {
            while (bufferedReader.ready()){
                String line = bufferedReader.readLine();
                String encipher = Cipher.encipher(line, key, alphabet);
                fileWriter.write(encipher);
                fileWriter.write('\n');
                fileWriter.flush();
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public void decipherFile(String cipheredFile, int key, String pathToDecipheredFile) {
        try (FileReader fileReader = new FileReader(cipheredFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(pathToDecipheredFile)) {
            while (bufferedReader.ready()){
                String line = bufferedReader.readLine();
                String decipher = Decipher.decipher(line, key, alphabet);
                fileWriter.write(decipher);
                fileWriter.write('\n');
                fileWriter.flush();
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
