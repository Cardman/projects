package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.formathtml.Configuration;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendGeneLinkTypes;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.FormParts;
import code.sml.IndexesFormInput;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendCstAnchor extends RendElement {
    private final RendGeneLinkTypes opExpAnch;

    public RendCstAnchor(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                         RendGeneLinkTypes _opAnc) {
        super(_read, _execAttributes, _execAttributesText);
        opExpAnch = _opAnc;
    }


    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        _rendStack.getFormParts().getCallsExps().add(new AnchorCall(opExpAnch.getGeneLink(),new CustList<AbstractWrapper>()));
        FormParts formParts_ = _rendStack.getFormParts();
        incrAncNb(_cont.getRend(), (Element) _nextWrite, formParts_.getIndexes(), _cont.getRendKeyWords().group());
        IndexesFormInput.incr(formParts_.getIndexes());
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

}
