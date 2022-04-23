package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefNodeContainer;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendForm extends RendElement implements RendFormInt {
    private final CustList<RendDynOperationNode> opForm;

    private final StringMap<CustList<RendDynOperationNode>> textPart;

    public RendForm(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, CustList<RendDynOperationNode> _opForm, StringMap<CustList<RendDynOperationNode>> _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        DefFormParts _formParts = _rendStack.getFormParts();
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            _formParts.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
            _formParts.getCallsFormExps().add(new AnchorCall(opForm,new CustList<Struct>()));
            incrForm(_formParts);
            procCstRef(_cont, elt_, _rendStack.getFormParts());
            return;
        }
        CustList<Struct> values_ = new CustList<Struct>();
        for (EntryCust<String, CustList<RendDynOperationNode>> e: textPart.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            values_.add(Argument.getNullableValue(args_.lastValue().getArgument()).getStruct());
            elt_.removeAttribute(e.getKey());
        }
        _formParts.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
        _formParts.getCallsFormExps().add(new AnchorCall(opForm,values_));
        incrForm(_formParts);
        String beanName_ = _rendStack.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_));
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn()), _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn())));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        long currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
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
