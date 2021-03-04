package aiki.sml.trs;
public final class Cst5{
private Cst5(){}
static String tr(){
String f="TRES_DIFFICILE\tcaracferme(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)*puis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)+caracdroiteouvert(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)\n";
f+="DIFFICILE\t1\n";
f+="TRES_FACILE\tcaracferme(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)+caracdroiteouvert(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)*puis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)\n";
f+="FACILE\tpuis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)\n";
return f;
}
}
