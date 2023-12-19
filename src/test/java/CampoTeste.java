
import br.com.projeto.cm.excecao.ExplosaoException;
import br.com.projeto.cm.modelo.Campo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriel.ferreira
 */
public class CampoTeste {
    private Campo campo;
    
    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3,3);
    }
    
    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3,4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoDistancia1Embaixo() {
        Campo vizinho = new Campo(4,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoDistancia1EmCima() {
        Campo vizinho = new Campo(2,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoDistanciaDiagonal() {
        Campo vizinho = new Campo(2,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertTrue(resultado);
    }
    
    @Test
    void testeNaoVizinhoDistanciaDiagonal() {
        Campo vizinho = new Campo(1,1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        Assertions.assertFalse(resultado);
    }
    
    @Test
    void testeAlternaMarcacao() {
        Assertions.assertFalse(campo.isMarcado());
    }
    
    @Test
    void testeAlternaMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.isMarcado());
    }
    
    @Test
    void testeValorPadraoAtributoMarcado() {
        campo.alternarMarcacao();
        Assertions.assertTrue(campo.isMarcado());
    }
    
    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        Assertions.assertTrue(campo.abrir());
    }
    
    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        
        Assertions.assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }
    
    
    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        Assertions.assertFalse(campo.abrir());
    }
    
    
    @Test
    void testeAbrirComVizinho() {
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);
        
        campo22.adicionarVizinho(campo11);
        
        campo.adicionarVizinho(campo22);
        campo.abrir();        
        
        Assertions.assertTrue(campo22.isAberto() && campo11.isAberto());
    }
    
    
    @Test
    void testeAbrirComVizinho2() {
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);
        Campo campo12 = new Campo(1,2);
        campo12.minar();
        
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        
        campo.adicionarVizinho(campo22);
        campo.abrir();        
        
        Assertions.assertTrue(campo22.isAberto() && !campo11.isAberto());
    }
    
    
    
}
