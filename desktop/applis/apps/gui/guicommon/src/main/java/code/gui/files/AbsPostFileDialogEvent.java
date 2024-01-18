package code.gui.files;

import code.gui.AbsPanel;

public interface AbsPostFileDialogEvent {
    String act(String _path);
    String title(String _path);
    String visible(AbsPanel _panel);
}
