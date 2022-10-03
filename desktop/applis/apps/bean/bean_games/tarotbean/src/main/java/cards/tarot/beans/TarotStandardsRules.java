package cards.tarot.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.formathtml.Configuration;
import code.util.CustList;

public final class TarotStandardsRules extends TarotStandards {
    private static final String TYPE_RULES_TAROT_BEAN = "cards.tarot.beans.RulesTarotBean";
    private static final String DISCARD_AFTER_CALL = "discardAfterCall";
    private static final String ALLOW_PLAY_CALLED_SUIT = "allowPlayCalledSuit";
    private static final String POIGNEES_AUTORISEES = "poigneesAutorisees";
    private static final String CONTRATS = "contrats";
    private static final String FIN_PARTIE_TAROT = "finPartieTarot";
    private static final String MODE = "mode";
    private static final String REPARTITION = "repartition";
    private static final String CARTES_BATTUES = "cartesBattues";
    private static final String MISERES = "miseres";

    @Override
    protected void buildAddon() {
        buildRulesTarotBean();
    }

    private void buildRulesTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RULES_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false,new RulesTarotBeanCartesBattues(),null));
        fields_.add( new StandardField(REPARTITION, STRING, false, false,new RulesTarotBeanRepartition(),null));
        fields_.add( new StandardField(MODE, STRING, false, false,new RulesTarotBeanMode(),null));
        fields_.add( new StandardField(FIN_PARTIE_TAROT, STRING, false, false,new RulesTarotBeanFinPartieTarot(),null));
        fields_.add( new StandardField(DISCARD_AFTER_CALL, PRIM_BOOLEAN, false, false,new RulesTarotBeanDiscardAfterCall(),null));
        fields_.add( new StandardField(ALLOW_PLAY_CALLED_SUIT, PRIM_BOOLEAN, false, false,new RulesTarotBeanAllowPlayCalledSuit(),null));
        fields_.add( new StandardField(CONTRATS, TYPE_LIST, false, false,new RulesTarotBeanContrats(),null));
        fields_.add( new StandardField(POIGNEES_AUTORISEES, TYPE_MAP, false, false,new RulesTarotBeanPoigneesAutorisees(),null));
        fields_.add( new StandardField(MISERES, TYPE_LIST, false, false,new RulesTarotBeanMiseres(),null));
        getStds().addEntry(TYPE_RULES_TAROT_BEAN, std_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0, beanRules(_language));
    }

    TarotBeanStruct beanRules(String _language) {
        return bean(new RulesTarotBean(), TYPE_RULES_TAROT_BEAN, _language);
    }
}
