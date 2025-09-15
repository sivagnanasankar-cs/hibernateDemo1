package org.example.hibernatedemo1.enums;

public enum DegreeType {

    BSC("Bachelor of Science"),
    BE("Bachelor of Engineering");

    private final String description;

    DegreeType(String description) {
        this.description = description;
    }

    public String getDegreeAbbreviation() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

