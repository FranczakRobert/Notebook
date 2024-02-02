package org.example.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nvm implements Serializable {
    private Map<Integer, ArrayList<String>> notes = new HashMap<>();
    private List<User> users = new ArrayList<>();

    public Nvm() {

    }

    public List<User> getUsers() {
        return users;
    }

    public Map<Integer, ArrayList<String>> getNotes() {
        return notes;
    }

}
