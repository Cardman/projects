package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Element;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class RendInput extends RendElement {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;

    protected RendInput(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText,
                        CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverter, CustList<RendDynOperationNode> _opsConverterField, String _varNameConverter,
                        String _varNameConverterField, String _idClass, String _idName, String _className) {
        super(_read, _execAttributes, _execAttributesText);
        this.opsRead = _opsRead;
        this.opsValue = _opsValue;
        this.opsConverter = _opsConverter;
        this.opsConverterField = _opsConverterField;
        this.varNameConverter = _varNameConverter;
        this.varNameConverterField = _varNameConverterField;
        this.idClass = _idClass;
        this.idName = _idName;
        this.className = _className;
    }

    protected Argument processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall) {
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setClassName(className);
        Argument arg_ = fetchName(_cont, _read, _write, f_, _ctx, _rendStackCall);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField, _ctx, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        return arg_;
    }

}
