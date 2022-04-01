package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatRendImg;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class HelpRendImg extends HelpRendElement {

    private final ExecTextPart textPart;

    public HelpRendImg(Element _read, StringMap<ExecTextPart> _execAttributes, ExecTextPart _textPart) {
        super(_read, _execAttributes);
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        String pageName_ = HelpRenderingText.render(textPart);
        NatRendImg.prImg(_cont,(Element)_nextWrite,pageName_);
    }
}
