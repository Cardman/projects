package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import code.bean.Bean;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.bean.nat.analyze.NatConfigurationCore;
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
        std_ = new SpecialNatClass(fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, new PresidentBeanNicknames(), null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, new PresidentBeanLinesDeal(), null));
        getStds().addEntry(TYPE_PRESIDENT_BEAN, std_);
    }

    private void buildLineDeal() {
        LineDealStruct.buildLineDeal(getStds());
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0, beanResults(_language));
    }

    PresidentBeanStruct beanResults(String _language) {
        PresidentBeanStruct str_ = bean(new PresidentBean());
        Bean bean_ = str_.getBean();
        ((PresidentBean)bean_).setDataBase(getDataBase());
        bean_.setLanguage(_language);
        return str_;
    }
}
