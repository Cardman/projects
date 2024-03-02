package cards.gui.containers;

import code.util.StringMap;

public final class ContainerMultiContent {
    private StringMap<String> messages = new StringMap<String>();

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _m) {
        this.messages = _m;
    }
}
