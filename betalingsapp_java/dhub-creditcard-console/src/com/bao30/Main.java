package com.bao30;

import java.io.IOException;
import java.util.Scanner;

import static com.bao30.MagneticCardHandler.parseCreditCardData;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        System.out.print("Tast inn pris på varen: ");
        Double pris = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Tast inn varekjøp/transaksjonstekst: ");
        String varetekst = scanner.nextLine();

        System.out.print("Terminal klar. Dra kortet!");
        String card_data = scanner.nextLine();

        String[] card = parseCreditCardData(card_data, true);

        try {
            dHubHttpHandler.dHubTransactionPOST(card[0], pris, varetekst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}