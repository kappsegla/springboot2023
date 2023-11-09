package com.example.springboot23;

import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Country}
 */
public record CountryDto(@Size(max = 255) String name, LocalDate nationalDay) implements Serializable {
}
