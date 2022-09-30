package cards.president.beans;

import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.Bean;
import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.formathtml.Configuration;
import code.util.CustList;

public abstract class PresidentStandards extends BeanNatCommonLgNames {
    protected static final String TYPE_LINE_DEAL = "cards.president.beans.LineDeal";
    private RulesPresident dataBaseRules;
    private ResultsPresident dataBase;
    protected PresidentStandards() {
        DefaultInitialization.basicStandards(this);
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, StringMapObjectBase _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, _rendStack);
    }

    protected static PresidentBeanStruct bean(Bean _bean, String _name) {
        _bean.setClassName(_name);
        return (new PresidentBeanStruct(_bean));
    }

    public static ArrayStruct getLineDealArray(CustList<PresidentLineDeal> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LINE_DEAL));
        int j_ = 0;
        for (PresidentLineDeal s:_ls) {
            arr_.set(j_,new PresidentLineDealStruct(s, TYPE_LINE_DEAL));
            j_++;
        }
        return arr_;
    }

    public ResultsPresident getDataBase() {
        return dataBase;
    }

    public void setDataBase(ResultsPresident _dataBase){
        dataBase = _dataBase;
    }

    public RulesPresident getDataBaseRules() {
        return dataBaseRules;
    }

    public void setDataBaseRules(RulesPresident _dataBase){
        dataBaseRules = _dataBase;
    }
}
