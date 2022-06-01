package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendGeneLinkTypes;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefNodeContainer;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendForm extends RendElement implements RendElem {
    private final RendGeneLinkTypes opForm;

    private final StringMap<CustList<RendDynOperationNode>> textPart;

    public RendForm(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, RendGeneLinkTypes _opForm, StringMap<CustList<RendDynOperationNode>> _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.textPart = _textPart;
    }

    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        DefFormParts formParts_ = _rendStack.getFormParts();
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            formParts_.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
            formParts_.getCallsFormExps().add(new AnchorCall(opForm.getGeneLink(),new CustList<AbstractWrapper>()));
            incrForm(formParts_);
            procCstRef(_cont, elt_, _rendStack.getFormParts());
            return _ctx.callsOrException(_rendStack.getStackCall());
        }
        CustList<AbstractWrapper> values_ = new CustList<AbstractWrapper>();
        int f_ = 0;
        for (EntryCust<String, CustList<RendDynOperationNode>> e: textPart.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return true;
            }
            Struct ar_ = Argument.getNullableValue(args_.lastValue().getArgument()).getStruct();
            LocalVariable locVar_ = LocalVariable.newLocalVariable(ar_, _rendStack.formatVarType(opForm.getTypes().get(f_)));
            values_.add(new VariableWrapper(locVar_));
            elt_.removeAttribute(e.getKey());
            f_++;
        }
        formParts_.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
        formParts_.getCallsFormExps().add(new AnchorCall(opForm.getGeneLink(),values_));
        incrForm(formParts_);
        String beanName_ = _rendStack.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_));
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn()), _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn())));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        long currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

    public static void incrForm(FormParts _formParts) {
        _formParts.getFormatIdMapStack().add(new StringList());
        long currentForm_ = _formParts.getCurrentForm();
        _formParts.getFormsNb().add(currentForm_);
        _formParts.getInputs().add(0L);
        currentForm_++;
        _formParts.setCurrentForm(currentForm_);
    }

    public static void procCstRef(Configuration _cont, Element _elt, FormParts _formParts) {
        long currentForm_;
        if (_elt.hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
            _elt.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        }
        currentForm_ = _formParts.getCurrentForm();
        _elt.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
    }

}
