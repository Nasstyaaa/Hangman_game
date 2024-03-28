import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private boolean isGuessed = false;
    private String userLetter;
    private final ArrayList<String> faults = new ArrayList<>(6);
    private int numOfFault = 0;
    private int numOfGuessed = 0;

    private String printArray(List<String> array) {
        return array.toString().replaceAll("[\\[\\],]", "");
    }

    private void userInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Ваша буква: ");
        userLetter = in.next();
        System.out.println("\n");
        if (!(userLetter.matches("^[а-я]$"))) {
            System.out.println("Недопустимый формат буквы, попробуйте ещё раз");
            userInput();
        }
    }

    private void drawHuman(int numOfFault) {
        switch (numOfFault) {
            case (0):
                String human = "_____\n" + "|\n" + "|\n" + "|\n";
                System.out.println(human);
                break;
            case (1):
                human = "_____\n" + "|\tO\n" + "|\n" + "|\n";
                System.out.println(human);
                break;
            case (2):
                human = "_____\n" + "|\tO\n" + "|\t|\n" + "|\n";
                System.out.println(human);
                break;
            case (3):
                human = "_____\n" + "|\t O\n" + "|\t/|\n" + "|\n";
                System.out.println(human);
                break;
            case (4):
                human = "_____\n" + "|\t O\n" + "|\t/|\\\n" + "|\n";
                System.out.println(human);
                break;
            case (5):
                human = "_____\n" + "|\t O\n" + "|\t/|\\\n" + "|\t/\n";
                System.out.println(human);
                break;
            case (6):
                human = "_____\n" + "|\t O\n" + "|\t/|\\\n" + "|\t/ \\\n";
                System.out.println(human);
                System.out.println("Вы проиграли");
                restartGame();
                break;
        }
    }

    private void restartGame() {
        System.out.println("Хотите начать новую игру(1) или закончить(2)?");
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        if (!(choice.matches("^(1|2)$"))) {
            System.out.println("Недопустимый ввод");
            restartGame();
        } else if (Integer.parseInt(choice) == 1) {
            new Game().startGame();
        } else if (Integer.parseInt(choice) == 2) {
            System.exit(0);
        }
    }

    public void startGame() {
        GamePreparation gamer = new GamePreparation();
        gamer.identifyWords();
        drawHuman(numOfFault);
        while (true) {
            System.out.println("Слово: " + printArray(gamer.getUserWord()));
            System.out.println("Ошибки: " + printArray(faults));
            userInput();

            for (int i = 0; i < gamer.getWord().size(); i++) {
                if (gamer.getWord().get(i).equals(userLetter)) {
                    gamer.getUserWord().set(i, userLetter);
                    numOfGuessed++;
                    if (numOfGuessed == gamer.getWord().size()) {
                        System.out.println("Вы выиграли");
                        restartGame();
                    }
                    isGuessed = true;
                }
            }
            if (!isGuessed && !(faults.contains(userLetter))) {
                faults.add(userLetter);
                numOfFault++;
            }
            drawHuman(numOfFault);
            isGuessed = false;
        }
    }
}