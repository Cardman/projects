package aiki.network.stream;


import code.network.QuitContent;

public final class QuitAiki extends PlayerActionGameAiki {

    private final QuitContent content = new QuitContent();

    public QuitContent getContent() {
        return content;
    }
}
