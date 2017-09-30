package pl.com.bottega.photostock.sales.misc;

import java.io.*;

public class IOPlay {

    static class Person implements Serializable{
        private int age;
        private String name;

        static final long serialVersionUID = 1L;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }


    public static void main(String[] args) {
        //basicRead();
        //basicReadTryWithRsources();
        //characterRead();
        //bufferedRead();
        //basicWrite();
        //writer();
        //printWriter();
        writeObjects();
        readObjects();
    }

    private static void readObjects() {
        try(ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\objects.bin"))) {
            Object o;
           while((o = ois.readObject()) != null) {
               Person p = (Person) o;
               System.out.printf("%s %d", p.name, p.age);
               System.out.println();
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (EOFException e){

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void writeObjects() {
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\objects.bin"))) {
            oos.writeObject(new Person(17, "Jan Nowak"));
            oos.writeObject(new Person(22, "Janina Nowak"));
            oos.writeObject(new Person(19, "Marcin"));
            oos.writeObject(new Person(44, "Zbigniew Kowalski"));
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printWriter() {
        try {
            try(OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\output.txt");
                PrintStream ps = new PrintStream(outputStream)){
                ps.println("Zażółć gęślą jaźń. Albo nie.");
                ps.println("Ala ma mysz.");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć do zapisu.");
        }
        catch (IOException e) {
            System.out.println("Bład wyjścia.");
        }
    }

    private static void writer() {
        try {
            try(OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\output.txt");
            Writer writer = new OutputStreamWriter(outputStream, "CP1250")){
                writer.write("Zażółć gęślą jaźń.\r\n");
                writer.write("Ala ma kota");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć do zapisu.");
        }
        catch (IOException e) {
            System.out.println("Bład wyjścia.");
        }
    }

    private static void basicWrite() {
        try {
            try(OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\output.txt", true)){
                outputStream.write("Zażółć gęślą jaźń. A zresztą, rób co chcesz. Jest niedizela.".getBytes("CP1250"));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć do zapisu.");
        }
        catch (IOException e) {
            System.out.println("Bład wyjścia.");
        }
    }

    private static void bufferedRead() {
        try(InputStream inputStream = new FileInputStream("C:\\test.txt")) {
            InputStreamReader reader = new InputStreamReader(inputStream, "CP1250");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                System.out.println(line);
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
        } catch (IOException e) {
            System.out.println("Błąd wejścia.");
        }
    }

    private static void characterRead() {
        try(InputStream inputStream = new FileInputStream("C:\\test.txt")) {
            InputStreamReader reader = new InputStreamReader(inputStream, "CP1250");
            System.out.println(reader.getEncoding());
            int c;
            while ((c = reader.read()) != -1)
                System.out.print((char)c);
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
        } catch (IOException e) {
            System.out.println("Błąd wejścia.");
        }
    }

    private static void basicReadTryWithRsources() {
        try(InputStream inputStream = new FileInputStream("C:\\test.txt")) {
            int b;
            while ((b = inputStream.read()) != -1)
                System.out.print((char)b);
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
        } catch (IOException e) {
            System.out.println("Błąd wejścia.");
        }
    }


    private static void basicRead() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\test.txt");
            int b;
            while ((b = inputStream.read()) != -1)
                System.out.print((char)b);
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
        } catch (IOException e) {
            System.out.println("Błąd wejścia.");
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Błąd zamykania pliku.");
                }
            }
        }
    }
}
