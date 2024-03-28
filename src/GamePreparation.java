import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePreparation {
    private final List<String> word = new ArrayList<>();

    private List<String> userWord;

    public List<String> getWord() {
        return word;
    }

    public List<String> getUserWord() {
        return userWord;
    }

    public void identifyWords() {
        List<String> wordsBuf = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("singular_and_plural.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                wordsBuf.add(line);
                line = reader.readLine();

            }
        } catch (IOException e) {
            System.out.println("Файл не прочитан");
        }
        word.add(wordsBuf.get((int) (Math.random() * wordsBuf.size())));

        userWord = new ArrayList<>(word.size());
        for (int i = 0; i < word.size(); i++) {
            userWord.set(i, "-");
        }
    }
}

