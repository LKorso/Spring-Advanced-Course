package web.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private final DateTimeFormatter dateTimeFormatter;

    public StringToLocalDateTimeConverter(String format) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }

    @Override
    public LocalDateTime convert(String dateValue) {
        return Objects.isNull(dateValue) ? null : LocalDateTime.parse(dateValue, dateTimeFormatter);
    }
}
