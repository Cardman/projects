package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendImg;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendImg extends NatRendElement {

    private final ExecTextPart textPart;

    public NatRendImg(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String pageName_ = NatRenderingText.render(textPart, _stds, _ctx, _rendStack);
        RendImg.prImg(_cont,_read,pageName_);
    }
}
