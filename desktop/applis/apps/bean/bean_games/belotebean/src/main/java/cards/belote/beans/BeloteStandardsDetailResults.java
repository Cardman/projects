package cards.belote.beans;

import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.formathtml.Configuration;
import code.util.CustList;

public final class BeloteStandardsDetailResults extends BeloteStandards {
    private static final String TYPE_DETAILS_RESULTS_BELOTE_BEAN = "cards.belote.beans.DetailsResultsBeloteBean";

    private static final String DECLARING = "declaring";
    @Override
    protected void buildAddon() {
        buildDetailsResultsBeloteBean();
        def();
    }

    private void buildDetailsResultsBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_DETAILS_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false,new DetailsResultsBeloteBeanDeclaring(),null));
        getStds().addEntry(TYPE_DETAILS_RESULTS_BELOTE_BEAN, std_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0,bean(new DetailsResultsBeloteBean(), TYPE_DETAILS_RESULTS_BELOTE_BEAN,_language));
    }

}