package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.MemoryFileSystem;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class ChooseInitialFolder implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public ChooseInitialFolder(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFolderOpenDialogInt().input(windowCdmEditor.getCommonFrame(), true));
        if (fileName_.isEmpty()) {
            return;
        }
        windowCdmEditor.folder(MemoryFileSystem.skipLastSep(fileName_));
    }
}
