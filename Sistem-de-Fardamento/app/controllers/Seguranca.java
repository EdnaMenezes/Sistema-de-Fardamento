package controllers;

import play.mvc.Before;
import play.mvc.Controller;


public class Seguranca extends Controller {

	@Before(unless={"Usuarios.listar"})
	static void verificar(){
		
		if (session.contains("usuario.email") == false){
			Login.form();
		}
		 
	}
	
	@Before(unless={"Usuarios.listar"})
	static void permissoes() {
	    String nivel = session.get("usuario.nivel");
	    if (nivel == null || !nivel.equals("1")) {
	        renderText("Acesso negado");
	    }
	}

}