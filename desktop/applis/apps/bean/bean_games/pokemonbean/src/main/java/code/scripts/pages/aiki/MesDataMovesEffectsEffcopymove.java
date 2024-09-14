package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcopymove{
private static final String C_P_43_0="The effect happens lors d''une copie d''attque de combattant\n";
private static final String C_P_43_10="Les attaques suivantes ne peuvent pas être copiées:\n";
private static final String C_P_43_11="L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur et prend {1} pp au départ. Si tous les pp de l''attaque copiée sont consommés alors le lanceur peut de nouveau utiliser l''attaque {0} avec un pp de moins.\n";
private static final String C_P_43_12="L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur définitivement sauf si le lanceur est sous l''effet d''une des attaques comme:\n";
private static final String C_P_43_13="L''attaque copiée de la cible vient substituer l''attaque {0} du lanceur définitivement.\n";
private static final String C_P_43_1="The effect does not happen if the target has not used any move; or if the last used move of the target is:\n";
private static final String C_P_43_2="The effect does not happen if the firstly chosen move of the user is:\n";
private static final String C_P_43_3="The following moves cannot be copied:\n";
private static final String C_P_43_4="The copied move of the target substitutes the move {0} of the user and has {1} pp at the beginning. If all pp of the copied move are used then the user can again use the move {0} with one pp less.\n";
private static final String C_P_43_5="The copied move of the target definitively substitutes the move {0} of the user except if the user is under the effect of one of the moves like:\n";
private static final String C_P_43_6="The copied move of the target definitively substitutes the move {0} of the user.\n";
private static final String C_P_43_7="L''effet a lieu lors d''une copie d''attque de combattant\n";
private static final String C_P_43_8="L''effet n''a pas lieu si la cible n''a pas attaqué ou si la dernière attaque lancée de la cible est:\n";
private static final String C_P_43_9="L''effet n''a pas lieu si l''attaque initialement choisie du lanceur est:\n";
private static final String M_P_43_COPY_DEF_MOVE="copy_def_move";
private static final String M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS="copy_def_move_without_trans";
private static final String M_P_43_COPY_TMP_MOVE="copy_tmp_move";
private static final String M_P_43_EFFECT="effect";
private static final String M_P_43_MOVES_NOT_COPIED="moves_not_copied";
private static final String M_P_43_NO_EFFECT="no_effect";
private static final String M_P_43_NO_EFFECT_2="no_effect_2";
private static final char SEP='=';
private MesDataMovesEffectsEffcopymove(){}
static String en(){
String f=M_P_43_EFFECT+SEP+C_P_43_0;
f+=M_P_43_NO_EFFECT+SEP+C_P_43_1;
f+=M_P_43_NO_EFFECT_2+SEP+C_P_43_2;
f+=M_P_43_MOVES_NOT_COPIED+SEP+C_P_43_3;
f+=M_P_43_COPY_TMP_MOVE+SEP+C_P_43_4;
f+=M_P_43_COPY_DEF_MOVE+SEP+C_P_43_5;
f+=M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS+SEP+C_P_43_6;
return f;
}
static String fr(){
String f=M_P_43_EFFECT+SEP+C_P_43_7;
f+=M_P_43_NO_EFFECT+SEP+C_P_43_8;
f+=M_P_43_NO_EFFECT_2+SEP+C_P_43_9;
f+=M_P_43_MOVES_NOT_COPIED+SEP+C_P_43_10;
f+=M_P_43_COPY_TMP_MOVE+SEP+C_P_43_11;
f+=M_P_43_COPY_DEF_MOVE+SEP+C_P_43_12;
f+=M_P_43_COPY_DEF_MOVE_WITHOUT_TRANS+SEP+C_P_43_13;
return f;
}
}
