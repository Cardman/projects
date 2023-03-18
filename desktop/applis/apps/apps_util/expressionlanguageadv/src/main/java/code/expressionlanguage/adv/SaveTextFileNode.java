package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.stream.StreamBinaryFile;
import code.util.core.StringUtil;

public final class SaveTextFileNode implements AbsActionListener {
    private final TabEditor editor;

    public SaveTextFileNode(TabEditor _ed) {
        this.editor = _ed;
    }

    @Override
    public void action() {
        byte[] enc_ = StringUtil.encode(editor.getCenter().getText());
        StreamBinaryFile.writeFile(editor.getFullPath(),enc_,editor.getFactories().getStreams());
    }
}
