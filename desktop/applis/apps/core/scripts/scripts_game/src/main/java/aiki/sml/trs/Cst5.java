package aiki.sml.trs;
import aiki.db.*;
import code.maths.litteral.*;
import code.util.*;
import aiki.game.params.enums.*;
public final class Cst5{
static final String CS_TRES_DIFFICILE=DataBase.DEF_TRES_DIFFICILE;
static final String CS_DIFFICILE=DataBase.DEF_DIFFICILE;
static final String CS_TRES_FACILE=DataBase.DEF_TRES_FACILE;
static final String CS_FACILE=DataBase.DEF_FACILE;
static final String VAR_LEVEL_LOOSER= MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_LEVEL_LOOSER;
static final String VAR_LEVEL_WINNER= MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+ MessagesDataBaseConstants.DEF_LEVEL_WINNER;
private Cst5(){}
static IdMap<DifficultyWinPointsFight,String> di(){
IdMap<DifficultyWinPointsFight,String> e=new IdMap<DifficultyWinPointsFight,String>(new CollCapacity(4));
e.addEntry(DifficultyWinPointsFight.TRES_FACILE,MbOperationNode.CARAC_FERME+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),0,1)+"+MbOperationNode.CARAC_DROITE_OUVERT+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),1)*"+MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),3)");
e.addEntry(DifficultyWinPointsFight.FACILE,MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),3)");
e.addEntry(DifficultyWinPointsFight.DIFFICILE,"1");
e.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,MbOperationNode.CARAC_FERME+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),0,1)*"+MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),3)+"+MbOperationNode.CARAC_DROITE_OUVERT+"("+MbOperationNode.DIV_FCT+"("+VAR_LEVEL_LOOSER+","+VAR_LEVEL_WINNER+"),1)");
return e;
}
}
