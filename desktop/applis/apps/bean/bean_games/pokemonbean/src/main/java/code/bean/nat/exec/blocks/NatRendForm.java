package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatFormParts;
import code.bean.nat.exec.NatNodeContainer;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.NatRendStackCallAdv;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class NatRendForm extends NatRendElementForm implements NatRendElem {
    private final CustList<NatExecOperationNode> opForm;

    private final StringList varNames;

    public NatRendForm(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText, CustList<NatExecOperationNode> _opForm, StringList _varNames) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.varNames = _varNames;
    }

    void form(NatConfigurationCore _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        NatFormParts formParts_ = ((NatRendStackCallAdv)_rendStack).getFormParts();
        formParts_.getContainersMapStack().add(new LongTreeMap<NatNodeContainer>());
        formParts_.getCallsFormExps().add(opForm);
        incrForm(varNames, formParts_);
        long currentForm_;
//        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
//        if (!href_.startsWith(RendBlockHelp.CALL_METHOD)) {
//            procCstRef(_cont, elt_, _rendStack.getFormParts());
//            return;
//        }
//        StringList alt_ = NatRenderingText.renderAltListNat(textPart, _rendStack);
        StringList arg_ = new StringList();
//        String render_ = StringUtil.join(alt_,"");
        ((NatRendStackCallAdv)_rendStack).getFormParts().getFormsArgs().add(arg_);
        ((NatRendStackCallAdv)_rendStack).getFormParts().getStructsForm().add(_rendStack.getLastPage().getGlobalArgument());
//        String beanName_ = _rendStack.getLastPage().getBeanName();
//        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(RendBlockHelp.CALL_METHOD,beanName_, RendBlockHelp.DOT,render_));
        elt_.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrAction(), RendBlockHelp.EMPTY_STRING);
        currentForm_ = ((NatRendStackCallAdv)_rendStack).getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrNf(), Long.toString(currentForm_ - 1));
    }

//    public static void procCstRef(Configuration _cont, Element _elt, NatFormParts _formParts) {
//        long currentForm_;
//        _formParts.getFormsArgs().add(new StringList());
//        if (_elt.hasAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()))) {
//            _elt.setAttribute(_cont.getRendKeyWords().getAttrAction(), "");
//        }
//        currentForm_ = _formParts.getCurrentForm();
//        _elt.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
//    }
    public static void incrForm(StringList _varNames, NatFormParts _formParts) {
        _formParts.getFormatIdMapStack().add(new StringList());
        long currentForm_ = _formParts.getCurrentForm();
        _formParts.getFormsNb().add(currentForm_);
        _formParts.getInputs().add(0L);
        currentForm_++;
        _formParts.setCurrentForm(currentForm_);
        _formParts.getFormsVars().add(_varNames);
    }
}
