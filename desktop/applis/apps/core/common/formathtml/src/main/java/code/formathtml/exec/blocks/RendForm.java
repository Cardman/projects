package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.*;
import code.util.core.StringUtil;

public final class RendForm extends RendElement {
    private CustList<RendDynOperationNode> opForm;

    private StringList varNames;
    private ExecTextPart textPart;

    public RendForm(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, CustList<RendDynOperationNode> _opForm, StringList _varNames, ExecTextPart _textPart) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        long currentForm_ = _cont.getFormParts().getCurrentForm();
        _cont.getFormParts().getContainersMapStack().add(new LongTreeMap< NodeContainer>());
        _cont.getFormParts().getFormatIdMapStack().add(new StringList());
        _cont.getFormParts().getFormsNb().add(currentForm_);
        _cont.getFormParts().getInputs().add(0L);
        currentForm_++;
        _cont.getFormParts().setCurrentForm(currentForm_);
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _cont.getFormParts().getCallsFormExps().add(opForm);
        _cont.getFormParts().getFormsVars().add(varNames);
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            _cont.getFormParts().getFormsArgs().add(new StringList());
            if (elt_.hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
            }
            _cont.getFormParts().getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getFormParts().getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        StringList alt_ = RenderingText.renderAltList(textPart, _cont, _stds, _ctx);
        StringList arg_ = new StringList();
        int len_ = alt_.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(alt_.get(i));
        }
        String render_ = StringUtil.join(alt_,"");
        if (_ctx.callsOrException()) {
            _cont.getFormParts().getFormsArgs().add(new StringList());
            _cont.getFormParts().getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getFormParts().getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        _cont.getFormParts().getFormsArgs().add(arg_);
        _cont.getFormParts().getFormsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,render_));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        currentForm_ = _cont.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
    }

}
