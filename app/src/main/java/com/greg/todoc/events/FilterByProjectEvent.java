package com.greg.todoc.events;

public class FilterByProjectEvent {
    String projectSelected;

    public FilterByProjectEvent(String projectSelected) {
        this.projectSelected = projectSelected;
    }

    public String getProjectSelected() {
        return projectSelected;
    }
}
