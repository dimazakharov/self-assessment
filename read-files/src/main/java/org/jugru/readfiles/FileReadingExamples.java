package org.jugru.readfiles;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReadingExamples {

    public static String readUsingBufferedReader(String name) {
        try (FileReader reader = new FileReader(new File(getAbsolutePath(name)));
             BufferedReader br = new BufferedReader(reader)) {
            List<String> answer = new ArrayList<>();
            String str;
            while ((str = br.readLine()) != null)
                answer.add(str);
            return StringUtils.join(answer, "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static String readUsingFileReader(String path) {
        try (FileReader fr =
                     new FileReader(getAbsolutePath(path))) {
            StringBuilder answer = new StringBuilder();
            int i;
            while ((i = fr.read()) != -1)
                answer.append((char) i);
            return answer.toString().replaceAll("(\r\n|\r)", "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readUsingLines(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getAbsolutePath(path)), StandardCharsets.UTF_8);
            return StringUtils.join(lines, "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getAbsolutePath(String name) {
        String s = FileReadingExamples.class.getClassLoader().getResource(name).getFile();
        if (SystemUtils.IS_OS_MAC)
            s = s.replaceAll("%20", "\\ ");
        else
            s = s.replaceAll("%20", " ");
        if (SystemUtils.IS_OS_WINDOWS)
            s = s.substring(1);
        return s;
    }

}
