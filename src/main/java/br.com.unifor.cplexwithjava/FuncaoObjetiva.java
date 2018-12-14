package br.com.unifor.cplexwithjava;

import java.util.ArrayList;
import java.util.List;

public class FuncaoObjetiva {
  private List<Double> valores;
  private List<String> variaveis;
  private List<String> sinais;

  public FuncaoObjetiva() {
  }

  public FuncaoObjetiva(List<Double> valores, List<String> variaveis, List<String> sinais) {
    this.valores = valores;
    this.variaveis = variaveis;
    this.sinais = sinais;
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
