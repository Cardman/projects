package code.scripts.confs;

import code.scripts.pages.cards.PageCardsCommon;
import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;

public final class BeanPageCardsSample extends PageCardsCommon {
    public BeanPageCardsSample self() {
        return this;
    }
    public static Attr at(String _name, String _value){
        return CoreDocument.createAttribute(_name,_value);
    }
    public static void at(Element _elt, CustList<Attr> _ls){
        _elt.setAttributes(new NamedNodeMap(_ls));
    }
    public static CustList<Attr> al(int _len){
        return new CustList<Attr>(new CollCapacity(_len));
    }
    public static Text tx(Document _doc, String _v){
        return _doc.createEscapedTextNode(_v);
    }
    public static Element el(Document _doc,String _value){
        return _doc.createElement(_value);
    }
    public static void ad(Element _elt,Node _value){
        _elt.appendChild(_value);
    }
}
