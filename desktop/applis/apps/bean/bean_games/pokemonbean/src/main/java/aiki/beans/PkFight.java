package aiki.beans;

import aiki.beans.facade.*;
import aiki.beans.facade.fight.AikiBeansFacadeFightStd;
import aiki.beans.fight.AikiBeansFightStd;
import aiki.beans.fight.*;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.formathtml.Configuration;
import code.util.CustList;

public final class PkFight extends PokemonStandards {

    private static final String GET_MOVE = "getMove";
    private static final String IS_ENABLED = "isEnabled";
    private static final String IS_INCREMENT_COUNT = "isIncrementCount";
    private static final String GET_NB_TURN = "getNbTurn";
    private static final String GET_TARGET = "getTarget";
    private static final String GET_POSITION = "getPosition";
    private static final String GET_CURRENT = "getCurrent";
    private static final String GET_MAX = "getMax";
    private static final String GET_PP = "getPp";
    private static final String GET_ACTIVITY = "getActivity";
    private static final String GET_NB_ROUNDS = "getNbRounds";
    private static final String IS_FIRST_STACKED = "isFirstStacked";
    private static final String IS_LAST_STACKED = "isLastStacked";
    private static final String GET_TARGET_POSITION = "getTargetPosition";
    private static final String GET_DAMAGE = "getDamage";
    private static final String IS_INCREMENTING = "isIncrementing";
    @Override
    public void buildAddon() {
        AikiBeansFightStd.build(this);
        AikiBeansFacadeFightStd.build(this);
        buildActivityOfMove(this);
        buildMoveTarget(this);
        buildTargetCoords(this);
        buildUsesOfMove(this);
        buildCopiedMove(this);
        buildMoveTeamPosition(this);
        buildAffectedMove(this);
        buildStacksOfUses(this);
        buildAnticipation(this);

    }
    private static void buildActivityOfMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ACTIVITY_OF_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(IS_ENABLED,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ActivityOfMoveIsEnabled()));
        methods_.add( new SpecNatMethod(IS_INCREMENT_COUNT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ActivityOfMoveIsIncrementCount()));
        methods_.add( new SpecNatMethod(GET_NB_TURN, PRIM_INTEGER, false, MethodModifier.NORMAL,new ActivityOfMoveGetNbTurn()));
        _std.getStds().addEntry(TYPE_ACTIVITY_OF_MOVE, type_);
    }
    private static void buildMoveTarget(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVE_TARGET, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveTargetGetMove()));
        methods_.add( new SpecNatMethod(GET_TARGET,PokemonStandards.TYPE_TARGET_COORDS, false, MethodModifier.NORMAL,new MoveTargetGetTarget()));
        _std.getStds().addEntry(TYPE_MOVE_TARGET, type_);
    }
    private static void buildTargetCoords(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TARGET_COORDS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_POSITION, PRIM_INTEGER, false, MethodModifier.NORMAL,new TargetCoordsGetPosition()));
        _std.getStds().addEntry(TYPE_TARGET_COORDS, type_);
    }
    public static void buildUsesOfMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_USES_OF_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_CURRENT, PRIM_INTEGER, false, MethodModifier.NORMAL,new UsesOfMoveGetCurrent()));
        methods_.add( new SpecNatMethod(GET_MAX, PRIM_INTEGER, false, MethodModifier.NORMAL,new UsesOfMoveGetMax()));
        _std.getStds().addEntry(TYPE_USES_OF_MOVE, type_);
    }
    private static void buildCopiedMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_COPIED_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CopiedMoveGetMove()));
        methods_.add( new SpecNatMethod(GET_PP, PRIM_INTEGER, false, MethodModifier.NORMAL,new CopiedMoveGetPp()));
        _std.getStds().addEntry(TYPE_COPIED_MOVE, type_);
    }
    private static void buildMoveTeamPosition(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MOVE_TEAM_POSITION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MoveTeamPositionGetMove()));
        _std.getStds().addEntry(TYPE_MOVE_TEAM_POSITION, type_);
    }
    private static void buildAffectedMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_AFFECTED_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new AffectedMoveGetMove()));
        methods_.add( new SpecNatMethod(GET_ACTIVITY,PokemonStandards.TYPE_ACTIVITY_OF_MOVE, false, MethodModifier.NORMAL,new AffectedMoveGetActivity()));
        _std.getStds().addEntry(TYPE_AFFECTED_MOVE, type_);
    }
    private static void buildStacksOfUses(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_STACKS_OF_USES, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NB_ROUNDS, PRIM_INTEGER, false, MethodModifier.NORMAL,new StacksOfUsesGetNbRounds()));
        methods_.add( new SpecNatMethod(IS_FIRST_STACKED,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new StacksOfUsesIsFirstStacked()));
        methods_.add( new SpecNatMethod(IS_LAST_STACKED,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new StacksOfUsesIsLastStacked()));
        _std.getStds().addEntry(TYPE_STACKS_OF_USES, type_);
    }
    private static void buildAnticipation(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ANTICIPATION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_TARGET_POSITION,PokemonStandards.TYPE_TARGET_COORDS, false, MethodModifier.NORMAL,new AnticipationGetTargetPosition()));
        methods_.add( new SpecNatMethod(GET_DAMAGE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new AnticipationGetDamage()));
        methods_.add( new SpecNatMethod(IS_INCREMENTING,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new AnticipationIsIncrementing()));
        methods_.add( new SpecNatMethod(GET_NB_ROUNDS, PRIM_INTEGER, false, MethodModifier.NORMAL,new AnticipationGetNbRounds()));
        _std.getStds().addEntry(TYPE_ANTICIPATION, type_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
//        super.initBeans(_conf, _language);
        getBeansStruct().setValue(0,beanFighter(_language));
        getBeansStruct().setValue(1,beanTeam(_language));
        getBeansStruct().setValue(2,beanFight(_language));
        getBeansStruct().setValue(3,beanFightCalculation(_language));
        /*
        NavBuilder.buildBeans(beans_,FIGHTER,AIKI_BEANS_FIGHT_FIGHTER_BEAN);
        NavBuilder.buildBeans(beans_,TEAM,AIKI_BEANS_FIGHT_TEAM_BEAN);
        NavBuilder.buildBeans(beans_,FIGHT,AIKI_BEANS_FIGHT_FIGHT_BEAN);
        NavBuilder.buildBeans(beans_,FIGHT_DETAIL,AIKI_BEANS_FIGHT_FIGHT_CALCULATION_BEAN);
        */
        /*
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHTER_BEAN)) {
            return bean(new FighterBean(), AikiBeansFightStd.TYPE_FIGHTER_BEAN, _lg);

        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_TEAM_BEAN)) {
            return bean(new TeamBean(), AikiBeansFightStd.TYPE_TEAM_BEAN, _lg);

        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHT_BEAN)) {
            return bean(new FightBean(), AikiBeansFightStd.TYPE_FIGHT_BEAN, _lg);

        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHT_CALCULATION_BEAN)) {
            return bean(new FightCalculationBean(), AikiBeansFightStd.TYPE_FIGHT_CALCULATION_BEAN, _lg);

        }*/
    }

    public PokemonBeanStruct beanFighter(String _language) {
        return bean(new FighterBean(), AikiBeansFightStd.TYPE_FIGHTER_BEAN, _language);
    }

    public PokemonBeanStruct beanTeam(String _language) {
        return bean(new TeamBean(), AikiBeansFightStd.TYPE_TEAM_BEAN, _language);
    }

    public PokemonBeanStruct beanFight(String _language) {
        return bean(new FightBean(), AikiBeansFightStd.TYPE_FIGHT_BEAN, _language);
    }

    public PokemonBeanStruct beanFightCalculation(String _language) {
        return bean(new FightCalculationBean(), AikiBeansFightStd.TYPE_FIGHT_CALCULATION_BEAN, _language);
    }
}
