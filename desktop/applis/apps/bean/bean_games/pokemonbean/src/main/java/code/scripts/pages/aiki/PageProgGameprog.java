package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageProgGameprog extends PageCardsCommon{
private static final String C_P_212_0="javahtml";
private static final String C_P_212_1="progressing";
private static final String C_P_212_2="msg_prog,title";
private static final String C_P_212_3=PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS;
private static final String C_P_212_4="stylesheet";
private static final String C_P_212_5="text/css";
private static final String C_P_212_6="finishedGame";
private static final String C_P_212_7="heroImage";
private static final String C_P_212_8="+";
private static final String C_P_212_9="heroImageOppositeSex";
private static final String C_P_212_10="=";
private static final String C_P_212_11="endGameImage";
private static final String C_P_212_12="!finishedGame";
private static final String C_P_212_13="msg_prog,nickname";
private static final String C_P_212_14="nickname";
private static final String C_P_212_15="heroImage";
private static final String C_P_212_16="$n()";
private static final String C_P_212_17="msg_prog,titleNotAtAll";
private static final String C_P_212_18="$p()";
private static final String C_P_212_19="msg_prog,titlePart";
private static final String C_P_212_20="$a()";
private static final String C_P_212_21="msg_prog,titleFull";
private static final String C_P_212_22="msg_prog,unBeatTrainer";
private static final String C_P_212_23="aiki.fight.pokemon.TrainerPlaceNames";
private static final String C_P_212_24="unBeatenImportantTrainers";
private static final String C_P_212_25="p";
private static final String C_P_212_26="{p.getTrainer()} - {p.getPlace()}";
private static final String C_P_212_27="msg_prog,beatTrainer";
private static final String C_P_212_28="aiki.fight.pokemon.TrainerPlaceNames";
private static final String C_P_212_29="beatenImportantTrainers";
private static final String C_P_212_30="p";
private static final String C_P_212_31="{p.getTrainer()} - {p.getPlace()}";
private static final String C_P_212_32="msg_prog,otherTrainers";
private static final String C_P_212_33="msg_prog,otherTrainersPlace";
private static final String C_P_212_34="msg_prog,otherTrainersNumber";
private static final String C_P_212_35="p";
private static final String C_P_212_36="remainingOtherTrainerPlaces";
private static final String C_P_212_37="n";
private static final String C_P_212_38="java.lang.Object";
private static final String C_P_212_39="java.lang.Integer";
private static final String C_P_212_40="{getRemainingOtherTrainersPlaceName(([p]))}";
private static final String C_P_212_41="{n}";
private static final String C_P_212_42="msg_prog,unVisitPlace";
private static final String C_P_212_43="unVisitedPlaces";
private static final String C_P_212_44="p";
private static final String C_P_212_45="java.lang.String";
private static final String C_P_212_46="{p}";
private static final String C_P_212_47="msg_prog,visitPlace";
private static final String C_P_212_48="visitedPlaces";
private static final String C_P_212_49="p";
private static final String C_P_212_50="java.lang.String";
private static final String C_P_212_51="{p}";
private static final String C_P_212_52="msg_prog,nbRemPkLevel";
private static final String C_P_212_53="nbRemainingNotMaxLevel";
private static final String C_P_212_54="msg_prog,nbRemPkHappiness";
private static final String C_P_212_55="nbRemainingNotMaxHappiness";
private static final String C_P_212_56="msg_prog,nbRemEgg";
private static final String C_P_212_57="nbRemainingEggs";
private static final String C_P_212_58="msg_prog,repel";
private static final String C_P_212_59="remainStepsRepel";
private static final String C_P_212_60="msg_prog,money";
private static final String C_P_212_61="money";
private PageProgGameprog(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc117){
Element elt0_=el(_doc117,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_212_0));
attrs0_.add(at(C_BEAN,C_P_212_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,HEAD);
Element elt2_=el(_doc117,TITLE);
Element elt3_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc117,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_212_3));
attrs2_.add(at(REL,C_P_212_4));
attrs2_.add(at(TYPE,C_P_212_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc117,BODY);
build0(elt5_,_doc117);
build1(elt5_,_doc117);
build2(elt5_,_doc117);
build3(elt5_,_doc117);
build4(elt5_,_doc117);
build5(elt5_,_doc117);
build6(elt5_,_doc117);
build7(elt5_,_doc117);
build8(elt5_,_doc117);
build9(elt5_,_doc117);
br(elt5_,_doc117);
build11(elt5_,_doc117);
br(elt5_,_doc117);
build13(elt5_,_doc117);
build14(elt5_,_doc117);
br(elt5_,_doc117);
build16(elt5_,_doc117);
build17(elt5_,_doc117);
br(elt5_,_doc117);
build19(elt5_,_doc117);
br(elt5_,_doc117);
build21(elt5_,_doc117);
br(elt5_,_doc117);
build23(elt5_,_doc117);
br(elt5_,_doc117);
build25(elt5_,_doc117);
br(elt5_,_doc117);
build27(elt5_,_doc117);
br(elt5_,_doc117);
ad(elt0_,elt5_);
_doc117.appendChild(elt0_);
}
static void build0(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_212_6));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,C_IMG);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(SRC,C_P_212_7));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Text txt0_=tx(_doc117,C_P_212_8);
ad(elt0_,txt0_);
Element elt2_=el(_doc117,C_IMG);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(SRC,C_P_212_9));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Text txt1_=tx(_doc117,C_P_212_10);
ad(elt0_,txt1_);
Element elt3_=el(_doc117,C_IMG);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(SRC,C_P_212_11));
at(elt3_,attrs3_);
ad(elt0_,elt3_);
Element elt4_=el(_doc117,BR);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_212_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_13));
at(elt1_,attrs1_);
Element elt2_=el(_doc117,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_212_14));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc117,C_IMG);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(SRC,C_P_212_15));
at(elt3_,attrs3_);
ad(elt0_,elt3_);
Element elt4_=el(_doc117,BR);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc117){
Element elt0_=el(_doc117,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_212_16));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_17));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc117,BR);
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc117){
Element elt0_=el(_doc117,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_212_18));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_19));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc117,BR);
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc117){
Element elt0_=el(_doc117,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_212_20));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_21));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc117,BR);
ad(_body,elt2_);
}
static void build5(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_22));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc117){
Element elt0_=el(_doc117,UL);
Element elt1_=el(_doc117,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(CLASSNAME,C_P_212_23));
attrs0_.add(at(LIST,C_P_212_24));
attrs0_.add(at(VAR,C_P_212_25));
at(elt1_,attrs0_);
Element elt2_=el(_doc117,LI);
Text txt0_=tx(_doc117,C_P_212_26);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc117){
Element elt0_=el(_doc117,BR);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_27));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc117){
Element elt0_=el(_doc117,UL);
Element elt1_=el(_doc117,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(CLASSNAME,C_P_212_28));
attrs0_.add(at(LIST,C_P_212_29));
attrs0_.add(at(VAR,C_P_212_30));
at(elt1_,attrs0_);
Element elt2_=el(_doc117,LI);
Text txt0_=tx(_doc117,C_P_212_31);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc117){
Element elt0_=el(_doc117,TABLE);
Element elt1_=el(_doc117,CAPTION);
Element elt2_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_32));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc117,THEAD);
Element elt4_=el(_doc117,TR);
Element elt5_=el(_doc117,TH);
Element elt6_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_33));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc117,TH);
Element elt8_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_212_34));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt9_=el(_doc117,TBODY);
Element elt10_=el(_doc117,C_FOR);
CustList<Attr> attrs3_=al(5);
attrs3_.add(at(KEY,C_P_212_35));
attrs3_.add(at(MAP,C_P_212_36));
attrs3_.add(at(VALUE,C_P_212_37));
attrs3_.add(at(KEYCLASSNAME,C_P_212_38));
attrs3_.add(at(VARCLASSNAME,C_P_212_39));
at(elt10_,attrs3_);
Element elt11_=el(_doc117,TR);
Element elt12_=el(_doc117,TD);
Text txt0_=tx(_doc117,C_P_212_40);
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc117,TD);
Text txt1_=tx(_doc117,C_P_212_41);
ad(elt13_,txt1_);
ad(elt11_,elt13_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_42));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc117){
Element elt0_=el(_doc117,UL);
Element elt1_=el(_doc117,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(LIST,C_P_212_43));
attrs0_.add(at(VAR,C_P_212_44));
attrs0_.add(at(CLASSNAME,C_P_212_45));
at(elt1_,attrs0_);
Element elt2_=el(_doc117,LI);
Text txt0_=tx(_doc117,C_P_212_46);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_47));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc117){
Element elt0_=el(_doc117,UL);
Element elt1_=el(_doc117,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(LIST,C_P_212_48));
attrs0_.add(at(VAR,C_P_212_49));
attrs0_.add(at(CLASSNAME,C_P_212_50));
at(elt1_,attrs0_);
Element elt2_=el(_doc117,LI);
Text txt0_=tx(_doc117,C_P_212_51);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_52));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_53));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_54));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_55));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build23(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_56));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_57));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_58));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_59));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build27(Element _body,Document _doc117){
Element elt0_=el(_doc117,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_212_60));
at(elt0_,attrs0_);
Element elt1_=el(_doc117,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_212_61));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
