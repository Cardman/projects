package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.formathtml.util.DefNodeContainer;
import code.formathtml.util.RendSelectOperators;
import code.sml.Element;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class RendInput extends RendElement {
    private final RendSelectOperators opers;
    private final DefFieldUpdates fieldUpdates;

    protected RendInput(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                        CustList<RendDynOperationNode> _opsValue,
                        CustList<RendDynOperationNode> _opsConverterField,
                        DefFieldUpdates _f) {
        super(_read, _execAttributes, _execAttributesText);
        opers = new RendSelectOperators(_opsValue,new CustList<RendDynOperationNode>(),new CustList<RendDynOperationNode>(),_opsConverterField,new CustList<RendDynOperationNode>());
        fieldUpdates = _f;
    }

    protected DefFetchedObjs processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall, CustList<RendDynOperationNode> _ls) {
        String idRad_;
        if (!_ls.isEmpty()) {
            idRad_ = idRad(_ls,_ctx,_rendStackCall);
            if (idRad_ == null) {
                CustList<LongTreeMap<DefNodeContainer>> stack_;
                stack_ = _rendStackCall.getFormParts().getContainersMapStack();
                return new DefFetchedObjs("",null, new CustList<Struct>(), stack_, null, "");
            }
        } else {
            idRad_ = "";
        }
        DefFetchedObjs arg_ = fetchName(_cont, _read, _ctx, _rendStackCall,idRad_, fieldUpdates.getOpsRead());
        look(_cont,_write,arg_,_rendStackCall);
        DefFetchedObjs finalArg_ = fetchValue(_cont, _read, _write, opers, _ctx, _rendStackCall, arg_);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getRendKeyWords().getAttrNr()));
        return finalArg_;
    }

    public void prStack(Configuration _cont, Element _write, DefFetchedObjs _fetch, Argument _globalArgument, RendStackCall _rend) {
        prStack(_cont, _write, fieldUpdates, _fetch, _globalArgument, _rend);
    }
    static String idRad(CustList<RendDynOperationNode> _args, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_args.isEmpty()) {
            return "";
        }
        return idRad(RenderExpUtil.getFinalArg(_args,_ctx,_rendStackCall),_ctx,_rendStackCall);
    }
    static String idRad(Struct _args, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_args == null) {
            return null;
        }
        return BeanCustLgNames.processString(_args, _ctx, _rendStackCall);
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
