package models; 
import javax.persistence.Entity; 
import javax.persistence.ManyToOne; 
import play.data.validation.Required; 
import play.db.jpa.Model; 

@Entity // Anotação para definir a classe como uma entidade JPA.
public class Pedido extends Model { // Define a classe Pedido que estende Model.

    @Required // Indica que o campo é obrigatório.
    @ManyToOne // Define um relacionamento muitos-para-um com a classe Produto.
    public Produto produto; // Campo que representa o produto associado ao pedido.

    @Required // Indica que o campo é obrigatório.
    public int quantidade; // Campo que representa a quantidade do pedido.

    @Required // Indica que o campo é obrigatório.
    public String tamanho; // Campo que representa o tamanho do produto no pedido.

    @Required // Indica que o campo é obrigatório.
    public String modelo; // Campo que representa o modelo do produto no pedido.

    @Required // Indica que o campo é obrigatório.
    public String cliente; // Campo que representa o cliente associado ao pedido.

    @ManyToOne // Define um relacionamento muitos-para-um com a classe Usuario.
    public Usuario usuario; // Campo que representa o usuário associado ao pedido.

}
