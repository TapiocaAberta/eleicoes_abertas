package org.eleicoesabertas.util;

import javax.persistence.NoResultException;
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
					"Erro conectando ao banco de dados, desculpe-nos o inc√¥modo. Contate o administrador do sistema se o erro persistir...",
					Status.INTERNAL_SERVER_ERROR);
	}

	public static boolean checa(String str, int lenght) {
		return checa(str) || str.length() < lenght;
	}

	public static boolean checa(String str) {
		return str == null || str.isEmpty();
	}

	public static WebApplicationException trataErro(Exception e) {
		if (e instanceof NoResultException) {
			//N„o È necess·rio printar o stack trace, erro previsto
			return new WebApplicationException(404);
		}
		else if(e instanceof WebApplicationException){
			return (WebApplicationException)e;
		}
		
		else {
			// Printa o stack trace para saber o que aconteceu
			e.printStackTrace();
			return new WebApplicationException(500);
		}

	}

}
