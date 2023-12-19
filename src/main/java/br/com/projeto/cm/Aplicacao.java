/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.cm;

import br.com.projeto.cm.modelo.Tabuleiro;
import br.com.projeto.cm.visao.TabuleiroConsole;

/**
 *
 * @author gabriel.ferreira
 */
public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);
    }
}
