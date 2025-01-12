package com.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager==null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }
    // Used to load a config file byt the path provided
    public void loadConfigurationFile(String filePath)  {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfiguratorException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ( ( i = fileReader.read()) != -1) {
                sb.append((char)i);
                }
            } catch (IOException e) {
                throw new HttpConfiguratorException(e);
            }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfiguratorException("Error parsing the configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfiguratorException("Error parsing the configuration file, internal", e);
        }
    }
    // Returns the current loaded configuration
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfiguratorException("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}
