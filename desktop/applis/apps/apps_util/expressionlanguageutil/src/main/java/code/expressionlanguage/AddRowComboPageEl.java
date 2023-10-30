package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.NumberUtil;

public final class AddRowComboPageEl extends AbstractBasicReflectPageEl {
    private final GraphicComboStruct instance;
    private final String info;
    private final int previousCount;
    private AbsPrepareCustomEvents callEvts;
    private ArrayStruct selections;
    private final boolean select;

    public AddRowComboPageEl(GraphicComboStruct _g, String _f, boolean _selecting) {
        super(true);
        this.instance = _g;
        this.info = _f;
        previousCount = _g.getItemCount().intStruct();
        selections = new ArrayStruct(0,"");
        select = _selecting;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            if (!select) {
                instance.getGraphicCombo().add(info);
            }
            selections = GraphicListStruct.getListeners(_context,instance.getGraphicCombo().getSelections());
            callEvts = new EvtPrepareCustomEvents(instance.getGraphicCombo().getList().generateAndSet(true));
            if (!select&&previousCount == 0) {
                instance.getGraphicCombo().select(0);
            }
        }
        if (!select&&previousCount > 0) {
            return true;
        }
        int l_ = selections.getLength();
        for (int i = NumberUtil.max(0,callEvts.getEventIndex()-1); i < l_; i++) {
            Struct inst_ = selections.get(i);
            if (callEvts.call(_context,_stack,inst_,i)) {
                return false;
            }
        }
        return true;
    }
}
