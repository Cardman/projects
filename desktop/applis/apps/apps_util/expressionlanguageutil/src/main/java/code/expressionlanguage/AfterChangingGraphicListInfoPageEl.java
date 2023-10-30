package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class AfterChangingGraphicListInfoPageEl extends AbstractBasicReflectPageEl {
    private final GraphicListStruct instance;
    private ArrayStruct selections;
    private CustList<RowGraphicList<Struct>> rows;
    private AbsPrepareCustomEvents callEvts;
    private CellPrepareCustomEvents callRefs;

    public AfterChangingGraphicListInfoPageEl(GraphicListStruct _g) {
        super(true);
        instance = _g;
        selections = new ArrayStruct(0,"");
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            selections =  GraphicListStruct.getListeners(_context,instance.getGrList().getSelections());
            callEvts = new EvtPrepareCustomEvents(instance.getGrList().generateAndSet(true));
        }
        int l_ = selections.getLength();
        for (int i = NumberUtil.max(0,callEvts.getEventIndex()-1); i < l_; i++) {
            Struct inst_ = selections.get(i);
            if (callEvts.call(_context,_stack,inst_,i)) {
                return false;
            }
        }
        if (rows == null) {
            this.rows = retrieve();
        }
        if (callRefs == null) {
            callRefs = new CellPrepareCustomEvents(null,-1,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,instance);
        }
        int count_ = rows.size();
        for (int i = NumberUtil.max(0,callRefs.getEventIndex()-1); i < count_; i++) {
            RowGraphicList<Struct> current_ = rows.get(i);
            callRefs.rowSet(current_,i);
            if (callRefs.call(_context,_stack,instance.getCellRender(),i)) {
                return false;
            }
            if (current_.isDirty()) {
                applyImage(current_, ArgumentListCall.toStr(getReturnedArgument()), instance);
            }
        }
        setReturnedArgument(Argument.createVoid());
        return true;
    }

    static void applyImage(RowGraphicList<Struct> _current, Struct _str, GraphicListStruct _instance) {
        _current.refresh(_instance.getGrList().getImageFactory(), PreparedLabelStruct.builImage(_instance.getGrList().getImageFactory(), _str));
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
