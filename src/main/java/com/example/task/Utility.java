package com.example.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

class Utility {
    static Map<String, ViewContainer> getSortedUniqueViews(Map<String, ViewContainer> viewsMap) {
        return viewsMap.entrySet().stream()
                .sorted(comparingInt(e -> -e.getValue().getUniqueViewsSet().size()))// -e DESC || e ASC
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }

    static Map<String, ViewContainer> getSortedTotalViews(Map<String, ViewContainer> viewsMap) {
        return viewsMap.entrySet().stream()
                .sorted(comparingInt(e -> -e.getValue().getTotalViewsList().size()))// -e DESC || e ASC
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }

    static void printUniqueViews(Map<String, ViewContainer> sortedUniqueViews) {
        for (Map.Entry<String, ViewContainer> entry : sortedUniqueViews.entrySet())
            System.out.println(entry.getKey() +
                    " " + entry.getValue().getUniqueViewsSet().size() + " unique views");
    }

    static void printTotalViews(Map<String, ViewContainer> sortedTotalViews) {
        for (Map.Entry<String, ViewContainer> entry : sortedTotalViews.entrySet())
            System.out.println(entry.getKey() +
                    " " + entry.getValue().getTotalViewsList().size() + " views");
    }

    static Map<String, ViewContainer> getViews(String filePath) {
        Map<String, ViewContainer> map = new HashMap<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> line.contains(" "))
                    .forEach(line -> {
                        String[] keyValuePair = line.split(" ", 2);
                        String key = keyValuePair[0];
                        String value = keyValuePair[1];
                        if (map.containsKey(key)) {
                            map.get(key).getTotalViewsList().add(value);
                            map.get(key).getUniqueViewsSet().add(value);
                        } else {
                            ViewContainer viewContainer = new ViewContainer();
                            map.put(key, viewContainer);
                            viewContainer.getTotalViewsList().add(value);
                            viewContainer.getUniqueViewsSet().add(value);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
