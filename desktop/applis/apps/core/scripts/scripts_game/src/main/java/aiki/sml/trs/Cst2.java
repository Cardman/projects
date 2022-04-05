package aiki.sml.trs;
public final class Cst2{
private static final String C_P="P";
private static final String C_L="L";
private static final String C_E="E";
private static final String C_F="F";
private static final String C_M="M";
private static final String C_R="R";
private Cst2(){}
static String tr(){
String f=C_P+"\tcaracferme(VAR__NIVEAU,1,1)+caracdroiteferme(VAR__NIVEAU,2)*(6/5*puis(VAR__NIVEAU,3)-15*puis(VAR__NIVEAU,2)+100*VAR__NIVEAU-140)\n";
f+=C_L+"\t5/4*puis(VAR__NIVEAU,3)\n";
f+=C_E+"\tcaracferme(VAR__NIVEAU,1,50)*puis(VAR__NIVEAU,3)*div(100-VAR__NIVEAU,50)+caracferme(VAR__NIVEAU,51,68)*puis(VAR__NIVEAU,3)*div((150-VAR__NIVEAU),100)+caracferme(VAR__NIVEAU,69,98)*puis(VAR__NIVEAU,3)*div(ent(div((1911-10*VAR__NIVEAU),3)),500)+caracferme(VAR__NIVEAU,99,110)*puis(VAR__NIVEAU,3)*div((160-VAR__NIVEAU),100)+(363*puis(VAR__NIVEAU,2)-75027*VAR__NIVEAU+4526170)*caracdroiteferme(VAR__NIVEAU,111)\n";
f+=C_F+"\tcaracferme(VAR__NIVEAU,1,15)*puis(VAR__NIVEAU,3)*div(24+ent(div(VAR__NIVEAU+1,3)),50)+caracferme(VAR__NIVEAU,16,35)*puis(VAR__NIVEAU,3)*div(14+VAR__NIVEAU,50)+caracdroiteferme(VAR__NIVEAU,36)*puis(VAR__NIVEAU,3)*div(32+ent(div(VAR__NIVEAU,2)),50)\n";
f+=C_M+"\tpuis(VAR__NIVEAU,3)\n";
f+=C_R+"\t4/5*puis(VAR__NIVEAU,3)\n";
return f;
}
}
