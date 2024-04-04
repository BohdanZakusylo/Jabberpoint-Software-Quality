package com.bohdanvlad;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Objects;

public class JsonReader
{
    protected static final String jsonName = "final-vars.json";

    public static void getJsonParser()
    {

        try
        {
            File file = new File(
                    Objects.requireNonNull(JsonReader.class.getClassLoader().getResource(jsonName)).getFile()
            );

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(file);

            String field1 = rootNode.path("JABERR").asText();
            System.out.println(field1);
        }
        catch (IOException e)
        {
            //TODO create custom exceptions
            throw new IllegalArgumentException("No final-vars.json is found");
        }

    }
}
