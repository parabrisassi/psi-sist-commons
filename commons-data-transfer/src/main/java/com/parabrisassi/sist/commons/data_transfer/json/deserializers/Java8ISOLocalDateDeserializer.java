package com.parabrisassi.sist.commons.data_transfer.json.deserializers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.parabrisassi.sist.commons.data_transfer.date_time.DateTimeFormatters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@link com.fasterxml.jackson.databind.JsonDeserializer} to transform a {@link String} to a {@link LocalDate},
 * using {@link DateTimeFormatters#ISO_LOCAL_DATE} {@link DateTimeFormatter}.
 */
public class Java8ISOLocalDateDeserializer extends StdDeserializer<LocalDate> {

    /**
     * Default constructor.
     */
    protected Java8ISOLocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext context)
            throws IOException {
        final String dateString = p.getText();
        try {
            return LocalDate.from(DateTimeFormatters.ISO_LOCAL_DATE.getFormatter().parse(dateString));
        } catch (DateTimeParseException e) {
            throw new JsonParseException(p, "Unable to deserialize the date", e);
        }
    }
}
