package aiki.sml.trs;
import code.maths.litteral.*;
import code.util.*;
public final class Cst1{
private Cst1(){}
static StringMap<String> cnn(){
StringMap<String> f = new StringMap<String>(new CollCapacity(8));
f.addEntry("RATE_BOOST_CRITICAL_HIT",MbOperationNode.PUIS+"(2,VAR__BOOST-4)");
f.addEntry("RATE_FLEEING",MbOperationNode.DIV_FCT+"((VAR__PK_UT_VITESSE*"+MbOperationNode.DIV_FCT+"(256,"+MbOperationNode.MAX+"(VAR__PK_SAUVAGE_VITESSE,1))+30*VAR__NB_FLEES),255)*"+MbOperationNode.CARAC_DROITE_FERME+"(VAR__PK_SAUVAGE_VITESSE,1)+"+MbOperationNode.CARAC_GAUCHE_OUVERT+"(VAR__PK_SAUVAGE_VITESSE,1)");
f.addEntry("DEF_MOVE","LUTTE");
f.addEntry("RATE_BOOST",MbOperationNode.DIV_FCT+"(2,"+MbOperationNode.MAX+"(2-VAR__BOOST,1))*"+MbOperationNode.CARAC_GAUCHE_OUVERT+"(VAR__BOOST,0)+"+MbOperationNode.DIV_FCT+"((2+VAR__BOOST),2)*"+MbOperationNode.CARAC_DROITE_FERME+"(VAR__BOOST,0)");
f.addEntry("DAMAGE_FORMULA",MbOperationNode.DIV_FCT+"((5+VAR__LANCEUR_NIVEAU),125)*VAR__POWER*"+MbOperationNode.DIV_FCT+"(VAR__ATTACK,VAR__DEFENSE)");
f.addEntry("BALL_DEF","POKE_BALL");
f.addEntry("DEFAULT_EGG_GROUP","METAMORPH");
f.addEntry("RATE_CATCHING",MbOperationNode.PUIS+"(VAR__BASE_CAPT_PK*VAR__RATE_BALL_STATUS*4*"+MbOperationNode.DIV_FCT+"((3*VAR__FOE_PK_MAX_HP-2*VAR__FOE_PK_REMOTE_HP),(VAR__FOE_PK_MAX_HP*3060)),1/4)");
f.addEntry("DEF_CAT","AUTRE");
return f;
}
}
