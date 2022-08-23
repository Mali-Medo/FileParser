package com.example.task;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static com.example.task.Utility.*;

public class FileParser {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/resources/webserver.log");
        Map<String, ViewContainer> viewsMap = getViews(filePath.toAbsolutePath().toString());

        Map<String, ViewContainer> sortedTotalViews = getSortedTotalViews(viewsMap);
        Map<String, ViewContainer> sortedUniqueViews = getSortedUniqueViews(viewsMap);

        printTotalViews(sortedTotalViews);
        printUniqueViews(sortedUniqueViews);
    }

}
