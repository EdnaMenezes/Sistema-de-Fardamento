package controllers;

import java.util.List; // Importa a classe List para manipulação de listas.
import models.Usuario; // Importa a classe Usuario do pacote models.
import models.NivelDeEnsino; // Importa a classe NivelDeEnsino do pacote models.
import play.mvc.Controller; // Importa a classe base Controller do Play Framework.
import play.mvc.With; // Importa a anotação With do Play Framework.

// Aplica os filtros de segurança da classe Seguranca.
@With(Seguranca.class)
public class Usuarios extends Controller { // Define a classe Usuarios que estende Controller.

    // Método para renderizar o formulário de usuários.
    public static void form() {
        render();
    }

    // Método para salvar um usuário.
    public static void salvar(Usuario usuario, String senha, String nivelDeEnsino, String curso) {
        if (senha != null && !senha.isEmpty()) { // Verifica se a senha não está vazia.
            usuario.senha = senha;
        }

        // Converter e setar o nivelDeEnsino.
        if (nivelDeEnsino != null && !nivelDeEnsino.isEmpty()) {
            usuario.nivelDeEnsino = NivelDeEnsino.valueOf(nivelDeEnsino);
        }

        // Setar o curso.
        if (curso != null && !curso.isEmpty()) {
            usuario.curso = curso;
        }

        usuario.save(); // Salva o usuário no banco de dados.
        Usuarios.nivel1(); // Redirecionar para a tela de nível 1.
    }

    // Método para editar um usuário.
    public static void editar(Long id) {
        Usuario usuario = Usuario.findById(id); // Busca o usuário pelo id.
        renderTemplate("Usuarios/form.html", usuario); // Renderiza o template de formulário com o usuário.
    }

    // Método para remover um usuário.
    public static void remover(Long id) {
        Usuario usuario = Usuario.findById(id); // Busca o usuário pelo id.
        if (usuario != null) {
            usuario.delete(); // Deleta o usuário.
            flash.success("Usuário removido com sucesso.");
        } else {
            flash.error("Usuário não encontrado.");
        }
        Usuarios.nivel1(); // Redirecionar para a tela de nível 1.
    }

    // Método para listar todos os usuários.
    public static void listar() {
        List<Usuario> usuarios = Usuario.findAll(); // Busca todos os usuários do banco de dados.
        renderTemplate("Usuarios/listar.html", usuarios); // Renderiza o template com a lista de usuários.
    }

    // Método para renderizar a tela de nível 1.
    public static void nivel1() {
        List<Usuario> usuarios = Usuario.findAll(); // Busca todos os usuários do banco de dados.
        renderTemplate("Usuarios/nivel1.html", usuarios); // Renderiza o template de nível 1 com a lista de usuários.
    }

    // Método para renderizar a tela de nível 0.
    public static void nivel0() {
        List<Usuario> usuarios = Usuario.findAll(); // Busca todos os usuários do banco de dados.
        renderTemplate("Usuarios/nivel0.html", usuarios); // Renderiza o template de nível 0 com a lista de usuários.
    }
}
