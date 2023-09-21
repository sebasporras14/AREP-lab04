package edu.escuelaing.arep.app;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

import edu.escuelaing.arep.app.anotaciones.Componente;
import edu.escuelaing.arep.app.anotaciones.GetMapping;
import edu.escuelaing.arep.app.anotaciones.PostMapping;


public class ComponentLoader {


    public static Map<String, Method> getServicios = new HashMap<>();
    public static Map<String, Method> postServicios = new HashMap<>();
    public static String path = "edu/escuelaing/arep/app";

    public static String ejecutar(Method method, String param) throws IllegalAccessException, InvocationTargetException {
        return (String) method.invoke(null, param);
    }

    public static Method search(String nombre, String accion) {
        return "GET".equals(accion) ? getServicios.get(nombre) :
               "POST".equals(accion) ? postServicios.get(nombre) :
               null;
    }

    public static void loadComponents() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL packageURL = classLoader.getResource(path);

        if (packageURL != null) {
            File packageDir = new File(packageURL.getFile());
            
            if (packageDir.isDirectory()) {
                File[] files = packageDir.listFiles();
                if (files != null) {
                    saveComponents(files);
                }
            }
        }
    }

    private static void saveComponents(File[] files) throws ClassNotFoundException {
        for (File file : files) {
            String className = file.getName();
            
            if (className.endsWith(".class")) {
                className = path + "/" + className.substring(0, className.length() - 6);
                Class<?> clase = Class.forName(className.replace('/', '.'));
                
                if (clase.isAnnotationPresent(Componente.class)) {
                    Method[] methods = clase.getDeclaredMethods();
                    
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(GetMapping.class)) {
                            String accion = method.getAnnotation(GetMapping.class).value();
                            getServicios.put(accion, method);
                        } else if (method.isAnnotationPresent(PostMapping.class)) {
                            String accion = method.getAnnotation(PostMapping.class).value();
                            postServicios.put(accion, method);
                        }
                    }
                }
            }
        }
    }


}