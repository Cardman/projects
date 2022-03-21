package code.scripts.pages.aiki;
final class MesDataEndroundIndividual{
private static final String C_P_6_23="Here is the rate of restored hp in function by the status of the fighter (the rates are multiplied if the fighter owns some status.):<br/>\n";
private static final String C_P_6_24="Owned status\n";
private static final String C_P_6_25="Rate of restored health points by the target\n";
private static final String C_P_6_26="The rate that the status are deleted is {0}.<br/>\n";
private static final String C_P_6_28="The rate of recoil damage for the fighter is {0}.<br/>\n";
private static final String C_P_6_29="The fighter restores {0} of its full life.<br/>\n";
private static final String C_P_6_30="Here is the variation of the hp of the fighter in function by the owned types,<br/>the variations of life are added in function by owned types:<br/>\n";
private static final String C_P_6_31="Other owned type(s)\n";
private static final String C_P_6_32="Owned types\n";
private static final String C_P_6_33="Variation of the rate of the hp of the fighter\n";
private static final String C_P_6_34="Win of {0} of the full hp\n";
private static final String C_P_6_35="Loss of {0} of the full hp\n";
private static final String C_P_6_36="The fighter is affected by the status <a c:command=\"$clickUserStatus({1})\">{0}</a> if not already done.<br/>\n";
private static final String C_P_6_44="Cet effet concerne un seul combattant.<br/>\n";
private static final String C_P_6_60="Voici le taux de pv restaur&eacute;s en fonction du statut du combattant (les coefficients sont mutlipli&eacute;s si le combattant poss&egrave;de plusieurs statuts.):<br/>\n";
private static final String C_P_6_61="Statut poss&eacute;d&eacute;\n";
private static final String C_P_6_62="Taux de vie restaur&eacute;e par la cible\n";
private static final String C_P_6_63="La probabilit&eacute; que les statuts du combattant soient supprim&eacute;s est de {0}.<br/>\n";
private static final String C_P_6_65="Le taux de d&eacute;g&acirc;ts de recul pour le combattant est de {0}.<br/>\n";
private static final String C_P_6_66="Le combattant restaure {0} de sa vie totale.<br/>\n";
private static final String C_P_6_67="Voici la variation des pv du combattant en fonction des types poss&eacute;d&eacute;s,<br/>les variations de vie sont cumul&eacute;s en fonction des types poss&eacute;d&eacute;s:<br/>\n";
private static final String C_P_6_68="Autre(s) type(s) poss&eacute;d&eacute;(s)\n";
private static final String C_P_6_69="Types poss&eacute;d&eacute;s\n";
private static final String C_P_6_70="Variation du taux de pv du combattant\n";
private static final String C_P_6_71="Gain de {0} du total des pv\n";
private static final String C_P_6_72="Perte de {0} du total des pv\n";
private static final String C_P_6_73="Le combattant prend le statut <a c:command=\"$clickUserStatus({1})\">{0}</a> si ce n''est pas d&eacute;j&agrave; fait.<br/>\n";
private static final String C_P_6_7="This effect regards one fighter.<br/>\n";
private static final String M_P_6_DELETE_ALL_STATUS="delete_all_status";
private static final String M_P_6_EFFECT="effect";
private static final String M_P_6_HEAL_HP="heal_hp";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES="heal_hp_by_owner_types";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY="heal_hp_by_owner_types_key";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER="heal_hp_by_owner_types_other";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE="heal_hp_by_owner_types_value";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L="heal_hp_by_owner_types_value_l";
private static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W="heal_hp_by_owner_types_value_w";
private static final String M_P_6_MULT_DAMAGE_STATUS="mult_damage_status";
private static final String M_P_6_MULT_DAMAGE_STATUS_KEY="mult_damage_status_key";
private static final String M_P_6_MULT_DAMAGE_STATUS_VALUE="mult_damage_status_value";
private static final String M_P_6_RECOIL_DAMAGE="recoil_damage";
private static final String M_P_6_USER_STATUS="user_status";
private static final char SEP='=';
private MesDataEndroundIndividual(){}
static String en(){
String f=M_P_6_EFFECT+SEP+C_P_6_7;
f+=M_P_6_MULT_DAMAGE_STATUS+SEP+C_P_6_23;
f+=M_P_6_MULT_DAMAGE_STATUS_KEY+SEP+C_P_6_24;
f+=M_P_6_MULT_DAMAGE_STATUS_VALUE+SEP+C_P_6_25;
f+=M_P_6_DELETE_ALL_STATUS+SEP+C_P_6_26;
f+=M_P_6_RECOIL_DAMAGE+SEP+C_P_6_28;
f+=M_P_6_HEAL_HP+SEP+C_P_6_29;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES+SEP+C_P_6_30;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER+SEP+C_P_6_31;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY+SEP+C_P_6_32;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE+SEP+C_P_6_33;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W+SEP+C_P_6_34;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L+SEP+C_P_6_35;
f+=M_P_6_USER_STATUS+SEP+C_P_6_36;
return f;
}
static String fr(){
String f=M_P_6_EFFECT+SEP+C_P_6_44;
f+=M_P_6_MULT_DAMAGE_STATUS+SEP+C_P_6_60;
f+=M_P_6_MULT_DAMAGE_STATUS_KEY+SEP+C_P_6_61;
f+=M_P_6_MULT_DAMAGE_STATUS_VALUE+SEP+C_P_6_62;
f+=M_P_6_DELETE_ALL_STATUS+SEP+C_P_6_63;
f+=M_P_6_RECOIL_DAMAGE+SEP+C_P_6_65;
f+=M_P_6_HEAL_HP+SEP+C_P_6_66;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES+SEP+C_P_6_67;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER+SEP+C_P_6_68;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY+SEP+C_P_6_69;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE+SEP+C_P_6_70;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W+SEP+C_P_6_71;
f+=M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L+SEP+C_P_6_72;
f+=M_P_6_USER_STATUS+SEP+C_P_6_73;
return f;
}
}
