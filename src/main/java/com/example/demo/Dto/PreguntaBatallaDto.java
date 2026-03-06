package com.example.demo.Dto;

public class PreguntaBatallaDto {
    private Integer id;
    private String textoPregunta;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private String respuestaCorrecta;
    private String explicacion;
    private Integer puntos;

    // Constructor desde entidad Pregunta
    public PreguntaBatallaDto(Integer id, String textoPregunta, String opcionesRespuesta, 
                              String respuestaCorrecta, String explicacion, Integer puntos) {
        this.id = id;
        this.textoPregunta = textoPregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.explicacion = explicacion;
        this.puntos = puntos;
        
        // Parsear opcionesRespuesta (formato: "A) texto1|B) texto2|C) texto3|D) texto4")
        if (opcionesRespuesta != null && !opcionesRespuesta.isEmpty()) {
            String[] opciones = opcionesRespuesta.split("\\|");
            for (String opcion : opciones) {
                if (opcion.startsWith("A)")) this.opcionA = opcion.substring(3).trim();
                else if (opcion.startsWith("B)")) this.opcionB = opcion.substring(3).trim();
                else if (opcion.startsWith("C)")) this.opcionC = opcion.substring(3).trim();
                else if (opcion.startsWith("D)")) this.opcionD = opcion.substring(3).trim();
            }
        }
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTextoPregunta() { return textoPregunta; }
    public void setTextoPregunta(String textoPregunta) { this.textoPregunta = textoPregunta; }

    public String getOpcionA() { return opcionA; }
    public void setOpcionA(String opcionA) { this.opcionA = opcionA; }

    public String getOpcionB() { return opcionB; }
    public void setOpcionB(String opcionB) { this.opcionB = opcionB; }

    public String getOpcionC() { return opcionC; }
    public void setOpcionC(String opcionC) { this.opcionC = opcionC; }

    public String getOpcionD() { return opcionD; }
    public void setOpcionD(String opcionD) { this.opcionD = opcionD; }

    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }

    public String getExplicacion() { return explicacion; }
    public void setExplicacion(String explicacion) { this.explicacion = explicacion; }

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }
}