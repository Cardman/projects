package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.NatRendStackCallAdv;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendSpan;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class NatRendSpan extends NatRendElementForm {
    private final NatExecTextPart result;
    private final StringMap<String> formatted;

    public NatRendSpan(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText, NatExecTextPart _result, StringMap<String> _formatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.result = _result;
        this.formatted = _formatted;
    }

    void span(Configuration _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        String txt_ = NatRenderingText.renderNat(result, _rendStack);
        RendSpan.setupTxt(_cont,_nextWrite, txt_,formatted, ((NatRendStackCallAdv)_rendStack).getFormParts());
    }

}
