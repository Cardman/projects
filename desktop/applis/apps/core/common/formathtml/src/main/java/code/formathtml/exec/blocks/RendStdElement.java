package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringMap;

public final class RendStdElement extends RendElement {

    public RendStdElement(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {

    }
}
