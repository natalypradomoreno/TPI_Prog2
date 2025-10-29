package Persistencia.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IllegalOrphanException extends Exception {

    private final List<String> messages;

    public IllegalOrphanException(List<String> messages) {
        super((messages != null && !messages.isEmpty() ? String.join("\n", messages) : null));
        this.messages = (messages != null) ? messages : new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }
}

