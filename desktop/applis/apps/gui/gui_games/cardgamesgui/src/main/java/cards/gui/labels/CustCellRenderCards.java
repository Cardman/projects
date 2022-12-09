package cards.gui.labels;

import code.gui.AbsCustCellRender;
import code.gui.CustCellRender;

public abstract class CustCellRenderCards<T> extends CustCellRender<T> {

    @Override
    public AbsCustCellRender fwd() {
        setList(getListGr().getList());
        return this;
    }
}
