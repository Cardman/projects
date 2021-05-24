package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.InputInfo;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;

final class RendRequestUtil {

    private RendRequestUtil() {
    }


    static Struct redirect(Argument _bean, int _url, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall, HtmlPage _htmlPage) {
        StringList varNames_ = _htmlPage.getAnchorsVars().get(_url);
        CustList<RendDynOperationNode> exps_ = _htmlPage.getCallsExps().get(_url);
        StringList args_ = _htmlPage.getAnchorsArgs().get(_url);
        return calculate(_bean, varNames_, exps_, args_, _advStandards, _context, _rendStackCall);
    }

    private static Struct calculate(Argument _bean, StringList _varNames, CustList<RendDynOperationNode> _exps, StringList _args, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        int s_ = _varNames.size();
        for (int i = 0; i< s_; i++) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(new LongStruct(NumberUtil.parseLongZero(_args.get(i))), _advStandards.getAliasPrimLong());
            ip_.putValueVar(_varNames.get(i), locVar_);
        }
        Argument arg_ = RenderExpUtil.calculateReuse(_exps, _bean, _advStandards, _context, _rendStackCall);
        for (String n: _varNames) {
            ip_.removeRefVar(n);
        }
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }

    static Struct redirectForm(Argument _bean, int _url, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall, HtmlPage _htmlPage) {
        StringList varNames_ = _htmlPage.getFormsVars().get(_url);
        CustList<RendDynOperationNode> exps_ = _htmlPage.getCallsFormExps().get(_url);
        StringList args_ = _htmlPage.getFormsArgs().get(_url);
        return calculate(_bean, varNames_, exps_, args_, _advStandards, _context, _rendStackCall);
    }
    static void setRendObject(NodeContainer _nodeContainer,
                              Struct _attribute, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Struct obj_ = _nodeContainer.getUpdated();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        LocalVariable lv_ = LocalVariable.newLocalVariable(obj_, _nodeContainer.getUpdatedClass());
        ip_.putValueVar(prev_, lv_);
        CustList<Struct> structParam_ = _nodeContainer.getStructParam();
        CustList<String> structParamClass_ = _nodeContainer.getStructParamClass();
        InputInfo info_ = _nodeContainer.getVarParamName();
        StringList refParams_ = new StringList();
        StringList locVars_ = new StringList();
        if (_nodeContainer.isIndexer()) {
            int i_ = 0;
            int j_ = 0;
            int k_ = 0;
            for (String p: info_.getVarNames()) {
                if (info_.getRefs().get(j_)) {
                    refParams_.add(p);
                    ip_.getRefParams().put(p, _nodeContainer.getWrappers().get(i_));
                    i_++;
                } else {
                    locVars_.add(p);
                    Struct arg_ = structParam_.get(k_);
                    lv_ = LocalVariable.newLocalVariable(arg_, structParamClass_.get(k_));
                    ip_.putValueVar(p, lv_);
                    k_++;
                }
                j_++;
            }
        } else {
            int i_ = 0;
            for (String p: info_.getVarNames()) {
                locVars_.add(p);
                Struct arg_ = structParam_.get(i_);
                lv_ = LocalVariable.newLocalVariable(arg_, structParamClass_.get(i_));
                ip_.putValueVar(p, lv_);
                i_++;
            }
        }
        String wrap_ = ExecInherits.toWrapper(_nodeContainer.getNodeInformation().getInputClass(), _context.getStandards());
        lv_ = LocalVariable.newLocalVariable(_attribute,wrap_);
        ip_.putValueVar(attrName_, lv_);
        RenderExpUtil.calculateReuse(wr_, _advStandards, _context, _rendStackCall);
        ip_.removeRefVar(prev_);
        for (String p: locVars_) {
            ip_.removeRefVar(p);
        }
        for (String p: refParams_) {
            ip_.getRefParams().removeKey(p);
        }
        ip_.removeRefVar(attrName_);
    }
}
