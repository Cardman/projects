package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.sml.NavigationCore;
import code.sml.RendReadWrite;
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
        String txt_ = RenderingText.render(textPart, _ctx, _rendStack);
        if (txt_ == null) {
            return;
        }
        t_.appendData(txt_);
        NavigationCore.simpleAppendChild(doc_,rend_,t_);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
