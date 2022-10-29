package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
public abstract class PageCardsCommon{
protected static final String A="a";
protected static final String ACTION="action";
protected static final String B="b";
protected static final String BODY="body";
protected static final String BORDER="border";
protected static final String BR="br";
protected static final String CAPTION="caption";
protected static final String CLASS="class";
protected static final String CLASSNAME="className";
protected static final String CONDITION="condition";
protected static final String C_A="c:a";
protected static final String C_BEAN="c:bean";
protected static final String C_CLASS="c:class";
protected static final String C_COMMAND="c:command";
protected static final String C_ELSE="c:else";
protected static final String C_ELSEIF="c:elseif";
protected static final String C_FIELD="c:field";
protected static final String C_FOR="c:for";
protected static final String C_GROUPID="c:groupId";
protected static final String C_IF="c:if";
protected static final String C_IMG="c:img";
protected static final String C_IMPORT="c:import";
protected static final String C_MESSAGE="c:message";
protected static final String C_PACKAGE="c:package";
protected static final String C_SELECT="c:select";
protected static final String C_SUBMIT="c:submit";
protected static final String C_VALIDATOR="c:validator";
protected static final String C_VALUEMESSAGE="c:valueMessage";
protected static final String C_VARVALUE="c:varValue";
protected static final String DEFAULT="default";
protected static final String FIELDSET="fieldset";
protected static final String FORM="form";
protected static final String H1="h1";
protected static final String H2="h2";
protected static final String H3="h3";
protected static final String HEAD="head";
protected static final String HR="hr";
protected static final String HREF="href";
protected static final String HTML="html";
protected static final String ID="id";
protected static final String INDEXCLASSNAME="indexClassName";
protected static final String INPUT="input";
protected static final String KEY="key";
protected static final String KEYCLASSNAME="keyClassName";
protected static final String LI="li";
protected static final String LINK="link";
protected static final String LIST="list";
protected static final String MAP="map";
protected static final String MESSAGE="message";
protected static final String METHOD="method";
protected static final String NAME="name";
protected static final String OL="ol";
protected static final String P="p";
protected static final String PAGE="page";
protected static final String PARAM="param";
protected static final String PREPARE="prepare";
protected static final String QUOTED="quoted";
protected static final String REL="rel";
protected static final String SPAN="span";
protected static final String SRC="src";
protected static final String STYLE="style";
protected static final String TABLE="table";
protected static final String TBODY="tbody";
protected static final String TD="td";
protected static final String TH="th";
protected static final String THEAD="thead";
protected static final String TITLE="title";
protected static final String TR="tr";
protected static final String TYPE="type";
protected static final String UL="ul";
protected static final String UPDATE="update";
protected static final String VALUE="value";
protected static final String VAR="var";
protected static final String VARCLASSNAME="varClassName";
protected static final String VARVALUE="varValue";
protected static final String WIDTH="width";
protected static final String XMLNS_C="xmlns:c";
protected static final String GO_TO_ENDROUND="$ge()";
protected static final String GO_TO_IND="$gi()";
protected static final String GO_TO_LEVEL = "$gl()";
protected static final String GO_TO_MAP = "$gm()";
protected static final String GO_TO_SIMULATION = "$gs()";
protected static final String DIFF = "{d}";
protected PageCardsCommon(){}
public static Attr at(String _name,String _value){
return CoreDocument.createAttribute(_name,_value);
}
public static void at(Element _elt,CustList<Attr> _ls){
_elt.setAttributes(new NamedNodeMap(_ls));
}
public static CustList<Attr> al(int _len){
return new CustList<Attr>(new CollCapacity(_len));
}
public static Text tx(Document _doc,String _v){
return _doc.createEscapedTextNode(_v);
}
public static Element el(Document _doc,String _value){
return _doc.createElement(_value);
}
public static void ad(Element _elt,Node _value){
_elt.appendChild(_value);
}
public static void br(Element _elt,Document _doc){
_elt.appendChild(_doc.createElement(BR));
}
}