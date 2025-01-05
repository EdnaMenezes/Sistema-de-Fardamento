package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
@With(Seguranca.class)
public class Usuarios extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void salvar(Usuario usuario, String senha) {
	    if (!senha.isEmpty()){
	        usuario.senha = senha;
	    }
	    
	    usuario.save();

	    // Atualize a sessão com o novo nome
	    session.put("usuario.nome", usuario.nome);

	    listar(); // Redirecionar para a tela de listagem
	}
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
}
