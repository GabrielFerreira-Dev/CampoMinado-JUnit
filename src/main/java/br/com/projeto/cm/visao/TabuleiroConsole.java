/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.cm.visao;

import br.com.projeto.cm.excecao.ExplosaoException;
import br.com.projeto.cm.excecao.SairException;
import br.com.projeto.cm.modelo.Tabuleiro;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author gabriel.ferreira
 */
public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);
    
    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while(continuar) {
                cicloDoJogo();
                
                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();
                if("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                };
            }
            
        } catch (SairException e) {
            System.out.println("Obrigado por jogar!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while(!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro.toString());
                String digitado = capturarTextoUsuario("Digite (x,y): ");
                Iterator<Integer> xy = Arrays.stream(digitado.trim().split(",")).map(e -> Integer.parseInt(e)).iterator();
                
                
                digitado = capturarTextoUsuario("1 - Abrir ou 2 - (Des)marcar: ");
                
                if("1".equalsIgnoreCase(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if("2".equalsIgnoreCase(digitado)) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
             }
            
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu!");
        }
    }
    
    private String capturarTextoUsuario(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();
        
        if("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        };
        
        return digitado;
    }
}
