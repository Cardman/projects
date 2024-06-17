package cards.network.common.before;


import code.network.ReadyContent;

public final class ReadyCards extends PlayerActionBeforeGameCards {

    private final ReadyContent content = new ReadyContent();

    public ReadyContent getContent() {
        return content;
    }

}
