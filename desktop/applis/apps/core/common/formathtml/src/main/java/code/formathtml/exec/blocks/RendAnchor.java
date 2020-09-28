package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendAnchor extends RendElement {
    private CustList<RendDynOperationNode> opExpAnch;

    private StringList varNames = new StringList();
    private ExecTextPart textPart;

    public RendAnchor(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText,
                      CustList<RendDynOperationNode> _opAnc,
                      StringList varNames, ExecTextPart textPart) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        opExpAnch = _opAnc;
        this.varNames = varNames;
        this.textPart = textPart;
    }


    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processLink(_cont, (Element) _nextWrite, _read, varNames, textPart,opExpAnch);
    }

}
