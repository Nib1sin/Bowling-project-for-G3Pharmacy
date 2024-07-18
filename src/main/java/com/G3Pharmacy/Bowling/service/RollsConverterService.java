package com.G3Pharmacy.Bowling.service;

import jakarta.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RollsConverterService implements AttributeConverter<int[], String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(int[] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting array to JSON", e);
        }
    }

    @Override
    public int[] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, int[].class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to array", e);
        }
    }
}
