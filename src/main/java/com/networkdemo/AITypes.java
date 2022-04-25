package com.networkdemo;

public enum AITypes implements Typess {

    MAKE_MOVE("Make a move"),
    REMATCH_REQUEST("Request rematch"),
    REMATCH_ACCEPT("Accept Rematch");

    private String description;

    private AITypes(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }
}


