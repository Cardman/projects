package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.FieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendTextArea extends RendParentBlock implements RendWithEl, RendReducableOperations {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private StringMap<ExecTextPart> execAttributesText = new StringMap<ExecTextPart>();
    private StringMap<ExecTextPart> execAttributes = new StringMap<ExecTextPart>();

    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String varName = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private Element elt;

    public RendTextArea(int _offsetTrim, CustList<RendDynOperationNode> opsRead, CustList<RendDynOperationNode> opsValue, CustList<RendDynOperationNode> opsWrite, CustList<RendDynOperationNode> opsConverter, CustList<RendDynOperationNode> opsConverterField, StringMap<ExecTextPart> execAttributesText, StringMap<ExecTextPart> execAttributes, String varNameConverter, String varNameConverterField, String varName, String id, String idClass, String idName, String className, Element elt) {
        super(_offsetTrim);
        this.opsRead = opsRead;
        this.opsValue = opsValue;
        this.opsWrite = opsWrite;
        this.opsConverter = opsConverter;
        this.opsConverterField = opsConverterField;
        this.execAttributesText = execAttributesText;
        this.execAttributes = execAttributes;
        this.varNameConverter = varNameConverter;
        this.varNameConverterField = varNameConverterField;
        this.varName = varName;
        this.id = id;
        this.idClass = idClass;
        this.idName = idName;
        this.className = className;
        this.elt = elt;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        RendReadWrite rw_ = _cont.getLastPage().getRendReadWrite();
        Element write_ = (Element) rw_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordTextarea());
        write_.appendChild(docElementSelect_);
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setClassName(className);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        for (EntryCust<String, ExecTextPart> e: execAttributesText.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _cont, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (elt.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()))) {
            docElementSelect_.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
        }
        fetchName(_cont, elt, docElementSelect_, f_, _stds, _ctx);
        fetchValue(_cont,elt,docElementSelect_,opsValue,varNameConverterField,opsConverterField, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        for (EntryCust<String, ExecTextPart> e: execAttributes.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _cont, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        processBlock(_cont, _stds, _ctx);
    }
}
