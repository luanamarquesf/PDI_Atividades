/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividadade4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Luana
 */
public class Atividade4 {

    public static int maxValue;
    public static int coluna_;
    public static int linha_;

    public static int[][] loadMatrix(String img) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(img));

        String aux = reader.nextLine();
        aux = reader.nextLine();
        System.out.println(aux);

        if (aux.charAt(0) == '#') {
            int coluna = reader.nextInt();
            coluna_ = coluna;

            int linha = reader.nextInt();
            linha_ = linha;

            int[][] Matriz = new int[linha + 1][coluna + 1];

            maxValue = reader.nextInt();

            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    Matriz[x][y] = reader.nextInt();
                }

            }
            return Matriz;
        } else {
            return null;
        }

    }

    public static void salvarImagem(int[][] imagem, String namepath) {
        try {
            File file = new File(namepath);
            FileWriter fw = new FileWriter(file);
            fw.write("P2");
            fw.write("\n");
            fw.write(Integer.toString(coluna_));
            fw.write(" ");
            fw.write(Integer.toString(linha_));
            fw.write("\n");
            fw.write(Integer.toString(maxValue));
            fw.write("\n");
            for (int i = 0; i < linha_; i++) {
                for (int j = 0; j < coluna_; j++) {
                    fw.write(imagem[i][j] + " ");
                }
                fw.write("\n");
            }
            fw.flush();
        } catch (IOException e) {
        }
    }

    public static int[] calcularHistograma(int matriz[][]) {
        int histograma[] = new int[maxValue + 1];
        for (int i = 0; i < linha_; i++) {
            for (int j = 0; j < coluna_; j++) {

                histograma[matriz[i][j]] += 1;
            }
        }
        return histograma;
    }

    public static int[][] equalizarHistograma(int img[][]) {
        int[] histograma = Atividade4.calcularHistograma(img);
        int mn = linha_ * coluna_;
        float[] pr = new float[maxValue + 1];
        int[] sk = new int[maxValue + 1];

        for (int i = 0; i < pr.length; i++) {
            pr[i] = ((float) histograma[i]) / mn;
        }

        float acumulado = 0.0f;
        for (int i = 0; i < sk.length; i++) {
            acumulado += pr[i];
            sk[i] = (int) Math.round(maxValue * acumulado);
        }
       
        for (int i = 0; i < linha_; i++) {
            for (int j = 0; j < coluna_; j++) {
                img[i][j] = sk[img[i][j]];
            }
        }

        return img;
    }

    public void caller(String fonte, String destino) throws FileNotFoundException {
        int img[][] = loadMatrix(fonte);
        if (img == null) {
            System.out.println("erro em ler imagem!");
            return;
        }

        int[][] eqhist = equalizarHistograma(img);

        salvarImagem(eqhist, destino + "equalizada.pgm");
    }

}
