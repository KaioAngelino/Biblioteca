package br.com.biblioteca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.biblioteca.model.bean.Livro;

@Controller
public class LivroController {

	@GetMapping("/inserirLivro")
	public String callInserirLivro(Livro livro) {

		return "inserir_livros";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Livro livro = new Livro();

		int paginas = Integer.parseInt(request.getParameter("paginas"));
		boolean disp;

		if (Integer.parseInt(request.getParameter("disp")) == 1) {
			disp = true;
		} else {
			disp = false;
		}

		livro.setNome(request.getParameter("nome"));
		livro.setAutor(request.getParameter("autor"));
		livro.setNumeroPaginas(paginas);
		livro.setDisponibilidade(disp);
		livro.setDescricao(request.getParameter("descricao"));
		
		InserirLivro(livro);

	}

	public boolean InserirLivro(Livro livro) {
		System.out.println("Nome" + livro.getNome());
		System.out.println("Autor" + livro.getAutor());
		System.out.println("Numero de Paginas" + livro.getNumeroPaginas());
		System.out.println("Descricao" + livro.getDescricao());
		System.out.println("Livro" + livro.getNome()+ "inserido com sucesso!");
		return true;
	}
}