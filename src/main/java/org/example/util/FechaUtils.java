package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtils {

    private static final String FORMATO_FECHA = "dd-MM-yyyy HH:mm:ss";

    public static LocalDateTime parseFecha(String fechaStr) {

        if (fechaStr == null || fechaStr.isEmpty()) {
            return null;
        }

        try {
            // caso ISO_OFFSET_DATE_TIME → 2025-04-15T01:33:06Z
            if (fechaStr.contains("T") || fechaStr.contains("+") || fechaStr.contains("-0") && fechaStr.contains("Z")) {
                System.out.println("Parseando fecha ISO_OFFSET_DATE_TIME: " + fechaStr);
                return LocalDateTime.parse(fechaStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }
            // caso ISO_LOCAL_DATE_TIME → 2025-04-15T01:33:06
            if (fechaStr.contains("T")) {
                System.out.println("Parseando fecha ISO_LOCAL_DATE_TIME: " + fechaStr);
                return LocalDateTime.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            // caso ISO_LOCAL_DATE → 2025-04-15
            if (fechaStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Parseando fecha ISO_LOCAL_DATE: " + fechaStr);
                return LocalDateTime.parse(fechaStr + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            // caso DATETIME NORMAL → 15-04-2025 01:33:06
            System.out.println("Parseando fecha FORMATO_FECHA: " + fechaStr);
            return LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern(FORMATO_FECHA));

        } catch (Exception e) {
            System.out.println("⚠ Error parseando fecha: " + fechaStr);
            e.printStackTrace();
            return null;
        }

    }
}
