package aiki.gui.events;

import aiki.gui.*;
import aiki.gui.dialogs.*;
import code.gui.events.*;

public final class ShowDataWebEvent implements AbsActionListener {

    private final WindowAiki window;
    private final FrameHtmlData renderDataWeb;

    public ShowDataWebEvent(WindowAiki _window, FrameHtmlData _rend) {
        window = _window;
        renderDataWeb = _rend;
    }

    @Override
    public void action() {
        window.showDataWeb(renderDataWeb);
    }

}
