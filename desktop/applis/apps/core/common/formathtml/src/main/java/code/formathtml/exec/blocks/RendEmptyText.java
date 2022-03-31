package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;

public final class RendEmptyText extends RendPossibleEmpty {

    private final String expression;

    private final boolean add;

    public RendEmptyText(String _expression, boolean _add) {
        this.expression = _expression;
        this.add = _add;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        if (!add) {
            processBlock(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ImportingPage lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(expression);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
