package edu.escuelaing.arep.app;
@Componente
public class HelloServices {

    @GetMapping("/hello")
    public static String hola(String arg){
        return "hola" + arg ; 
    }
    @GetMapping("/hellopost")
    public static String post(String arg){
        return "hola" + arg ; 
    }
    
}
