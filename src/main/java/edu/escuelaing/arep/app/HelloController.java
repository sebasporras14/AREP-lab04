package edu.escuelaing.arep.app;

import java.util.*;

import edu.escuelaing.arep.app.anotaciones.Componente;
import edu.escuelaing.arep.app.anotaciones.GetMapping;
import edu.escuelaing.arep.app.anotaciones.PostMapping;

@Componente
public class HelloController {

    private static final Map<String, String> map = new HashMap<>();

    @GetMapping("/hello")
    public static String getPerson(String name) {
        if (map.containsKey(name)) {
            return "Hola  " + name + " " + "su calificacion fue " + map.get(name);
        }
        return "La calificion no ha sido guardada";
    }

    @PostMapping("/hello")
    public static String postPerson(String arg){
        map.put(arg.split("&")[0], arg.split("&")[1]);
        return "La calificacion ha sido guardada";
    }

}
