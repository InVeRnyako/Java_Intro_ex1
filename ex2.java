// 3.** Дана json строка (можно сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].

// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;


public class ex2 {
    public static void main(String[] args) throws IOException {
        String[][][] fullSplitArray = fullSplit(readLineFromFile("ex2_data.txt"));
        StringBuilder outputLine = new StringBuilder();
        for (int i = 0; i < fullSplitArray.length; i++) {
            outputLine.setLength(0);
            outputLine.append(String.format("Студент %s получил %s по предмету %s.",
                    fullSplitArray[i][0][1], fullSplitArray[i][1][1], fullSplitArray[i][2][1]));
            System.out.println(outputLine);
        }
    }

    public static String readLineFromFile(String fileName) throws IOException {
        try {
            String pathProject = System.getProperty("user.dir");
            String pathFile = pathProject.concat("\\" + fileName);
            String dataLoc = pathFile;
            BufferedReader bRead = new BufferedReader(
                    new FileReader(dataLoc, Charset.forName("UTF-8")));
            String inputString = bRead.readLine();
            bRead.close();
            return inputString;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static String[][][] fullSplit(String inputString) {
        String[] firstSplit = splitPackages(inputString);
        String[][][] fullSplitArray = new String[firstSplit.length][][];
        for (int i = 0; i < fullSplitArray.length; i++) {
            fullSplitArray[i] = splitIntoArrayOfPairs(firstSplit[i]);
        }
        return fullSplitArray;
    }

    public static String[] splitPackages(String inputString) {
        if (inputString.isEmpty() || inputString.equals(null)) {
            System.out.println("Ошибка входящих данных.");
            System.exit(0);
        }
        String[] split = inputString.split("\\},\\{");

        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].replaceAll("[\\[\\]{}\"]", "").replaceAll(", ", ",");
        }
        return split;
    }

    public static String[][] splitIntoArrayOfPairs(String inputString) {
        String[] inputPairs = inputString.split(",");
        String[][] output = new String[inputPairs.length][];
        for (int i = 0; i < inputPairs.length; i++) {
            output[i] = inputPairs[i].split(":");
        }
        return output;
    }
}
