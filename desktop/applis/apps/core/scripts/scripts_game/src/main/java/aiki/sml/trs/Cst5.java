package aiki.sml.trs;
import code.maths.litteral.*;
import code.util.*;
import aiki.game.params.enums.*;
public final class Cst5{
static final String CS_TRES_DIFFICILE="TRES_DIFFICILE";
static final String CS_DIFFICILE="DIFFICILE";
static final String CS_TRES_FACILE="TRES_FACILE";
static final String CS_FACILE="FACILE";
private Cst5(){}
static IdMap<DifficultyWinPointsFight,String> di(){
IdMap<DifficultyWinPointsFight,String> e=new IdMap<DifficultyWinPointsFight,String>(new CollCapacity(4));
e.addEntry(DifficultyWinPointsFight.TRES_FACILE,MbOperationNode.CARAC_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)+"+MbOperationNode.CARAC_DROITE_OUVERT+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)*"+MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)");
e.addEntry(DifficultyWinPointsFight.FACILE,MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)");
e.addEntry(DifficultyWinPointsFight.DIFFICILE,"1");
e.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,MbOperationNode.CARAC_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)*"+MbOperationNode.PUIS+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)+"+MbOperationNode.CARAC_DROITE_OUVERT+"("+MbOperationNode.DIV_FCT+"(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)");
return e;
}
}
