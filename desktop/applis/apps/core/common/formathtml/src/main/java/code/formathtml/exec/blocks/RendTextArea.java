package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendTextArea extends RendParentBlock implements RendWithEl {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private StringMap<DefExecTextPart> execAttributesText = new StringMap<DefExecTextPart>();
    private StringMap<DefExecTextPart> execAttributes = new StringMap<DefExecTextPart>();

    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private final Element elt;

    public RendTextArea(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverter, CustList<RendDynOperationNode> _opsConverterField,
                        StringMap<DefExecTextPart> _execAttributesText, StringMap<DefExecTextPart> _execAttributes,
                        String _varNameConverter, String _varNameConverterField,
                        String _idClass, String _idName, String _className, Element _elt) {
        this.opsRead = _opsRead;
        this.opsValue = _opsValue;
        this.opsConverter = _opsConverter;
        this.opsConverterField = _opsConverterField;
        this.execAttributesText = _execAttributesText;
        this.execAttributes = _execAttributes;
        this.varNameConverter = _varNameConverter;
        this.varNameConverterField = _varNameConverterField;
        this.idClass = _idClass;
        this.idName = _idName;
        this.className = _className;
        this.elt = _elt;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordTextarea());
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setClassName(className);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        for (EntryCust<String, DefExecTextPart> e: execAttributesText.entryList()) {
            DefExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (elt.hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()))) {
            docElementSelect_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
        }
        DefFetchedObjs def_ = fetchName(_cont, elt, _ctx, _rendStack, "", opsRead);
        look(_cont,docElementSelect_,def_,_rendStack);
        fetchValue(_cont,elt,docElementSelect_,opsValue,varNameConverterField,opsConverterField, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        for (EntryCust<String, DefExecTextPart> e: execAttributes.entryList()) {
            DefExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        prStack(_cont,docElementSelect_,f_,def_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        simpleAppendChild(doc_, rw_, docElementSelect_);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
