package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//URL - localhost:8080/produtos

// -- código omitido --
@Controller // Gerenciado pelo Spring
@RequestMapping("/produtos") //mapeando URL
public class ProdutoController {

    //URL - localhost:8080/produtos/novo

    @Autowired
    private ProdutoRepository repository;
    @GetMapping("/novo")
    public String adicionarProduto(Model model){
        //Model é injetado pelo Spring
        //instância um objeto Produto vazio, associado à chave produto
        model.addAttribute("produto", new Produto());
        //model -> enviar o obj. Produto para a view
        return "produto/novo-produto";
    }
    // receber dados do form da View novo-produto.html
    //URL - localhost:8080/produtos/listar
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", repository.findAll());
        return "/produto/listar-produtos";
    }


    @PostMapping("/salvar")
    public String insertProduto(@Valid Produto produto,
                    BindingResult results,
                    RedirectAttributes atributes
                    ){ //recebe objeto
        if(results.hasErrors()){
            System.out.println("Erro para salvar produto");
            return "/produto/novo-produto";

        }


        System.out.println(produto.toString());
        repository.save(produto);
        atributes.addFlashAttribute("mensagem", "produto salvo com sucesso");
        //provisório - redireciona para localhost:8080/produtos/novo
        return "redirect:/produtos/novo";
    }
}


