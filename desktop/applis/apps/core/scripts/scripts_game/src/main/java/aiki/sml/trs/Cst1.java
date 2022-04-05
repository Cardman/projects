package aiki.sml.trs;
public final class Cst1{
private static final String SEP="\t";
private Cst1(){}
static String tr(){
String f="RATE_BOOST_CRITICAL_HIT"+SEP+"puis(2,VAR__BOOST-4)\n";
f+="RATE_FLEEING"+SEP+"div((VAR__PK_UT_VITESSE*div(256,max(VAR__PK_SAUVAGE_VITESSE,1))+30*VAR__NB_FLEES),255)*caracdroiteferme(VAR__PK_SAUVAGE_VITESSE,1)+caracgaucheouvert(VAR__PK_SAUVAGE_VITESSE,1)\n";
f+="DEF_MOVE"+SEP+"LUTTE\n";
f+="RATE_BOOST"+SEP+"div(2,max(2-VAR__BOOST,1))*caracgaucheouvert(VAR__BOOST,0)+div((2+VAR__BOOST),2)*caracdroiteferme(VAR__BOOST,0)\n";
f+="DAMAGE_FORMULA"+SEP+"div((5+VAR__LANCEUR_NIVEAU),125)*VAR__POWER*div(VAR__ATTACK,VAR__DEFENSE)\n";
f+="BALL_DEF"+SEP+"POKE_BALL\n";
f+="DEFAULT_EGG_GROUP"+SEP+"METAMORPH\n";
f+="RATE_CATCHING"+SEP+"puis(BASE_CAPT_PK*RATE_BALL_STATUS*4*div((3*FOE_PK_MAX_HP-2*FOE_PK_REMOTE_HP),(FOE_PK_MAX_HP*3060)),1/4)\n";
return f;
}
}
