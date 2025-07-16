package aiki.gui.components.editor;

import aiki.gui.*;
import aiki.gui.dialogs.*;
import code.gui.events.*;

public final class EditorShowDataWebEvent implements AbsActionListener {


    private final WindowPkEditor window;
    private final FrameHtmlData renderDataWeb;

    public EditorShowDataWebEvent(WindowPkEditor _w, FrameHtmlData _rend) {
        window = _w;
        renderDataWeb = _rend;
    }

    @Override
    public void action() {
        WindowAiki.showDataWeb(renderDataWeb, MessagesPkEditor.getMessagesEditorSelectTitlesTr(MessagesPkEditor.getAppliTr(window.getFrames().currentLg())).getMapping(), window.getResult(), MessagesEditorSelect.TITLE_WEB);
    }

}
