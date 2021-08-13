package code.bean.help.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendImg;
import code.formathtml.util.BeanLgNames;
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
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String pageName_ = HelpRenderingText.render(textPart);
        RendImg.prImg(_cont,(Element)_nextWrite,pageName_);
    }
}
