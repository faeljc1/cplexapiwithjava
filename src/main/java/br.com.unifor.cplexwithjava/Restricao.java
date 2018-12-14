package br.com.unifor.cplexwithjava;

import java.util.ArrayList;
import java.util.List;

public class Restricao {
  private List<Double> valores;
  private List<String> variaveis;
  private List<String> sinais;
  private String operador;
  private Double comparador;

  public Restricao() {
  }

  public Restricao(List<Double> valores, List<String> variaveis, List<String> sinais, String operador, Double comparador) {
    this.valores = valores;
    this.variaveis = variaveis;
    this.sinais = sinais;
    this.operador = operador;
    this.comparador = comparador;
  }

  public List<Double> getValores() {
    return valores;
  }

  public void setValores(List<Double> valores) {
    this.valores = valores;
  }

  public List<String> getVariaveis() {
    return variaveis;
  }

  public void setVariaveis(List<String> variaveis) {
    this.variaveis = variaveis;
  }

  public List<String> getSinais() {
    return sinais;
  }

  public void setSinais(List<String> sinais) {
    this.sinais = sinais;
  }

  public String getOperador() {
    return operador;
  }

  public void setOperador(String operador) {
    this.operador = operador;
  }

  public Double getComparador() {
    return comparador;
  }

  public void setComparador(Double comparador) {
    this.comparador = comparador;
  }

  public void addValor(Double valor) {
    if (valores == null) {
      valores = new ArrayList<>();
    }
    valores.add(valor);
  }

  public void addVariavel(String variavel) {
    if (variaveis == null) {
      variaveis = new ArrayList<>();
    }
    variaveis.add(variavel);
  }

  public void addSinal(String sinal) {
    if (sinais == null) {
      sinais = new ArrayList<>();
    }
    sinais.add(sinal);
  }
}
