package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.RendSelectOperators;
import code.sml.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendTextArea extends RendElement {
    private final RendSelectOperators opers;

    private final DefFieldUpdates defFieldUpdates;

    public RendTextArea(CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _execAttributes,
                        Element _elt, DefFieldUpdates _txt) {
        super(_elt,_execAttributes,_execAttributesText);
        opers = new RendSelectOperators(_opsValue,new CustList<RendDynOperationNode>(),new CustList<RendDynOperationNode>(),_opsConverterField,new CustList<RendDynOperationNode>());
        defFieldUpdates = _txt;
    }

    public static DefFieldUpdates initElts(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsConverter, String _idClass, String _idName, String _className) {
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(_idClass);
        f_.setIdName(_idName);
        f_.setOpsRead(_opsRead);
        f_.setClassName(_className);
        f_.setOpsConverter(_opsConverter);
        return f_;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        doc_.renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordTextarea());
        Element docElementArea_ = (Element) _nextWrite;
        if (getRead().hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()))) {
            docElementArea_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    getRead().getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
        }
        DefFetchedObjs defArea_ = fetchName(_cont, getRead(), _ctx, _rendStack, "", defFieldUpdates.getOpsRead());
        look(_cont,docElementArea_,defArea_,_rendStack);
        DefFetchedObjs finalArg_ = fetchValue(_cont, getRead(), docElementArea_, opers, _ctx, _rendStack, defArea_);
        if (finalArg_.getArg() == null) {
            return null;
        }
        docElementArea_.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        docElementArea_.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        prStack(_cont,docElementArea_,defFieldUpdates,defArea_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        return NullStruct.NULL_VALUE;
    }
}
