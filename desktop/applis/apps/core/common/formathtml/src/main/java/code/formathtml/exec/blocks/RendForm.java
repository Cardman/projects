package code.formathtml.exec.blocks;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class RendForm extends RendElement {
    private CustList<OperationNode> opExpRoot;
    private CustList<RendDynOperationNode> opForm;

    private StringList varNames = new StringList();
    private ExecTextPart textPart;

    public RendForm(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, CustList<RendDynOperationNode> opForm, StringList varNames, ExecTextPart textPart) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.opForm = opForm;
        this.varNames = varNames;
        this.textPart = textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        long currentForm_ = _cont.getCurrentForm();
        _cont.getContainersMapStack().add(new LongTreeMap< NodeContainer>());
        _cont.getFormatIdMapStack().add(new StringList());
        _cont.getFormsNb().add(currentForm_);
        _cont.getInputs().add(0L);
        currentForm_++;
        _cont.setCurrentForm(currentForm_);
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _cont.getCallsFormExps().add(opForm);
        _cont.getFormsVars().add(varNames);
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            _cont.getFormsArgs().add(new StringList());
            if (elt_.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
            }
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        StringList alt_ = RenderingText.renderAltList(textPart, _cont);
        StringList arg_ = new StringList();
        int len_ = alt_.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(alt_.get(i));
        }
        String render_ = StringList.join(alt_,"");
        if (_cont.getContext().callsOrException()) {
            _cont.getFormsArgs().add(new StringList());
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        _cont.getFormsArgs().add(arg_);
        _cont.getFormsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        elt_.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        currentForm_ = _cont.getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
    }

}
