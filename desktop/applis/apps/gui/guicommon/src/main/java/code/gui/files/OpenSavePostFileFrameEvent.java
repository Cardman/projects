package code.gui.files;

import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.AbsTabbedPane;

public final class OpenSavePostFileFrameEvent implements AbsPostFileDialogEvent {
    private final FileFrame fileFrame;
    private final FileDialogContent fileFrameContent;
    private final String transFile;
    private final String key;
    private final AbsTabbedPane tabs;
    private final AbsPlainLabel label;

    public OpenSavePostFileFrameEvent(FileFrame _f, FileDialogContent _c, String _tf, String _k, AbsTabbedPane _tab, AbsPlainLabel _lab) {
        this.fileFrame = _f;
        this.fileFrameContent = _c;
        this.transFile = _tf;
        this.key = _k;
        this.tabs = _tab;
        this.label = _lab;
    }

    @Override
    public String act(String _path) {
        fileFrameContent.setSelectedAbsolutePath(_path);
        fileFrameContent.setSelectedPath(_path);
        label.setText(_path);
        return _path;
    }

    @Override
    public String title(String _path) {
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        tabs.add(FileDialog.getAppliTr(fileFrame.getPrInfos().currentLg()).getMapping().getVal(transFile).getMapping().getVal(key),_panel);
        return "";
    }
}
