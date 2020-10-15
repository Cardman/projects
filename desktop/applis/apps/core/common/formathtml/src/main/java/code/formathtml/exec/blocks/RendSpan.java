package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendSpan extends RendElement {
    private ExecTextPart result;
    private StringMap<String> formatted=new StringMap<String>();

    public RendSpan(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _result, StringMap<String> _formatted) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
        this.result = _result;
        this.formatted = _formatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        String txt_ = RenderingText.render(result, _cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            ((Element)_nextWrite).removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
            return;
        }
        ((Element)_nextWrite).setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()),txt_);
        CustList<StringList> stack_ = _cont.getFormParts().getFormatIdMapStack();
        if (stack_.isEmpty()) {
            return;
        }
        stack_.last().add(formatted.getVal(_cont.getCurrentLanguage()));
    }

}
