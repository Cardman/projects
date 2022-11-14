package aiki.beans.map.characters;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import aiki.beans.map.pokemon.*;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbCharacters extends InitDbConstr {

    public static Struct callDealerBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanClickItem(),_str,_args);
    }

    public static Struct callDealerBeanClickTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanClickTm(),_str,_args);
    }

    public static Struct callDealerBeanGetAllTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetAllTm(),_str,_args);
    }

    public static Struct callDealerBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItem(),_str,_args);
    }

    public static Struct callDealerBeanGetItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItems(),_str,_args);
    }

    public static Struct callDealerBeanGetTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetTm(),_str,_args);
    }


    public static Struct callSellerBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanClickItem(),_str,_args);
    }

    public static Struct callSellerBeanClickTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanClickTm(),_str,_args);
    }

    public static Struct callSellerBeanGetAllTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetAllTm(),_str,_args);
    }

    public static Struct callSellerBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItem(),_str,_args);
    }

    public static Struct callSellerBeanGetItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItems(),_str,_args);
    }

    public static Struct callSellerBeanGetTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetTm(),_str,_args);
    }
    public static Struct callAllyBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanClickAbility(),_str,_args);
    }

    public static Struct callAllyBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanClickItem(),_str,_args);
    }

    public static Struct callAllyBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanClickMove(),_str,_args);
    }

    public static Struct callAllyBeanClickName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanClickName(),_str,_args);
    }

    public static Struct callAllyBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetAbility(),_str,_args);
    }

    public static Struct callAllyBeanGetImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetImage(),_str,_args);
    }

    public static Struct callAllyBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetItem(),_str,_args);
    }

    public static Struct callAllyBeanGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetMove(),_str,_args);
    }

    public static Struct callAllyBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetName(),_str,_args);
    }

    public static Struct callAllyBeanTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanTeamGet(),_str,_args);
    }

//    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }

    public static Struct callDualFightBeanImageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageGet(),_str,_args);
    }

    public static Struct callDualFightBeanImageMiniGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageMiniGet(),_str,_args);
    }

    public static Struct callDualFightBeanImageMiniSecondGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageMiniSecondGet(),_str,_args);
    }

    public static Struct callDualFightBeanPageAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanPageAllyGet(),_str,_args);
    }

//    public static Struct callDualFightBeanPageTeamGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanPageTeamGet(),_str,_args);
//    }
//
//    public static Struct callDualFightBeanTrainerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
//    }

    public static Struct callTrainerBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanClickMove(),_str,_args);
    }

    public static Struct callTrainerBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetName(),_str,_args);
    }

    public static Struct callTrainerBeanGetTeamsRewards(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTeamsRewards(),_str,_args);
    }

    public static Struct callTrainerBeanGetTrMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTrMove(),_str,_args);
    }

    public static Struct callTrainerBeanImageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageGet(),_str,_args);
    }

    public static Struct callTrainerBeanImageMiniGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageMiniGet(),_str,_args);
    }

    public static Struct callTrainerBeanMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanMoveGet(),_str,_args);
    }

//    public static Struct callTrainerBeanPageTeamGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanPageTeamGet(),_str,_args);
//    }

//    public static Struct callTrainerBeanTrainerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
//    }

    public static Struct callPokemonTeamBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickAbility(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickItem(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickMove(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickName(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetAbility(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetItem(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetMove(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetName(),_str,_args);
    }

    public static Struct callPokemonTeamBeanMultiplicityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanMultiplicityGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanNoFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanNoFightGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanRewardGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanRewardGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanNoFightSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new PokemonTeamBeanNoFightSet(),_str,_args);
    }
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
