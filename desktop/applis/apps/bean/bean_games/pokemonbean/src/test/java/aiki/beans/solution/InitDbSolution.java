package aiki.beans.solution;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.solution.dto.*;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbSolution extends InitDbConstr {


    public static Struct callSolutionBeanGetPlace(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SolutionBeanGetPlace(),_str,_args);
    }

    public static Struct callSolutionBeanStepsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SolutionBeanStepsGet(),_str,_args);
    }

    public static Struct callPlaceTrainerDtoPlaceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PlaceTrainerDtoPlaceGet(),_str,_args);
    }

    public static Struct callPlaceTrainerDtoTrainerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PlaceTrainerDtoTrainerGet(),_str,_args);
    }

    public static Struct callStepDtoGetNames(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StepDtoGetNames(),_str,_args);
    }

    public static Struct callStepDtoGetPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StepDtoGetPokemon(),_str,_args);
    }

    public static Struct callWildPokemonDtoGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoGenderGet(),_str,_args);
    }

    public static Struct callWildPokemonDtoImageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoImageGet(),_str,_args);
    }

    public static Struct callWildPokemonDtoNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoNameGet(),_str,_args);
    }

}
