package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.FieldUpdates;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class RendInput extends RendElement {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private String varName = EMPTY_STRING;
    private String varNameConverter = EMPTY_STRING;
    private String varNameConverterField = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;

    public RendInput(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, CustList<RendDynOperationNode> opsRead, CustList<RendDynOperationNode> opsValue, CustList<RendDynOperationNode> opsWrite, CustList<RendDynOperationNode> opsConverter, CustList<RendDynOperationNode> opsConverterField, String varName, String varNameConverter, String varNameConverterField, String id, String idClass, String idName, String className) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.opsRead = opsRead;
        this.opsValue = opsValue;
        this.opsWrite = opsWrite;
        this.opsConverter = opsConverter;
        this.opsConverterField = opsConverterField;
        this.varName = varName;
        this.varNameConverter = varNameConverter;
        this.varNameConverterField = varNameConverterField;
        this.id = id;
        this.idClass = idClass;
        this.idName = idName;
        this.className = className;
    }

    protected Argument processIndexes(Configuration _cont, Element _read, Element _write) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setClassName(className);
        Argument arg_ = fetchName(_cont, _read, _write, f_);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField);
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        return arg_;
    }

}
