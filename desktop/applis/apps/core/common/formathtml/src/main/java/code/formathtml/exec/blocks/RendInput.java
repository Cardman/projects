package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.formathtml.util.DefNodeContainer;
import code.sml.Element;
import code.util.CustList;
import code.util.IdMap;
import code.util.LongTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class RendInput extends RendElement {
    private final CustList<RendDynOperationNode> opsValue;
    private final CustList<RendDynOperationNode> opsConverterField;
    private final DefFieldUpdates fieldUpdates;

    protected RendInput(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                        CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        DefFieldUpdates _f) {
        super(_read, _execAttributes, _execAttributesText);
        this.opsValue = _opsValue;
        this.opsConverterField = _opsConverterField;
        fieldUpdates = _f;
    }

    protected DefFetchedObjs processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall, CustList<RendDynOperationNode> _ls) {
        String idRad_;
        if (!_ls.isEmpty()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(_ls, _ctx, _rendStackCall);
            idRad_ = idRad(args_,_ctx,_rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                CustList<LongTreeMap<DefNodeContainer>> stack_;
                stack_ = _rendStackCall.getFormParts().getContainersMapStack();
                return new DefFetchedObjs("",null, new CustList<Struct>(), stack_, Argument.createVoid(), "");
            }
        } else {
            idRad_ = "";
        }
        DefFetchedObjs arg_ = fetchName(_cont, _read, _ctx, _rendStackCall,idRad_, fieldUpdates.getOpsRead());
        look(_cont,_write,arg_,_rendStackCall);
        fetchValue(_cont,_read,_write,opsValue, opsConverterField, _ctx, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getRendKeyWords().getAttrNr()));
        return arg_;
    }

    public void prStack(Configuration _cont, Element _write, DefFetchedObjs _fetch, Argument _globalArgument, RendStackCall _rend) {
        prStack(_cont, _write, fieldUpdates, _fetch, _globalArgument, _rend);
    }
    static String idRad(IdMap<RendDynOperationNode, ArgumentsPair> args_, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return "";
        }
        if (args_.isEmpty()) {
            return "";
        }
        return BeanCustLgNames.processStr(Argument.getNullableValue(args_.lastValue().getArgument()), _ctx,_rendStackCall);
    }

    public static DefFieldUpdates initUpdates(String _idClass, String _idName, CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsConverter, String _className, CustList<RendDynOperationNode> _idRadio) {
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(_idClass);
        f_.setIdName(_idName);
        f_.setOpsRead(_opsRead);
        f_.setOpsConverter(_opsConverter);
        f_.setClassName(_className);
        f_.setIdRadio(_idRadio);
        return f_;
    }
}
