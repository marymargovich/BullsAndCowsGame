package bulls_and_cows;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BullsAndCowsGame {
    private String secret;

    public BullsAndCowsGame(){
        this.secret = generateSecret();
        System.out.println(secret);
    }



    public String getSecret() {
        return secret;
    }

    public int [] check(String guess) {
        if (guess == null || guess.length() != 4
                || !guess.chars().allMatch(Character::isDigit))
            throw new IllegalArgumentException(" Need 4 digits!");
        if (hasRepeat(guess))
            throw new IllegalArgumentException("digits must be unique");
        if (guess.charAt(0) == '0')
            throw new IllegalArgumentException("0 is FORBIDDEN");
        int  bulls = 0;
        int cows = 0;
        for (int i  =0; i< 4; i++ ) {
            char temp = guess.charAt(i);
            if (temp == secret.charAt(i)) bulls++;
            else if (secret.indexOf(temp) >= 0) cows++;

        }
        return new int[]{bulls, cows};

    }

    private boolean hasRepeat(String guess) {
        return guess.chars()
                .distinct()
                .count() != guess.length();


    }


    private String generateSecret() {
        Random r = new Random();
        int first = r.nextInt(9)+1;//1->9
        IntStream rest = IntStream.generate(()-> r.nextInt(10))
                .filter(d-> d!= first)
                .distinct().limit(3);
        return IntStream.concat(IntStream.of(first), rest)
                .mapToObj(Integer:: toString)
                .collect(Collectors.joining());






    }

}
