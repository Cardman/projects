package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbMoves extends InitDbConstr {

    public static NaSt callMovesBeanCategorySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanCategorySet(),_str,_args);
    }

    public static NaSt callMovesBeanMaxAccuracySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMaxAccuracySet(),_str,_args);
    }

    public static NaSt callMovesBeanMaxPowerSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMaxPowerSet(),_str,_args);
    }

    public static NaSt callMovesBeanMinAccuracySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMinAccuracySet(),_str,_args);
    }

    public static NaSt callMovesBeanMinPowerSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanMinPowerSet(),_str,_args);
    }

    public static NaSt callMovesBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callMovesBeanTypedTypeSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanTypedTypeSet(),_str,_args);
    }

    public static NaSt callMovesBeanWholeWordSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new MovesBeanWholeWordSet(),_str,_args);
    }

    public static NaSt callMovesBeanLearntSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new MovesBeanLearntSet(),_str,_args);
    }

    public static NaSt callMovesBeanCategoriesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanCategoriesGet(),_str,_args);
    }

    public static NaSt callMovesBeanCategoryGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanCategoryGet(),_str,_args);
    }

    public static NaSt callMovesBeanMaxAccuracyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMaxAccuracyGet(),_str,_args);
    }

    public static NaSt callMovesBeanMaxPowerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMaxPowerGet(),_str,_args);
    }

    public static NaSt callMovesBeanMinAccuracyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMinAccuracyGet(),_str,_args);
    }

    public static NaSt callMovesBeanMinPowerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMinPowerGet(),_str,_args);
    }

    public static NaSt callMovesBeanBooleansGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanBooleansGet(),_str,_args);
    }

    public static NaSt callMovesBeanMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanMovesGet(),_str,_args);
    }

    public static NaSt callMovesBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanSearch(),_str,_args);
    }

    public static NaSt callMovesBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callMovesBeanTypedTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanTypedTypeGet(),_str,_args);
    }

    public static NaSt callMovesBeanWholeWordGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanWholeWordGet(),_str,_args);
    }

    public static NaSt callMoveLineBeanAccuracyGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineAccuracyGet(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMoveLineBeanCategoryGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineCategoryGet(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMoveLineBeanDisplayNameGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineDisplayNameGet(),dispLine(feedDb(), _index),_args);
    }

//    private static int callMoveLineBeanIndexGet(Struct _str, long... _args) {
//        return toInt(BeanPokemonCommonTs.callLongs(new MoveLineBeanIndexGet(),_str,_args));
//    }

    public static NaSt callMoveLineBeanPowerGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLinePowerGet(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMoveLineBeanPpGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLinePpGet(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMoveLineBeanPriorityGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLinePriorityGet(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMoveLineBeanTypesGet(int _index, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineGetTypes(),dispLine(feedDb(), _index),_args);
    }

    public static NaSt callMovesBeanLearntGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MovesBeanLearntGet(),_str,_args);
    }

    public static String goToLine(int _index) {
        NaSt bean_ = moveLine(_index);
        return navigateData(clickMovesBeanMove(), bean_, _index);
    }

    public static String goToLineId(int _index) {
        NaSt moveline_ = moveLine(_index);
        navigateData(clickMovesBeanMove(), moveline_, _index);
        return getValMoveId(moveline_);
    }

    private static NaSt moveLine(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToMoves(pk_);
        return transitToAllMoves(pk_, all_, _index, mappingToMoves());
    }

    protected static NaSt dispLine(FacadeGame _fac, int _index) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMoves(pk_);
        NaSt moves_ = transitToAllMoves(pk_, all_, _index, mappingToMoves());
        return elt(callMovesBeanMovesGet(moves_),_index);
    }

    protected static NaSt transitToAllMoves(PkData _pk, StringMap<NaSt> _all,int _index, StringMap<String> _mapping) {
        NaSt welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = _all.getVal(AikiBeansMovesStd.BEAN_MOVES);
        transit(_pk,new WelcomeBeanSeeAllMoves(),welcome_,moves_);
        transit(_pk,new MovesBeanSearch(),moves_,moves_);
        return displayMoveLine(_all, _index,_mapping);
    }

    protected static NaSt dispAllMoves(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllMoves(pk_);
    }

    private static NaSt dispAllMoves(PkData _pk) {
        StringMap<NaSt> all_ = beanToMoves(_pk);
        NaSt welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVES);
        transit(_pk,new WelcomeBeanSeeAllMoves(),welcome_,moves_);
        return moves_;
    }

    protected static NaSt dispAllMovesSearch(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        NaSt moves_ = dispAllMoves(pk_);
        transit(pk_,new MovesBeanSearch(),moves_,moves_);
        return moves_;
    }

    protected static String navigateMovesSearch(NaSt _moves) {
        return navigateData(new MovesBeanSearch(), _moves);
    }

    private static NaSt displayMoveLine(StringMap<NaSt> _all, int _index, StringMap<String> _mapping) {
        NaSt moves_ = _all.getVal(AikiBeansMovesStd.BEAN_MOVES);
//        Struct moveline_ = byStr(_all, _mapping, callMovesBeanMovesBeanGet(moves_));
//        fwdLineFull(moveline_, moves_, _index);
//        beforeDisplaying(moveline_);
        return moves_;
    }

//    private static void fwdLineFull(Struct _update, Struct _use, int _index) {
//        PokemonStandards.fwd((PokemonBeanStruct) _use, (PokemonBeanStruct) _update);
//        callMoveLineBeanIndexSet(_update,_index);
//        fwdLine(_update, _use,_index);
//    }

//    private static void fwdLine(Struct _update, Struct _use, int _index) {
//        fwdMoveLine(_update, _use,_index);
//        fwdMoveSet(_update, _use);
//    }

//    private static void fwdMoveLine(Struct _update, Struct _use, int _index) {
//        callMoveLineBeanMoveLineSet(_update,elt(callMovesBeanMovesGet(_use),_index));
//    }
//    private static void fwdMoveSet(Struct _update, Struct _use) {
//        callMoveLineBeanSortedMovesSet(_update,callMovesBeanSortedMovesGet(_use));
//    }
//    private static Struct callMovesBeanMovesBeanGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MovesBeanMovesBeanGet(),_str,_args);
//    }

//    private static MoveLineBeanClickMove clickMoveLineBeanMove() {
//        return new MoveLineBeanClickMove();
//    }

    private static MovesBeanClickLink clickMovesBeanMove() {
        return new MovesBeanClickLink();
    }

//    private static Struct callMoveLineBeanIndexSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new MoveLineBeanIndexSet(),_str,_args);
//    }
    public static NaSt callMoveLineBeanMoveLineGet(int _index, long... _args) {
        return dispLine(feedDb(), _index);
//        return BeanPokemonCommonTs.callLongs(new MoveLineBeanMoveLineGet(),dispLine(feedDb(), _index),_args);
    }
    public static NaSt callMoveLineIsDamageMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineIsDamageMove(),_str,_args);
    }

    public static NaSt callMoveLineCategoryGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineCategoryGet(),_str,_args);
    }

    public static NaSt callMoveLineDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineDisplayNameGet(),_str,_args);
    }

    public static NaSt callMoveLineGetTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineGetTypes(),_str,_args);
    }

    public static NaSt callMoveLinePpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLinePpGet(),_str,_args);
    }

    public static NaSt callMoveLinePriorityGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLinePriorityGet(),_str,_args);
    }

    public static NaSt callMoveLineIsDirect(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MoveLineIsDirect(),_str,_args);
    }
//    private static Struct callMoveLineBeanMoveLineSet(Struct _str, Struct _args) {
//        return BeanPokemonCommonTs.callStruct(new MoveLineBeanMoveLineSet(),_str,_args);
//    }
//    private static Struct callMovesBeanSortedMovesGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MovesBeanSortedMovesGet(),_str,_args);
//    }
//    private static Struct callMoveLineBeanSortedMovesSet(Struct _str, Struct _args) {
//        return BeanPokemonCommonTs.callStruct(new MoveLineBeanSortedMovesSet(),_str,_args);
//    }
    public static StringMap<NaSt> beanToMoves(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansMovesStd.BEAN_MOVES,_pk.beanMovesBean(EN));
//        map_.addEntry(AikiBeansMovesStd.BEAN_MOVE_LINE,_pk.beanMoveLineBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToMoves() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,AikiBeansMovesStd.BEAN_MOVES);
//        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVE_LINE_HTML,AikiBeansMovesStd.BEAN_MOVE_LINE);
        return map_;
    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = moveDam(TargetChoice.ANY_FOE);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower("1");
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        DamagingMoveData dam2_ = moveDam(TargetChoice.ANY_FOE);
        dam2_.setDirect(false);
        dam2_.setAccuracy(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        EffectDamage ef2_ = Instances.newEffectDamage();
        ef2_.setPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        target(dam2_, ef2_);
        facade_.getData().completeMembers(M_DAM_VAR, dam2_);
        MoveData damBad_ = moveDam(TargetChoice.ADJ_ADV);
        EffectStatistic st_ = Instances.newEffectStatistic();
        target(damBad_,st_);
        facade_.getData().completeMembers(M_DAM_BAD, damBad_);
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveDam(TargetChoice.ADJ_UNIQ));
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        DamagingMoveData dam3_ = moveDam(TargetChoice.ANY_FOE);
        dam3_.setAccuracy("7/10");
        EffectDamage ef3_ = Instances.newEffectDamage();
        ef3_.setPower("10");
        target(dam3_, ef3_);
        facade_.getData().completeMembers(M_DAM_POW, dam3_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        facade_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VERY_BAD,M_DAM_VERY_BAD_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_POW,M_DAM_POW_TR);
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
        facade_.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }

    protected static NaSt transitMove(int _index, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping) {
        NaSt moveline_ = transitToAllMoves(_pk, _all, _index, _mapping);
        NaSt mbean_ = _all.getVal(AikiBeansMovesStd.BEAN_MOVE);
        transit(_pk,new MovesBeanClickLink(), moveline_, mbean_,_index);
        return mbean_;
    }
}
