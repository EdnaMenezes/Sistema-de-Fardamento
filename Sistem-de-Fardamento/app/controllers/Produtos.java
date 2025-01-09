package controllers;

import play.mvc.Controller; 
import play.mvc.With; 
import play.db.jpa.Blob; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.util.List; 
import models.Produto; 

// Aplica os filtros de segurança da classe Seguranca.
@With(Seguranca.class)
public class Produtos extends Controller { // Define a classe Produtos que estende Controller.

    // Método para renderizar o formulário de produtos.
    public static void form(Long id) {
        Produto produto = id != null ? Produto.findById(id) : new Produto(); // Busca o produto pelo id ou cria um novo.
        render(produto);
    }

    // Método para salvar um produto.
    public static void salvar(Long id, String nomeProduto, List<String> tamanhoProdutoDisp, List<String> modelo, File foto) {
        Produto produto;
        if (id != null) {
            produto = Produto.findById(id);
            if (produto == null) {
                flash.error("Produto não encontrado.");
                listar(); // Redirecionar para a lista de produtos.
                return;
            }
        } else {
            produto = new Produto();
        }

        // Atribui os valores ao produto.
        produto.nomeProduto = nomeProduto;
        produto.tamanhoProdutoDisp = tamanhoProdutoDisp;
        produto.modelo = modelo;

        // Lida com a foto do produto.
        if (foto != null) {
            produto.foto = new Blob();
            try {
                produto.foto.set(new FileInputStream(foto), "image/jpeg"); // Ajuste o tipo MIME conforme necessário.
                System.out.println("Foto carregada com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
                flash.error("Erro ao carregar a foto.");
                listar();
                return;
            }
        } else {
            System.out.println("Nenhuma foto enviada.");
        }

        produto.save(); // Salva o produto no banco de dados.
        flash.success("Produto salvo com sucesso.");
        Produtos.listar(); // Redireciona para a lista de produtos.
    }

    // Método para remover um produto.
    public static void remover(Long id) {
        Produto produto = Produto.findById(id);
        if (produto != null) {
            produto.delete(); // Deleta o produto.
            flash.success("Produto removido com sucesso.");
        } else {
            flash.error("Produto não encontrado.");
        }
        listar(); // Redireciona de volta para a lista de produtos.
    }

    // Método para listar todos os produtos.
    public static void listar() {
        List<Produto> produtos = Produto.findAll(); // Busca todos os produtos do banco de dados.
        render(produtos);
    }

    // Método para listar os pedidos (mantido caso seja necessário).
    public static void listarPedidos() {
        List<Produto> produtos = Produto.findAll();
        render("Pedidos/listar.html", produtos); // Renderizar a página de pedidos de fardamento.
    }

    // Método para exibir a imagem do produto.
    public static void imagem(Long id) {
        Produto produto = Produto.findById(id);
        if (produto != null && produto.foto != null) {
            response.setContentTypeIfNotSet(produto.foto.type());
            renderBinary(produto.foto.get());
        } else {
            notFound("Imagem não encontrada.");
        }
    }

    // Método para editar um produto.
    public static void editar(Long id) {
        Produto produto = Produto.findById(id);
        if (produto == null) {
            flash.error("Produto não encontrado.");
            listar(); // Redirecionar para a lista de produtos.
            return;
        }
        render("Produtos/form.html", produto); // Renderizar a página de formulário para edição.
    }
}
