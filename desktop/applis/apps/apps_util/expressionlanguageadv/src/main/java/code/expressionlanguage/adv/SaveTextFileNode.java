package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.stream.StreamTextFile;

public final class SaveTextFileNode implements AbsActionListener {
    private final TabEditor editor;

    public SaveTextFileNode(TabEditor _ed) {
        this.editor = _ed;
    }

    @Override
    public void action() {
        StreamTextFile.saveTextFile(editor.getFullPath(),editor.getCenter().getText(),editor.getCommonFrame().getFrames().getStreams());
    }
}
