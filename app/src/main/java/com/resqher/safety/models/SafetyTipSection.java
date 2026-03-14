package com.resqher.safety.models;

import java.util.List;

public class SafetyTipSection {
    private final String title;
    private final String subtitle;
    private final List<String> points;

    public SafetyTipSection(String title, String subtitle, List<String> points) {
        this.title = title;
        this.subtitle = subtitle;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<String> getPoints() {
        return points;
    }
}
