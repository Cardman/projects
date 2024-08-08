package code.converterimages.gui;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;

public final class CreateMainWindowConverter implements Runnable {
    private final AbstractProgramInfos list;
    private final StringList ls;
    private final LanguagesButtonsPair pair;
    private WindowConverter window;

    public CreateMainWindowConverter(StringList _ls, AbstractProgramInfos _list, LanguagesButtonsPair _p) {
        ls = _ls;
        list = _list;
        pair = _p;
    }
    @Override
    public void run() {
        window = new WindowConverter(list, pair);
        CustList<String> infos_;
        if (!ls.isEmpty()) {
            infos_ = DocumentImagesUtil.parse(StreamTextFile.contentsOfFile(ls.first(), list.getFileCoreStream(), list.getStreams()));
        } else {
            infos_ = new CustList<String>();
        }
        if (infos_.isEmpty()) {
            return;
        }
//        window.getReadImages().setSelected(infos_.get(0).charAt(0) == '0');
//        window.getPathExport().setText(infos_.get(1));
//        window.getPath().setText(infos_.get(2));
        window.getPathExport().setText(infos_.get(0));
        window.getPath().setText(infos_.get(1));
        window.export();
    }

    public WindowConverter getWindow() {
        return window;
    }
}
