package controllers;

import play.mvc.Controller;
import java.util.Arrays; 
import java.util.List; 

public class Cursos extends Controller { // Define a classe Cursos que estende Controller.
    
    // Método que retorna os cursos com base no nível de ensino fornecido.
    public static void getCursos(String nivelDeEnsino) {
        List<String> cursos;
        
        // Verifica o nível de ensino e define a lista de cursos correspondente.
        if ("SUPERIOR".equalsIgnoreCase(nivelDeEnsino)) {
            cursos = Arrays.asList("Física", "Energias Renováveis");
        } else if ("MEDIO".equalsIgnoreCase(nivelDeEnsino)) {
            cursos = Arrays.asList("Eletrotécnica", "Informática", "Administração");
        } else {
            cursos = Arrays.asList(); // Caso o nível de ensino não corresponda, retorna uma lista vazia.
        }
        
        // Converte a lista de cursos em JSON e envia como resposta.
        renderJSON(cursos);
    }
}
