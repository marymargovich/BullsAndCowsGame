package bulls_and_cows;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BullsAndCowsAppl {
    public static void main(String[] args) throws IOException {
        File src = new File("BullsAndCowsLogs.txt");
        BullsAndCowsGame game = new BullsAndCowsGame();

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String CYAN = "\u001B[36m";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        try (BufferedReader bReader =
                     new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bWriter =
                     new BufferedWriter(new FileWriter(src, true));) {

            int attempts = 0;


            String start = "===== GAME START "+ LocalDateTime.now().format(dtf)+"==========";
            System.out.println(start);
            bWriter.write(start);
            bWriter.newLine();
            bWriter.flush();

            while (true) {
                String msg = CYAN+"Enter 4 unique digits\n  0 on the first place  is FORBIDDEN"+RESET;
                System.out.println(msg);

                String line = bReader.readLine();

                if (line.equalsIgnoreCase("exit")) {
                    System.out.println(GREEN + "Game over!" + RESET);
                    bWriter.write("Game over"+ LocalDateTime.now().format(dtf) + " =====");
                    bWriter.newLine();
                    bWriter.flush();
                    break;
                }
                if (line.isBlank()) {
                    System.out.println(RED + "please, enter your number!"+ RESET);
                    bWriter.write("empty input");
                    bWriter.newLine();
                    bWriter.flush();
                    continue;
                }

                try {
                   attempts ++;
                    int[] result = game.check(line);
                    System.out.printf("%s%s%s -> %sBulls:%d%s, %sCows:%d%s%n",
                            CYAN, line, RESET,
                            YELLOW, result[0], RESET,
                            YELLOW, result[1], RESET);

                    bWriter.write( line + " -> Bulls:" + result[0] + ", Cows:" + result[1]);
                    bWriter.newLine();
                    bWriter.flush();

                    if (result[0] == 4) {
                        System.out.println( GREEN + "🎉 Congratulations! You guessed the number! 🎉\n WIN in " + attempts+ " attempts"+ RESET);
                        bWriter.write("WIN in " + attempts + " attempts!\n "+ " Secret was "+ game.getSecret());
                        bWriter.newLine();
                        bWriter.write("===== GAME END " + LocalDateTime.now().format(dtf) + " =====");
                        bWriter.newLine();
                        bWriter.flush();
                        break;
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(RED+"invalid input: " + e.getMessage()+RESET);
                    bWriter.write("invalid input: " + e.getMessage());
                    bWriter.newLine();
                    bWriter.flush();
                }
            }
        }
    }
}
