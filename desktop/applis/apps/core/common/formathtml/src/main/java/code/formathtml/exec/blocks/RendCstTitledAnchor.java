package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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

public final class RendCstTitledAnchor extends RendElement {
    private final RendGeneLinkTypes opExpAnch;

    private final StringMap<CustList<RendDynOperationNode>> opExpTitle;

    private final StringMap<String> preformatted;

    public RendCstTitledAnchor(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                               RendGeneLinkTypes _opExpAnch,
                               StringMap<CustList<RendDynOperationNode>> _opExpTitle, StringMap<String> _preformatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExpAnch = _opExpAnch;
        this.opExpTitle = _opExpTitle;
        this.preformatted = _preformatted;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = RendTitledAnchor.processTitle(_cont, (Element) _nextWrite, _ctx, _rendStack, opExpTitle, preformatted);
        if (curWr_ == null) {
            return null;
        }
        _rendStack.getFormParts().getCallsExps().add(new AnchorCall(opExpAnch.getGeneLink(),new CustList<AbstractWrapper>()));
        FormParts formParts_ = _rendStack.getFormParts();
        incrAncNb(_cont.getRend(), (Element) _nextWrite, formParts_.getIndexes(), _cont.getRendKeyWords().group());
        IndexesFormInput.incr(formParts_.getIndexes());
        return NullStruct.NULL_VALUE;
    }

}
