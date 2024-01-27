package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogDisplaying;
import code.gui.events.AbsActionListener;

public class ValidateDisplayingEvent implements AbsActionListener {

    private final WindowCardsInt window;
    private final DialogDisplaying dialog;

    public ValidateDisplayingEvent(WindowCardsInt _w, DialogDisplaying _dialog) {
        window = _w;
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.getDialogDisplayingContent().validateDisplaying();
        dialog.validateDisplaying();
        dialog.savePrefs(window);
    }
}
