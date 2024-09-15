package services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class CipherAndDecipherFiles {
    private final List<Character> alphabet;

    public CipherAndDecipherFiles(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public void encryptFile(String pathToOriginalFile, int key, String pathToEncryptedFile) throws IOException {
        try (FileReader fileReader = new FileReader(pathToOriginalFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(pathToEncryptedFile)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String encipher = Cipher.encipher(line, key, alphabet);
                fileWriter.write(encipher);
                fileWriter.write('\n');
                fileWriter.flush();
            }
        }
    }

    public void decryptFile(String pathToEncryptedFile, int key, String pathToDecryptedFile) throws IOException {
        try (FileReader fileReader = new FileReader(pathToEncryptedFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(pathToDecryptedFile)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String decipher = Decipher.decipher(line, key, alphabet);
                fileWriter.write(decipher);
                fileWriter.write('\n');
                fileWriter.flush();
            }
        }
    }

    public void decryptWithBruteForceFile(String pathToEncryptedFile, String pathForSaveDecryptedFiles) throws IOException {
        try (FileReader fileReader = new FileReader(pathToEncryptedFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            for (int i = 1; i < alphabet.size() - 1; i++) {
                bufferedReader.mark(10024000);
                Path newFile = Files.createFile(Path.of(pathForSaveDecryptedFiles + "variant " + i + ".txt"));
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String decipherWithBruteForce = BruteForce.decipherWithBruteForce(line, i, alphabet);
                    Files.writeString(newFile, decipherWithBruteForce, APPEND);
                    Files.writeString(newFile,"\n", APPEND);
                }
                bufferedReader.reset();
            }
        }
    }
}
