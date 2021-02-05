package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsGraphicListPainter;

public abstract class AbsAdvGraphicListPainter implements AbsGraphicListPainter {
    private final CommonExecutionInfos executionInfos;
    private Struct value;

    protected AbsAdvGraphicListPainter(CommonExecutionInfos _executionInfos) {
        executionInfos = _executionInfos;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public Struct getValue() {
        return value;
    }

    public void setValue(Struct _value) {
        value = _value;
    }
}
