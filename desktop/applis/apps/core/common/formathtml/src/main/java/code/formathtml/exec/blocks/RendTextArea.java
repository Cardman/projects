package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendTextArea extends RendParentBlock implements RendWithEl {
    private final CustList<RendDynOperationNode> opsValue;
    private final CustList<RendDynOperationNode> opsConverterField;
    private final StringMap<CustList<RendDynOperationNode>> execAttributesText;
    private final StringMap<CustList<RendDynOperationNode>> execAttributes;

    private final Element elt;
    private final DefFieldUpdates defFieldUpdates;

    public RendTextArea(CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _execAttributes,
                        Element _elt, DefFieldUpdates _txt) {
        this.opsValue = _opsValue;
        this.opsConverterField = _opsConverterField;
        this.execAttributesText = _execAttributesText;
        this.execAttributes = _execAttributes;
        this.elt = _elt;
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
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordTextarea());

        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributesText.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (elt.hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()))) {
            docElementSelect_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
        }
        DefFetchedObjs def_ = fetchName(_cont, elt, _ctx, _rendStack, "", defFieldUpdates.getOpsRead());
        look(_cont,docElementSelect_,def_,_rendStack);
        fetchValue(_cont,elt,docElementSelect_,opsValue, opsConverterField, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributes.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        prStack(_cont,docElementSelect_,defFieldUpdates,def_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        simpleAppendChild(doc_, rw_, docElementSelect_);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
