package com.checkersgame.tests;

import com.checkersgame.clases.FichaBlanca;
import org.junit.Assert;
import org.junit.*;

public class FichBlancaTest {
    
    @Test
    public void testValidarComidaReina(){
        FichaBlanca ficha = new FichaBlanca();
        int [] estadoCasillas = {1,5,0,5,0,5,1,5,5,1,5,1,5,1,5,0,1,5,1,5,0,5,0,5,5,1,5,0,5,3,5,1,1,5,0,5,2,5,0,5,5,1,5,0,5,0,5,0,2,5,2,5,2,5,2,5,5,0,5,0,5,2,5,2};
        
        int resultado = ficha.validarComidaReina(29, estadoCasillas);
        Assert.assertEquals(1, resultado);
    }
}
