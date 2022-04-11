package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendStdInput extends RendInput {

    public RendStdInput(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText,
                        CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverter, CustList<RendDynOperationNode> _opsConverterField,
                        String _varNameConverter, String _varNameConverterField,
                        String _id, String _idClass, String _idName, String _className) {
        super(_read, _execAttributes, _execAttributesText, _opsRead, _opsValue, _opsConverter, _opsConverterField, _varNameConverter, _varNameConverterField, _id, _idClass, _idName, _className);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processIndexes(_cont,_read, (Element) _nextWrite, _ctx, _rendStack);
    }
}
