package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapLevel extends PageAikiCommon{
private static final String C_P_141_0="javahtml";
private static final String C_P_141_1="level_map";
private static final String C_P_141_2="web/css/pokedex.css";
private static final String C_P_141_3="stylesheet";
private static final String C_P_141_4="text/css";
private static final String C_P_141_5="possibleMultiLayer";
private static final String C_P_141_6="msg_levelmap,title_level_place";
private static final String C_P_141_7="placeName";
private static final String C_P_141_8="levelIndex";
private static final String C_P_141_9="!possibleMultiLayer";
private static final String C_P_141_10="outside";
private static final String C_P_141_11="road";
private static final String C_P_141_12="msg_levelmap,title_out_road";
private static final String C_P_141_13="placeName";
private static final String C_P_141_14="!road";
private static final String C_P_141_15="msg_levelmap,title_out_city";
private static final String C_P_141_16="placeName";
private static final String C_P_141_17="!outside";
private static final String C_P_141_18="gym";
private static final String C_P_141_19="msg_levelmap,title_gym";
private static final String C_P_141_20="placeName";
private static final String C_P_141_21="pokemonCenter";
private static final String C_P_141_22="msg_levelmap,title_pk_center";
private static final String C_P_141_23="placeName";
private static final String C_P_141_24=GO_TO_IND;
private static final String C_P_141_25="";
private static final String C_P_141_26="msg_levelmap,index";
private static final String C_P_141_27=GO_TO_MAP;
private static final String C_P_141_28="";
private static final String C_P_141_29="msg_levelmap,map";
private static final String C_P_141_30="{getMapWidth()}";
private static final String C_P_141_31="p";
private static final String C_P_141_32="tiles";
private static final String C_P_141_33="b";
private static final String C_P_141_34="java.lang.Object";
private static final String C_P_141_35="java.lang.String";
private static final String C_P_141_36="withoutTitle(([p]))";
private static final String C_P_141_37="!isAccessibleByBeatingSomeTrainers(([p]))";
private static final String C_P_141_38="$clickTileOnMap({([p])})";
private static final String C_P_141_39="0";
private static final String C_P_141_40="{b}";
private static final String C_P_141_41="isAccessibleByBeatingSomeTrainers(([p]))";
private static final String C_P_141_42="$clickTileOnMap({([p])})";
private static final String C_P_141_43="msg_levelmap,title_access";
private static final String C_P_141_44="0";
private static final String C_P_141_45="{b}";
private static final String C_P_141_46="isStorage(([p]))";
private static final String C_P_141_47="$clickTileOnMap({([p])})";
private static final String C_P_141_48="msg_levelmap,title_storage";
private static final String C_P_141_49="0";
private static final String C_P_141_50="{b}";
private static final String C_P_141_51="isHealer(([p]))";
private static final String C_P_141_52="$clickTileOnMap({([p])})";
private static final String C_P_141_53="msg_levelmap,title_heal";
private static final String C_P_141_54="0";
private static final String C_P_141_55="{b}";
private static final String C_P_141_56="isHost(([p]))";
private static final String C_P_141_57="$clickTileOnMap({([p])})";
private static final String C_P_141_58="msg_levelmap,title_host";
private static final String C_P_141_59="0";
private static final String C_P_141_60="{b}";
private static final String C_P_141_61="isFossile(([p]))";
private static final String C_P_141_62="$clickTileOnMap({([p])})";
private static final String C_P_141_63="msg_levelmap,title_fossile";
private static final String C_P_141_64="0";
private static final String C_P_141_65="{b}";
private static final String C_P_141_66="isMoveTutors(([p]))";
private static final String C_P_141_67="$clickTileOnMap({([p])})";
private static final String C_P_141_68="msg_levelmap,title_mt";
private static final String C_P_141_69="0";
private static final String C_P_141_70="{b}";
private static final String C_P_141_71="proponeTile";
private static final String C_P_141_72="$clickTile";
private static final String C_P_141_73="msg_levelmap,click_tile";
private static final String C_P_141_74="proponeLink";
private static final String C_P_141_75="$clickLink";
private static final String C_P_141_76="msg_levelmap,click_link";
private static final String C_P_141_77="seeArea";
private static final String C_P_141_78="$seeArea";
private static final String C_P_141_79="msg_levelmap,see_area";
private static final String C_P_141_80="!dirs.isEmpty()";
private static final String C_P_141_81="d";
private static final String C_P_141_82="java.lang.Object";
private static final String C_P_141_83="java.lang.Object";
private static final String C_P_141_84="dirs";
private static final String C_P_141_85="e";
private static final String C_P_141_86="isUp(([d]))";
private static final String C_P_141_87="$clickDirectedLink({([d])})";
private static final String C_P_141_88="msg_levelmap,UP";
private static final String C_P_141_89="isDown(([d]))";
private static final String C_P_141_90="$clickDirectedLink({([d])})";
private static final String C_P_141_91="msg_levelmap,DOWN";
private static final String C_P_141_92="isLeft(([d]))";
private static final String C_P_141_93="$clickDirectedLink({([d])})";
private static final String C_P_141_94="msg_levelmap,LEFT";
private static final String C_P_141_95="isRight(([d]))";
private static final String C_P_141_96="$clickDirectedLink({([d])})";
private static final String C_P_141_97="msg_levelmap,RIGHT";
private PageDataMapLevel(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc46){
Element elt0_=el(_doc46,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_141_0));
attrs0_.add(at(C_BEAN,C_P_141_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,HEAD);
Element elt2_=el(_doc46,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_141_2));
attrs1_.add(at(REL,C_P_141_3));
attrs1_.add(at(TYPE,C_P_141_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc46,TITLE);
Element elt4_=el(_doc46,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_141_5));
at(elt4_,attrs2_);
Element elt5_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_141_6));
at(elt5_,attrs3_);
Element elt6_=el(_doc46,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_141_7));
at(elt6_,attrs4_);
ad(elt5_,elt6_);
Element elt7_=el(_doc46,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_141_8));
at(elt7_,attrs5_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt8_=el(_doc46,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_141_9));
at(elt8_,attrs6_);
Element elt9_=el(_doc46,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_141_10));
at(elt9_,attrs7_);
Element elt10_=el(_doc46,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_141_11));
at(elt10_,attrs8_);
Element elt11_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_141_12));
at(elt11_,attrs9_);
Element elt12_=el(_doc46,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_141_13));
at(elt12_,attrs10_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt13_=el(_doc46,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_141_14));
at(elt13_,attrs11_);
Element elt14_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_141_15));
at(elt14_,attrs12_);
Element elt15_=el(_doc46,PARAM);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_141_16));
at(elt15_,attrs13_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt9_,elt13_);
ad(elt8_,elt9_);
Element elt16_=el(_doc46,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_141_17));
at(elt16_,attrs14_);
Element elt17_=el(_doc46,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_141_18));
at(elt17_,attrs15_);
Element elt18_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_141_19));
at(elt18_,attrs16_);
Element elt19_=el(_doc46,PARAM);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_141_20));
at(elt19_,attrs17_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Element elt20_=el(_doc46,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_141_21));
at(elt20_,attrs18_);
Element elt21_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_141_22));
at(elt21_,attrs19_);
Element elt22_=el(_doc46,PARAM);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_141_23));
at(elt22_,attrs20_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt16_,elt20_);
ad(elt8_,elt16_);
ad(elt3_,elt8_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt23_=el(_doc46,BODY);
build0(elt23_,_doc46);
build1(elt23_,_doc46);
build2(elt23_,_doc46);
build3(elt23_,_doc46);
build4(elt23_,_doc46);
build5(elt23_,_doc46);
build6(elt23_,_doc46);
build7(elt23_,_doc46);
ad(elt0_,elt23_);
_doc46.appendChild(elt0_);
}
static void build0(Element _body,Document _doc46){
Element elt0_=el(_doc46,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_141_24));
attrs0_.add(at(HREF,C_P_141_25));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_141_26));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc46,BR);
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc46){
Element elt0_=el(_doc46,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_141_27));
attrs0_.add(at(HREF,C_P_141_28));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_141_29));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc46,BR);
ad(_body,elt2_);
}
static void build2(Element _body,Document _doc46){
Element elt0_=el(_doc46,MAP);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(WIDTH,C_P_141_30));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,C_FOR);
CustList<Attr> attrs1_=al(5);
attrs1_.add(at(KEY,C_P_141_31));
attrs1_.add(at(MAP,C_P_141_32));
attrs1_.add(at(VALUE,C_P_141_33));
attrs1_.add(at(KEYCLASSNAME,C_P_141_34));
attrs1_.add(at(VARCLASSNAME,C_P_141_35));
at(elt1_,attrs1_);
Element elt2_=el(_doc46,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_141_36));
at(elt2_,attrs2_);
Element elt3_=el(_doc46,C_IF);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(CONDITION,C_P_141_37));
at(elt3_,attrs3_);
Element elt4_=el(_doc46,A);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(C_COMMAND,C_P_141_38));
at(elt4_,attrs4_);
Element elt5_=el(_doc46,C_IMG);
CustList<Attr> attrs5_=al(2);
attrs5_.add(at(BORDER,C_P_141_39));
attrs5_.add(at(SRC,C_P_141_40));
at(elt5_,attrs5_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt6_=el(_doc46,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_141_41));
at(elt6_,attrs6_);
Element elt7_=el(_doc46,C_A);
CustList<Attr> attrs7_=al(2);
attrs7_.add(at(C_COMMAND,C_P_141_42));
attrs7_.add(at(VALUE,C_P_141_43));
at(elt7_,attrs7_);
Element elt8_=el(_doc46,C_IMG);
CustList<Attr> attrs8_=al(2);
attrs8_.add(at(BORDER,C_P_141_44));
attrs8_.add(at(SRC,C_P_141_45));
at(elt8_,attrs8_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt2_,elt6_);
ad(elt1_,elt2_);
Element elt9_=el(_doc46,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_141_46));
at(elt9_,attrs9_);
Element elt10_=el(_doc46,C_A);
CustList<Attr> attrs10_=al(2);
attrs10_.add(at(C_COMMAND,C_P_141_47));
attrs10_.add(at(VALUE,C_P_141_48));
at(elt10_,attrs10_);
Element elt11_=el(_doc46,C_IMG);
CustList<Attr> attrs11_=al(2);
attrs11_.add(at(BORDER,C_P_141_49));
attrs11_.add(at(SRC,C_P_141_50));
at(elt11_,attrs11_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt1_,elt9_);
Element elt12_=el(_doc46,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_141_51));
at(elt12_,attrs12_);
Element elt13_=el(_doc46,C_A);
CustList<Attr> attrs13_=al(2);
attrs13_.add(at(C_COMMAND,C_P_141_52));
attrs13_.add(at(VALUE,C_P_141_53));
at(elt13_,attrs13_);
Element elt14_=el(_doc46,C_IMG);
CustList<Attr> attrs14_=al(2);
attrs14_.add(at(BORDER,C_P_141_54));
attrs14_.add(at(SRC,C_P_141_55));
at(elt14_,attrs14_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt1_,elt12_);
Element elt15_=el(_doc46,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_141_56));
at(elt15_,attrs15_);
Element elt16_=el(_doc46,C_A);
CustList<Attr> attrs16_=al(2);
attrs16_.add(at(C_COMMAND,C_P_141_57));
attrs16_.add(at(VALUE,C_P_141_58));
at(elt16_,attrs16_);
Element elt17_=el(_doc46,C_IMG);
CustList<Attr> attrs17_=al(2);
attrs17_.add(at(BORDER,C_P_141_59));
attrs17_.add(at(SRC,C_P_141_60));
at(elt17_,attrs17_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt1_,elt15_);
Element elt18_=el(_doc46,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_141_61));
at(elt18_,attrs18_);
Element elt19_=el(_doc46,C_A);
CustList<Attr> attrs19_=al(2);
attrs19_.add(at(C_COMMAND,C_P_141_62));
attrs19_.add(at(VALUE,C_P_141_63));
at(elt19_,attrs19_);
Element elt20_=el(_doc46,C_IMG);
CustList<Attr> attrs20_=al(2);
attrs20_.add(at(BORDER,C_P_141_64));
attrs20_.add(at(SRC,C_P_141_65));
at(elt20_,attrs20_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
ad(elt1_,elt18_);
Element elt21_=el(_doc46,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_141_66));
at(elt21_,attrs21_);
Element elt22_=el(_doc46,C_A);
CustList<Attr> attrs22_=al(2);
attrs22_.add(at(C_COMMAND,C_P_141_67));
attrs22_.add(at(VALUE,C_P_141_68));
at(elt22_,attrs22_);
Element elt23_=el(_doc46,C_IMG);
CustList<Attr> attrs23_=al(2);
attrs23_.add(at(BORDER,C_P_141_69));
attrs23_.add(at(SRC,C_P_141_70));
at(elt23_,attrs23_);
ad(elt22_,elt23_);
ad(elt21_,elt22_);
ad(elt1_,elt21_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc46){
Element elt0_=el(_doc46,BR);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc46){
Element elt0_=el(_doc46,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_141_71));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_141_72));
at(elt1_,attrs1_);
Element elt2_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_141_73));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc46,BR);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc46){
Element elt0_=el(_doc46,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_141_74));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_141_75));
at(elt1_,attrs1_);
Element elt2_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_141_76));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc46,BR);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc46){
Element elt0_=el(_doc46,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_141_77));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_141_78));
at(elt1_,attrs1_);
Element elt2_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_141_79));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc46,BR);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc46){
Element elt0_=el(_doc46,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_141_80));
at(elt0_,attrs0_);
Element elt1_=el(_doc46,UL);
Element elt2_=el(_doc46,C_FOR);
CustList<Attr> attrs1_=al(5);
attrs1_.add(at(KEY,C_P_141_81));
attrs1_.add(at(KEYCLASSNAME,C_P_141_82));
attrs1_.add(at(VARCLASSNAME,C_P_141_83));
attrs1_.add(at(MAP,C_P_141_84));
attrs1_.add(at(VALUE,C_P_141_85));
at(elt2_,attrs1_);
Element elt3_=el(_doc46,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_141_86));
at(elt3_,attrs2_);
Element elt4_=el(_doc46,LI);
Element elt5_=el(_doc46,A);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(C_COMMAND,C_P_141_87));
at(elt5_,attrs3_);
Element elt6_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_141_88));
at(elt6_,attrs4_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt7_=el(_doc46,C_IF);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(CONDITION,C_P_141_89));
at(elt7_,attrs5_);
Element elt8_=el(_doc46,LI);
Element elt9_=el(_doc46,A);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(C_COMMAND,C_P_141_90));
at(elt9_,attrs6_);
Element elt10_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_141_91));
at(elt10_,attrs7_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
Element elt11_=el(_doc46,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_141_92));
at(elt11_,attrs8_);
Element elt12_=el(_doc46,LI);
Element elt13_=el(_doc46,A);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(C_COMMAND,C_P_141_93));
at(elt13_,attrs9_);
Element elt14_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_141_94));
at(elt14_,attrs10_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt2_,elt11_);
Element elt15_=el(_doc46,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_141_95));
at(elt15_,attrs11_);
Element elt16_=el(_doc46,LI);
Element elt17_=el(_doc46,A);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(C_COMMAND,C_P_141_96));
at(elt17_,attrs12_);
Element elt18_=el(_doc46,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_141_97));
at(elt18_,attrs13_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt2_,elt15_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
