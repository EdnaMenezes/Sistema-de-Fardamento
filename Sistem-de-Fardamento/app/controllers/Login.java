package controllers;

import models.Usuario; 
import play.mvc.Controller; 

public class Login extends Controller { // Define a classe Login que estende Controller.
    
    // Método para renderizar o formulário de login.
    public static void form() {
        render();
    } 
    
    // Método para realizar o login do usuário.
    public static void logar(String email, String senha) {
        // Busca um usuário no banco de dados com o email e senha fornecidos.
        Usuario usu = Usuario.find("email = ?1 and senha = ?2 ", email, senha).first();
        
        // Se o usuário não for encontrado, exibe uma mensagem de erro e retorna ao formulário.
        if (usu == null) {
            flash.error("Credenciais inválidas! Tente novamente.");
            form();
        } else {
            // Armazena informações do usuário na sessão.
            session.put("usuario.nome", usu.nome);
            session.put("usuario.email", usu.email);
            session.put("usuario.nivel", Integer.toString(usu.nivel)); // Armazena como string na sessão
            
            // Redireciona o usuário com base no nível.
            if (usu.nivel == 1) {
                // Redireciona para tela de usuário de nível 1.
                redirect("/usuarios/nivel1");
            } else if (usu.nivel == 0) {
                // Redireciona para tela de usuário de nível 0.
                redirect("/usuarios/nivel0");
            }
        }
    }
    
    // Método para realizar o logout do usuário.
    public static void sair() {
        session.clear(); // Limpa a sessão.
        flash.success("Você saiu com sucesso."); // Exibe mensagem de sucesso.
        Login.form(); // Renderiza o formulário de login.
    }
}
