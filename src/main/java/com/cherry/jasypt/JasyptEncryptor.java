package com.cherry.jasypt;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.cherry.jasypt.option.Options;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricConfig;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricStringEncryptor;
import org.jasypt.encryption.StringEncryptor;

import java.io.*;

import static com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography.KeyFormat.PEM;

public class JasyptEncryptor {

    public static void main(String[] args) {
        try {
            Options options = setUpJCommand(args);
            String publicKey = readPemFileContent(options.getPublicKeyFilePath());

            SimpleAsymmetricConfig config = new SimpleAsymmetricConfig();
            config.setKeyFormat(PEM);
            config.setPublicKey(publicKey);

            StringEncryptor encryptor = new SimpleAsymmetricStringEncryptor(config);
            String encrypted = encryptor.encrypt(options.getPrepareToEncString());

            System.out.println("ENC(" + encrypted + ")");
        } catch (Exception e) {
            System.err.println("Error reading public key: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Options setUpJCommand(String[] args) {
        Options options = new Options();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(options)
                .build();
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jCommander.usage(); // 顯示使用說明
            System.exit(1);
        }
        return options;
    }

    private static String readPemFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n"); // 保留換行符號
            }
        }
        return content.toString();
    }
}