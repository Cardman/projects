package aiki.sml.trs;
import aiki.db.*;
import code.maths.litteral.*;
import code.util.*;
public final class Cst1{
private Cst1(){}
static StringMap<String> cnn(){
StringMap<String> f = new StringMap<String>(new CollCapacity(8));
f.addEntry(DataBase.RATE_BOOST_CRITICAL_HIT,MbOperationNode.PUIS+"(2,"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BOOST+"-4)");
f.addEntry(DataBase.RATE_FLEEING,MbOperationNode.DIV_FCT+"(("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_PK_UT_VITESSE+"*"+MbOperationNode.DIV_FCT+"(256,"+MbOperationNode.MAX+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_PK_SAUVAGE_VITESSE+",1))+30*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_NB_FLEES+"),255)*"+MbOperationNode.CARAC_DROITE_FERME+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_PK_SAUVAGE_VITESSE+",1)+"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_PK_SAUVAGE_VITESSE+",1)");
f.addEntry(DataBase.DEF_MOVE,"LUTTE");
f.addEntry(DataBase.RATE_BOOST,MbOperationNode.DIV_FCT+"(2,"+MbOperationNode.MAX+"(2-"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BOOST+",1))*"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BOOST+",0)+"+MbOperationNode.DIV_FCT+"((2+"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BOOST+"),2)*"+MbOperationNode.CARAC_DROITE_FERME+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BOOST+",0)");
f.addEntry(DataBase.DAMAGE_FORMULA,MbOperationNode.DIV_FCT+"((5+"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_LANCEUR_NIVEAU+"),125)*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_POWER+"*"+MbOperationNode.DIV_FCT+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_ATTACK+","+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_DEFENSE+")");
f.addEntry(DataBase.BALL_DEF,"POKE_BALL");
f.addEntry(DataBase.DEFAULT_EGG_GROUP,DataBase.DEFAULT_EGG_GROUP_VALUE);
f.addEntry(DataBase.RATE_CATCHING,MbOperationNode.PUIS+"("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_BASE_CAPT_PK+"*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_RATE_BALL_STATUS+"*4*"+MbOperationNode.DIV_FCT+"((3*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_FOE_PK_MAX_HP+"-2*"+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_FOE_PK_REMOTE_HP+"),("+DataBase.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+DataBase.DEF_FOE_PK_MAX_HP+"*3060)),1/4)");
f.addEntry(DataBase.DEF_CAT,"AUTRE");
return f;
}
}
