package code.formathtml;

import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RenderingText;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendSpan extends RendElement {
    private ExecTextPart result;
    private StringMap<String> formatted=new StringMap<String>();

    public RendSpan(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, ExecTextPart result, StringMap<String> formatted) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.result = result;
        this.formatted = formatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String txt_ = RenderingText.render(result, _cont);
        if (_cont.getContext().hasException()) {
            ((Element)_nextWrite).removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
            return;
        }
        ((Element)_nextWrite).setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()),txt_);
        CustList<StringList> stack_ = _cont.getFormatIdMapStack();
        if (stack_.isEmpty()) {
            return;
        }
        stack_.last().add(formatted.getVal(_cont.getCurrentLanguage()));
    }

}
