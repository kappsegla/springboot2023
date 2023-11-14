package com.example.springboot23.valueobjects.converters;

import com.example.springboot23.valueobjects.EmailAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmailAddressConverter implements AttributeConverter<EmailAddress,String> {
    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        return emailAddress == null ? null: emailAddress.email();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String s) {
        return s == null ? null : new EmailAddress(s);
    }
}
