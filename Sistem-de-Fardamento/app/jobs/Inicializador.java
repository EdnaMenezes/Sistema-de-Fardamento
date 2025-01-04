package jobs;

import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() {
        if (Usuario.count() == 0) {
            Usuario u = new Usuario();
            u.email = "admin@admin.com";
            u.nome = "Sr. Edna";
            u.senha = "123456";
            u.nivel = 1;
            u.save();
        }
    }
}
