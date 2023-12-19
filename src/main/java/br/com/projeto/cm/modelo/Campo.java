/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.cm.modelo;

import br.com.projeto.cm.excecao.ExplosaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel.ferreira
 */
public class Campo {

    private final int linha;
    private final int coluna;

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public boolean isMinado() {
        return minado;
    }

    public void setMinado(boolean minado) {
        this.minado = minado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public List<Campo> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(List<Campo> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    public boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar() {
        minado = true;
    }
    
    boolean objetivoAlcando() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        
        return desvendado || protegido;
    }
    
    long minasNaVizinhanca() {
        return vizinhos.stream().filter(x -> x.minado).count();
    }
    
    void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }
    
    public String toString() {
        if(marcado) {
            return "x";
        } else if(aberto && minado) {
            return "*";
        } else if(aberto && minasNaVizinhanca() > 0) {
            return Long.toString(minasNaVizinhanca());
        } else if(aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}
