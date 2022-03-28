package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
public abstract class PageCardsCommon{
protected static final String BODY="body";
protected static final String BORDER="border";
protected static final String BR="br";
protected static final String CAPTION="caption";
protected static final String CLASSNAME="className";
protected static final String CONDITION="condition";
protected static final String C_BEAN="c:bean";
protected static final String C_FOR="c:for";
protected static final String C_IF="c:if";
protected static final String C_MESSAGE="c:message";
protected static final String H1="h1";
protected static final String HEAD="head";
protected static final String HREF="href";
protected static final String HTML="html";
protected static final String KEY="key";
protected static final String KEYCLASSNAME="keyClassName";
protected static final String LI="li";
protected static final String LINK="link";
protected static final String LIST="list";
protected static final String MAP="map";
protected static final String P="p";
protected static final String PARAM="param";
protected static final String REL="rel";
protected static final String TABLE="table";
protected static final String TBODY="tbody";
protected static final String TD="td";
protected static final String THEAD="thead";
protected static final String TITLE="title";
protected static final String TR="tr";
protected static final String TYPE="type";
protected static final String UL="ul";
protected static final String VALUE="value";
protected static final String VAR="var";
protected static final String VARCLASSNAME="varClassName";
protected static final String XMLNS_C="xmlns:c";
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
}