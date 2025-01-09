package models; 
import java.util.List; 
import javax.persistence.ElementCollection; 
import javax.persistence.Entity; 
import play.db.jpa.Blob; 
import play.db.jpa.Model; 

@Entity // Anotação para definir a classe como uma entidade JPA.
public class Produto extends Model { // Define a classe Produto que estende Model.

    public String nomeProduto; // Campo que representa o nome do produto.
    public Blob foto; // Campo que representa a foto do produto.

    @ElementCollection // Define uma coleção de elementos embutidos para tamanhos disponíveis do produto.
    public List<String> tamanhoProdutoDisp; // Campo que representa os tamanhos disponíveis do produto.

    @ElementCollection // Define uma coleção de elementos embutidos para modelos do produto.
    public List<String> modelo; // Campo que representa os modelos do produto.
}
