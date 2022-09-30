package cards.president.beans;

import code.bean.Bean;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.formathtml.Configuration;
import code.util.CustList;

public final class PresidentStandardsResults extends PresidentStandards {
    private static final String TYPE_PRESIDENT_BEAN = "cards.president.beans.PresidentBean";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
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
        CustList<StandardField> fields_ = new CustList<StandardField>();
        CustList<SpecNatMethod> methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NUMBER, PRIM_INTEGER, false, false, new LineDealNumber(),null));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false, new LineDealScores(),null));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0,update(_language,bean(new PresidentBean(), TYPE_PRESIDENT_BEAN)));
    }

    private PresidentBeanStruct update(String _language, PresidentBeanStruct _str) {
        Bean bean_ = _str.getBean();
        ((PresidentBean)bean_).setDataBase(getDataBase());
        bean_.setLanguage(_language);
        return _str;
    }
}
