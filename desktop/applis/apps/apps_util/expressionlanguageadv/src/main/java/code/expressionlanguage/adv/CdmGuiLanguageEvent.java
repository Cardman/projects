package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.stream.StreamLanguageUtil;
import code.util.core.StringUtil;

public final class CdmGuiLanguageEvent implements AbsActionListener {
    private final WindowCdmEditor window;

    public CdmGuiLanguageEvent(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getSetterLanguage().init(window.getCommonFrame(),window.getCommonFrame().getFrames(), "language");
        String langue_ = StringUtil.nullToEmpty(window.getSetterLanguage().getLanguage());
        if (langue_.isEmpty()) {
            return;
        }
        GuiBaseUtil.changeStaticLanguage(langue_, window.getCommonFrame().getFrames(), window.getCoreMessages());
        StreamLanguageUtil.saveLanguage(WindowCdmEditor.getTempFolder(window.getCommonFrame().getFrames()), langue_,window.getCommonFrame().getFrames().getStreams());
    }
}
