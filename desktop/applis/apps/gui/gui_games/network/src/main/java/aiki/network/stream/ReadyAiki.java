package aiki.network.stream;


import code.network.ReadyContent;

public final class ReadyAiki {

    private final ReadyContent content = new ReadyContent();

    private int index;

    public ReadyContent getContent() {
        return content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

}
