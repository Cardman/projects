package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatRendWithEl;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendLeaf;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Text;

public final class HelpRendText extends RendLeaf implements NatRendWithEl {

    private final ExecTextPart textPart;

    public HelpRendText(ExecTextPart _textPart) {
        textPart = _textPart;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack) {
        NatImportingPage lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(HelpRendBlockHelp.EMPTY_STRING);
        simpleAppendChild(doc_,rend_,t_);
        t_.appendData(HelpRenderingText.render(textPart));
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
