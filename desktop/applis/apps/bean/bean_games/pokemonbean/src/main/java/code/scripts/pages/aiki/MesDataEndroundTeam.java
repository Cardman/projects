package code.scripts.pages.aiki;
final class MesDataEndroundTeam{
private static final String C_P_13_0="This effect regards the team of the user.\n";
private static final String C_P_13_1="The rate that the status of the fighter are deleted is {0}.\n";
private static final String C_P_13_2="The rate that the status of the partners of the fighter are deleted is {0}.\n";
private static final String C_P_13_3="Cet effet concerne l''équipe du lanceur.\n";
private static final String C_P_13_4="La probabilité que les statuts du combattant soient supprimés est de {0}.\n";
private static final String C_P_13_5="La probabilité que les statuts des partenaires du combattant soient supprimés est de {0}.\n";
private static final String M_P_13_EFFECT="effect";
private static final String M_P_13_OWNER="owner";
private static final String M_P_13_TEAM="team";
private static final char SEP='=';
private MesDataEndroundTeam(){}
static String en(){
String f=M_P_13_EFFECT+SEP+C_P_13_0;
f+=M_P_13_OWNER+SEP+C_P_13_1;
f+=M_P_13_TEAM+SEP+C_P_13_2;
return f;
}
static String fr(){
String f=M_P_13_EFFECT+SEP+C_P_13_3;
f+=M_P_13_OWNER+SEP+C_P_13_4;
f+=M_P_13_TEAM+SEP+C_P_13_5;
return f;
}
}
