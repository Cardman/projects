package aiki.sml.trs;
public final class Trs9{
private Trs9(){}
static String tr(){
String e=Cst4.CS_DECROISSANT+"\tlowing\n";
e+=Cst4.CS_CROISSANT+"\tgrowing\n";
e+=Cst4.CS_CONSTANT_MAX+"\tmax value\n";
e+=Cst4.CS_UNIFORME+"\tuniform\n";
e+=Cst4.CS_CONSTANT_MIN+"\tmin value\n";
return e;
}
}
