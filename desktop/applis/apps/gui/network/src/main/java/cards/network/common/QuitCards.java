package cards.network.common;


import code.network.QuitContent;

public final class QuitCards extends PlayerActionGame {

    private final QuitContent content = new QuitContent();

    public QuitContent getContent() {
        return content;
    }

}
