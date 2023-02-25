package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.MemoryFileSystem;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class FolderForExpression implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;

    public FolderForExpression(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }
    @Override
    public void action() {
        String f_ = windowCdmEditor.getFolderExpression();
        if (!f_.isEmpty()) {
            windowCdmEditor.folderExp(f_);
            return;
        }
        String fileName_ = StringUtil.nullToEmpty(windowCdmEditor.getFolderOpenDialogInt().input(windowCdmEditor.getCommonFrame(), windowCdmEditor.getCommonFrame().getLanguageKey(), true));
        if (fileName_.isEmpty()) {
            return;
        }
        windowCdmEditor.folderExp(MemoryFileSystem.skipLastSep(fileName_));
    }
}
