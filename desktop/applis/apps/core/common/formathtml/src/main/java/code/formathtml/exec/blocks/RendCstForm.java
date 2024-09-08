package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendGeneLinkTypes;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefNodeContainer;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringMap;

public final class RendCstForm extends RendElement implements RendElem {
    private final RendGeneLinkTypes opForm;

    public RendCstForm(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, RendGeneLinkTypes _opForm) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        DefFormParts formParts_ = _rendStack.getFormParts();
        Element elt_ = (Element) _nextWrite;
        formParts_.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
        formParts_.getCallsFormExps().add(new AnchorCall(opForm.getGeneLink(),new CustList<AbstractWrapper>()));
        RendForm.incrForm(formParts_);
        long currentForm_;
        currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
        return NullStruct.NULL_VALUE;
    }

}
