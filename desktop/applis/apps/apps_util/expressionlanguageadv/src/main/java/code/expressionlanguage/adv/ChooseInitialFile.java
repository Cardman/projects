package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class ChooseInitialFile implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public ChooseInitialFile(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFileOpenDialogInt().input(windowCdmEditor.getCommonFrame(), true, "", windowCdmEditor.getCommonFrame().getFrames().getHomePath()));
        if (fileName_.isEmpty()) {
            return;
        }
        windowCdmEditor.updateEnv(fileName_);
    }
}
