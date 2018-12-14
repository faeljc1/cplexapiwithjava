package br.com.unifor.cplexwithjava;

import java.util.List;

public class Equacao {
  // min; max
  private String operador;

  private FuncaoObjetiva funcaoObjetiva;

  private List<Restricao> restricoes;

  private String[] condicoes;

  private String[] variaveis;

  public Equacao() {
  }

  public Equacao(String operador, FuncaoObjetiva funcaoObjetiva, List<Restricao> restricoes, String[] condicoes, String[] variaveis) {
    this.operador = operador;
    this.funcaoObjetiva = funcaoObjetiva;
    this.restricoes = restricoes;
    this.condicoes = condicoes;
    this.variaveis = variaveis;
  }

  public String getOperador() {
    return operador;
  }

  public void setOperador(String operador) {
    this.operador = operador;
  }

  public FuncaoObjetiva getFuncaoObjetiva() {
    return funcaoObjetiva;
  }

  public void setFuncaoObjetiva(FuncaoObjetiva funcaoObjetiva) {
    this.funcaoObjetiva = funcaoObjetiva;
  }

  public List<Restricao> getRestricoes() {
    return restricoes;
  }

  public void setRestricoes(List<Restricao> restricoes) {
    this.restricoes = restricoes;
  }

  public String[] getCondicoes() {
    return condicoes;
  }

  public void setCondicoes(String[] condicoes) {
    this.condicoes = condicoes;
  }

  public String[] getVariaveis() {
    return variaveis;
  }

  public void setVariaveis(String[] variaveis) {
    this.variaveis = variaveis;
  }
}
