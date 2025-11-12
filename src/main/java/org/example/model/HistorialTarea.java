package org.example.model;

import lombok.*;
import org.example.model.enums.AccionTarea;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class HistorialTarea {
    private Integer id;
    private Integer tareaId;
    private AccionTarea accion;
    private LocalDateTime fecha;
    private String comentario;
}
