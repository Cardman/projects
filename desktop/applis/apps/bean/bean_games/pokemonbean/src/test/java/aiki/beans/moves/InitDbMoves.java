package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbMoves extends InitDbConstr {

    public static Struct callMovesBeanCategorySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanCategorySet(),_str,_args);
    }

    public static Struct callMovesBeanMaxAccuracySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMaxAccuracySet(),_str,_args);
    }

    public static Struct callMovesBeanMaxPowerSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMaxPowerSet(),_str,_args);
    }

    public static Struct callMovesBeanMinAccuracySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMinAccuracySet(),_str,_args);
    }

    public static Struct callMovesBeanMinPowerSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMinPowerSet(),_str,_args);
    }

    public static Struct callMovesBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanTypedNameSet(),_str,_args);
    }

    public static Struct callMovesBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callMovesBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new MovesBeanWholeWordSet(),_str,_args);
    }

    public static Struct callMovesBeanCategoriesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanCategoriesGet(),_str,_args);
    }

    public static Struct callMovesBeanCategoryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanCategoryGet(),_str,_args);
    }

    public static Struct callMovesBeanMaxAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMaxAccuracyGet(),_str,_args);
    }

    public static Struct callMovesBeanMaxPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMaxPowerGet(),_str,_args);
    }

    public static Struct callMovesBeanMinAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMinAccuracyGet(),_str,_args);
    }

    public static Struct callMovesBeanMinPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMinPowerGet(),_str,_args);
    }

    public static Struct callMovesBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMovesGet(),_str,_args);
    }

    public static Struct callMovesBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanSearch(),_str,_args);
    }

    public static Struct callMovesBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanTypedNameGet(),_str,_args);
    }

    public static Struct callMovesBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callMovesBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanWholeWordGet(),_str,_args);
    }

    public static Struct callMoveLineBeanAccuracyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanAccuracyGet(),_str,_args);
    }

    public static Struct callMoveLineBeanCategoryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanCategoryGet(),_str,_args);
    }

    public static Struct callMoveLineBeanDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanDisplayNameGet(),_str,_args);
    }

    public static Struct callMoveLineBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanIndexGet(),_str,_args);
    }

    public static Struct callMoveLineBeanPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanPowerGet(),_str,_args);
    }

    public static Struct callMoveLineBeanPpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanPpGet(),_str,_args);
    }

    public static Struct callMoveLineBeanPriorityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanPriorityGet(),_str,_args);
    }

    public static Struct callMoveLineBeanTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanTypesGet(),_str,_args);
    }

    public static String goToLine(FacadeGame _fac, int _index) {
        Struct moveline_ = disp(_fac, _index);
        return navigateData(clickMoveLineBeanMove(),"","",moveline_, toInt(callMoveLineBeanIndexGet(moveline_)));
    }

    private static Struct disp(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToMoves(pk_);
        Struct welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct moves_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVES);
        transit(pk_,new WelcomeBeanSeeAllMoves(),welcome_,moves_);
        transit(pk_,new MovesBeanSearch(),moves_,moves_);
        return displayMoveLine(all_, _index);
    }

    private static Struct displayMoveLine(StringMap<Struct> _all, int _index) {
        Struct moves_ = _all.getVal(AikiBeansMovesStd.BEAN_MOVES);
        Struct moveline_ = byString(_all, callMovesBeanMovesBeanGet(moves_));
        fwdLineFull(moveline_, moves_, _index);
        beforeDisplaying(moveline_);
        return moveline_;
    }

    public static Struct byString(StringMap<Struct> _all, Struct _resultAsString) {
        return byStr(_all, mappingToMoves(), _resultAsString);
    }

    public static void fwdLineFull(Struct _update, Struct _use, int _index) {
        PokemonStandards.fwd((PokemonBeanStruct) _use, (PokemonBeanStruct) _update);
        callMoveLineBeanIndexSet(_update,_index);
        fwdLine(_update, _use,_index);
    }

    public static void fwdLine(Struct _update, Struct _use, int _index) {
        fwdMoveLine(_update, _use,_index);
        fwdMoveSet(_update, _use);
    }

    public static void fwdMoveLine(Struct _update, Struct _use, int _index) {
        callMoveLineBeanMoveLineSet(_update,elt(callMovesBeanMovesGet(_use),_index));
    }
    public static void fwdMoveSet(Struct _update, Struct _use) {
        callMoveLineBeanSortedMovesSet(_update,callMovesBeanSortedMovesGet(_use));
    }
    public static Struct callMovesBeanMovesBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMovesBeanGet(),_str,_args);
    }

    public static Struct callMoveLineBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(clickMoveLineBeanMove(),_str,_args);
    }

    private static MoveLineBeanClickMove clickMoveLineBeanMove() {
        return new MoveLineBeanClickMove();
    }

    public static Struct callMoveLineBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new MoveLineBeanIndexSet(),_str,_args);
    }
    public static Struct callMoveLineBeanMoveLineGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineBeanMoveLineGet(),_str,_args);
    }
    public static Struct callMoveLineBeanMoveLineSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new MoveLineBeanMoveLineSet(),_str,_args);
    }
    public static Struct callMovesBeanSortedMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanSortedMovesGet(),_str,_args);
    }
    public static Struct callMoveLineBeanSortedMovesSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new MoveLineBeanSortedMovesSet(),_str,_args);
    }
    public static StringMap<Struct> beanToMoves(PkData _pk) {
        StringMap<Struct> map_ = new StringMap<Struct>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansMovesStd.BEAN_MOVES,_pk.beanMovesBean(EN));
        map_.addEntry(AikiBeansMovesStd.BEAN_MOVE_LINE,_pk.beanMoveLineBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToMoves() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(AikiBeansStd.WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,AikiBeansMovesStd.BEAN_MOVES);
        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVE_LINE_HTML,AikiBeansMovesStd.BEAN_MOVE_LINE);
        return map_;
    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        MoveData dam_ = moveDam(TargetChoice.ANY_FOE);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower("1");
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        MoveData dam2_ = moveDam(TargetChoice.ANY_FOE);
        dam2_.setAccuracy(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        EffectDamage ef2_ = Instances.newEffectDamage();
        ef2_.setPower(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        target(dam2_, ef2_);
        facade_.getData().completeMembers(M_DAM_VAR, dam2_);
        MoveData damBad_ = moveDam(TargetChoice.ADJ_ADV);
        damBad_.getEffects().add(Instances.newEffectDamage());
        facade_.getData().completeMembers(M_DAM_BAD, damBad_);
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveDam(TargetChoice.ADJ_UNIQ));
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE,T_TYPE_TR);
        facade_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT,C_CAT_TR);
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VERY_BAD,M_DAM_VERY_BAD_TR);
        facade_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON,P_POKEMON_TR);
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
        facade_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }
}
