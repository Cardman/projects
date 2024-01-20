package cards.gui.dialogs;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListenerAct;

public final class EditorCardsNonModalEvent implements AbsActionListenerAct {
    private final WindowCards window;

    public EditorCardsNonModalEvent(WindowCards _w) {
        this.window = _w;
    }
    @Override
    public boolean act() {
        return !window.getCore().getContainerGame().playingSingleGame()&&!window.getFileSaveFrame().getFrame().isVisible();
    }
}
