package jobs;

import models.Usuario;
import play.jobs.Job; 
import play.jobs.OnApplicationStart;

// Anotação para indicar que este job deve ser executado no início da aplicação.
@OnApplicationStart
public class Inicializador extends Job { // Define a classe Inicializador que estende Job.

    // Método que é executado quando a aplicação inicia.
    @Override
    public void doJob() {
        // Verifica se não há usuários cadastrados no banco de dados.
        if (Usuario.count() == 0) {
            // Cria um novo usuário administrador.
            Usuario u = new Usuario();
            u.email = "admin@admin.com";
            u.nome = "Administrador";
            u.senha = "123456";
            u.nivel = 1;
            u.curso = "informática";
            u.save(); // Salva o usuário no banco de dados.
        }
    }
}
