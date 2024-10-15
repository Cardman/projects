package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.maths.litteral.*;
import code.util.*;
import aiki.game.params.enums.*;
public final class Cst5 extends CstIgame {
static final String CS_TRES_DIFFICILE=DataBase.DEF_TRES_DIFFICILE;
static final String CS_DIFFICILE=DataBase.DEF_DIFFICILE;
static final String CS_TRES_FACILE=DataBase.DEF_TRES_FACILE;
static final String CS_FACILE=DataBase.DEF_FACILE;
static final String VAR_LEVEL_LOOSER= MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_LEVEL_LOOSER;
static final String VAR_LEVEL_WINNER= MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_LEVEL_WINNER;
private Cst5(){}
static IdMap<DifficultyWinPointsFight,String> di(){
IdMap<DifficultyWinPointsFight,String> e=new IdMap<DifficultyWinPointsFight,String>(new CollCapacity(4));
e.addEntry(DifficultyWinPointsFight.TRES_FACILE,MbOperationNode.CARAC_FERME+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_0+OC+R_1+RP+OP+MbOperationNode.CARAC_DROITE_OUVERT+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_1+RP+OM+MbOperationNode.PUIS+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_3+RP);
e.addEntry(DifficultyWinPointsFight.FACILE,MbOperationNode.PUIS+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_3+RP);
e.addEntry(DifficultyWinPointsFight.DIFFICILE,R_1);
e.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,MbOperationNode.CARAC_FERME+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_0+OC+R_1+RP+OM+MbOperationNode.PUIS+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_3+RP+OP+MbOperationNode.CARAC_DROITE_OUVERT+LP+MbOperationNode.DIV_FCT+LP+VAR_LEVEL_LOOSER+OC+VAR_LEVEL_WINNER+RP+OC+R_1+RP);
return e;
}
}
