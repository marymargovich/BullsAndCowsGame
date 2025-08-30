package bulls_and_cows;

import java.io.*;

public class BullsAndCowsAppl {
    public static void main(String[] args) throws IOException {
        File src = new File("BullsAndCowsLogs.txt");
        BullsAndCowsGame game = new BullsAndCowsGame();

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String CYAN = "\u001B[36m";


        try (BufferedReader bReader =
                     new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bWriter =
                     new BufferedWriter(new FileWriter(src));) {

            while (true) {
                System.out.println("Enter 4 unique digits\n  0 on the first place  is FORBIDDEN");
                String line = bReader.readLine();

                if (line.equalsIgnoreCase("exit")) {
                    System.out.println(GREEN + "Game over!" + RESET);
                    break;
                }
                if (line.isBlank()) {
                    System.out.println(RED + "please, enter your number!"+ RESET);
                    continue;
                }

                try {
                    int[] result = game.check(line);
                    String output = String.format(
                            "%s%s%s -> %sBulls:%d%s, %sCows:%d%s",
                            CYAN, line, RESET,
                            YELLOW, result[0], RESET,
                            YELLOW, result[1], RESET
                    );
                    System.out.println(output);
                    bWriter.write(line + " -> Bulls:" + result[0] + ", Cows:" + result[1]);
                    bWriter.newLine();
                    bWriter.newLine();
                    bWriter.flush();

                    if (result[0] == 4) {
                        System.out.println(GREEN + "🎉 Congratulations! You guessed the number! 🎉" + RESET);
                        break;
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(RED+"invalid input: " + e.getMessage()+RESET);
                }
            }
        }
    }
}
