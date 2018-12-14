package br.com.unifor.cplexwithjava;

import ilog.concert.IloException;
import ilog.concert.IloIntExpr;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("Informe a função");
    Scanner s = new Scanner(System.in);
    String aux = s.nextLine().replaceAll(" ", "");

    String[] funcao = aux.split("s\\.t\\.");

    String operador = funcao[0].substring(0, 3);

    String funcaoObj = funcao[0].replace(operador, "").replaceAll(";", "");
    String valor = "";

    FuncaoObjetiva f = new FuncaoObjetiva();
    for (int i = 0; i < funcaoObj.length(); i++) {
      if (funcaoObj.charAt(i) == '+' || funcaoObj.charAt(i) == '-') {
        f.addSinal(funcaoObj.charAt(i) + "");
      } else if (Character.isDigit(funcaoObj.charAt(i)) || funcaoObj.charAt(i) == '.') {
        valor += funcaoObj.charAt(i) + "";
      } else {
        f.addVariavel(funcaoObj.charAt(i) + "");
        f.addValor(new Double(valor));
        valor = "";
      }
    }

    funcao = funcao[1].split("r\\.");

    List<Restricao> r = new ArrayList<>();
    String[] restricoes = funcao[0].split(";");
    for (int i = 0; i < restricoes.length; i++) {
      restricoes[i] = restricoes[i].replaceAll(";", "");

      Restricao res = new Restricao();
      if (restricoes[i].contains(">=")) {
        res.setOperador(">=");
        res.setComparador(getComparador(restricoes[i], "="));
        restricoes[i] = restricoes[i].split(">")[0];
      } else if (restricoes[i].contains("<=")) {
        res.setOperador("<=");
        res.setComparador(getComparador(restricoes[i], "="));
        restricoes[i] = restricoes[i].split("<")[0];
      } else if (restricoes[i].contains(">")) {
        res.setOperador(">");
        res.setComparador(getComparador(restricoes[i], ">"));
        restricoes[i] = restricoes[i].split(">")[0];
      } else if (restricoes[i].contains("<")) {
        res.setOperador("<");
        res.setComparador(getComparador(restricoes[i], "<"));
        restricoes[i] = restricoes[i].split("<")[0];
      }
      for (int j = 0; j < restricoes[i].length(); j++) {
        if (restricoes[i].charAt(j) == '+' || restricoes[i].charAt(j) == '-') {
          res.addSinal(restricoes[i].charAt(j) + "");
        } else if (Character.isDigit(restricoes[i].charAt(j)) || restricoes[i].charAt(j) == '.') {
          valor += restricoes[i].charAt(j) + "";
        } else {
          res.addVariavel(restricoes[i].charAt(j) + "");
          res.addValor(new Double(valor));
          valor = "";
        }
      }
      r.add(res);
    }

    String[] condicoes = funcao[1].split(",");
    String[] variaveis = new String[condicoes.length];
    for (int i = 0; i < condicoes.length; i++) {
      condicoes[i] = condicoes[i].replaceAll(";", "");
      variaveis[i] = "" + condicoes[i].replaceAll(";", "").charAt(0);
    }


    Equacao equacao = new Equacao(operador, f, r, condicoes, variaveis);

    cplexProcess(equacao);
  }

  public static void cplexProcess(Equacao equacao) {
    try {
      IloCplex cplex = new IloCplex();

      IloNumVar[] vars = null;
      vars = new IloNumVar[equacao.getCondicoes().length];
      for (int i = 0; i < equacao.getCondicoes().length; i++) {
        String eq = equacao.getCondicoes()[i];
        vars[i] = cplex.numVar(Integer.parseInt(eq.substring(2, 3)),
            eq.substring(1, 2).equals(">") ? Double.MAX_VALUE : Double.MIN_VALUE, eq.substring(0, 1));
      }

      IloLinearNumExpr objective = cplex.linearNumExpr();

      for (int i = 0; i < equacao.getFuncaoObjetiva().getValores().size(); i++) {
        objective.addTerm(equacao.getFuncaoObjetiva().getValores().get(i), vars[i]);
      }

      if (equacao.getOperador().equals("min")) {
        cplex.addMinimize(objective);
      } else {
        cplex.addMaximize(objective);
      }


      try {
        for (int i = 0; i < equacao.getRestricoes().size(); i++) {
          Restricao restricao = equacao.getRestricoes().get(i);
          IloIntExpr[] prods = new IloIntExpr[vars.length];
          for (int j = 0; j < restricao.getValores().size(); j++) {
            prods[j] = (IloIntExpr) cplex.prod(restricao.getValores().get(j), vars[j]);
          }

          IloIntExpr sum = cplex.sum(prods);
          cplex.addGe(sum, restricao.getComparador());
        }
        cplex.solve();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
      } catch (IloException e) {
        e.printStackTrace();
      }

      // min 0.12x + 0.15y; s.t. 60x+60y>=300;12x+6y>=36;10x+30y>=90; r.    x>0,y>0;

    } catch (IloException e) {
      e.printStackTrace();
    }
  }

  public static Double getComparador(String v, String operador) {
    Double valor = new Double(0);
    for (int i = 0; i < v.length(); i++) {
      if (v.charAt(i) == operador.charAt(0)) {
        return valor = new Double(v.substring(i + 1, v.length()));
      }
    }
    return valor;
  }
}
