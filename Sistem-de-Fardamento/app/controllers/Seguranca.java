package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

    // Verifica se o usuário está logado antes de acessar qualquer página (exceto páginas de login)
    @Before(unless = {"Login.form", "Login.logar", "Login.sair"})
    static void verificar() {
        if (!session.contains("usuario.email")) {
            flash.put("error", "Você precisa estar logado para acessar esta página.");
            Login.form();
        }
    }

    // Permite apenas usuários com nível correto acessar certas áreas
    @Before(unless = {"Login.form", "Login.logar", "Login.sair", "Usuarios.nivel1", "Usuarios.nivel0"})
    static void permissoes() {
        String nivel = session.get("usuario.nivel");
        if (nivel == null || (!nivel.equals("1") && !nivel.equals("0"))) {
            flash.put("error", "Permissões insuficientes para acessar esta página.");
            Login.form();
        }
    }
}
