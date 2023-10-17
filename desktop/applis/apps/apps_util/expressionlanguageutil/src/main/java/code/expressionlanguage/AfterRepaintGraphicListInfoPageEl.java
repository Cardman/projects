package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;
import code.util.CustList;

public final class AfterRepaintGraphicListInfoPageEl extends AbstractBasicReflectPageEl {
    private final GraphicListStruct instance;
    private CustList<RowGraphicList<Struct>> rows;
    private ForceCellPrepareCustomEvents callRefs;

    public AfterRepaintGraphicListInfoPageEl(GraphicListStruct _g) {
        super(true);
        instance = _g;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (rows == null) {
            this.rows = retrieve();
        }
        if (callRefs == null) {
            callRefs = new ForceCellPrepareCustomEvents(null,-1,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,instance);
        }
        int count_ = rows.size();
        for (int i = 0; i < count_; i++) {
            RowGraphicList<Struct> current_ = rows.get(i);
            callRefs.rowSet(current_,i);
            if (callRefs.call(_context,_stack,instance.getCellRender(),i)) {
                return false;
            }
            AfterChangingGraphicListInfoPageEl.applyImage(i, current_, ArgumentListCall.toStr(getReturnedArgument()), callRefs, instance);
        }
        instance.getGrList().revalidate();
        setReturnedArgument(Argument.createVoid());
        return true;
    }

    private CustList<RowGraphicList<Struct>> retrieve() {
        CustList<RowGraphicList<Struct>> rs_ = new CustList<RowGraphicList<Struct>>();
        RowGraphicList<Struct> r_ = instance.getGrList().getRow(0);
        while (r_ != null) {
            rs_.add(r_);
            r_ = r_.getNext();
        }
        return rs_;
    }

}
