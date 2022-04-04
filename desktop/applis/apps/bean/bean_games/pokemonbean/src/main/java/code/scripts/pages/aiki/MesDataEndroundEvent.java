package code.scripts.pages.aiki;
final class MesDataEndroundEvent{
private static final String C_P_4_0="The rank of the effect is {0}.\n";
private static final String C_P_4_1="Le rang de l''effet est de {0}.\n";
private static final String M_P_4_RANK="rank";
private static final char SEP='=';
private MesDataEndroundEvent(){}
static String en(){
return M_P_4_RANK+SEP+C_P_4_0;
}
static String fr(){
return M_P_4_RANK+SEP+C_P_4_1;
}
}
