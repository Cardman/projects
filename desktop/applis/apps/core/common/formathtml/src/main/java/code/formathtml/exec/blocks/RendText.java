package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;

public final class RendText extends RendLeaf implements RendWithEl, RendReducableOperations {

    private int expressionOffset;

    private ExecTextPart textPart;

    public RendText(int _offsetTrim, ExecTextPart _textPart, int _offset) {
        super(_offsetTrim);
        expressionOffset = _offset;
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage lastPage_ = _cont.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(RenderingText.render(textPart,_cont, _stds, _ctx));
        if (_ctx.callsOrException()) {
            return;
        }
        processBlock(_cont, _stds, _ctx);
    }
}
