package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffcopymove {
    public static final String M_P_43_COPY_DEF_MOVE="copy_def_move";
    public static final String M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS="copy_def_move_without_trans";
    public static final String M_P_43_COPY_TMP_MOVE="copy_tmp_move";
    public static final String M_P_43_COPY_TMP_MOVE_INTRO="copy_tmp_move";
    public static final String M_P_43_EFFECT="effect";
    public static final String M_P_43_MOVES_NOT_COPIED="moves_not_copied";
    public static final String M_P_43_NO_EFFECT="no_effect";
    public static final String M_P_43_NO_EFFECT_2="no_effect_2";
    private MessagesDataEffcopymove(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_43_COPY_DEF_MOVE,"The copied move of the target definitively substitutes the move {0} of the user except if the user is under the effect of one of the moves like:");
        e_.add(M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS,"The copied move of the target definitively substitutes the move {0} of the user.");
        e_.add(M_P_43_COPY_TMP_MOVE,"The copied move of the target substitutes the move {0} of the user and has {1} pp at the beginning. If all pp of the copied move are used then the user can again use the move {0} with one pp less.");
        e_.add(M_P_43_COPY_TMP_MOVE_INTRO,"PP of the copied move of the target at the beginning:");
        e_.add(M_P_43_EFFECT,"The effect happens lors d'une copie d'attque de combattant");
        e_.add(M_P_43_MOVES_NOT_COPIED,"The following moves cannot be copied:");
        e_.add(M_P_43_NO_EFFECT,"The effect does not happen if the target has not used any move; or if the last used move of the target is:");
        e_.add(M_P_43_NO_EFFECT_2,"The effect does not happen if the firstly chosen move of the user is:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_43_COPY_DEF_MOVE,"L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur définitivement sauf si le lanceur est sous l''effet d''une des attaques comme:");
        f_.add(M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS,"L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur définitivement.");
        f_.add(M_P_43_COPY_TMP_MOVE,"L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur et prend {1} pp au départ. Si tous les pp de l''attaque copiée sont consommés alors le lanceur peut de nouveau utiliser l''attaque {0} avec un pp de moins.");
        f_.add(M_P_43_COPY_TMP_MOVE_INTRO,"PP de l'attaque copiée de la cible au départ:");
        f_.add(M_P_43_EFFECT,"L'effet a lieu lors d'une copie d'attque de combattant");
        f_.add(M_P_43_MOVES_NOT_COPIED,"Les attaques suivantes ne peuvent pas être copiées:");
        f_.add(M_P_43_NO_EFFECT,"L'effet n'a pas lieu si la cible n'a pas attaqué ou si la dernière attaque lancée de la cible est:");
        f_.add(M_P_43_NO_EFFECT_2,"L'effet n'a pas lieu si l'attaque initialement choisie du lanceur est:");
        return f_;
    }
}