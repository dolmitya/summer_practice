package summerpractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordProvider {
    private static final Logger logger = LogManager.getLogger(WordProvider.class);
    private final List<String> words;

    public WordProvider(String resourceName) {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName);
            if (input == null) {
                throw new RuntimeException("Файл не найден: " + resourceName);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                words = reader.lines()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            if (words.isEmpty()) {
                throw new RuntimeException("Файл пуст: " + resourceName);
            }

            logger.info("Загружено {} слов из файла {}", words.size(), resourceName);

        } catch (Exception e) {
            logger.error("Ошибка при чтении файла: {}", resourceName, e);
            throw new RuntimeException("Ошибка при чтении файла слов: " + resourceName, e);
        }
    }

    public String getRandomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}