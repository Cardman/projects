package cards.belote.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.formathtml.Configuration;
import code.util.CustList;

public final class BeloteStandardsRules extends BeloteStandards {
    private static final String TYPE_RULES_BELOTE_BEAN = "cards.belote.beans.RulesBeloteBean";
    private static final String COMPTE_POINTS_CLASSIQUE = "comptePointsClassique";
    private static final String REPARTITION = "repartition";
    private static final String GESTION_COUPE_PARTENAIRE = "gestionCoupePartenaire";
    private static final String SOUS_COUPE_ADV = "sousCoupeAdv";
    private static final String ENCHERES_AUTORISEES = "encheresAutorisees";
    private static final String ANNONCES_AUTORISEES = "annoncesAutorisees";
    private static final String DEAL_ALL = "dealAll";
    private static final String CARTES_BATTUES = "cartesBattues";

    @Override
    protected void buildAddon() {
        buildRulesBeloteBean();
        def();
    }

    private void buildRulesBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RULES_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false,new RulesBeloteBeanCartesBattues(),null));
        fields_.add( new StandardField(DEAL_ALL, PRIM_BOOLEAN, false, false,new RulesBeloteBeanDealAll(),null));
        fields_.add( new StandardField(ENCHERES_AUTORISEES, TYPE_LIST, false, false,new RulesBeloteBeanEncheresAutorisees(),null));
        fields_.add( new StandardField(SOUS_COUPE_ADV, PRIM_BOOLEAN, false, false,new RulesBeloteBeanSousCoupeAdv(),null));
        fields_.add( new StandardField(ANNONCES_AUTORISEES, TYPE_LIST, false, false,new RulesBeloteBeanAnnoncesAutorisees(),null));
        fields_.add( new StandardField(GESTION_COUPE_PARTENAIRE, STRING, false, false,new RulesBeloteBeanGestionCoupePartenaire(),null));
        fields_.add( new StandardField(REPARTITION, STRING, false, false,new RulesBeloteBeanRepartition(),null));
        fields_.add( new StandardField(COMPTE_POINTS_CLASSIQUE, PRIM_BOOLEAN, false, false,new RulesBeloteBeanComptePointsClassique(),null));
        getStds().addEntry(TYPE_RULES_BELOTE_BEAN, std_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0, beanRules(_language));
    }

    BeloteBeanStruct beanRules(String _language) {
        return bean(new RulesBeloteBean(), _language);
    }

}
