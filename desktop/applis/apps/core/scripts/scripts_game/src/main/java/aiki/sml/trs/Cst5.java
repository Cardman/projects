package aiki.sml.trs;
public final class Cst5{
static final String CS_TRES_DIFFICILE="TRES_DIFFICILE";
static final String CS_DIFFICILE="DIFFICILE";
static final String CS_TRES_FACILE="TRES_FACILE";
static final String CS_FACILE="FACILE";
private Cst5(){}
static String tr(){
String f=CS_TRES_DIFFICILE+"\tcaracferme(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)*puis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)+caracdroiteouvert(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)\n";
f+=CS_DIFFICILE+"\t1\n";
f+=CS_TRES_FACILE+"\tcaracferme(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),0,1)+caracdroiteouvert(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),1)*puis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)\n";
f+=CS_FACILE+"\tpuis(div(VAR__LEVEL_LOOSER,VAR__LEVEL_WINNER),3)\n";
return f;
}
}
