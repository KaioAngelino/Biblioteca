package br.com.biblioteca.db;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.biblioteca.model.bean.Livro;

public class LivroDAO {

	private Integer idLivro;
	private String filtroNome;
	private String filtroAutor;
	private String filtroDisponibilidade;

	public LivroDAO() {
		idLivro = 0;
		filtroNome = "";
		filtroAutor = "";
	}

	public void setidLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public List<?> getLivros() {

		EntityManager em = EMFactory.obterEM();

		if (filtroAutor == null && filtroNome == null) {
			return em.createNamedQuery("todosLivros").getResultList();
		} else {
			/* Busca Filtrada */
			String query = "FROM Livro l WHERE 1=1 ";
			if (!filtroNome.isEmpty()) {
				query += "AND l.nome LIKE '" + filtroNome + "%' "; // ATENÇÂO: uso de %
			}
			if (!filtroAutor.isEmpty() && !filtroAutor.equals("0")) {
				query += "AND l.autor = " + filtroAutor;
			}
			if (!filtroDisponibilidade.isEmpty() && !filtroDisponibilidade.equals("0")) {
				query += "AND l.disponibilidade = " + filtroDisponibilidade;
			}

			List<?> resultados = em.createQuery(query).getResultList();
			return resultados;
		}

	}

	public Livro getLivro() {

		EntityManager em = EMFactory.obterEM();
		return em.find(Livro.class, idLivro);
	}

	public boolean salvarLivro(Livro l) {

		EntityManager em = EMFactory.obterEM();
		em.getTransaction().begin();

		if (l.getId() > 0) {

			String query = "UPDATE Livro p SET ";
			if (l.getNome() != null) {
				query += "l.nome = '" + l.getNome() + "' ";
			}
			/*
			 * TODO: Tratamento para upload de Imagem
			 */
			if (l.getAutor() != null) {
				query += ", l.autor = '" + l.getAutor();
			}
			if (l.getNumeroPaginas() > 0) {
				query += ", l.numeroPaginas = " + l.getNumeroPaginas();
			}
			if (l.getDescricao() != null) {
				query += ", l.descricao = " + l.getDescricao() + " ";
			}

			/* Fixo - Checkbox */
			query += ", l.disponibilidade = " + l.isDisponibilidade() + " ";

			query += "WHERE l.id = " + l.getId();

			em.createQuery(query).executeUpdate();

		} else {
			em.persist(l);
		}

		em.getTransaction().commit();
		em.close();
		return true;
	}

	public boolean excluirLivro(Integer idLivro) {

		EntityManager em = EMFactory.obterEM();
		em.getTransaction().begin();

		Livro l = (Livro) em.find(Livro.class, idLivro);
		em.remove(l);

		em.getTransaction().commit();
		em.close();
		return true;
	}

	/* Seção de Getters e Setters para os filtros */

}
