package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.sml.FormParts;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendSpan extends RendElement {

    private final RendOperationNodeListOff rendExp;
    private final StringMap<String> formatted;

    public RendSpan(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<String> _formatted, RendOperationNodeListOff _re) {
        super(_read, _execAttributes, _execAttributesText);
        this.formatted = _formatted;
        rendExp = _re;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage last_ = _rendStack.getLastPage();
        last_.setOffset(rendExp.getOffset());
        String txt_ = RendInput.idRad(rendExp.getList(),_ctx,_rendStack);
        if (txt_ == null) {
            ((Element)_nextWrite).removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrFor()));
            return null;
        }
        setupTxt(_cont, _nextWrite, txt_, formatted, _rendStack.getFormParts());
        return NullStruct.NULL_VALUE;
    }

    public static void setupTxt(Configuration _cont, Node _nextWrite, String _txt, StringMap<String> _formatted, FormParts _formParts) {
        ((Element)_nextWrite).setAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrFor()), _txt);
        CustList<StringList> stack_ = _formParts.getFormatIdMapStack();
        if (stack_.isEmpty()) {
            return;
        }
        stack_.last().add(_formatted.getVal(_cont.getCurrentLanguage()));
    }

}
