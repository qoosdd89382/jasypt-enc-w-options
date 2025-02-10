package com.cherry.jasypt.option;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Options {

    @Parameter(names = {"--pub-key", "-pk"}, description = "Path to the public key file (.pem)", required = true)
    private String publicKeyFilePath;

    @Parameter(names = {"--input", "-i"}, description = "The text to be encrypted", required = true)
    private String prepareToEncString;

}
