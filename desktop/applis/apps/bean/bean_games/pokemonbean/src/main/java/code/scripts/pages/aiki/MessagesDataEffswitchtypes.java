package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchtypes {
    public static final String M_P_65_ADDED_TYPES="added_types";
    public static final String M_P_65_AFFECT_TYPES="affect_types";
    public static final String M_P_65_AFFECT_TYPES_NOT_CONST_TARGET="affect_types_not_const_target";
    public static final String M_P_65_AFFECT_TYPES_NOT_CONST_USER="affect_types_not_const_user";
    public static final String M_P_65_EFFECT="effect";
    public static final String M_P_65_ENVIR="envir";
    public static final String M_P_65_ENVIR_ENV="envir_env";
    public static final String M_P_65_ENVIR_ENV_EXC="envir_env_exc";
    public static final String M_P_65_ENVIR_TYPE="envir_type";
    public static final String M_P_65_RES_MOVES="res_moves";
    public static final String M_P_65_SWITCH_TYPES="switch_types";
    public static final String M_P_65_USER_MOVES="user_moves";
    private MessagesDataEffswitchtypes(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_65_ADDED_TYPES,"The following types are added to the one of the target:");
        e_.add(M_P_65_AFFECT_TYPES,"The target takes the following types:");
        e_.add(M_P_65_AFFECT_TYPES_NOT_CONST_TARGET,"The target takes the types of the user.");
        e_.add(M_P_65_AFFECT_TYPES_NOT_CONST_USER,"The user takes the types of the target.");
        e_.add(M_P_65_EFFECT,"This effect change the types of a fighter at least.");
        e_.add(M_P_65_ENVIR,"The user take the type in function by the environment of fight:");
        e_.add(M_P_65_ENVIR_ENV,"Environment of fight");
        e_.add(M_P_65_ENVIR_ENV_EXC,"The types above are not taken if one of the following moves is enabled:");
        e_.add(M_P_65_ENVIR_TYPE,"Type to be affected");
        e_.add(M_P_65_RES_MOVES,"The user take a random type resisting against the type of the suffered damaging move during the round by changing types.");
        e_.add(M_P_65_SWITCH_TYPES,"The user and the target switch their types.");
        e_.add(M_P_65_USER_MOVES,"The user take a random type among the types of its moves by changing types.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_65_ADDED_TYPES,"Les types suivants sont ajoutés à ceux de la cible:");
        f_.add(M_P_65_AFFECT_TYPES,"La cible prend les types suivants:");
        f_.add(M_P_65_AFFECT_TYPES_NOT_CONST_TARGET,"La cible prend les types du lanceur.");
        f_.add(M_P_65_AFFECT_TYPES_NOT_CONST_USER,"Le lanceur prend les types de la cible.");
        f_.add(M_P_65_EFFECT,"Cet effet change les types d'au moins un combattant.");
        f_.add(M_P_65_ENVIR,"Le lanceur prend le type en fonction de l'environnement de combat:");
        f_.add(M_P_65_ENVIR_ENV,"Environnement de combat");
        f_.add(M_P_65_ENVIR_ENV_EXC,"Les types ci-dessus ne sont pas pris si une des attaques suivantes est active:");
        f_.add(M_P_65_ENVIR_TYPE,"Type à attribuer");
        f_.add(M_P_65_RES_MOVES,"Le lanceur prend un type aléatoire résistant au type de l'attaque offensive subie pendant le tour en changeant de type.");
        f_.add(M_P_65_SWITCH_TYPES,"Le lanceur et la cible cible échangent leurs types.");
        f_.add(M_P_65_USER_MOVES,"Le lanceur prend un type aléatoire de ses attaques en changeant de type.");
        return f_;
    }
}