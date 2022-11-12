package aiki.beans.endround;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.Combos;
import aiki.fight.abilities.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.util.ListEffectCombo;
import aiki.game.fight.Fight;
import aiki.instances.*;
import code.expressionlanguage.structs.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbEndRound extends InitDbConstr {


    public static final String T_TYPE_ROUND_1 = "T_TYPE1";
    public static final String S_STA_END_ROUND = "S_STA_END_ROUND";
    public static final String T_TYPE_HEAL = "T_TYPE_HEAL";
    public static final String S_STA_DAM = "STA_DAM";
    public static final String S_STA_RELATION = "S_STA_RELATION";

    public static Struct callEffectEndRoundBeanAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanAbilityGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanClickAbility(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanClickItem(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanClickMove(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanClickMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanClickMoves(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanClickStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanClickStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanEndRoundHtmlGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanEndRoundHtmlGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanEndRoundRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanEndRoundRankGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanGetTrMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanGetTrMoves(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanIndexGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanItemGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanMapVarsFailEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanMapVarsFailEndRoundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanMoveGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanMovesGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanReasonsEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanReasonsEndRoundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundBeanStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundBeanStatusGet(),_str,_args);
    }

    public static Struct callEndRoundBeanGetEvts(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EndRoundBeanGetEvts(),_str,_args);
    }

    public static Struct callEndRoundBeanGetPage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EndRoundBeanGetPage(),_str,_args);
    }
    public static Struct callEffectEndRoundBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EffectEndRoundBeanIndexSet(),_str,_args);
    }

    public static Struct callEffectEndRoundFoeBeanInflictedRateHpTargetGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundFoeBeanInflictedRateHpTargetGet(),_str,_args);
    }

    public static Struct callEffectEndRoundGlobalBeanDamageEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundGlobalBeanDamageEndRoundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundGlobalBeanHealingEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundGlobalBeanHealingEndRoundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundGlobalBeanHealingEndRoundGroundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundGlobalBeanHealingEndRoundGroundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundGlobalBeanImmuneTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundGlobalBeanImmuneTypesGet(),_str,_args);
    }

    public static Struct callEffectEndRoundGlobalBeanPuttingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundGlobalBeanPuttingKoGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanClickDamageStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanClickDamageStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanClickUserStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanClickUserStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanDeleteAllStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanDeleteAllStatusGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanGetTrDamageStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanGetTrDamageStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanGetTrType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanGetTrType(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanGetTrUserStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanGetTrUserStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanHealHpByOwnerTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanHealHpByOwnerTypesGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanHealHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanHealHpGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanIsType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanIsType(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanMultDamageStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanMultDamageStatusGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanRecoilDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanRecoilDamageGet(),_str,_args);
    }

    public static Struct callEffectEndRoundIndividualBeanUserStatusEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundIndividualBeanUserStatusEndRoundGet(),_str,_args);
    }

    public static Struct callEffectEndRoundMultiRelationBeanClickDamageStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMultiRelationBeanClickDamageStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundMultiRelationBeanDamageByStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMultiRelationBeanDamageByStatusGet(),_str,_args);
    }

    public static Struct callEffectEndRoundMultiRelationBeanGetTrDamageStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundMultiRelationBeanGetTrDamageStatus(),_str,_args);
    }

    public static Struct callEffectEndRoundPositionRelationBeanHealHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundPositionRelationBeanHealHpGet(),_str,_args);
    }

    public static Struct callEffectEndRoundPositionTargetBeanClickTargetRelationMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundPositionTargetBeanClickTargetRelationMove(),_str,_args);
    }

    public static Struct callEffectEndRoundPositionTargetBeanGetMovesSameCategory(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundPositionTargetBeanGetMovesSameCategory(),_str,_args);
    }

    public static Struct callEffectEndRoundPositionTargetBeanGetTrTargetRelationMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundPositionTargetBeanGetTrTargetRelationMove(),_str,_args);
    }

    public static Struct callEffectEndRoundSingleRelationBeanLawForEnablingEffectGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundSingleRelationBeanLawForEnablingEffectGet(),_str,_args);
    }

    public static Struct callEffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(),_str,_args);
    }

    public static Struct callEffectEndRoundStatusBeanEndRoundStatusHtmlGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundStatusBeanEndRoundStatusHtmlGet(),_str,_args);
    }

    public static Struct callEffectEndRoundStatusBeanInflictedRateHpTargetGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundStatusBeanInflictedRateHpTargetGet(),_str,_args);
    }

    public static Struct callEffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet(),_str,_args);
    }

    public static Struct callEffectEndRoundTeamBeanDeleteAllStatusAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundTeamBeanDeleteAllStatusAllyGet(),_str,_args);
    }

    public static Struct callEffectEndRoundTeamBeanDeleteAllStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectEndRoundTeamBeanDeleteAllStatusGet(),_str,_args);
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers("M_END_1",incr(1));
        facade_.getData().completeMembers("M_END_2",incr(2));
        facade_.getData().completeMembers("M_END_3",endRoundGlobal(true, 3));
        facade_.getData().completeMembers("M_END_4",endRoundGlobal(false, 4));
        facade_.getData().completeMembers("M_END_5",moveEndPositionTargetRelation(5));
        facade_.getData().completeMembers("M_END_6",moveEndPositionTargetRelation(6));
        facade_.getData().completeMembers("M_END_7",endRound(individual(7)));
        facade_.getData().completeMembers("M_END_8",endRound(individual(8)));
        facade_.getData().completeMembers("I_END_9",item(multRelation(9)));
        facade_.getData().completeMembers("I_END_10",item(multRelation(10)));
        facade_.getData().completeMembers("I_END_11",item(multPosRelation(11)));
        facade_.getData().completeMembers("I_END_12",item(multPosRelation(12)));
        facade_.getData().completeMembers("A_END_13",ability(singlePosRelation(13)));
        facade_.getData().completeMembers("A_END_14",ability(singlePosRelation(14)));
        facade_.getData().completeMembers("A_END_15",ability(team(15)));
        facade_.getData().completeMembers("A_END_16",ability(team(16)));
        facade_.getData().completeMembers("S_END_17",simpleStatus(true,17,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers("S_END_18",simpleStatus(false,18,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers("S_END_19",simpleStatus(statRel(20),true,19,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers("S_END_20",simpleStatus(statRel(22),false,21,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers("S_END_21",simpleStatus(statSimple(true,24),true,23,StatusType.INDIVIDUEL));
        facade_.getData().completeMembers("S_END_22",simpleStatus(statSimple(false,26),false,25,StatusType.RELATION_UNIQUE));
        facade_.getData().completeMembers("M_END_23",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_24",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_25",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_26",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_27",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_28",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_29",Instances.newDamagingMoveData());
        facade_.getData().completeMembers("M_END_30",Instances.newDamagingMoveData());
        Combos co_ = Instances.newCombos();
        co_.getEffects().add(new ListEffectCombo(new StringList("M_END_23","M_END_24"), combo(27)));
        co_.getEffects().add(new ListEffectCombo(new StringList("M_END_25","M_END_26"), combo(28)));
        co_.getEffects().add(new ListEffectCombo(new StringList("M_END_27","M_END_28"), comboFoe(29,30)));
        co_.getEffects().add(new ListEffectCombo(new StringList("M_END_29","M_END_30"), comboFoe(31,32)));
        facade_.getData().setCombos(co_);
        facade_.getData().completeMembersCombos();
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        facade_.getData().completeVariables();
        facade_.getData().sortEndRound();
        return facade_;
    }
    private static MoveData incr(int _rank) {
        MoveData m_ = Instances.newDamagingMoveData();
        m_.setRankIncrementNbRound((short) _rank);
        return m_;
    }

    private static MoveData moveEndPositionTargetRelation(int _rk) {
        MoveData m_ = Instances.newDamagingMoveData();
        EffectEndRoundPositionTargetRelation end_ = Instances.newEffectEndRoundPositionTargetRelation();
        end_.setEndRoundRank(_rk);
        m_.getEffects().add(end_);
        return m_;
    }

    private static MoveData endRound(EffectEndRound _endRound) {
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getEffects().add(_endRound);
        return m_;
    }

    private static MoveData endRoundGlobal(boolean _puttingKo, int _rk) {
        MoveData m_ = Instances.newDamagingMoveData();
        EffectEndRoundGlobal c_ = Instances.newEffectEndRoundGlobal();
        c_.setEndRoundRank(_rk);
        m_.getEffects().add(c_);
        EffectGlobal gl_ = Instances.newEffectGlobal();
        gl_.setDamageEndRound(Rate.one());
        gl_.setHealingEndRoundGround(Rate.one());
        gl_.setHealingEndRound(Rate.one());
        gl_.setPuttingKo(_puttingKo);
        gl_.getImmuneTypes().add(T_TYPE_ROUND_1);
        m_.getEffects().add(gl_);
        return m_;
    }
    private static ItemForBattle item(EffectEndRound _endRound) {
        ItemForBattle it_ = Instances.newItemForBattle();
        it_.getEffectEndRound().add(_endRound);
        return it_;
    }
    private static AbilityData ability(EffectEndRound _endRound) {
        AbilityData it_ = Instances.newAbilityData();
        it_.getEffectEndRound().add(_endRound);
        return it_;
    }
    private static StatusSimple simpleStatus(boolean _incrementingEndRound, int _incrementEndRound, StatusType _relation) {
        StatusSimple st_ = Instances.newStatusSimple();
        st_.setIncrementingEndRound(_incrementingEndRound);
        st_.setIncrementEndRound(_incrementEndRound);
        st_.setStatusType(_relation);
        return st_;
    }
    private static StatusSimple simpleStatus(EffectEndRoundStatus _endRound, boolean _incrementingEndRound, int _incrementEndRound, StatusType _relation) {
        StatusSimple st_ = Instances.newStatusSimple();
        st_.getEffectEndRound().add(_endRound);
        st_.setIncrementingEndRound(_incrementingEndRound);
        st_.setIncrementEndRound(_incrementEndRound);
        st_.setStatusType(_relation);
        return st_;
    }
    private static EffectCombo combo(int _rank) {
        EffectCombo ec_ = Instances.newEffectCombo();
        ec_.setRankIncrementNbRound((short)_rank);
        return ec_;
    }

    private static EffectCombo comboFoe(int _incr, int _act) {
        EffectCombo ec_ = Instances.newEffectCombo();
        ec_.setRankIncrementNbRound((short)_incr);
        ec_.getEffectEndRound().add(foe(_act));
        return ec_;
    }
    private static EffectEndRoundFoe foe(int _rk) {
        EffectEndRoundFoe e_ = Instances.newEffectEndRoundFoe();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        return e_;
    }
    private static EffectEndRoundIndividual individual(int _rk) {
        EffectEndRoundIndividual e_ = Instances.newEffectEndRoundIndividual();
        e_.setFailEndRound(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        e_.setEndRoundRank(_rk);
        e_.setDeleteAllStatus(Rate.one());
        e_.setRecoilDamage(Rate.one());
        e_.setHealHp(Rate.one());
        e_.setUserStatusEndRound(S_STA_END_ROUND);
        e_.getHealHpByOwnerTypes().addEntry(T_TYPE_HEAL,Rate.one());
        e_.getMultDamageStatus().addEntry(S_STA_DAM,Rate.one());
        return e_;
    }
    private static EffectEndRoundMultiRelation multRelation(int _rk) {
        EffectEndRoundMultiRelation e_ = Instances.newEffectEndRoundMultiRelation();
        e_.setEndRoundRank(_rk);
        e_.getDamageByStatus().addEntry(S_STA_RELATION,Rate.one());
        return e_;
    }
    private static EffectEndRoundPositionRelation multPosRelation(int _rk) {
        EffectEndRoundPositionRelation e_ = Instances.newEffectEndRoundPositionRelation();
        e_.setEndRoundRank(_rk);
        e_.setHealHp(Rate.one());
        return e_;
    }
    private static EffectEndRoundSingleRelation singlePosRelation(int _rk) {
        EffectEndRoundSingleRelation e_ = Instances.newEffectEndRoundSingleRelation();
        e_.setEndRoundRank(_rk);
        e_.getRateDamageFunctionOfNbRounds().addEntry(1L,Rate.one());
        e_.getRateDamageFunctionOfNbRounds().addEntry(3L,Rate.newRate("5"));
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("5"), LgInt.newLgInt("15"));
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("7"), LgInt.newLgInt("12"));
        return e_;
    }
    private static EffectEndRoundTeam team(int _rk) {
        EffectEndRoundTeam e_ = Instances.newEffectEndRoundTeam();
        e_.setEndRoundRank(_rk);
        e_.setDeleteAllStatus(Rate.one());
        e_.setDeleteAllStatusAlly(Rate.one());
        return e_;
    }
    private static EffectEndRoundStatusRelation statRel(int _rk) {
        EffectEndRoundStatusRelation e_ = Instances.newEffectEndRoundStatusRelation();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        e_.setThievedHpRateTargetToUser(Rate.one());
        return e_;
    }
    private static EffectEndRoundSingleStatus statSimple(boolean _incr, int _rk) {
        EffectEndRoundSingleStatus e_ = Instances.newEffectEndRoundSingleStatus();
        e_.setEndRoundRank(_rk);
        e_.setInflictedRateHpTarget(Rate.one());
        e_.setIncrementingDamageByRounds(_incr);
        return e_;
    }
}
