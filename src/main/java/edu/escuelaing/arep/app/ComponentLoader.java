package edu.escuelaing.arep.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ComponentLoader {
    private static Map<String, Method> servicios = new HashMap();
    
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        cargarComponentes(args);

        System.out.println(ejecutar("/hello", "?name=pedro&sn=perez"));
    }
    public static String ejecutar(String ruta, String param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        return servicios.get(ruta).invoke(null, (Object) param) + " ";

    }
    public static void cargarComponentes(String[] args) throws ClassNotFoundException {
        for(String arg : args){
            Class c = Class.forName(arg);
            if(c.isAnnotationPresent(Componente.class)){
                Method[] metodos = c.getDeclaredMethods();
                for(Method m : metodos){
                    if(m.isAnnotationPresent(GetMapping.class)){
                        //extraer parametro
                        String ruta = m.getAnnotation(GetMapping.class).value();
                        //extraer nombre del metodo
                        System.out.println("cargando metodo: " + m.getName());
                        //crear la lista de tipos del metodo
                        servicios.put(ruta, m);
                        //obtener el metodo
                        //agregar el metodo a la tabla de objetos ejecutables

                    }
                }
            }
        }
    }        
}
