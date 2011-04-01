package org.eleicoesabertas.recursos;

import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eleicoesabertas.definicoes.Mensagens;



public class Verificacoes {

	public String verificaChave(@QueryParam("chave") String chave) {
		if (chave == null) {
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity(Mensagens.INFORMAR_CHAVE)
					.build());
		}
		if (!chave.equals("123")) {
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity(Mensagens.CHAVE_ERRADA)
					.build());
		}
		return "";
	}

}
