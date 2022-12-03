package code.bean.help.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatBlock;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.formathtml.exec.blocks.RendBlock;
import code.sml.RendReadWrite;
import code.sml.Document;
import code.sml.Text;

public final class HelpRendText extends NatBlock {

    private final NatExecTextPart textPart;

    public HelpRendText(NatExecTextPart _textPart) {
        textPart = _textPart;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs lastPage_ = _rendStack.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Document doc_ = rend_.getDocument();
        Text t_ = doc_.createTextNode(HelpRendBlockHelp.EMPTY_STRING);
        RendBlock.simpleAppendChild(doc_,rend_,t_);
        t_.appendData(HelpRenderingText.render(textPart));
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
