package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;
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

    public static String clickLine(Struct _use, StringMap<Struct> _all, int _index) {
        MoveLineBean bean_ = new MoveLineBean();
        CommonBean c_ = (CommonBean) ((PokemonBeanStruct) _use).getBean();
        bean_.setLanguage(c_.getLanguage());
        bean_.setDataBase(c_.db());
        bean_.setForms(new StringMapObject());
        PokemonBeanStruct moveline_ = new PokemonBeanStruct(bean_);
        fwdLine(moveline_, _use, _index);
        beforeDisplaying(moveline_);
        return navigateData(clickMoveLineBeanMove(),"","",moveline_,_index);
    }
    public static void fwdLine(Struct _update, Struct _use, int _index) {
        callMoveLineBeanIndexSet(_update,_index);
        fwdLine(_update, _use);
    }

    public static void fwdLine(Struct _update, Struct _use) {
        fwdMoveLine(_update, _use);
        fwdMoveSet(_update, _use);
    }

    public static void fwdMoveLine(Struct _update, Struct _use) {
        callMoveLineBeanMoveLineSet(_update,callMoveLineBeanMoveLineGet(_use));
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
    public static StringMap<String> mappingToMoves() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(AikiBeansStd.WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,AikiBeansMovesStd.BEAN_MOVES);
        map_.addEntry(AikiBeansMovesStd.WEB_HTML_MOVES_MOVE_LINE_HTML,AikiBeansMovesStd.BEAN_MOVE_LINE);
        return map_;
    }
}
