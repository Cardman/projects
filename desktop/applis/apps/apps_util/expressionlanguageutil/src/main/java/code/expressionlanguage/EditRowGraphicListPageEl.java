package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;
import code.gui.images.MetaFont;

public final class EditRowGraphicListPageEl extends AbstractBasicReflectPageEl {
    private final GraphicListStruct instance;
    private final int index;
    private final Struct info;
    private AbsPrepareCustomEvents call;

    public EditRowGraphicListPageEl(GraphicListStruct _g, int _i, Struct _f) {
        super(true);
        this.instance = _g;
        this.index = _i;
        this.info = _f;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        RowGraphicList<Struct> row_ = instance.getGrList().getRow(index);
        if (row_ == null) {
            setReturnedArgument(NullStruct.NULL_VALUE);
            return true;
        }
        if (call == null) {
            MetaFont mf_ = row_.getLabel().getMetaFont();
            FontStruct fs_ = new FontStruct(mf_);
            row_.setInfo(info);
            call = new ForceCellPrepareCustomEvents(row_,index,info,fs_,instance);
        }
        if (call.call(_context,_stack,instance.getCellRender(),0)) {
            return false;
        }
        Struct res_ = getReturnedArgument();
        row_.refresh(instance.getGrList().getImageFactory(), PreparedLabelStruct.builImage(instance.getGrList().getImageFactory(), res_));
        setReturnedArgument(NullStruct.NULL_VALUE);
        return true;
    }
}
