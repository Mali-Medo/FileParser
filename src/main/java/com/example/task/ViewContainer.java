package com.example.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ViewContainer {
    private List<String> totalViewsList;
    private Set<String> uniqueViewsSet;

    ViewContainer() {
        this.totalViewsList = new ArrayList<>();
        this.uniqueViewsSet = new HashSet<>();
    }

    List<String> getTotalViewsList() {
        return totalViewsList;
    }

    Set<String> getUniqueViewsSet() {
        return uniqueViewsSet;
    }
}