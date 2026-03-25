package com.example.Mathservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FibwinController {

    @GetMapping("/fibwin")
    public Map<String, Object> fibwin(@RequestParam int value) {
        // Generar serie de Fibonacci de F_0 hasta F_value
        List<Long> fib = new ArrayList<>();
        fib.add(0L);
        fib.add(1L);
        for (int i = 2; i <= value; i++) {
            fib.add(fib.get(i - 1) + fib.get(i - 2));
        }

        // Calcular suma movil con ventana K=3
        List<Long> ventana = new ArrayList<>();
        for (int i = 0; i <= fib.size() - 3; i++) {
            ventana.add(fib.get(i) + fib.get(i + 1) + fib.get(i + 2));
        }

        // Construir strings de salida
        StringBuilder serieSb = new StringBuilder();
        for (int i = 0; i < fib.size(); i++) {
            if (i > 0) serieSb.append(", ");
            serieSb.append(fib.get(i));
        }

        StringBuilder ventanaSb = new StringBuilder();
        for (int i = 0; i < ventana.size(); i++) {
            if (i > 0) ventanaSb.append(", ");
            ventanaSb.append(ventana.get(i));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("operation", "Fibonacci con ventana K=3");
        response.put("input", value);
        response.put("output", "serie: " + serieSb + " | ventana: " + ventanaSb);

        return response;
    }
}
