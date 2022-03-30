package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendForm extends RendElement implements RendFormInt {
    private final CustList<RendDynOperationNode> opForm;

    private final StringList varNames;
    private final ExecTextPart textPart;

    public RendForm(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, CustList<RendDynOperationNode> _opForm, StringList _varNames, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        feedFormParts(opForm, varNames, _rendStack.getFormParts());
        long currentForm_;
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            procCstRef(_cont, elt_, _rendStack.getFormParts());
            return;
        }
        StringList alt_ = RenderingText.renderAltList(textPart, _ctx, _rendStack);
        StringList arg_ = new StringList();
        feedList(alt_, arg_);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            _rendStack.getFormParts().getFormsArgs().add(new StringList());
            currentForm_ = _rendStack.getFormParts().getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
            return;
        }
        _rendStack.getFormParts().getFormsArgs().add(arg_);
        String beanName_ = _rendStack.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_));
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn()), _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn())));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
    }

    public static void feedFormParts(CustList<RendDynOperationNode> _opForm, StringList _varNames, FormParts _formParts) {
        long currentForm_ = _formParts.getCurrentForm();
        _formParts.getContainersMapStack().add(new LongTreeMap< NodeContainer>());
        _formParts.getFormatIdMapStack().add(new StringList());
        _formParts.getFormsNb().add(currentForm_);
        _formParts.getInputs().add(0L);
        currentForm_++;
        _formParts.setCurrentForm(currentForm_);
        _formParts.getCallsFormExps().add(_opForm);
        _formParts.getFormsVars().add(_varNames);
    }

    public static void feedList(StringList _alt, StringList _arg) {
        int len_ = _alt.size();
        for (int i = 1; i < len_; i += 2) {
            _arg.add(_alt.get(i));
        }
    }

    public static void procCstRef(Configuration _cont, Element _elt, FormParts _formParts) {
        long currentForm_;
        _formParts.getFormsArgs().add(new StringList());
        if (_elt.hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
            _elt.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        }
        currentForm_ = _formParts.getCurrentForm();
        _elt.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
    }

}
