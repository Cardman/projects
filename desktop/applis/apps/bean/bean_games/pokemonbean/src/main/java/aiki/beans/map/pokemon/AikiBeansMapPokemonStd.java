package aiki.beans.map.pokemon;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMapPokemonStd{
    public static final String TYPE_POKEMON_TEAM_BEAN = "aiki.beans.map.pokemon.PokemonTeamBean";
    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
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
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POKEMON_TEAM_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(REWARD, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonTeamBeanRewardGet(),null));
        fields_.add(new StandardField(MULTIPLICITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonTeamBeanMultiplicityGet(),null));
        fields_.add(new StandardField(TEAM, BeanNatLgNames.TYPE_LIST,false,false,new PokemonTeamBeanTeamGet(),null));
        fields_.add(new StandardField(NO_FIGHT, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonTeamBeanNoFightGet(),new PokemonTeamBeanNoFightSet()));
        fields_.add(new StandardField(TRAINER,PokemonStandards.TYPE_TRAINER,false,false,null,new PokemonTeamBeanTrainerSet()));
        methods_.add( new SpecNatMethod(GET_IMAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanGetImage()));
        methods_.add( new SpecNatMethod(CLICK_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanClickName()));
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanGetName()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanClickAbility()));
        methods_.add( new SpecNatMethod(GET_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanGetAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanClickItem()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanGetItem()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonTeamBeanGetMove()));
        _std.getStds().addEntry(TYPE_POKEMON_TEAM_BEAN, type_);
    }
}
