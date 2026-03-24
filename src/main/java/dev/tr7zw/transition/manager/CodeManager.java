package dev.tr7zw.transition.manager;

import lombok.*;

import java.util.*;

public class CodeManager {

    @Getter
    private static final CodeManager instance = new CodeManager();
    private final Map<String, Runnable> codeMap = new HashMap<>();

    public void registerCode(String code, Runnable action) {
        codeMap.put(code, action);
    }

    public void executeCode(String code) {
        Runnable action = codeMap.get(code);
        if (action != null) {
            action.run();
        }
    }

}
