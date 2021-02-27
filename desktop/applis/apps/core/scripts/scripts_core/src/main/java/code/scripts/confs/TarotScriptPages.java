package code.scripts.confs;

import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;

public final class TarotScriptPages {

    public static final String STR = "java.lang.String";
    public static final String CFG = "cfg";
    public static final String CLASS = "class";
    public static final String FIELD = "field";
    public static final String FIRST_URL = "firstUrl";
    public static final String VALUE = "value";
    public static final String PREFIX = "prefix";
    public static final String BEANS = "beans";
    public static final String FORMS = "forms";
    public static final String SCOPE = "scope";
    public static final String REQUEST = "request";
    public static final String CLASS_NAME = "className";
    public static final String NAVIGATION = "navigation";
    public static final String MESSAGES_FOLDER = "messagesFolder";
    public static final String RESOURCES_CARDS_MESSAGES = "resources_cards/messages";
    public static final String DATA_BASE_CLASS_NAME = "dataBaseClassName";
    public static final String PROPERTIES = "properties";
    public static final String MESSAGES_TAROT = "messages_tarot";
    public static final String ADDED_FILES = "addedFiles";
    public static final String CSS = "resources_cards/css/tarot.css";
    public static final String SM = "sm";
    public static final String MAP = "m";
    public static final String SL = "sl";
    public static final String STR_ELT = "str";
    public static final String KEY = "key";
    public static final String MSG = "msg";
    public static final String PRE_VALUE = "c";
    public static final String BEAN = "b";

    private TarotScriptPages() {
    }
    public static FullDocument info0(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement(CFG);
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement(STR);
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs1_.add(CoreDocument.createAttribute(FIELD, FIRST_URL));
        attrs1_.add(CoreDocument.createAttribute(VALUE,"resources_cards/classes/cards/tarot/detailsresults.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        Element elt2_=doc_.createElement(STR);
        CustList<Attr> attrs2_=new CustList<Attr>(new CollCapacity(3));
        attrs2_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs2_.add(CoreDocument.createAttribute(FIELD, PREFIX));
        attrs2_.add(CoreDocument.createAttribute(VALUE, PRE_VALUE));
        elt2_.setAttributes(new NamedNodeMap(attrs2_));
        elt0_.appendChild(elt2_);
        Element elt3_=doc_.createElement(SM);
        CustList<Attr> attrs3_=new CustList<Attr>(new CollCapacity(2));
        attrs3_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs3_.add(CoreDocument.createAttribute(FIELD, BEANS));
        elt3_.setAttributes(new NamedNodeMap(attrs3_));
        Element elt4_=doc_.createElement(STR);
        CustList<Attr> attrs4_=new CustList<Attr>(new CollCapacity(3));
        attrs4_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs4_.add(CoreDocument.createAttribute(KEY,""));
        attrs4_.add(CoreDocument.createAttribute(VALUE,"details"));
        elt4_.setAttributes(new NamedNodeMap(attrs4_));
        elt3_.appendChild(elt4_);
        Element elt5_=doc_.createElement(BEAN);
        CustList<Attr> attrs5_=new CustList<Attr>(new CollCapacity(1));
        attrs5_.add(CoreDocument.createAttribute(CLASS, MAP));
        elt5_.setAttributes(new NamedNodeMap(attrs5_));
        Element elt6_=doc_.createElement("null");
        CustList<Attr> attrs6_=new CustList<Attr>(new CollCapacity(2));
        attrs6_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs6_.add(CoreDocument.createAttribute(FIELD, FORMS));
        elt6_.setAttributes(new NamedNodeMap(attrs6_));
        elt5_.appendChild(elt6_);
        Element elt7_=doc_.createElement(STR);
        CustList<Attr> attrs7_=new CustList<Attr>(new CollCapacity(3));
        attrs7_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs7_.add(CoreDocument.createAttribute(FIELD, SCOPE));
        attrs7_.add(CoreDocument.createAttribute(VALUE, REQUEST));
        elt7_.setAttributes(new NamedNodeMap(attrs7_));
        elt5_.appendChild(elt7_);
        Element elt8_=doc_.createElement(STR);
        CustList<Attr> attrs8_=new CustList<Attr>(new CollCapacity(3));
        attrs8_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs8_.add(CoreDocument.createAttribute(FIELD, CLASS_NAME));
        attrs8_.add(CoreDocument.createAttribute(VALUE,"cards.tarot.beans.DetailsResultsTarotBean"));
        elt8_.setAttributes(new NamedNodeMap(attrs8_));
        elt5_.appendChild(elt8_);
        elt3_.appendChild(elt5_);
        elt0_.appendChild(elt3_);
        Element elt9_=doc_.createElement(SM);
        CustList<Attr> attrs9_=new CustList<Attr>(new CollCapacity(2));
        attrs9_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs9_.add(CoreDocument.createAttribute(FIELD, NAVIGATION));
        elt9_.setAttributes(new NamedNodeMap(attrs9_));
        elt0_.appendChild(elt9_);
        Element elt10_=doc_.createElement(STR);
        CustList<Attr> attrs10_=new CustList<Attr>(new CollCapacity(3));
        attrs10_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs10_.add(CoreDocument.createAttribute(FIELD, MESSAGES_FOLDER));
        attrs10_.add(CoreDocument.createAttribute(VALUE, RESOURCES_CARDS_MESSAGES));
        elt10_.setAttributes(new NamedNodeMap(attrs10_));
        elt0_.appendChild(elt10_);
        Element elt11_=doc_.createElement(STR);
        CustList<Attr> attrs11_=new CustList<Attr>(new CollCapacity(3));
        attrs11_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs11_.add(CoreDocument.createAttribute(FIELD, DATA_BASE_CLASS_NAME));
        attrs11_.add(CoreDocument.createAttribute(VALUE,"ResultsTarot"));
        elt11_.setAttributes(new NamedNodeMap(attrs11_));
        elt0_.appendChild(elt11_);
        Element elt12_=doc_.createElement(SM);
        CustList<Attr> attrs12_=new CustList<Attr>(new CollCapacity(2));
        attrs12_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs12_.add(CoreDocument.createAttribute(FIELD, PROPERTIES));
        elt12_.setAttributes(new NamedNodeMap(attrs12_));
        Element elt13_=doc_.createElement(STR);
        CustList<Attr> attrs13_=new CustList<Attr>(new CollCapacity(3));
        attrs13_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs13_.add(CoreDocument.createAttribute(KEY,""));
        attrs13_.add(CoreDocument.createAttribute(VALUE, MSG));
        elt13_.setAttributes(new NamedNodeMap(attrs13_));
        elt12_.appendChild(elt13_);
        Element elt14_=doc_.createElement(STR);
        CustList<Attr> attrs14_=new CustList<Attr>(new CollCapacity(2));
        attrs14_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs14_.add(CoreDocument.createAttribute(VALUE, MESSAGES_TAROT));
        elt14_.setAttributes(new NamedNodeMap(attrs14_));
        elt12_.appendChild(elt14_);
        elt0_.appendChild(elt12_);
        Element elt15_=doc_.createElement(SL);
        CustList<Attr> attrs15_=new CustList<Attr>(new CollCapacity(1));
        attrs15_.add(CoreDocument.createAttribute(FIELD, ADDED_FILES));
        elt15_.setAttributes(new NamedNodeMap(attrs15_));
        Element elt16_=doc_.createElement(STR_ELT);
        CustList<Attr> attrs16_=new CustList<Attr>(new CollCapacity(1));
        attrs16_.add(CoreDocument.createAttribute(VALUE, CSS));
        elt16_.setAttributes(new NamedNodeMap(attrs16_));
        elt15_.appendChild(elt16_);
        elt0_.appendChild(elt15_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    public static FullDocument info1(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement(CFG);
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement(STR);
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs1_.add(CoreDocument.createAttribute(FIELD, FIRST_URL));
        attrs1_.add(CoreDocument.createAttribute(VALUE,"resources_cards/classes/cards/tarot/results.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        Element elt2_=doc_.createElement(STR);
        CustList<Attr> attrs2_=new CustList<Attr>(new CollCapacity(3));
        attrs2_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs2_.add(CoreDocument.createAttribute(FIELD, PREFIX));
        attrs2_.add(CoreDocument.createAttribute(VALUE, PRE_VALUE));
        elt2_.setAttributes(new NamedNodeMap(attrs2_));
        elt0_.appendChild(elt2_);
        Element elt3_=doc_.createElement(SM);
        CustList<Attr> attrs3_=new CustList<Attr>(new CollCapacity(2));
        attrs3_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs3_.add(CoreDocument.createAttribute(FIELD, BEANS));
        elt3_.setAttributes(new NamedNodeMap(attrs3_));
        Element elt4_=doc_.createElement(STR);
        CustList<Attr> attrs4_=new CustList<Attr>(new CollCapacity(3));
        attrs4_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs4_.add(CoreDocument.createAttribute(KEY,""));
        attrs4_.add(CoreDocument.createAttribute(VALUE,"results"));
        elt4_.setAttributes(new NamedNodeMap(attrs4_));
        elt3_.appendChild(elt4_);
        Element elt5_=doc_.createElement(BEAN);
        CustList<Attr> attrs5_=new CustList<Attr>(new CollCapacity(1));
        attrs5_.add(CoreDocument.createAttribute(CLASS, MAP));
        elt5_.setAttributes(new NamedNodeMap(attrs5_));
        Element elt6_=doc_.createElement("null");
        CustList<Attr> attrs6_=new CustList<Attr>(new CollCapacity(2));
        attrs6_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs6_.add(CoreDocument.createAttribute(FIELD, FORMS));
        elt6_.setAttributes(new NamedNodeMap(attrs6_));
        elt5_.appendChild(elt6_);
        Element elt7_=doc_.createElement(STR);
        CustList<Attr> attrs7_=new CustList<Attr>(new CollCapacity(3));
        attrs7_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs7_.add(CoreDocument.createAttribute(FIELD, SCOPE));
        attrs7_.add(CoreDocument.createAttribute(VALUE, REQUEST));
        elt7_.setAttributes(new NamedNodeMap(attrs7_));
        elt5_.appendChild(elt7_);
        Element elt8_=doc_.createElement(STR);
        CustList<Attr> attrs8_=new CustList<Attr>(new CollCapacity(3));
        attrs8_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs8_.add(CoreDocument.createAttribute(FIELD, CLASS_NAME));
        attrs8_.add(CoreDocument.createAttribute(VALUE,"cards.tarot.beans.ResultsTarotBean"));
        elt8_.setAttributes(new NamedNodeMap(attrs8_));
        elt5_.appendChild(elt8_);
        elt3_.appendChild(elt5_);
        elt0_.appendChild(elt3_);
        Element elt9_=doc_.createElement(SM);
        CustList<Attr> attrs9_=new CustList<Attr>(new CollCapacity(2));
        attrs9_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs9_.add(CoreDocument.createAttribute(FIELD, NAVIGATION));
        elt9_.setAttributes(new NamedNodeMap(attrs9_));
        elt0_.appendChild(elt9_);
        Element elt10_=doc_.createElement(STR);
        CustList<Attr> attrs10_=new CustList<Attr>(new CollCapacity(3));
        attrs10_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs10_.add(CoreDocument.createAttribute(FIELD, MESSAGES_FOLDER));
        attrs10_.add(CoreDocument.createAttribute(VALUE, RESOURCES_CARDS_MESSAGES));
        elt10_.setAttributes(new NamedNodeMap(attrs10_));
        elt0_.appendChild(elt10_);
        Element elt11_=doc_.createElement(STR);
        CustList<Attr> attrs11_=new CustList<Attr>(new CollCapacity(3));
        attrs11_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs11_.add(CoreDocument.createAttribute(FIELD, DATA_BASE_CLASS_NAME));
        attrs11_.add(CoreDocument.createAttribute(VALUE,"ResultsTarot"));
        elt11_.setAttributes(new NamedNodeMap(attrs11_));
        elt0_.appendChild(elt11_);
        Element elt12_=doc_.createElement(SM);
        CustList<Attr> attrs12_=new CustList<Attr>(new CollCapacity(2));
        attrs12_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs12_.add(CoreDocument.createAttribute(FIELD, PROPERTIES));
        elt12_.setAttributes(new NamedNodeMap(attrs12_));
        Element elt13_=doc_.createElement(STR);
        CustList<Attr> attrs13_=new CustList<Attr>(new CollCapacity(3));
        attrs13_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs13_.add(CoreDocument.createAttribute(KEY,""));
        attrs13_.add(CoreDocument.createAttribute(VALUE, MSG));
        elt13_.setAttributes(new NamedNodeMap(attrs13_));
        elt12_.appendChild(elt13_);
        Element elt14_=doc_.createElement(STR);
        CustList<Attr> attrs14_=new CustList<Attr>(new CollCapacity(2));
        attrs14_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs14_.add(CoreDocument.createAttribute(VALUE, MESSAGES_TAROT));
        elt14_.setAttributes(new NamedNodeMap(attrs14_));
        elt12_.appendChild(elt14_);
        elt0_.appendChild(elt12_);
        Element elt15_=doc_.createElement(SL);
        CustList<Attr> attrs15_=new CustList<Attr>(new CollCapacity(1));
        attrs15_.add(CoreDocument.createAttribute(FIELD, ADDED_FILES));
        elt15_.setAttributes(new NamedNodeMap(attrs15_));
        Element elt16_=doc_.createElement(STR_ELT);
        CustList<Attr> attrs16_=new CustList<Attr>(new CollCapacity(1));
        attrs16_.add(CoreDocument.createAttribute(VALUE, CSS));
        elt16_.setAttributes(new NamedNodeMap(attrs16_));
        elt15_.appendChild(elt16_);
        elt0_.appendChild(elt15_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    public static FullDocument info2(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement(CFG);
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement(STR);
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs1_.add(CoreDocument.createAttribute(FIELD, FIRST_URL));
        attrs1_.add(CoreDocument.createAttribute(VALUE,"resources_cards/classes/cards/tarot/rules.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        Element elt2_=doc_.createElement(STR);
        CustList<Attr> attrs2_=new CustList<Attr>(new CollCapacity(3));
        attrs2_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs2_.add(CoreDocument.createAttribute(FIELD, PREFIX));
        attrs2_.add(CoreDocument.createAttribute(VALUE, PRE_VALUE));
        elt2_.setAttributes(new NamedNodeMap(attrs2_));
        elt0_.appendChild(elt2_);
        Element elt3_=doc_.createElement(SM);
        CustList<Attr> attrs3_=new CustList<Attr>(new CollCapacity(2));
        attrs3_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs3_.add(CoreDocument.createAttribute(FIELD, BEANS));
        elt3_.setAttributes(new NamedNodeMap(attrs3_));
        Element elt4_=doc_.createElement(STR);
        CustList<Attr> attrs4_=new CustList<Attr>(new CollCapacity(3));
        attrs4_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs4_.add(CoreDocument.createAttribute(KEY,""));
        attrs4_.add(CoreDocument.createAttribute(VALUE,"rules"));
        elt4_.setAttributes(new NamedNodeMap(attrs4_));
        elt3_.appendChild(elt4_);
        Element elt5_=doc_.createElement(BEAN);
        CustList<Attr> attrs5_=new CustList<Attr>(new CollCapacity(1));
        attrs5_.add(CoreDocument.createAttribute(CLASS, MAP));
        elt5_.setAttributes(new NamedNodeMap(attrs5_));
        Element elt6_=doc_.createElement("null");
        CustList<Attr> attrs6_=new CustList<Attr>(new CollCapacity(2));
        attrs6_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs6_.add(CoreDocument.createAttribute(FIELD, FORMS));
        elt6_.setAttributes(new NamedNodeMap(attrs6_));
        elt5_.appendChild(elt6_);
        Element elt7_=doc_.createElement(STR);
        CustList<Attr> attrs7_=new CustList<Attr>(new CollCapacity(3));
        attrs7_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs7_.add(CoreDocument.createAttribute(FIELD, SCOPE));
        attrs7_.add(CoreDocument.createAttribute(VALUE, REQUEST));
        elt7_.setAttributes(new NamedNodeMap(attrs7_));
        elt5_.appendChild(elt7_);
        Element elt8_=doc_.createElement(STR);
        CustList<Attr> attrs8_=new CustList<Attr>(new CollCapacity(3));
        attrs8_.add(CoreDocument.createAttribute(CLASS, BEAN));
        attrs8_.add(CoreDocument.createAttribute(FIELD, CLASS_NAME));
        attrs8_.add(CoreDocument.createAttribute(VALUE,"cards.tarot.beans.RulesTarotBean"));
        elt8_.setAttributes(new NamedNodeMap(attrs8_));
        elt5_.appendChild(elt8_);
        elt3_.appendChild(elt5_);
        elt0_.appendChild(elt3_);
        Element elt9_=doc_.createElement(SM);
        CustList<Attr> attrs9_=new CustList<Attr>(new CollCapacity(2));
        attrs9_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs9_.add(CoreDocument.createAttribute(FIELD, NAVIGATION));
        elt9_.setAttributes(new NamedNodeMap(attrs9_));
        elt0_.appendChild(elt9_);
        Element elt10_=doc_.createElement(STR);
        CustList<Attr> attrs10_=new CustList<Attr>(new CollCapacity(3));
        attrs10_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs10_.add(CoreDocument.createAttribute(FIELD, MESSAGES_FOLDER));
        attrs10_.add(CoreDocument.createAttribute(VALUE, RESOURCES_CARDS_MESSAGES));
        elt10_.setAttributes(new NamedNodeMap(attrs10_));
        elt0_.appendChild(elt10_);
        Element elt11_=doc_.createElement(STR);
        CustList<Attr> attrs11_=new CustList<Attr>(new CollCapacity(3));
        attrs11_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs11_.add(CoreDocument.createAttribute(FIELD, DATA_BASE_CLASS_NAME));
        attrs11_.add(CoreDocument.createAttribute(VALUE,"RulesTarot"));
        elt11_.setAttributes(new NamedNodeMap(attrs11_));
        elt0_.appendChild(elt11_);
        Element elt12_=doc_.createElement(SM);
        CustList<Attr> attrs12_=new CustList<Attr>(new CollCapacity(2));
        attrs12_.add(CoreDocument.createAttribute(CLASS, CFG));
        attrs12_.add(CoreDocument.createAttribute(FIELD, PROPERTIES));
        elt12_.setAttributes(new NamedNodeMap(attrs12_));
        Element elt13_=doc_.createElement(STR);
        CustList<Attr> attrs13_=new CustList<Attr>(new CollCapacity(3));
        attrs13_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs13_.add(CoreDocument.createAttribute(KEY,""));
        attrs13_.add(CoreDocument.createAttribute(VALUE, MSG));
        elt13_.setAttributes(new NamedNodeMap(attrs13_));
        elt12_.appendChild(elt13_);
        Element elt14_=doc_.createElement(STR);
        CustList<Attr> attrs14_=new CustList<Attr>(new CollCapacity(2));
        attrs14_.add(CoreDocument.createAttribute(CLASS, MAP));
        attrs14_.add(CoreDocument.createAttribute(VALUE, MESSAGES_TAROT));
        elt14_.setAttributes(new NamedNodeMap(attrs14_));
        elt12_.appendChild(elt14_);
        elt0_.appendChild(elt12_);
        Element elt15_=doc_.createElement(SL);
        CustList<Attr> attrs15_=new CustList<Attr>(new CollCapacity(1));
        attrs15_.add(CoreDocument.createAttribute(FIELD, ADDED_FILES));
        elt15_.setAttributes(new NamedNodeMap(attrs15_));
        Element elt16_=doc_.createElement(STR_ELT);
        CustList<Attr> attrs16_=new CustList<Attr>(new CollCapacity(1));
        attrs16_.add(CoreDocument.createAttribute(VALUE, CSS));
        elt16_.setAttributes(new NamedNodeMap(attrs16_));
        elt15_.appendChild(elt16_);
        elt0_.appendChild(elt15_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    public static StringMap<FullDocument> infos(){
        StringMap<FullDocument> map_ = new StringMap<FullDocument>(new CollCapacity(3));
        map_.addEntry("resources_cards/conf/details_results_tarot.xml",info0());
        map_.addEntry("resources_cards/conf/results_tarot.xml",info1());
        map_.addEntry("resources_cards/conf/rules_tarot.xml",info2());
        return map_;
    }

}
