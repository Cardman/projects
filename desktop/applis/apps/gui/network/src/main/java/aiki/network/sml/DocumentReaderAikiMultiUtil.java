package aiki.network.sml;
import aiki.db.ExchangedData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.pokemon.PokemonPlayer;
import aiki.network.stream.*;
import aiki.sml.DocumentReaderAikiCoreUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderAikiMultiUtil {

    private DocumentReaderAikiMultiUtil() {
    }
    public static String tagName(Element _elt) {
        return _elt.getTagName();
    }
    public static Document getDoc(String _input) {
        return DocumentBuilder.parseNoTextDocument(_input);
//        Document doc_ = DocumentBuilder.parseNoTextDocument(_input);
//        Element elt_ = doc_.getDocumentElement();
//        String tagName_ = elt_.getTagName();
//        if (StringUtil.quickEq(tagName_, DocumentWriterAikiCoreUtil.TYPE_POKEMON_PLAYER) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_EXCHANGED_DATA) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_OK) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_INIT_TRADING) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_BYE) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_CHECK_COMPATIBILITY) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_INDEX_OF_ARRIVING) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_NET_POKEMON) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_NEW_PLAYER) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_PLAYER_ACTION_BEFORE_GAME) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_PLAYER_ACTION_GAME) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_QUIT) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_READY) || StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_SENT_POKEMON)) {
//            return doc_;
//        }
//        return null;
    }
    public static Exiting getExiting(Document _input) {
        Element elt_ = _input.getDocumentElement();
        String tagName_ = elt_.getTagName();
        if (StringUtil.quickEq(tagName_, DocumentWriterAikiMultiUtil.TYPE_BYE)) {
            return getBye(elt_);
        }
        return null;
    }

    public static ExchangedData getExchangedData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ExchangedData object_ = new ExchangedData();
        for (Element c: childElements_) {
            getExchangedData(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getExchangedData(ExchangedData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_GENDER_REPARTITIONS)) {
            _object.setGenderRepartitions(getStringMapGenderRepartition(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_INDEX_TEAM)) {
            _object.setIndexTeam(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static Exiting getBye(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Exiting object_ = new Exiting();
        for (Element c: childElements_) {
            getBye(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getBye(Exiting _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_FORCED)) {
            _object.setForced(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_TOO_MANY_PLAYERS)) {
            _object.setTooManyPlayers(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_BUSY)) {
            _object.setBusy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static CheckCompatibility getCheckCompatibility(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CheckCompatibility object_ = new CheckCompatibility();
        for (Element c: childElements_) {
            getCheckCompatibility(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getCheckCompatibility(CheckCompatibility _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_DATA)) {
            _object.setData(getExchangedData(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_TEAM)) {
            _object.setTeam(DocumentReaderAikiCoreUtil.getListUsablePokemon(_element));
            return;
        }
    }

    public static NetPokemon getNetPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        NetPokemon object_ = new NetPokemon();
        for (Element c: childElements_) {
            getNetPokemon(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getNetPokemon(NetPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_TRADABLE_POKEMON)) {
            _object.setTradablePokemon(getMapBytePokemonPlayer(_element));
            return;
        }
    }

    private static void getNewPlayer(NewPlayerAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_ARRIVING)) {
            _object.setArriving(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_LANGUAGE)) {
            _object.setLanguage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_ACCEPTABLE)) {
            _object.setAcceptable(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static PlayerActionBeforeGameAiki getPlayerActionBeforeGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiMultiUtil.TYPE_INDEX_OF_ARRIVING)) {
            IndexOfArrivingAiki object_ = new IndexOfArrivingAiki();
            for (Element c: childElements_) {
                getPlayerActionBeforeGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiMultiUtil.TYPE_NEW_PLAYER)) {
            NewPlayerAiki object_ = new NewPlayerAiki();
            for (Element c: childElements_) {
                getNewPlayer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiMultiUtil.TYPE_READY)) {
            ReadyAiki object_ = new ReadyAiki();
            for (Element c: childElements_) {
                getReady(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionBeforeGame(PlayerActionBeforeGameAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static PlayerActionGameAiki getPlayerActionGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiMultiUtil.TYPE_QUIT)) {
            QuitAiki object_ = new QuitAiki();
            for (Element c: childElements_) {
                getQuit(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionGame(PlayerActionGameAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_LOCALE)) {
            _object.setLocale(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getQuit(QuitAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getReady(ReadyAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_READY)) {
            _object.setReady(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static SentPokemon getSentPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        SentPokemon object_ = new SentPokemon();
        for (Element c: childElements_) {
            getSentPokemon(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getSentPokemon(SentPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiMultiUtil.FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
    }

    public static StringMap<GenderRepartition> getStringMapGenderRepartition(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<GenderRepartition> map_ = new StringMap<GenderRepartition>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<GenderRepartition> values_ = new CustList<GenderRepartition>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(DocumentReaderAikiCoreUtil.getGenderRepartition(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static ByteTreeMap<PokemonPlayer> getMapBytePokemonPlayer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteTreeMap<PokemonPlayer> map_ = new ByteTreeMap<PokemonPlayer>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<PokemonPlayer> values_ = new CustList<PokemonPlayer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(DocumentReaderAikiCoreUtil.getPokemonPlayer(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
}