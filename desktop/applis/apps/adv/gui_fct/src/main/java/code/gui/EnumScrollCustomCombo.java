package code.gui;

import code.gui.events.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;

public final class EnumScrollCustomCombo<E> extends AbsStringScrollCustomCombo<E> {
    public EnumScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img, AbsMap<E,String> _m) {
        this(_compo,_img,new AlwaysActionListenerAct(),_m);
    }
    public EnumScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img, AbsActionListenerAct _act, AbsMap<E,String> _m) {
        super(_compo, _img, _act,new DefCustCellRenderGeneImpl<E>(_compo, _img,_m));
    }

    @Override
    protected E adj(E _i) {
        return _i;
    }
}
