package org.eleicoesabertas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "candidato")
@NamedQueries(value = {
		@NamedQuery(name = "buscaCandidatoPorEleicao", query = "select c from Candidato c where c.eleicao = ?1 ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoEleitoPorEleicao", query = "select c from Candidato c where c.eleicao = ?1 AND c.resultadoEleicao.resultadoEleicao = 'Eleito' ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoPorEleicaoEEstado", query = "select c from Candidato c where c.eleicao = ?1 AND c.estado = ?2 ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoEleitoPorEleicaoEEstado", query = "select c from Candidato c where c.eleicao = ?1 AND c.estado = ?2 "
				+ "AND c.resultadoEleicao.resultadoEleicao = 'Eleito' ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoPorId", query = "select c from Candidato c where c.eleicao = ?1 AND c.id = ?2"),
		@NamedQuery(name = "buscaCandidatoPorEleicaoENome", query = "select c from Candidato c where c.eleicao = ?1 AND c.nome LIKE ?2 ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoPorEleicaoEEstadoECargo", query = "select c from Candidato c "
				+ "where c.eleicao = ?1 AND c.estado = ?2 AND c.cargo = ?3 ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoPorEleicaoEEstadoECargoEPartido", query = "select c from Candidato c "
				+ "where c.eleicao = ?1 AND c.estado = ?2 AND c.cargo = ?3 AND c.partido = ?4 ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoEleitoPorEleicaoEEstadoECargo", query = "select c from Candidato c "
				+ "where c.eleicao = ?1 AND c.estado = ?2 AND c.cargo = ?3 AND c.resultadoEleicao.resultadoEleicao = 'Eleito' ORDER BY c.nome"),
		@NamedQuery(name = "buscaCandidatoEleitoPorEleicaoEEstadoECargoEPartido", query = "select c from Candidato c "
				+ "where c.eleicao = ?1 AND c.estado = ?2 AND c.cargo = ?3 AND c.partido = ?4  AND c.resultadoEleicao.resultadoEleicao = 'Eleito' ORDER BY c.nome") })
@XmlType(propOrder = { "id", "nome", "nomeUrna", "numeroEleicao",
		"cnpjCampanha", "dataNascimento", "eleicao", "partido", "coligacao",
		"cargo", "cidadeNascimento", "estado", "urlPaginaTse", "urlDadosEmPdf",
		"urlDadosArrecadao", "urlDadosDespesa", "numeroSequencia",
		"estadoCivil", "grauInstrucao", "nacionalidade", "ocupacao",
		"processo", "protocolo", "resultadoEleicao", "sexo", "situacao" })
@XmlRootElement(name = "candidato")
public class Candidato {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "nome_urna", nullable = false, length = 50)
	private String nomeUrna;

	@Column(name = "numero_candidato", nullable = false, length = 50)
	private String numeroEleicao;

	@Column(name = "data_nascimento", nullable = false)
	private String dataNascimento;

	@Column(name = "protocolo", nullable = false, length = 45)
	private String protocolo;

	@Column(name = "processo", nullable = false, length = 45)
	private String processo;

	@Column(name = "cnpj_campanha", length = 45)
	private String cnpjCampanha;

	// Início novas colunas dia 22/11/2010
	@Column(name = "url_pagina_tse", length = 100)
	private String urlPaginaTse;

	@Column(name = "url_arrecadacao", length = 100)
	private String urlDadosArrecadao;

	@Column(name = "url_despesa", length = 100)
	private String urlDadosDespesa;

	@Column(name = "url_dados_pdf", length = 100)
	private String urlDadosEmPdf;

	@Column(name = "sequencia", length = 50)
	private String numeroSequencia;
	// Fim novas colunas dia 22/11/2010

	@ManyToOne
	// (cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
	// CascadeType.REMOVE })
	private Situacao situacao;

	@ManyToOne
	private Sexo sexo;

	@ManyToOne
	private Partido partido;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@ManyToOne(optional = true)
	private Coligacao coligacao;

	@ManyToOne
	private Nacionalidade nacionalidade;

	@ManyToOne
	@JoinColumn(name = "grau_instrucao_id")
	private GrauInstrucao grauInstrucao;

	@ManyToOne
	@JoinColumn(name = "resultado_eleicao_id")
	private ResultadoEleicao resultadoEleicao;

	@ManyToOne
	@JoinColumn(name = "estado_civil_id")
	private EstadoCivil estadoCivil;

	@ManyToOne
	@JoinColumn(name = "cidade_nascimento_id")
	private Cidade cidadeNascimento;

	// Se for nulo, será o Brasil :D
	@ManyToOne(optional = true)
	private Estado estado;

	@ManyToOne
	private Ocupacao ocupacao;

	@ManyToOne
	private Eleicao eleicao;

	public Candidato() {

	}

	public Candidato(String nome, String nomeUrna, String numeroEleicao,
			String dataNascimento, String protocolo, String processo,
			String cnpjCampanha, Situacao situacao, Sexo sexo, Partido partido,
			Cargo cargo, Coligacao coligacao, Nacionalidade nacionalidade,
			GrauInstrucao grauInstrucao, ResultadoEleicao resultadoEleicao,
			EstadoCivil estadoCivil, Cidade cidadeNascimento, Estado estado,
			Ocupacao ocupacao, Eleicao eleicao) {
		setNome(nome);
		setNomeUrna(nomeUrna);
		setNumeroEleicao(numeroEleicao);
		setDataNascimento(dataNascimento);
		setProtocolo(protocolo);
		setProcesso(processo);
		setCnpjCampanha(cnpjCampanha);
		setSituacao(situacao);
		setSexo(sexo);
		setPartido(partido);
		setCargo(cargo);
		setColigacao(coligacao);
		setNacionalidade(nacionalidade);
		setGrauInstrucao(grauInstrucao);
		setResultadoEleicao(resultadoEleicao);
		setEstadoCivil(estadoCivil);
		setCidadeNascimento(cidadeNascimento);
		setEstado(estado);
		setOcupacao(ocupacao);
		setEleicao(eleicao);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUrna() {
		return nomeUrna;
	}

	public void setNomeUrna(String nomeUrna) {
		this.nomeUrna = nomeUrna;
	}

	public String getNumeroEleicao() {
		return numeroEleicao;
	}

	public void setNumeroEleicao(String numeroEleicao) {
		this.numeroEleicao = numeroEleicao;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getCnpjCampanha() {
		return cnpjCampanha;
	}

	public void setCnpjCampanha(String cnpjCampanha) {
		this.cnpjCampanha = cnpjCampanha;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Coligacao getColigacao() {
		return coligacao;
	}

	public void setColigacao(Coligacao coligacao) {
		this.coligacao = coligacao;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public GrauInstrucao getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(GrauInstrucao grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public ResultadoEleicao getResultadoEleicao() {
		return resultadoEleicao;
	}

	public void setResultadoEleicao(ResultadoEleicao resultadoEleicao) {
		this.resultadoEleicao = resultadoEleicao;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Cidade getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(Cidade cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Eleicao getEleicao() {
		return eleicao;
	}

	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}

	public String getUrlPaginaTse() {
		return urlPaginaTse;
	}

	public void setUrlPaginaTse(String urlPaginaTse) {
		this.urlPaginaTse = urlPaginaTse;
	}

	public String getUrlDadosEmPdf() {
		return urlDadosEmPdf;
	}

	public void setUrlDadosEmPdf(String urlDadosEmPdf) {
		this.urlDadosEmPdf = urlDadosEmPdf;
	}

	public String getUrlDadosArrecadao() {
		return urlDadosArrecadao;
	}

	public void setUrlDadosArrecadao(String urlDadosArrecadao) {
		this.urlDadosArrecadao = urlDadosArrecadao;
	}

	public String getUrlDadosDespesa() {
		return urlDadosDespesa;
	}

	public void setUrlDadosDespesa(String urlDadosDespesa) {
		this.urlDadosDespesa = urlDadosDespesa;
	}

	public String getNumeroSequencia() {
		return numeroSequencia;
	}

	public void setNumeroSequencia(String numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}

}
