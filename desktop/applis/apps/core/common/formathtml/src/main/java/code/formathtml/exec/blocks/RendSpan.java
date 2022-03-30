package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendSpan extends RendElement {
    private final ExecTextPart result;
    private StringMap<String> formatted=new StringMap<String>();

    public RendSpan(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _result, StringMap<String> _formatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.result = _result;
        this.formatted = _formatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String txt_ = RenderingText.render(result, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            ((Element)_nextWrite).removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
            return;
        }
        setupTxt(_cont, _nextWrite, _rendStack, txt_, formatted);
    }

    public static void setupTxt(Configuration _cont, Node _nextWrite, RendStackCall _rendStack, String _txt, StringMap<String> _formatted) {
        ((Element)_nextWrite).setAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrFor()), _txt);
        CustList<StringList> stack_ = _rendStack.getFormParts().getFormatIdMapStack();
        if (stack_.isEmpty()) {
            return;
        }
        stack_.last().add(_formatted.getVal(_cont.getCurrentLanguage()));
    }

}
