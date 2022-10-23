package aiki.beans;

import aiki.beans.db.InitDbConstr;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbWelcome extends InitDbConstr {

    public static Struct callWelcomeBeanClickAbilities(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickAbilities(),_str,_args);
    }

    public static Struct callWelcomeBeanClickItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickItems(),_str,_args);
    }

    public static Struct callWelcomeBeanClickPokedex(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickPokedex(),_str,_args);
    }

    public static Struct callWelcomeBeanClickSimulation(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickSimulation(),_str,_args);
    }

    public static Struct callWelcomeBeanClickStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickStatus(),_str,_args);
    }

    public static Struct callWelcomeBeanSeeAllMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeAllMoves(),_str,_args);
    }

    public static Struct callWelcomeBeanSeeLearntMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeLearntMoves(),_str,_args);
    }

    public static Struct callWelcomeBeanSeeNotLearntMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeNotLearntMoves(),_str,_args);
    }

}
