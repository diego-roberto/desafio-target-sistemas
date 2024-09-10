package io.github.diegors;

public class CruzamentoVeiculos {

    public static void main(String[] args) {
        double distanciaTotal = 125.0;
        double velocidadeCarro = 90.0;
        double velocidadeCaminhao = 80.0;
        double tempoPedagioCarro = (5.0 / 60.0); //convertido para horas
        int numPedagios = 3;

        double velocidadeRelativa = velocidadeCarro + velocidadeCaminhao;

        /* tempo até o ponto de cruzamento (sem considerar os pedágios do carro) */
        double tempoCruzamento = distanciaTotal / velocidadeRelativa; // em horas
        double tempoTotalPedagios = numPedagios * tempoPedagioCarro; // tempo total nos pedágios

        double tempoEfetivoCarro = tempoCruzamento - tempoTotalPedagios;
        double distanciaPercorridaCarro = velocidadeCarro * tempoEfetivoCarro;
        double distanciaPercorridaCaminhao = velocidadeCaminhao * tempoCruzamento;

        System.out.println("Distância total entre Ribeirão Preto e Barretos: " + distanciaTotal + " km");
        System.out.println("Tempo para o cruzamento dos veículos: " + (tempoCruzamento * 60) + " minutos");

        System.out.println("\nDistância percorrida pelo carro: " + String.format("%.2f", distanciaPercorridaCarro) + " km");
        System.out.println("Distância percorrida pelo caminhão: " + String.format("%.2f", distanciaPercorridaCaminhao) + " km");
    }
}

