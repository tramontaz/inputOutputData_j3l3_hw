import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.in;

public class Main {

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
//        taskOne();
//        taskTwo();
//        taskThree();
        System.out.println(System.currentTimeMillis() - t + " ms");
    }

    private static void taskThree() {
        try {
            readBookFast(new File("warandpeace.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readBook(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int x = 0;
        char[] page = new char[1800];
        StringBuilder stringBuilder = new StringBuilder(in.available());
        while ((x = bufferedReader.read(page)) != -1) {
            stringBuilder.append(new String(page));
            System.out.println(stringBuilder);
        }
    } // this do it per pages, but not quick

    private static void readBookFast(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String page;
        StringBuilder stringBuilder = new StringBuilder(in.available());
        while ((page = bufferedReader.readLine()) != null) {
            System.out.println(page);
        }
    } // this is the quicker method to read file and print it in console, but we don't use "pages"

    private static void taskTwo() {
        try {
            File result = joinAllFilesIntoOne(new File("partOne.txt"), new File("partTwo.txt"),
                    new File("partThree.txt"), new File("partFour.txt"), new File("partFive.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File joinAllFilesIntoOne(File... files) throws IOException {
        ArrayList<InputStream> arrayList = new ArrayList<>();
        File allFilesInOne = new File("allFilesInOne.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(allFilesInOne);
        for (File f : files) {
            arrayList.add(new FileInputStream(f));
        }
        Enumeration<InputStream> en = Collections.enumeration(arrayList);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(en);
        int temp = 0;
        while ((temp = sequenceInputStream.read()) != -1) {
            fileOutputStream.write(temp);
        }
        return allFilesInOne;
    }

    private static void taskOne() {
        File fiftyBytes = new File("fiftyKilobytes.txt");
        File fileWithArray = new File("fileWithArray.txt");
        byte[] array = readAFileToByteArray(fiftyBytes);
        System.out.println(Arrays.toString(array));
        arrayToFile(fileWithArray, array); //check my array
    }

    public static byte[] readAFileToByteArray(File file) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                byteArrayOutputStream.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();

    }

    public static void arrayToFile(File file, byte[] array) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(array);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
