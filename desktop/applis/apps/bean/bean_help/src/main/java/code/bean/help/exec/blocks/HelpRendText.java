package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.exec.blocks.NatRendWithEl;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendLeaf;
import code.formathtml.exec.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.Text;

public final class HelpRendText extends RendLeaf implements NatRendWithEl {

    private final NatExecTextPart textPart;

    public HelpRendText(NatExecTextPart _textPart) {
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(HelpRendBlockHelp.EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(HelpRenderingText.render(textPart));
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
