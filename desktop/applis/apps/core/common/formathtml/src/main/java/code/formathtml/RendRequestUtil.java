package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefNodeContainer;
import code.formathtml.util.InputInfo;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public final class RendRequestUtil {

    private RendRequestUtil() {
    }


    public static void removeVars(StringList _varNames, ImportingPage ip_) {
        for (String n: _varNames) {
            ip_.removeRefVar(n);
        }
    }

    public static void setRendObject(DefNodeContainer _nodeContainer,
                                     Struct _attribute, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall) {
        Struct obj_ = _nodeContainer.getUpdated();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        LocalVariable lv_ = LocalVariable.newLocalVariable(obj_, _nodeContainer.getUpdatedClass());
        ip_.putValueVar(prev_, new VariableWrapper(lv_));
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
                if (info_.getRefs().get(j_) == BoolVal.TRUE) {
                    refParams_.add(p);
                    ip_.getRefParams().put(p, _nodeContainer.getWrappers().get(i_));
                    i_++;
                } else {
                    locVars_.add(p);
                    Struct arg_ = structParam_.get(k_);
                    lv_ = LocalVariable.newLocalVariable(arg_, structParamClass_.get(k_));
                    ip_.putValueVar(p, new VariableWrapper(lv_));
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
                ip_.putValueVar(p, new VariableWrapper(lv_));
                i_++;
            }
        }
        String wrap_ = ExecInherits.toWrapper(_nodeContainer.getNodeInformation().getInputClass(), _advStandards);
        lv_ = LocalVariable.newLocalVariable(_attribute,wrap_);
        ip_.putValueVar(attrName_, new VariableWrapper(lv_));
        RenderExpUtil.getAllArgs(wr_, _context, _rendStackCall).lastValue();
        ip_.removeRefVar(prev_);
        removeVars(locVars_, ip_);
        for (String p: refParams_) {
            ip_.getRefParams().removeKey(p);
        }
        ip_.removeRefVar(attrName_);
    }
}
