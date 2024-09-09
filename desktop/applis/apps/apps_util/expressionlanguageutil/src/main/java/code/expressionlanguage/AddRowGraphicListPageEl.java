package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsPreparedLabel;
import code.gui.images.MetaFont;

public final class AddRowGraphicListPageEl extends AbstractBasicReflectPageEl {
    private final GraphicListStruct instance;
    private final int index;
    private final Struct info;
    private AbsPrepareCustomEvents call;

    public AddRowGraphicListPageEl(GraphicListStruct _g, int _i, Struct _f) {
        super(true);
        this.instance = _g;
        this.index = _i;
        this.info = _f;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (index < 0) {
            return true;
        }
        if (call == null) {
            MetaFont mf_ = instance.visible().getMetaFont();
            FontStruct fs_ = new FontStruct(mf_);
            call = new CellPrepareCustomEvents(null,index,info,fs_,instance);
        }
        if (call.call(_context,_stack,instance.getCellRender(),0)) {
            return false;
        }
        AbsPreparedLabel lab_ = instance.getGrList().preparedLabel();
        Struct res_ = getReturnedArgument();
        lab_.setIcon(instance.getGrList().getImageFactory(), PreparedLabelStruct.builImage(instance.getGrList().getImageFactory(), res_));
        instance.addRow(index,lab_,info);
        return true;
    }
}
