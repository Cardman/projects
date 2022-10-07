package cards.president.beans;

import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.Bean;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.InvokedPageOutput;
import code.bean.nat.StringMapObjectBase;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.DefNatBlockBuilder;
import code.formathtml.Configuration;

public abstract class PresidentStandards extends BeanNatCommonLgNames {
    private RulesPresident dataBaseRules;
    private ResultsPresident dataBase;
    protected PresidentStandards() {
    }

    @Override
    public InvokedPageOutput processAfterInvoke(Configuration _conf, String _dest, String _curUrl, String _beanName, StringMapObjectBase _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack);
        return new InvokedPageOutput(_dest,res_);
    }

    protected static PresidentBeanStruct bean(Bean _bean, String _name) {
        _bean.setClassName(_name);
        return (new PresidentBeanStruct(_bean));
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

    @Override
    protected AbstractNatBlockBuilder blockBuilder() {
        return new DefNatBlockBuilder();
    }
}
