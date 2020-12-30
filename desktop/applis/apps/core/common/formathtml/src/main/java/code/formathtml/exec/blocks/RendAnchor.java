package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendAnchor extends RendElement {
    private final CustList<RendDynOperationNode> opExpAnch;

    private final StringList varNames;
    private final ExecTextPart textPart;

    public RendAnchor(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                      CustList<RendDynOperationNode> _opAnc,
                      StringList _varNames, ExecTextPart _textPart) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
        opExpAnch = _opAnc;
        this.varNames = _varNames;
        this.textPart = _textPart;
    }


    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        processLink(_cont, (Element) _nextWrite, _read, varNames, textPart,opExpAnch, _stds, _ctx, _stack, _rendStack);
    }

}
