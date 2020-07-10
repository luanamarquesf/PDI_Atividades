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

    public static int[][][] loadMatrix_PPM(String img) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(img));

        String aux = reader.nextLine();
        aux = reader.nextLine();
        System.out.println(aux);

        if (aux.charAt(0) == '#') {
            int coluna = reader.nextInt();
            coluna_ = coluna;

            int linha = reader.nextInt();
            linha_ = linha;

            int[][][] Matriz = new int[linha + 1][coluna + 1][3];

            maxValue = reader.nextInt();

            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    Matriz[x][y][0] = reader.nextInt();
                    Matriz[x][y][1] = reader.nextInt();
                    Matriz[x][y][2] = reader.nextInt();

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

    public static void salvarImagem_PPM(int[][][] imagem, String namepath) {
        try {
            File file = new File(namepath);
            FileWriter fw = new FileWriter(file);
            fw.write("P3");
            fw.write("\n");
            fw.write(Integer.toString(coluna_));
            fw.write(" ");
            fw.write(Integer.toString(linha_));
            fw.write("\n");
            fw.write(Integer.toString(maxValue));
            fw.write("\n");
            for (int i = 0; i < linha_; i++) {
                for (int j = 0; j < coluna_; j++) {
                    fw.write(imagem[i][j][0] + " ");
                    fw.write(imagem[i][j][1] + " ");
                    fw.write(imagem[i][j][2] + " ");

                }
                fw.write("\n");
            }
            fw.flush();
        } catch (IOException e) {
        }
    }

    public static int[][] binFat(int[][] matrix, int op,  int sup, int inf) {
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

                    if ((img[x][y] <= inf) || (img[x][y] > sup)) {
                        img[x][y] = 10;
                    } else {
                        img[x][y] = 250;
                    }

                }
            }
        } else {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    if (!((img[x][y] <= inf) || (img[x][y] > sup))) {
                        img[x][y] = 200;
                    }
                }
            }
        }

        return img;
    }

    public static int[][][] binFat_PPM(int[][][] matrix, int op, int sup, int inf) {
        int coluna, linha;

        linha = matrix.length - 1;
        coluna = matrix[0].length - 1;

        int[][][] img = new int[linha][coluna][3];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y][0] = matrix[x][y][0];
                img[x][y][1] = matrix[x][y][1];
                img[x][y][2] = matrix[x][y][2];

            }
        }
        if (op == 1) {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {

                    if ((img[x][y][0] < inf) || (img[x][y][0] > sup)) {
                        img[x][y][0] = 10;
                    } else {
                        img[x][y][0] = 250;
                    }
                    if ((img[x][y][1] < inf) || (img[x][y][1] > sup)) {
                        img[x][y][1] = 10;
                    } else {
                        img[x][y][1] = 250;
                    }
                    if ((img[x][y][2] < inf) || (img[x][y][2] > sup)) {
                        img[x][y][2] = 10;
                    } else {
                        img[x][y][2] = 250;
                    }

                }
            }
        } else {
            for (int x = 0; x < linha; x++) {
                for (int y = 0; y < coluna; y++) {
                    if (!((img[x][y][0] <= inf) || (img[x][y][0] > sup))) {
                        img[x][y][0] = 200;
                    }
                    if (!((img[x][y][1] <= inf) || (img[x][y][1] > sup))) {
                        img[x][y][1] = 200;
                    }
                    if (!((img[x][y][2] <= inf) || (img[x][y][2] > sup))) {
                        img[x][y][2] = 200;
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
                double convertido = img[x][y] / 255.0;
                int funcao = (int) Math.round((const_ * (Math.pow(convertido, gama))));
                img[x][y] = const_ * (int) Math.pow((imagem[x][y]), gama);
                img[x][y] = (int) Math.round(funcao * 255.0);
                if (img[x][y] > 255) {
                    img[x][y] = 255;
                }
            }
        }
        return img;
    }

    public static int[][][] transfPotencia_PPM(int[][][] imagem, double gama, int const_) {
        int coluna, linha;

        linha = imagem.length - 1;
        coluna = imagem[0].length - 1;

        int[][][] img = new int[linha][coluna][3];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                img[x][y][0] = imagem[x][y][0];
                img[x][y][1] = imagem[x][y][1];
                img[x][y][2] = imagem[x][y][2];

            }
        }

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                double convertido = img[x][y][0] / 255.0;
                double convertido1 = img[x][y][1] / 255.0;
                double convertido2 = img[x][y][2] / 255.0;

                int funcao = (int) Math.round((const_ * (Math.pow(convertido, gama))));
                int funcao1 = (int) Math.round((const_ * (Math.pow(convertido1, gama))));
                int funcao2 = (int) Math.round((const_ * (Math.pow(convertido2, gama))));

                //img[x][y] = const_ * (int) Math.pow((imagem[x][y]), gama);
                img[x][y][0] = (int) Math.round(funcao * 255.0);
                img[x][y][1] = (int) Math.round(funcao1 * 255.0);
                img[x][y][2] = (int) Math.round(funcao2 * 255.0);

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

    public static int[][][] espelhamento_PPM(int[][][] img) {
        int coluna, linha;
        int auxLinha, auxColuna;
        int z;

        linha = img.length;
        coluna = img[0].length;

        auxLinha = 0;
        auxColuna = coluna - 1;

        int aux;

        int[][][] espelhada = new int[linha][coluna][3];

        for (int x = 0; x < linha; x++) {
            for (int y = 0; y < coluna; y++) {
                espelhada[x][y][0] = img[x][y][0];
                espelhada[x][y][1] = img[x][y][1];
                espelhada[x][y][2] = img[x][y][2];

            }
        }

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                espelhada[i][j][0] = img[i][coluna - j - 1][0];
                espelhada[i][j][1] = img[i][coluna - j - 1][1];
                espelhada[i][j][2] = img[i][coluna - j - 1][2];

            }
        }

        return espelhada;
    }

    public void caller(String fonte, String destino, int tipo, int s, int i) throws FileNotFoundException {
        if (tipo == 1) {
            int img[][] = loadMatrix(fonte);
            if (img == null) {
                System.out.println("erro em ler imagem!");
                return;
            }

            int[][] fat1 = binFat(img, 1,s,i);
            int[][] fat2 = binFat(img, 2,s,i);
            System.out.println(destino + "fatiamento1.pgm");

            salvarImagem(fat1, destino + "fatiamento1.pgm");
            salvarImagem(fat2, destino + "fatiamento2.pgm");

            double gama = 1.125;
            int[][] imag = transfPotencia(img, gama, 1);
            salvarImagem(imag, destino + "imagem3.pgm");

            int[][] espelhada = espelhamento(img);
            salvarImagem(espelhada, destino + "imagem4.pgm");

        } else {
            int img[][][] = loadMatrix_PPM(fonte);
            if (img == null) {
                System.out.println("erro em ler imagem!");
                return;
            }
            double gama = 0.74;

            int[][][] imag = transfPotencia_PPM(img, gama, 1);

            salvarImagem_PPM(imag, destino + "ppm_gama.ppm");

            int[][][] espelhada = espelhamento_PPM(img);

            salvarImagem_PPM(espelhada, destino + "ppm_espelhamento.ppm");

            int[][][] binfati = binFat_PPM(img, 1, s, i);

            salvarImagem_PPM(binfati, destino + "ppm_binfat1.ppm");

            int[][][] binfati2 = binFat_PPM(img, 2,s,i);
            salvarImagem_PPM(binfati2, destino + "ppm_binfat2.ppm");

        }

    }

}
