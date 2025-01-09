package models; 

import javax.persistence.Entity; 
import play.db.jpa.Model; 

@Entity // Anotação para definir a classe como uma entidade JPA.
public class Usuario extends Model { // Define a classe Usuario que estende Model.

    public String nome; // Campo que representa o nome do usuário.
    public String email; // Campo que representa o email do usuário.
    public String senha; // Campo que representa a senha do usuário.
    public NivelDeEnsino nivelDeEnsino; // Campo que representa o nível de ensino do usuário.
    public String curso; // Campo que representa o curso do usuário.
    public int nivel; // Campo que representa o nível do usuário (0 ou 1).
}
