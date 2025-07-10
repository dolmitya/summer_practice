import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordProvider {
    private final List<String> words;

    public WordProvider(String resourceName) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceName)))) {

            if (reader == null) {
                throw new RuntimeException("Файл не найден: " + resourceName);
            }

            words = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении файла слов: " + e.getMessage(), e);
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Список слов пуст");
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}