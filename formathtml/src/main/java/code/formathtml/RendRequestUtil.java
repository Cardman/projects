package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

final class RendRequestUtil {

    private RendRequestUtil() {
    }


    static Struct invokeMethodWithNumbersBis(Configuration _conf, String _action) {
        Argument arg_ = RenderExpUtil.processEl(_action, 0, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty() || _conf.getContext().getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }

    static Struct redirect(Configuration _conf, Argument _bean, int _url) {
        if (_conf.getHtmlPage().getConstAnchors().get(_url)) {
            String action_ = _conf.getHtmlPage().getAnchorsNames().get(_url);
            if (action_.indexOf('(') == CustList.INDEX_NOT_FOUND_ELT) {
                action_ = StringList.concat(action_,"()");
            }
            return invokeMethodWithNumbersBis(_conf,action_);
        }
        StringList varNames_ = _conf.getHtmlPage().getAnchorsVars().get(_url);
        StringList args_ = _conf.getHtmlPage().getAnchorsArgs().get(_url);
        ImportingPage ip_ = _conf.getLastPage();
        int s_ = varNames_.size();
        for (int i =0; i< s_; i++) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(new IntStruct(Numbers.parseInt(args_.get(i))),_conf.getStandards().getAliasPrimInteger());
            ip_.putLocalVar(varNames_.get(i), locVar_);
        }
        CustList<RendDynOperationNode> exps_ = _conf.getHtmlPage().getCallsExps().get(_url);
        Argument arg_ = RenderExpUtil.calculateReuse(exps_,_conf,_bean);
        for (String n: varNames_) {
            ip_.removeLocalVar(n);
        }
        if (_conf.getContext().getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }
    static void setRendObject(Configuration _conf, NodeContainer _nodeContainer,
                          Struct _attribute) {
        Struct obj_ = _nodeContainer.getStruct();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable lv_ = LocalVariable.newLocalVariable(obj_, _conf);
        ip_.putLocalVar(prev_, lv_);
        lv_ = LocalVariable.newLocalVariable(_attribute,_conf);
        ip_.putLocalVar(attrName_, lv_);
        RenderExpUtil.calculateReuse(wr_,_conf);
        ip_.removeLocalVar(prev_);
        ip_.removeLocalVar(attrName_);
    }
}
