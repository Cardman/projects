package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

final class RendRequestUtil {

    private RendRequestUtil() {
    }


    static Struct redirect(Configuration _conf, Argument _bean, int _url) {
        StringList varNames_ = _conf.getHtmlPage().getAnchorsVars().get(_url);
        CustList<RendDynOperationNode> exps_ = _conf.getHtmlPage().getCallsExps().get(_url);
        StringList args_ = _conf.getHtmlPage().getAnchorsArgs().get(_url);
        return calculate(_conf, _bean, varNames_, exps_, args_);
    }

    private static Struct calculate(Configuration _conf, Argument _bean, StringList varNames_, CustList<RendDynOperationNode> exps_, StringList args_) {
        ImportingPage ip_ = _conf.getLastPage();
        int s_ = varNames_.size();
        for (int i =0; i< s_; i++) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(new LongStruct(Numbers.parseLongZero(args_.get(i))),_conf.getStandards().getAliasPrimLong());
            ip_.putLocalVar(varNames_.get(i), locVar_);
        }
        Argument arg_ = RenderExpUtil.calculateReuse(exps_,_conf,_bean);
        for (String n: varNames_) {
            ip_.removeLocalVar(n);
        }
        if (_conf.getContext().hasException()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }

    static Struct redirectForm(Configuration _conf, Argument _bean, int _url) {
        StringList varNames_ = _conf.getHtmlPage().getFormsVars().get(_url);
        CustList<RendDynOperationNode> exps_ = _conf.getHtmlPage().getCallsFormExps().get(_url);
        StringList args_ = _conf.getHtmlPage().getFormsArgs().get(_url);
        return calculate(_conf, _bean, varNames_, exps_, args_);
    }
    static void setRendObject(Configuration _conf, NodeContainer _nodeContainer,
                          Struct _attribute) {
        Struct obj_ = _nodeContainer.getUpdated();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable lv_ = LocalVariable.newLocalVariable(obj_, _nodeContainer.getUpdatedClass());
        ip_.putLocalVar(prev_, lv_);
        int i_ = 0;
        CustList<Struct> structParam_ = _nodeContainer.getStructParam();
        CustList<String> structParamClass_ = _nodeContainer.getStructParamClass();
        for (String p: _nodeContainer.getVarParamName()) {
            Struct arg_ = structParam_.get(i_);
            lv_ = LocalVariable.newLocalVariable(arg_, structParamClass_.get(i_));
            ip_.putLocalVar(p, lv_);
            i_++;
        }
        String wrap_ = PrimitiveTypeUtil.toWrapper(_nodeContainer.getNodeInformation().getInputClass(), _conf.getStandards());
        lv_ = LocalVariable.newLocalVariable(_attribute,wrap_);
        ip_.putLocalVar(attrName_, lv_);
        RenderExpUtil.calculateReuse(wr_,_conf);
        ip_.removeLocalVar(prev_);
        for (String p: _nodeContainer.getVarParamName()) {
            ip_.removeLocalVar(p);
        }
        ip_.removeLocalVar(attrName_);
    }
}
