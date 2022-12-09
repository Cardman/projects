package aiki.gui.components;

import code.gui.AbsCustCellRender;
import code.gui.CustCellRender;

public abstract class CustCellRenderPk<T> extends CustCellRender<T> {

    @Override
    public AbsCustCellRender fwd() {
        setList(getListGr().getList());
        return this;
    }
}
