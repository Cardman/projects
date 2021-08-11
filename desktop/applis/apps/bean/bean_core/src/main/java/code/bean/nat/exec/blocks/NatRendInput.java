package code.bean.nat.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.FieldUpdates;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class NatRendInput extends NatRendElement {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varName = RendBlockHelp.EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String varNameConverter = RendBlockHelp.EMPTY_STRING;
    private String id = RendBlockHelp.EMPTY_STRING;
    private String idClass = RendBlockHelp.EMPTY_STRING;
    private String idName = RendBlockHelp.EMPTY_STRING;
    private String className = RendBlockHelp.EMPTY_STRING;

    public NatRendInput(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                        CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue, CustList<RendDynOperationNode> _opsWrite,
                        String _varName, String _varNameConverter,
                        String _id, String _idClass, String _idName, String _className, InputInfo _list) {
        super(_read, _execAttributes, _execAttributesText);
        this.opsRead = _opsRead;
        this.opsValue = _opsValue;
        this.opsWrite = _opsWrite;
        this.opsConverter = new CustList<RendDynOperationNode>();
        varNames = _list;
        this.varName = _varName;
        this.varNameConverter = _varNameConverter;
        this.id = _id;
        this.idClass = _idClass;
        this.idName = _idName;
        this.className = _className;
    }

    protected Argument processIndexes(Configuration _cont, Element _read, Element _write, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNames(varNames);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setClassName(className);
        Argument arg_ = RendBlockHelp.fetchName(_cont, _read, _write, f_, _advStandards, _ctx, _rendStackCall);
        RendBlockHelp.fetchValue(_cont,_read,_write,opsValue, _advStandards, _ctx, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        return arg_;
    }

}
