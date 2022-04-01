package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendSpan;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendSpan extends NatRendElement {
    private final ExecTextPart result;
    private StringMap<String> formatted=new StringMap<String>();

    public NatRendSpan(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, ExecTextPart _result, StringMap<String> _formatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.result = _result;
        this.formatted = _formatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        String txt_ = NatRenderingText.renderNat(result, _rendStack);
        RendSpan.setupTxt(_cont,_nextWrite, txt_,formatted, _rendStack.getFormParts());
    }

}
