package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Text;

public final class RendText extends RendLeaf implements RendWithEl {

    private final int expressionOffset;

    private final DefExecTextPart textPart;

    public RendText(DefExecTextPart _textPart, int _offset) {
        expressionOffset = _offset;
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage lastPage_ = _rendStack.getLastPage();
        lastPage_.setOffset(expressionOffset);
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(RenderingText.render(textPart, _ctx, _rendStack));
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
