package controllers;

import models.Pedido; 
import models.Produto; 
import play.mvc.With;
import java.util.List; 

// Aplica os filtros de segurança da classe Seguranca.
@With(Seguranca.class)
public class Pedidos extends Seguranca {

    // Método para renderizar o formulário de pedidos.
    public static void form(Long id) {
        // Busca todos os produtos do banco de dados.
        List<Produto> produtos = Produto.findAll();
        
        // Se o id for nulo, cria um novo Pedido, caso contrário, busca o pedido pelo id.
        Pedido pedido = id == null ? new Pedido() : Pedido.findById(id);
        
        // Renderiza o template com a lista de produtos e o pedido.
        render(produtos, pedido);
    }

    // Método para salvar um pedido.
    public static void salvar(Pedido pedido) {
        // Associa o cliente ao usuário logado.
        String usuarioLogado = session.get("usuario.email");
        if (usuarioLogado != null) {
            pedido.cliente = usuarioLogado;
            pedido.save();
            flash.success("Pedido salvo com sucesso!");
        } else {
            flash.error("Erro: Usuário não autenticado.");
        }
        Usuarios.nivel0(); // Redireciona para a tela de nível 0.
    }

    // Método para listar todos os pedidos.
    public static void listar() {
        // Busca todos os pedidos do banco de dados.
        List<Pedido> pedidos = Pedido.findAll();
        
        // Renderiza o template com a lista de pedidos.
        render(pedidos);
    }

    // Método para remover um pedido.
    public static void remover(Long id) {
        // Busca o pedido pelo id.
        Pedido pedido = Pedido.findById(id);
        if (pedido != null) {
            pedido.delete();
            flash.success("Pedido removido com sucesso!");
        } else {
            flash.error("Erro: Pedido não encontrado.");
        }
        listar(); // Redireciona para a lista de pedidos.
    }
}
