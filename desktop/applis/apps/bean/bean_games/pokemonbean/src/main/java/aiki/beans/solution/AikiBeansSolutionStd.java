package aiki.beans.solution;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansSolutionStd{
    public static final String TYPE_SOLUTION_BEAN = "aiki.beans.solution.SolutionBean";
    private static final String GET_PLACE = "getPlace";
    private static final String STEPS = "steps";
    private AikiBeansSolutionStd(){}
    public static void build(PokemonStandards _std) {
        buildSolutionBean(_std);
    }
    private static void buildSolutionBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(STEPS, BeanNatCommonLgNames.TYPE_LIST, new SolutionBeanStepsGet(),null));
        methods_.add( new SpecNatMethod(GET_PLACE,BeanNatCommonLgNames.STRING, new SolutionBeanGetPlace()));
        _std.getStds().addEntry(TYPE_SOLUTION_BEAN, type_);
    }
}
