package aiki.beans;

import aiki.beans.endround.AikiBeansEndroundStd;
import aiki.beans.map.elements.*;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansStd{
    public static final String WEB_HTML_INDEX_HTML="web/html/index.html";
    public static final String BEAN_WELCOME = "welcome";
    public static final String TYPE_COMMON_BEAN = "aiki.beans.CommonBean";
    public static final String TYPE_WELCOME_BEAN = "aiki.beans.WelcomeBean";
    private static final String CLICK_POKEDEX = "clickPokedex";
    private static final String CLICK_ITEMS = "clickItems";
    private static final String SEE_ALL_MOVES = "seeAllMoves";
    private static final String SEE_LEARNT_MOVES = "seeLearntMoves";
    private static final String SEE_NOT_LEARNT_MOVES = "seeNotLearntMoves";
    private static final String CLICK_ABILITIES = "clickAbilities";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String CLICK_SIMULATION = "clickSimulation";
    private static final String GO_TO_INDEX = "gi";
    private static final String GO_TO_ENDROUND = "ge";
    private static final String GO_TO_GENERAL = "g";
    private static final String GO_TO_HELPROUND = "h";
    private static final String GO_TO_COMBOS = "c";
    private static final String GO_TO_SOLUTION = "s";
    private static final String GO_TO_LANGS = "l";

    private AikiBeansStd(){}
    public static void build(PokemonStandards _std) {
        buildCommonBean(_std);
        buildWelcomeBean(_std);
    }
    private static void buildCommonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_COMMON_BEAN, fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        methods_.add( new SpecNatMethod(GO_TO_INDEX, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(WEB_HTML_INDEX_HTML)));
        methods_.add( new SpecNatMethod(GO_TO_ENDROUND, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(AikiBeansEndroundStd.PAGE_END_ROUND)));
        _std.getStds().addEntry(TYPE_COMMON_BEAN, type_);
    }
    private static void buildWelcomeBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WELCOME_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(CLICK_POKEDEX, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickPokedex()));
        methods_.add( new SpecNatMethod(CLICK_ITEMS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickItems()));
        methods_.add( new SpecNatMethod(SEE_ALL_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeAllMoves()));
        methods_.add( new SpecNatMethod(SEE_LEARNT_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeLearntMoves()));
        methods_.add( new SpecNatMethod(SEE_NOT_LEARNT_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeNotLearntMoves()));
        methods_.add( new SpecNatMethod(CLICK_ABILITIES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickAbilities()));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickStatus()));
        methods_.add( new SpecNatMethod(CLICK_SIMULATION,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickSimulation()));
        methods_.add( new SpecNatMethod(AikiBeansMapElementsStd.GM, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(AikiBeansMapElementsStd.WEB_HTML_MAP_MAP_HTML)));
        methods_.add( new SpecNatMethod(GO_TO_GENERAL, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/general/general.html")));
        methods_.add( new SpecNatMethod(GO_TO_HELPROUND, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/round/helpround.html")));
        methods_.add( new SpecNatMethod(GO_TO_COMBOS, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/combo/combos.html")));
        methods_.add( new SpecNatMethod(GO_TO_SOLUTION, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/solution/solution.html")));
        methods_.add( new SpecNatMethod(GO_TO_LANGS, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/langs/langs.html")));
        _std.getStds().addEntry(TYPE_WELCOME_BEAN, type_);
    }
}
