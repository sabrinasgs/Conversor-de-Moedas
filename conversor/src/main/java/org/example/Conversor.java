package org.example;

import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorAPI api = new ConversorAPI("348e3b6056cfe0f254cd433b");

        while (true) {
            System.out.println("""
                    ****************************************************
                    Seja bem-vindo ao Conversor de Moedas =]

                    1) Dólar => Peso Argentino
                    2) Peso Argentino => Dólar
                    3) Dólar => Real Brasileiro
                    4) Real Brasileiro => Dólar
                    5) Dólar => Peso Colombiano
                    6) Peso Colombiano => Dólar
                    7) Sair
                    Escolha uma opção válida:
                    ****************************************************
                    """);

            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Encerrando o programa.");
                break;
            }

            try {
                String moedaOrigem = "", moedaDestino = "";
                switch (opcao) {
                    case 1 -> {
                        moedaOrigem = "USD";
                        moedaDestino = "ARS";
                    }
                    case 2 -> {
                        moedaOrigem = "ARS";
                        moedaDestino = "USD";
                    }
                    case 3 -> {
                        moedaOrigem = "USD";
                        moedaDestino = "BRL";
                    }
                    case 4 -> {
                        moedaOrigem = "BRL";
                        moedaDestino = "USD";
                    }
                    case 5 -> {
                        moedaOrigem = "USD";
                        moedaDestino = "COP";
                    }
                    case 6 -> {
                        moedaOrigem = "COP";
                        moedaDestino = "USD";
                    }
                    default -> {
                        System.out.println("Opção inválida. Tente novamente.");
                        continue;
                    }
                }

                double taxa = api.obterTaxaCambio(moedaOrigem, moedaDestino);
                System.out.print("Digite o valor a ser convertido: ");
                double valor = scanner.nextDouble();
                double valorConvertido = valor * taxa;

                System.out.printf("%.2f %s corresponde a %.2f %s\n", valor, moedaOrigem, valorConvertido, moedaDestino);
            } catch (Exception e) {
                System.out.println("Erro ao obter taxa de câmbio: " + e.getMessage());
            }
        }

        scanner.close();
    }
}