package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsGraphicListPainter;
import code.gui.images.AbstractImageFactory;

public abstract class AbsAdvGraphicListPainter implements AbsGraphicListPainter {
    private final CommonExecutionInfos executionInfos;
    private Struct value;
    private AbstractImageFactory fact;

    protected AbsAdvGraphicListPainter(AbstractImageFactory _fact, CommonExecutionInfos _executionInfos) {
        fact = _fact;
        executionInfos = _executionInfos;
    }

    @Override
    public AbstractImageFactory getFact() {
        return fact;
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
