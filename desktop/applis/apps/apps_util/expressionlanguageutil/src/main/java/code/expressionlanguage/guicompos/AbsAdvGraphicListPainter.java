package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsGraphicListPainterImpl;
import code.gui.images.AbstractImageFactory;

public abstract class AbsAdvGraphicListPainter extends AbsGraphicListPainterImpl {
    private final CommonExecutionInfos executionInfos;
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

}
