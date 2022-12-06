package aiki.beans.map.pokemon;

import aiki.beans.AikiBeansStd;
import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansMapPokemonStd{
    public static final String TYPE_POKEMON_TEAM_BEAN = "aiki.beans.map.pokemon.PokemonTeamBean";
    private static final String TRAINER = "trainer";
    private static final String REWARD = "reward";
    private static final String MULTIPLICITY = "multiplicity";
    private static final String TEAM = "team";
    private static final String NO_FIGHT = "noFight";
    private AikiBeansMapPokemonStd(){}
    public static void build(PokemonStandards _std) {
        buildPokemonTeamBean(_std);
    }
    private static void buildPokemonTeamBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(REWARD, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonTeamBeanRewardGet(),null));
        fields_.add(new StandardField(MULTIPLICITY, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonTeamBeanMultiplicityGet(),null));
        fields_.add(new StandardField(TEAM, BeanNatCommonLgNames.TYPE_LIST, new PokemonTeamBeanTeamGet(),null));
        fields_.add(new StandardField(NO_FIGHT, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonTeamBeanNoFightGet(),new PokemonTeamBeanNoFightSet()));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER, null,new PokemonTeamBeanTrainerSet()));
        methods_.add( new SpecNatMethod(CommonBean.GET_IMAGE,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetImage()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_NAME,BeanNatCommonLgNames.STRING, new PokemonTeamBeanClickName()));
        methods_.add( new SpecNatMethod(CommonBean.GET_NAME,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetName()));
        methods_.add( new SpecNatMethod(CommonBean.GET_GENDER,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetGender()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_ABILITY,BeanNatCommonLgNames.STRING, new PokemonTeamBeanClickAbility()));
        methods_.add( new SpecNatMethod(CommonBean.GET_ABILITY,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetAbility()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_ITEM,BeanNatCommonLgNames.STRING, new PokemonTeamBeanClickItem()));
        methods_.add( new SpecNatMethod(CommonBean.GET_ITEM,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetItem()));
        methods_.add( new SpecNatMethod(CommonBean.CLICK_MOVE,BeanNatCommonLgNames.STRING, new PokemonTeamBeanClickMove()));
        methods_.add( new SpecNatMethod(CommonBean.GET_MOVE,BeanNatCommonLgNames.STRING, new PokemonTeamBeanGetMove()));
        _std.getStds().addEntry(TYPE_POKEMON_TEAM_BEAN, type_);
    }
}
