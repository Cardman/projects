package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class NatRendStdInput extends NatRendInput {

    public NatRendStdInput(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                           CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue, CustList<RendDynOperationNode> _opsWrite,
                           String _varName, String _varNameConverter,
                           String _id, String _idClass, String _idName, String _className, InputInfo _list) {
        super(_read, _execAttributes, _execAttributesText, _opsRead, _opsValue, _opsWrite, _varName, _varNameConverter, _id, _idClass, _idName, _className,_list);
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processIndexes(_cont,_read, (Element) _nextWrite, _stds, _ctx, _rendStack);
    }
}
