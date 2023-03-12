//  Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ex1 {
    public static void main(String[] args) throws IOException {
        int[] array = { 5, 1, 4, 2, 8 };
        String logLoc = getLogFileLocation();
        FileWriter writeToFile = new FileWriter(logLoc, Charset.forName("UTF-8"), true);
        int n = array.length - 1;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    writeToFile.append(Arrays.toString(array));
                    writeToFile.append("\n");
                }
            }
        }
        writeToFile.append("Сортировка завершена. \n");
        writeToFile.close();
    }

    public static String getLogFileLocation() throws IOException {
        try {
            String pathProject = System.getProperty("user.dir");
            String pathFile = pathProject.concat("/log.txt");
            File fileToSave = new File(pathFile);
            try {
                fileToSave.canWrite();
            } catch (Exception e) {
                fileToSave.createNewFile();
            }
            return pathFile;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
