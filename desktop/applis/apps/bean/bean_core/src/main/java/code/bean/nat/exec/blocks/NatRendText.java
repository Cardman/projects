package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendLeaf;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Text;

public final class NatRendText extends RendLeaf implements NatRendWithEl{

    private final ExecTextPart textPart;

    public NatRendText(ExecTextPart _textPart) {
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        ImportingPage lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(RendBlockHelp.EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(NatRenderingText.renderNat(textPart, _stds, _rendStack));
        RendBlockHelp.processBlock(_rendStack, this);
    }
}
