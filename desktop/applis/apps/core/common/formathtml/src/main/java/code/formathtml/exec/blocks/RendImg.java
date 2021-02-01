package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class RendImg extends RendElement {

    private final ExecTextPart textPart;

    public RendImg(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _textPart) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        String pageName_ = RenderingText.render(textPart,_cont, _stds, _ctx, _stack, _rendStack);
        if (_ctx.callsOrException(_stack)) {
            return;
        }
        String lg_ = _cont.getCurrentLanguage();
        String link_ = Configuration.getRealFilePath(lg_,pageName_);
        String file_ = _cont.getFiles().getVal(link_);
        if (file_ == null) {
            return;
        }
        ((Element)_nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),file_);
    }
}
