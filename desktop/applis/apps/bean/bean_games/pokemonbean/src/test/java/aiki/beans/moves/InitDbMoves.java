package aiki.beans.moves;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbMoves extends InitDbConstr {
    public static void fwdMoveLine(Struct _update, Struct _use) {
        callMoveLineBeanMoveLineSet(_update,callMoveLineBeanMoveLineGet(_use));
    }
    public static void fwdMoveSet(Struct _update, Struct _use) {
        callMoveLineBeanSortedMovesSet(_update,callMovesBeanSortedMovesGet(_use));
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
