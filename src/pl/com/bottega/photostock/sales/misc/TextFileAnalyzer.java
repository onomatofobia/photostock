package pl.com.bottega.photostock.sales.misc;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class TextFileAnalyzer {

    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        writeInputFile();
        bufferedRead();
        writeOutputFile();
    }


    private static void writeInputFile() {
        try {
            try (OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\input.txt");
                 PrintStream ps = new PrintStream(outputStream)) {
                ps.println("Ala ma psa");
                ps.println("ala ala psa");
                ps.println("ma");
                ps.println("ma");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć do zapisu.");
        } catch (IOException e) {
            System.out.println("Bład wyjścia.");
        }
    }

    private static void bufferedRead() {
        try (InputStream inputStream = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\input.txt")) {
            InputStreamReader reader = new InputStreamReader(inputStream, "CP1250");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s");
                for (int i = 0; i < words.length; i++) {
                    if (map.get(words[i]) == null) {
                        map.put(words[i], 1);
                    } else {
                        counter = map.get(words[i]);
                        map.put(words[i], ++counter);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
        } catch (IOException e) {
            System.out.println("Błąd wejścia.");
        }
    }

    private static void writeOutputFile() {
        try {
            try (OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\output.txt");
                 PrintStream ps = new PrintStream(outputStream)) {
                String key;
                Integer value;
                for(Map.Entry<String, Integer> entry : map.entrySet() ) {
                    key = entry.getKey();
                    value = entry.getValue();
                    ps.println(key + " " + value);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć do zapisu.");
        } catch (IOException e) {
            System.out.println("Bład wyjścia.");
        }


    }
}
