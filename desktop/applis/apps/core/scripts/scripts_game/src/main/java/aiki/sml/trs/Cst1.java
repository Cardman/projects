package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.maths.litteral.*;
import code.util.*;
public final class Cst1 extends CstIgame{
private Cst1(){}
static StringMap<String> cnn(){
StringMap<String> f = new StringMap<String>(new CollCapacity(8));
f.addEntry(DataBase.RATE_BOOST_CRITICAL_HIT,MbOperationNode.PUIS+LP+R_2+OC+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BOOST+OD+R_4+RP);
f.addEntry(DataBase.RATE_FLEEING,MbOperationNode.DIV_FCT+LP+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_PK_UT_VITESSE+OM+MbOperationNode.DIV_FCT+LP+R_256+OC+MbOperationNode.MAX+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE+OC+R_1+RP+RP+OP+R_30+OM+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_NB_FLEES+RP+OC+R_255+RP+OM+MbOperationNode.CARAC_DROITE_FERME+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE+OC+R_1+RP+OP+MbOperationNode.CARAC_GAUCHE_OUVERT+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE+OC+R_1+RP);
f.addEntry(DataBase.DEF_MOVE,I_LUTTE);
f.addEntry(DataBase.RATE_BOOST,MbOperationNode.DIV_FCT+LP+R_2+OC+MbOperationNode.MAX+LP+R_2+OD+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BOOST+OC+R_1+RP+RP+OM+MbOperationNode.CARAC_GAUCHE_OUVERT+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BOOST+OC+R_0+RP+OP+MbOperationNode.DIV_FCT+LP+LP+R_2+OP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BOOST+RP+OC+R_2+RP+OM+MbOperationNode.CARAC_DROITE_FERME+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BOOST+OC+R_0+RP);
f.addEntry(DataBase.DAMAGE_FORMULA,MbOperationNode.DIV_FCT+LP+LP+R_5+OP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU+RP+OC+R_125+RP+OM+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_POWER+OM+MbOperationNode.DIV_FCT+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_ATTACK+OC+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_DEFENSE+RP);
f.addEntry(DataBase.BALL_DEF,I_POKE_BALL);
f.addEntry(DataBase.DEFAULT_EGG_GROUP, MessagesDataBaseConstants.DEFAULT_EGG_GROUP_VALUE);
f.addEntry(DataBase.RATE_CATCHING,MbOperationNode.PUIS+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_BASE_CAPT_PK+OM+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_RATE_BALL_STATUS+OM+R_4+OM+MbOperationNode.DIV_FCT+LP+LP+R_3+OM+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP+OD+R_2+OM+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP+RP+OC+LP+ MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP+OM+R_3060+RP+RP+OC+R_1_4+RP);
f.addEntry(DataBase.DEF_CAT,I_AUTRE);
return f;
}
}
