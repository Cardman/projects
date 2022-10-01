package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import cards.president.ResultsPresident;
import code.bean.Bean;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.formathtml.Configuration;
import code.util.CustList;

public final class PresidentStandardsResults extends PresidentStandards {
    private static final String TYPE_PRESIDENT_BEAN = "cards.president.beans.PresidentBean";
    private static final String LINES_DEAL = "linesDeal";
    private static final String NICKNAMES = "nicknames";
    @Override
    public void buildOther() {
        buildPresidentBean();
        buildLineDeal();
    }

    private void buildPresidentBean() {
        CustList<StandardField> fields_;
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new SpecialNatClass(TYPE_PRESIDENT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, false, false, new PresidentBeanNicknames(), null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false, new PresidentBeanLinesDeal(), null));
        getStds().addEntry(TYPE_PRESIDENT_BEAN, std_);
    }

    private void buildLineDeal() {
        LineDealStruct.buildLineDeal(getStds());
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0, beanResults(_language, getDataBase()));
    }

    static PresidentBeanStruct beanResults(String _language, ResultsPresident _dataBase) {
        PresidentBeanStruct str_ = bean(new PresidentBean(), TYPE_PRESIDENT_BEAN);
        Bean bean_ = str_.getBean();
        ((PresidentBean)bean_).setDataBase(_dataBase);
        bean_.setLanguage(_language);
        return str_;
    }
}
