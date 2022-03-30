package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class RendImg extends RendElement {

    private final ExecTextPart textPart;

    public RendImg(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String pageName_ = RenderingText.render(textPart, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        prImg(_cont, (Element) _nextWrite, pageName_);
    }

    public static void prImg(Configuration _cont, Element _nextWrite, String _link) {
        String file_ = _cont.getFiles().getVal(_link);
        if (file_ == null) {
            return;
        }
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrSrc(),file_);
    }
}
