package aiki.sml.trs;
import code.util.*;
public final class Cst1{
private Cst1(){}
static StringMap<String> cnn(){
StringMap<String> f = new StringMap<String>(new CollCapacity(8));
f.addEntry("RATE_BOOST_CRITICAL_HIT","puis(2,VAR__BOOST-4)");
f.addEntry("RATE_FLEEING","div((VAR__PK_UT_VITESSE*div(256,max(VAR__PK_SAUVAGE_VITESSE,1))+30*VAR__NB_FLEES),255)*caracdroiteferme(VAR__PK_SAUVAGE_VITESSE,1)+caracgaucheouvert(VAR__PK_SAUVAGE_VITESSE,1)");
f.addEntry("DEF_MOVE","LUTTE");
f.addEntry("RATE_BOOST","div(2,max(2-VAR__BOOST,1))*caracgaucheouvert(VAR__BOOST,0)+div((2+VAR__BOOST),2)*caracdroiteferme(VAR__BOOST,0)");
f.addEntry("DAMAGE_FORMULA","div((5+VAR__LANCEUR_NIVEAU),125)*VAR__POWER*div(VAR__ATTACK,VAR__DEFENSE)");
f.addEntry("BALL_DEF","POKE_BALL");
f.addEntry("DEFAULT_EGG_GROUP","METAMORPH");
f.addEntry("RATE_CATCHING","puis(VAR__BASE_CAPT_PK*VAR__RATE_BALL_STATUS*4*div((3*VAR__FOE_PK_MAX_HP-2*VAR__FOE_PK_REMOTE_HP),(VAR__FOE_PK_MAX_HP*3060)),1/4)");
f.addEntry("DEF_CAT","AUTRE");
return f;
}
}
