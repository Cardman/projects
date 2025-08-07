package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;

public final class CreateMainWindowPkEditor implements Runnable {

    private final AbstractProgramInfos list;

    private final String withParam;

    private final AbstractImage image;
    private WindowPkEditor window;
    private final SexListInt sexList;
    private final LanguagesButtonsPair pair;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindowPkEditor(String _withParam, AbstractProgramInfos _list, AbstractImage _icon, SexListInt _s, LanguagesButtonsPair _p) {
        withParam = _withParam;
        list = _list;
        image = _icon;
        sexList = _s;
        pair = _p;
    }

    @Override
    public void run() {
        WindowPkEditor window_ = new WindowPkEditor(list, pair);
        window_.getFacade().setSexList(sexList);
        window_.setImageIconFrame(image);
        if (!withParam.isEmpty()) {
            window_.loadData(withParam);
        } else {
            window_.newData();
        }
        window = window_;
    }

    public WindowPkEditor getWindow() {
        return window;
    }
}
