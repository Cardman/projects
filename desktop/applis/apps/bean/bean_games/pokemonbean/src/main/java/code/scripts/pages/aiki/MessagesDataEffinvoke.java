package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffinvoke {
    public static final String M_P_50_EFFECT="effect";
    public static final String M_P_50_ENV_TYPE="env_type";
    public static final String M_P_50_INVOKED_MOVE="invoked_move";
    public static final String M_P_50_INVOKE_MOVE_BUT_USER="invoke_move_but_user";
    public static final String M_P_50_INVOKE_MOVE_PART="invoke_move_part";
    public static final String M_P_50_INVOKE_MOVE_SUCCESS_TARGET="invoke_move_success_target";
    public static final String M_P_50_INVOKE_MOVE_TYPE="invoke_move_type";
    public static final String M_P_50_INVOKE_SUFFERED_MOVE="invoke_suffered_move";
    public static final String M_P_50_INVOKE_TARGET_CHOSEN_MOVE="invoke_target_chosen_move";
    public static final String M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP="invoke_user_move_while_sleep";
    public static final String M_P_50_MOVES_NOT_INVOKED="moves_not_invoked";
    public static final String M_P_50_MOVE_FCT_ENV="move_fct_env";
    public static final String M_P_50_MOVE_FCT_ENV_EXC="move_fct_env_exc";
    public static final String M_P_50_OTHER_OWNED_TYPE="other_owned_type";
    public static final String M_P_50_OWNED_TYPE="owned_type";
    public static final String M_P_50_RATE_INVOKE_MOVE="rate_invoke_move";
    private MessagesDataEffinvoke(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_50_EFFECT,"The effect of the move invokes an other move not invoked while the round of the user.While the move has an effect that invokes a not already invoked move, the moves are consecutively invoked.");
        e_.add(M_P_50_ENV_TYPE,"Type of environment");
        e_.add(M_P_50_INVOKED_MOVE,"Invoked move");
        e_.add(M_P_50_INVOKE_MOVE_BUT_USER,"The user invokes a not invoked move that the user does not know currently.");
        e_.add(M_P_50_INVOKE_MOVE_PART,"The user invokes a move of one of its partners that the user does not know currently.");
        e_.add(M_P_50_INVOKE_MOVE_SUCCESS_TARGET,"The user invokes the last successful move by the target while the fight.");
        e_.add(M_P_50_INVOKE_MOVE_TYPE,"Here is le table of the invoked moves in function by owned types by the user:");
        e_.add(M_P_50_INVOKE_SUFFERED_MOVE,"The user invokes the last move that he is suffered while the fight.");
        e_.add(M_P_50_INVOKE_TARGET_CHOSEN_MOVE,"The user invokes the chosen move by the target.");
        e_.add(M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP,"The asleep user invokes a not invoked move that the user knows currently.");
        e_.add(M_P_50_MOVES_NOT_INVOKED,"The following moves cannot be invoked:");
        e_.add(M_P_50_MOVE_FCT_ENV,"Here is the table of the invoked moves in function by the environment type of fight:");
        e_.add(M_P_50_MOVE_FCT_ENV_EXC,"The above moves are not invoked if one of the following moves is enabled:");
        e_.add(M_P_50_OTHER_OWNED_TYPE,"Other owned type");
        e_.add(M_P_50_OWNED_TYPE,"Owned Type(s)");
        e_.add(M_P_50_RATE_INVOKE_MOVE,"Damage inflicted by the invoked move are multiplied by {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_50_EFFECT,"L''effet de l''attaque invoque une autre attaque non invoquée dans le tour du lanceur.Tant que l''attaque possède un effet qui invoque une attaque pas encore invoquée, les attaques sont successivement invoquées.");
        f_.add(M_P_50_ENV_TYPE,"Type d''environnement");
        f_.add(M_P_50_INVOKED_MOVE,"Attaque invoquée");
        f_.add(M_P_50_INVOKE_MOVE_BUT_USER,"Le lanceur invoque une attaque non invoquée qu''il ne connaît pas actuellement.");
        f_.add(M_P_50_INVOKE_MOVE_PART,"Le lanceur invoque une attaque d''un de ses partenaire qu''il ne connaît pas actuellement.");
        f_.add(M_P_50_INVOKE_MOVE_SUCCESS_TARGET,"Le lanceur invoque la dernière attaque réussie par la cible pendant le combat.");
        f_.add(M_P_50_INVOKE_MOVE_TYPE,"Voici le tableau des attaques invoquées en fonction des types possédés par le lanceur:");
        f_.add(M_P_50_INVOKE_SUFFERED_MOVE,"Le lanceur invoque la dernière attaque qu''il a subie pendant le combat.");
        f_.add(M_P_50_INVOKE_TARGET_CHOSEN_MOVE,"Le lanceur invoque l''attaque choisie par la cible.");
        f_.add(M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP,"Le lanceur endormi invoque une attaque non invoquée qu''il connaît actuellement.");
        f_.add(M_P_50_MOVES_NOT_INVOKED,"Les attaques suivantes ne peuvent pas être invoquées:");
        f_.add(M_P_50_MOVE_FCT_ENV,"Voici le tableau des attaques invoquées en fonction du type d''environnement de combat:");
        f_.add(M_P_50_MOVE_FCT_ENV_EXC,"Les attaques ci-dessus ne sont pas invoquées si une des attaques suivantes est active:");
        f_.add(M_P_50_OTHER_OWNED_TYPE,"Autre type possédé");
        f_.add(M_P_50_OWNED_TYPE,"Type(s) possédé(s)");
        f_.add(M_P_50_RATE_INVOKE_MOVE,"Les dégâts causés par l''attaque invoquée sont multipliés par {0}.");
        return f_;
    }
}