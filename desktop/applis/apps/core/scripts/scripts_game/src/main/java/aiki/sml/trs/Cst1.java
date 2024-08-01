package aiki.sml.trs;
import aiki.db.*;
import code.maths.litteral.*;
import code.util.*;
public final class Cst1{
private Cst1(){}
static StringMap<String> cnn(){
StringMap<String> f = new StringMap<String>(new CollCapacity(8));
f.addEntry("RATE_BOOST_CRITICAL_HIT",MbOperationNode.PUIS+"(2,"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BOOST-4)");
f.addEntry("RATE_FLEEING",MbOperationNode.DIV_FCT+"(("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"PK_UT_VITESSE*"+MbOperationNode.DIV_FCT+"(256,"+MbOperationNode.MAX+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"PK_SAUVAGE_VITESSE,1))+30*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"NB_FLEES),255)*"+MbOperationNode.CARAC_DROITE_FERME+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"PK_SAUVAGE_VITESSE,1)+"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"PK_SAUVAGE_VITESSE,1)");
f.addEntry("DEF_MOVE","LUTTE");
f.addEntry("RATE_BOOST",MbOperationNode.DIV_FCT+"(2,"+MbOperationNode.MAX+"(2-"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BOOST,1))*"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BOOST,0)+"+MbOperationNode.DIV_FCT+"((2+"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BOOST),2)*"+MbOperationNode.CARAC_DROITE_FERME+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BOOST,0)");
f.addEntry("DAMAGE_FORMULA",MbOperationNode.DIV_FCT+"((5+"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_LANCEUR_NIVEAU+"),125)*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"POWER*"+MbOperationNode.DIV_FCT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"ATTACK,"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"DEFENSE)");
f.addEntry("BALL_DEF","POKE_BALL");
f.addEntry("DEFAULT_EGG_GROUP","METAMORPH");
f.addEntry("RATE_CATCHING",MbOperationNode.PUIS+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"BASE_CAPT_PK*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"RATE_BALL_STATUS*4*"+MbOperationNode.DIV_FCT+"((3*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"FOE_PK_MAX_HP-2*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"FOE_PK_REMOTE_HP),("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+"FOE_PK_MAX_HP*3060)),1/4)");
f.addEntry("DEF_CAT","AUTRE");
return f;
}
}
