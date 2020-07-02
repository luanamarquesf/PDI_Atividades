/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Luana
 */
public class Atividade3 {

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

    public static int[][] clarear_escurecer(int[][] matrix, int op, int fator) {
        int coluna, linha;

        coluna = matrix.length - 1;
        linha = matrix[0].length - 1;

        int[][] img = new int[linha][coluna];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y] = matrix[x][y];
            }
        }
        //clarear
        if (op == 1) {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {

                    img[x][y] += fator;

                }
            }

            return img;
        }//escurecer
        else {
            for (int x = 0; x < coluna; x++) {
                for (int y = 0; y < linha; y++) {

                    img[x][y] -= fator;

                }
            }

            return img;
        }

    }

    public static int[][] binFat(int[][] matrix, int op) {
        int coluna, linha;

        linha = matrix.length - 1;
        coluna = matrix[0].length - 1;

        int[][] img = new int[linha][coluna];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y] = matrix[x][y];
            }
        }
        if (op == 1) {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {

                    if ((img[x][y] <= 125) || (img[x][y] > 220)) {
                        img[x][y] = 10;
                    } else {
                        img[x][y] = 250;
                    }

                }
            }
        } else {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    if (!((img[x][y] <= 150) || (img[x][y] > 250))) {
                        img[x][y] = 200;
                    }
                }
            }
        }

        return img;
    }

    public static int[][] transfPotencia(int[][] imagem, double gama, int const_) {
        int coluna, linha;

        linha = imagem.length - 1;
        coluna = imagem[0].length - 1;

        int[][] img = new int[linha][coluna];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y] = imagem[x][y];
            }
        }

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y] = const_ * (int) Math.pow((imagem[x][y]), gama);
                if (img[x][y] > 255) {
                    img[x][y] = 255;
                }
            }
        }
        return img;
    }

    public static int[][] espelhamento(int[][] img) {
        int coluna, linha;
        int auxLinha, auxColuna;
        int z;

        linha = img.length;
        coluna = img[0].length;

        auxLinha = 0;
        auxColuna = coluna - 1;

        int aux;

        int[][] espelhada = new int[linha][coluna];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                espelhada[x][y] = img[x][y];
            }
        }

        //return cpy_espelhada;
        /*for (int x = 0; x < (int)coluna/2; x++) {
            for (int y = 0; y < linha; y++) {
                aux = img[x][y];
                espelhada[x][y] = img[coluna-x-1][y];
                espelhada[coluna-x-1][y] = aux;
            }
           
        }*/
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                espelhada[i][j] = img[i][coluna - j - 1];
            }
        }

        return espelhada;
    }

    public void caller(String fonte, String destino) throws FileNotFoundException {
        int img[][] = loadMatrix(fonte);
        if (img == null) {
            System.out.println("erro em ler imagem!");
            return;
        }
        
        int[][] fat1 = binFat(img, 1);
        int[][] fat2 = binFat(img, 2);
        System.out.println(destino + "fatiamento1.pgm");

        salvarImagem(fat1, destino + "fatiamento1.pgm");
        salvarImagem(fat2, destino + "fatiamento2.pgm");

        double gama = 1.125;
        img = transfPotencia(img, gama, 1);
        salvarImagem(img, destino + "imagem3.pgm");

        int[][] espelhada = espelhamento(img);
        salvarImagem(espelhada, destino + "imagem4.pgm");

    }

}
