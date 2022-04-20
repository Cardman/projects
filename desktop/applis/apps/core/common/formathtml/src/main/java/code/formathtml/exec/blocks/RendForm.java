package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefNodeContainer;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendForm extends RendElement implements RendFormInt {
    private final CustList<RendDynOperationNode> opForm;

    private final StringList varNames;
    private final DefExecTextPart textPart;

    public RendForm(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText, CustList<RendDynOperationNode> _opForm, StringList _varNames, DefExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        DefFormParts _formParts = _rendStack.getFormParts();
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            _formParts.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
            _formParts.getCallsFormExps().add(opForm);
            incrForm(varNames, _formParts);
            procCstRef(_cont, elt_, _rendStack.getFormParts());
            return;
        }
        StringList alt_ = RenderingText.renderAltList(textPart, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        StringList arg_ = new StringList();
        feedList(alt_, arg_);
        _formParts.getContainersMapStack().add(new LongTreeMap<DefNodeContainer>());
        _formParts.getCallsFormExps().add(opForm);
        incrForm(varNames, _formParts);
        _rendStack.getFormParts().getFormsArgs().add(arg_);
        String beanName_ = _rendStack.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_));
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn()), _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn())));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        long currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
    }

    public static void incrForm(StringList _varNames, FormParts _formParts) {
        _formParts.getFormatIdMapStack().add(new StringList());
        long currentForm_ = _formParts.getCurrentForm();
        _formParts.getFormsNb().add(currentForm_);
        _formParts.getInputs().add(0L);
        currentForm_++;
        _formParts.setCurrentForm(currentForm_);
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
