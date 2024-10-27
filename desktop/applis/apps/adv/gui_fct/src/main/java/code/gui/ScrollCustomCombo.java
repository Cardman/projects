package code.gui;

import code.gui.events.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class ScrollCustomCombo extends AbsStringScrollCustomCombo<String> {
    public ScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img) {
        this(_compo,_img,new AlwaysActionListenerAct());
    }
    public ScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img, AbsActionListenerAct _act) {
        super(_compo, _img, _act,new StrCustCellRenderGeneImpl(_compo, _img));
    }

    @Override
    protected String adj(String _i) {
        return StringUtil.nullToEmpty(_i);
    }
}
