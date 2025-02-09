package aiki.gui.events;

import aiki.gui.*;
import aiki.gui.dialogs.*;
import aiki.main.*;
import code.gui.events.*;
import code.threads.*;

public final class ShowDataWebEvent implements AbsActionListener {

    private final WindowAiki window;
    private final FrameHtmlData renderDataWeb;
    private final AbstractFutureParam<AikiNatLgNamesNavigation> preparedDataWebTask;

    public ShowDataWebEvent(WindowAiki _window, FrameHtmlData _rend, AbstractFutureParam<AikiNatLgNamesNavigation> _task) {
        window = _window;
        renderDataWeb = _rend;
        preparedDataWebTask = _task;
    }

    @Override
    public void action() {
        window.showDataWeb(renderDataWeb, preparedDataWebTask);
    }

}
