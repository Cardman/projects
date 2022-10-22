package aiki.beans.map.characters;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import aiki.beans.map.pokemon.PokemonTeamBeanTrainerSet;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbCharacters extends InitDbConstr {

    public static void fwdTrainerDual(Struct _update, Struct _use) {
        callPokemonTeamBeanTrainerSet(_update,callDualFightBeanTrainerGet(_use));
    }

    public static void fwdTrainer(Struct _update, Struct _use) {
        callPokemonTeamBeanTrainerSet(_update,callTrainerBeanTrainerGet(_use));
    }
    public static void fwdAlly(Struct _update, Struct _use) {
        callAllyBeanAllySet(_update,callDualFightBeanAllyGet(_use));
    }
    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
    }

    public static Struct callAllyBeanAllySet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new AllyBeanAllySet(),_str,_args);
    }
    public static Struct callDualFightBeanTrainerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
    }
    public static Struct callTrainerBeanTrainerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
    }
    public static Struct callPokemonTeamBeanTrainerSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new PokemonTeamBeanTrainerSet(),_str,_args);
    }
}
