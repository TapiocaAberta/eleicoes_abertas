package org.eleicoesabertas.recursos;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eleicoesabertas.model.Erro;

public class RecursosUtil {

	public static void lancaErro(String mensagem, Status status) {
		throw new WebApplicationException(Response.status(status)
				.entity(new Erro(mensagem)).build());
	}

	public static void lancaErro(String mensagem) {
		lancaErro(mensagem, Status.BAD_REQUEST);
	}

	public static void lancaErroPorExcecao(Exception e, String mensagem) {
		if (e instanceof javax.persistence.NoResultException)
			lancaErro(mensagem, Status.BAD_REQUEST);
		else
			lancaErro(
					"Erro conectando ao banco de dados, desculpe-nos o incômodo. Contate o administrador do sistema se o erro persistir...",
					Status.INTERNAL_SERVER_ERROR);
	}

	public static boolean checa(String str, int lenght) {
		return checa(str) || str.length() < lenght;
	}

	public static boolean checa(String str) {
		return str == null || str.isEmpty();
	}

}
