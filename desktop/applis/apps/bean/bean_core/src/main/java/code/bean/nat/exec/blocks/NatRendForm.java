package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatFormParts;
import code.bean.nat.exec.NatNodeContainer;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendForm;
import code.formathtml.exec.blocks.RendFormInt;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendForm extends NatRendElement implements RendFormInt {
    private final CustList<NatExecOperationNode> opForm;

    private final StringList varNames;
    private final NatExecTextPart textPart;

    public NatRendForm(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText, CustList<NatExecOperationNode> _opForm, StringList _varNames, NatExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opForm = _opForm;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        NatFormParts _formParts = _rendStack.getFormParts();
        _formParts.getContainersMapStack().add(new LongTreeMap<NatNodeContainer>());
        _formParts.getCallsFormExps().add(opForm);
        RendForm.incrForm(varNames, _formParts);
        long currentForm_;
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(RendBlockHelp.CALL_METHOD)) {
            RendForm.procCstRef(_cont, elt_, _rendStack.getFormParts());
            return;
        }
        StringList alt_ = NatRenderingText.renderAltListNat(textPart, _rendStack);
        StringList arg_ = new StringList();
        RendForm.feedList(alt_,arg_);
        String render_ = StringUtil.join(alt_,"");
        _rendStack.getFormParts().getFormsArgs().add(arg_);
        String beanName_ = _rendStack.getLastPage().getBeanName();
        elt_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(RendBlockHelp.CALL_METHOD,beanName_, RendBlockHelp.DOT,render_));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), RendBlockHelp.EMPTY_STRING);
        currentForm_ = _rendStack.getFormParts().getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), Long.toString(currentForm_ - 1));
    }

}
