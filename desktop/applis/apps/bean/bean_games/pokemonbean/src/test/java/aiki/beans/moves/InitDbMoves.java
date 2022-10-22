package aiki.beans.moves;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.CommonBean;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.StringMapObject;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMoves extends InitDbConstr {

    public static String clickLine(Struct _use, int _index) {
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
}
