package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import models.Produto;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.Controller;


public class Produtos extends Controller {
	public static void form() { 
		renderTemplate("Produtos/form.html"); 
	}
   	public static void listar(String termo) { 
	    List<Produto> produtos = null;
	    if (termo == null) {
	        produtos = Produto.findAll();
	    } else {
	        produtos = Produto.find("lower(nomeProduto) like ?1",
	                "%" + termo.toLowerCase() + "%").fetch();
	    }
	    renderTemplate("Produtos/listarProdutos.html", produtos, termo);

    }

    public static void detalhar(Long id) {
        Produto produto = Produto.findById(id);
        notFoundIfNull(produto);
        renderTemplate("Produtos/detalharProduto.html", produto);
    }

    public static void verFoto(Long id) {
        Produto p = Produto.findById(id);
        notFoundIfNull(p);
        response.setContentTypeIfNotSet(p.foto.type());
        renderBinary(p.foto.get());
    }

    public static void salvar(File foto, Produto produto, List<String> tamanhoProdutoDisp, List<String> modelo) {
        String mensagem = "Fardamento cadastrado com sucesso!";
        if (produto.id != null) {
            mensagem = "Fardamento Editado com sucesso!";
        }
        produto.nomeProduto = produto.nomeProduto.toUpperCase();

        if (tamanhoProdutoDisp != null) {
            produto.tamanhoProdutoDisp = tamanhoProdutoDisp.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        }

        if (modelo != null) {
            produto.modelo = modelo.stream()
                                    .map(String::toUpperCase)
                                    .collect(Collectors.toList());
        }

        if (foto != null) {
            produto.foto = new Blob();
            try (InputStream is = new FileInputStream(foto)) {
                produto.foto.set(is, MimeTypes.getContentType(foto.getName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace(); 
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }

        produto.save();
        flash.success(mensagem);
        listar(null);
    }

    public static void editar(Long id) { 
    	Produto produto = Produto.findById(id); 
    	notFoundIfNull(produto); 
    	renderTemplate("Produtos/form.html", produto);
    }

    public static void remover(Long id) {
        Produto p = Produto.findById(id);
        if (p != null) {
            p.delete();
            flash.success("Removido com sucesso!");
        } else {
            flash.error("Produto não encontrado!");
        }
        listar(null);
    }
}
