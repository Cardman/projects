package code.bean.nat.exec.blocks;

import code.bean.nat.exec.*;
import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.Text;

public final class NatRendText extends NatBlock{

    private final NatExecTextPart textPart;

    public NatRendText(NatExecTextPart _textPart) {
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(RendBlockHelp.EMPTY_STRING);
        RendBlock.simpleAppendChild(doc_,rend_,t_);
        t_.appendData(NatRenderingText.renderNat(textPart, _rendStack));
        RendBlockHelp.processBlock(_rendStack, this);
    }
}
