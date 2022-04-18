package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Element;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class RendInput extends RendElement {
    private final CustList<RendDynOperationNode> opsValue;
    private final CustList<RendDynOperationNode> opsConverterField;
    private final String varNameConverterField;
    private final DefFieldUpdates fieldUpdates;

    protected RendInput(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText,
                        CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        String _varNameConverterField, DefFieldUpdates _f) {
        super(_read, _execAttributes, _execAttributesText);
        this.opsValue = _opsValue;
        this.opsConverterField = _opsConverterField;
        this.varNameConverterField = _varNameConverterField;
        fieldUpdates = _f;
    }

    protected Argument processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall, CustList<RendDynOperationNode> _ls) {
        String idRad_;
        if (!_ls.isEmpty()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(_ls, _ctx, _rendStackCall);
            idRad_ = idRad(args_,_ctx,_rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return Argument.createVoid();
            }
        } else {
            idRad_ = "";
        }
        Argument arg_ = fetchName(_cont, _read, _write, fieldUpdates, _ctx, _rendStackCall,idRad_);
        fetchValue(_cont,_read,_write,opsValue,varNameConverterField,opsConverterField, _ctx, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getRendKeyWords().getAttrNr()));
        return arg_;
    }

    private static String idRad(IdMap<RendDynOperationNode, ArgumentsPair> args_, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return "";
        }
        return BeanCustLgNames.processStr(Argument.getNullableValue(args_.lastKey().getArgument()), _ctx,_rendStackCall);
    }

    public static DefFieldUpdates initUpdates(String _idClass, String _idName, CustList<RendDynOperationNode> _opsRead, String _varNameConverter, CustList<RendDynOperationNode> _opsConverter, String _className, CustList<RendDynOperationNode> _idRadio) {
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(_idClass);
        f_.setIdName(_idName);
        f_.setOpsRead(_opsRead);
        f_.setVarNameConverter(_varNameConverter);
        f_.setOpsConverter(_opsConverter);
        f_.setClassName(_className);
        f_.setIdRadio(_idRadio);
        return f_;
    }
}
