package com.polystar.analytics.model.service;

import com.polystar.analytics.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;


@Service
public class AnalyticsService {


    @Autowired
    public AnalyticsService() {

    }

    private volatile HashMap<String, Integer> repeatedWords = new HashMap<>();



    public LinkedHashMap<String, Integer> topRepeatedCountWords(int topWordsCount, List<String> filesPaths) throws CustomException, InterruptedException {
        this.repeatedWords = new HashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(filesPaths.size());
        Collection<Callable<Void>> tasks = new ArrayList<>();
        for (String filePath : filesPaths) {
            Callable<Void> task = () -> {
                getAllWordsAndCount(filePath);
                return null;
            };
            tasks.add(task);
        }
        executor.invokeAll(tasks);

        return sortLinkedListAndGetTopCount(repeatedWords, topWordsCount);
    }

    public void getAllWordsAndCount(String filePath) throws CustomException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.ISO_8859_1)) {
            for (String line = null; (line = br.readLine()) != null; ) {

                String[] lineInWords = line.toLowerCase().split("([()/!?,.\\s]+)");
                addWordAndAddCount(lineInWords,repeatedWords);
            }
        } catch (IOException e) {
            throw new CustomException(e);
        }
    }
    public synchronized void addWordAndAddCount(String[] lineInWords ,HashMap<String, Integer> repeatedWords) {
        for (String word : lineInWords) {
            if (!word.isEmpty()) {
                if (repeatedWords.containsKey(word.trim())) {
                    Integer oldValue = repeatedWords.get(word.trim());
                    repeatedWords.replace(word.trim(), oldValue + 1);
                } else {
                    repeatedWords.put(word.trim(), 1);
                }
            }
        }
    }
    public LinkedHashMap<String, Integer> sortLinkedListAndGetTopCount(HashMap<String, Integer> repeatedWords, int topWordsCount) {
        LinkedHashMap<String, Integer> topWordsMap = new LinkedHashMap<>();

        Map<String, Integer> unSortedMap = repeatedWords;
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        unSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        int count = 0;
        Iterator<String> itr = reverseSortedMap.keySet().iterator();
        while (itr.hasNext() && count < topWordsCount) {
            String key = itr.next();
            topWordsMap.put(key, reverseSortedMap.get(key));
            System.out.println((count + 1) + ". " + key + " repeated: " + reverseSortedMap.get(key) + " times");
            count++;
        }
        return topWordsMap;
    }




    //Used for reading line by line from other server and analysing it
    private volatile HashMap<String, Integer> repeatedWordsConcat = new HashMap<>();
    private volatile HashMap<String, Integer> repeatedWordsOneTimeConcat = new HashMap<>();

    public LinkedHashMap<String, Integer> topRepeatedCountWordsConcat(String line) {

        String[] lineInWords = line.toLowerCase().split("([()/!?,.\\s]+)");
        addWordAndAddCount(lineInWords,repeatedWordsConcat);
        return sortLinkedListAndGetTopCount(repeatedWordsConcat, 5);
    }


    public LinkedHashMap<String, Integer> repeatedWordsOneTimeConcat(String line) {

        String[] lineInWords = line.toLowerCase().split("([()/!?,.\\s]+)");
        addWordAndAddCount(lineInWords,repeatedWordsOneTimeConcat);
        return sortLinkedListAndGetTopCount(repeatedWordsConcat, 5);
    }

    public LinkedHashMap<String, Integer> getRepeatedWordsConcat() {

        return sortLinkedListAndGetTopCount(repeatedWordsConcat, 5);
    }

    public void setRepeatedWordsOneTimeConcat(HashMap<String, Integer> repeatedWordsOneTimeConcat) {
        this.repeatedWordsOneTimeConcat = repeatedWordsOneTimeConcat;
    }

    public LinkedHashMap<String, Integer> repeatedWordsOneTimeConcat() {

        return sortLinkedListAndGetTopCount(repeatedWordsOneTimeConcat, 5);
    }
}
