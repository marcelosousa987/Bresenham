/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class ControllerBresenham extends JPanel {

    private final boolean gradeAtivada;
    private final boolean linhaAtivada;
    private final boolean bresenhamAtivada;

    private int x, x2, y, y2; // tamanho da linha 
    private int jpX, jpY; // tamanho dos quadrados da grade
    private int quadSize; // tamanho da grade

    public ControllerBresenham(int x, int x2, int y, int y2,
            int jpX, int jpY,
            boolean gradeAtivada, boolean linhaAtivada, boolean bresenhamAtivada) {

        this.x = x;
        this.x2 = x2;
        this.y = y;
        this.y2 = y2;

        this.jpX = jpX;
        this.jpY = jpY;
        this.gradeAtivada = gradeAtivada;
        this.linhaAtivada = linhaAtivada;
        this.bresenhamAtivada = bresenhamAtivada;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPixels(g);
        setBackground(Color.WHITE);
    }

    public int getQuadSize() {
        return quadSize;
    }

    public void setQuadSize(int quadSize) {
        this.quadSize = quadSize;
    }

    public void showBresenham(Graphics g){
            int x  = this.x;
            int x2 = this.x2;
            int y  = this.y;
            int y2 = this.y2;

            int Dx = x2 - x; // Delta X, verifica a diferença entre ponto final X e ponto inicial X
            int Dy = y2 - y; // Delta Y, verifica a diferença entre ponto final Y e ponto inicial Y
            int D =  2 * Dy - Dx; // Delta Y * 2 - Delta X
            int Dn = 2 * Dy; // Delta Y * 2
            int Dp = 2 * (Dy - Dx); // 2 * (Delta Y - Delta X)
           
            g.setColor(Color.YELLOW);
            
            g.fillRect(x, y, quadSize, quadSize);
            while (x < x2) { // Enquanto ponto inicial nao atingir o ponto final
                x += quadSize; // Incrementa o tamanho do quadrado em X 
                if (D < 0) { 
                    D += Dn;
                } else {
                    y += quadSize; // Incrementa o tamanho do quadrado em Y
                    D += Dp;
                }
                g.fillRect(x, y, quadSize, quadSize);
            }
    }
    
    public void drawPixels(Graphics g) {
        int i, j;

        System.out.println("X: " + x + " X2: " + x2
                + "Y: " + y + " Y2: " + y2);
        
        if (gradeAtivada) { // Cria a matriz
            for (i = 0; i <= this.jpX; i += quadSize) 
                for (j = 0; j <= this.jpY; j += quadSize) 
                    g.drawRect(i, j, quadSize, quadSize);
        }

        if (bresenhamAtivada) { // Gera algoritmo
           showBresenham(g);
        }
        
        if (linhaAtivada) { // Gera a linha
            g.setColor(Color.GREEN);
            g.drawLine(x, y, x2, y2);
        }
    }

}
