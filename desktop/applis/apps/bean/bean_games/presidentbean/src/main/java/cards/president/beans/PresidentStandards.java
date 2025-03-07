package cards.president.beans;

import cards.president.*;
import code.bean.*;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.fwd.*;

public abstract class PresidentStandards extends BeanNatCommonLgNames {
    private RulesPresident dataBaseRules;
    private ResultsPresident dataBase;
    protected PresidentStandards() {
    }

    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack,ip_);
        return new InvokedPageOutput(_dest,res_);
    }

    @Override
    public void initializeRendSessionDoc(NatNavigation _nav, NatRendStackCall _rendStackCall) {
        _rendStackCall.init();
        NatConfigurationCore session_ = _nav.getSession();
        initBeans(session_,_nav.getLanguage());
        proc(_nav, _rendStackCall, session_.getFirstUrl(), getBeanOrNull(_nav.getCurrentBeanName()));
    }
    protected static PresidentBeanStruct bean(Bean _bean) {
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

    @Override
    protected NatRendStackCall newNatRendStackCall() {
        return new NatRendStackCall();
    }
}
