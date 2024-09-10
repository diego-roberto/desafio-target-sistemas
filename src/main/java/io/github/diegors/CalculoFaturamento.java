package io.github.diegors;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.text.NumberFormat;

public class CalculoFaturamento {

        public static void main(String[] args) {
            double[] faturamento = preencherFaturamento();
//            calcularEstatisticas(faturamento);
//            calcularEstatisticasStreams(faturamento);

            /* por que não medir performance dos métodos para calcular estatisticas? */
            long inicio1 = System.nanoTime();
            calcularEstatisticas(faturamento);
            long fim1 = System.nanoTime();
            long duracao1 = fim1 - inicio1;
            System.out.println("Tempo gasto pelo método calcularEstatisticas: " + duracao1 + " nanosegundos\n");

            long inicio2 = System.nanoTime();
            calcularEstatisticasStreams(faturamento);
            long fim2 = System.nanoTime();
            long duracao2 = fim2 - inicio2;
            System.out.println("Tempo gasto pelo método calcularEstatisticasStreams: " + duracao2 + " nanosegundos\n");

        }

        public static double[] preencherFaturamento() {
            double[] faturamento = new double[365];
            Random random = new Random();

            /* simula dias da semana (dias 0 a 6 são segunda a domingo)
            * valor aleatorio de faturamento (entre 1000 e 5000) */
            for (int i = 0; i < faturamento.length; i++) {

                int diaDaSemana = i % 7;

                if (diaDaSemana == 5 || diaDaSemana == 6) {
                    faturamento[i] = 0.0;
                } else {
                    faturamento[i] = 1000 + random.nextDouble() * 4000;
                }
            }

            System.out.println("Faturamento gerado para o ano.\n");
            return faturamento;
        }

        public static void mostrarResultado(double menorFaturamento, double maiorFaturamento, int diasAcimaDaMedia){
            /* apenas para formatar o valor bonitinho... não afeta o desempenho :) */
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            System.out.println("Menor valor de faturamento em um dia do ano: " + formatoMoeda.format(menorFaturamento));
            System.out.println("Maior valor de faturamento em um dia do ano: " + formatoMoeda.format(maiorFaturamento));
            System.out.println("Número de dias com faturamento acima da média anual: " + diasAcimaDaMedia);
        }

        public static void calcularEstatisticas(double[] faturamento) {
            System.out.println("Método utilizando lógica clássica, sem recursos adicionais\n");

            double menorFaturamento = Double.MAX_VALUE;
            double maiorFaturamento = Double.MIN_VALUE;
            double somaFaturamento = 0.0;
            int diasComFaturamento = 0;

            /* encontrar o menor, o maior e a soma total dos dias com faturamento */
            for (double valor : faturamento) {
                if (valor > 0) {
                    if (valor < menorFaturamento) {
                        menorFaturamento = valor;
                    }
                    if (valor > maiorFaturamento) {
                        maiorFaturamento = valor;
                    }
                    somaFaturamento += valor;
                    diasComFaturamento++;
                }
            }

            /* calcular a média anual de faturamento (somente dias com faturamento) */
            double mediaAnual = somaFaturamento / diasComFaturamento;

            /* contar quantos dias o faturamento foi maior que a média anual */
            int diasAcimaDaMedia = 0;
            for (double valor : faturamento) {
                if (valor > 0 && valor > mediaAnual) {
                    diasAcimaDaMedia++;
                }
            }

            mostrarResultado(menorFaturamento, maiorFaturamento, diasAcimaDaMedia);
        }

    public static void calcularEstatisticasStreams(double[] faturamento) {
        System.out.println("Método utilizando Streams\n");
        double menorFaturamento = Arrays.stream(faturamento).filter(valor -> valor > 0).min().orElse(0);

        double maiorFaturamento = Arrays.stream(faturamento).filter(valor -> valor > 0).max().orElse(0);

        double somaFaturamento = Arrays.stream(faturamento).filter(valor -> valor > 0).sum();

        long diasComFaturamento = Arrays.stream(faturamento).filter(valor -> valor > 0).count();

        double mediaAnual = diasComFaturamento > 0 ? somaFaturamento / diasComFaturamento : 0;

        /* contar dias com faturamento acima da média usando Stream pq sim */
        Long diasAcimaDaMedia = Arrays.stream(faturamento).filter(valor -> valor > mediaAnual).count();

        mostrarResultado(menorFaturamento, maiorFaturamento, diasAcimaDaMedia.intValue());
    }

}