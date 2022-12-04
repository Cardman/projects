package aiki.network.sml;
import aiki.db.ExchangedData;
import aiki.network.stream.*;
import aiki.sml.DocumentReaderAikiCoreUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.core.StringUtil;

public final class DocumentReaderAikiMultiUtil {

    public static final String TYPE_CHECK_COMPATIBILITY = "CheckCompatibility";
    public static final String TYPE_SENT_POKEMON = "SentPokemon";
    public static final String TYPE_OK = "Ok";
    public static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    public static final String TYPE_INIT_TRADING = "InitTrading";
    public static final String TYPE_NET_POKEMON = "NetPokemon";
    public static final String TYPE_POKEMON_PLAYER = "PokemonPlayer";
    private static final String ATTR_FIELD = "field";
    private static final char DOT = '.';
    private static final String FIELD_ABILITIES = "abilities";
    private static final String FIELD_ACCEPTABLE = "acceptable";
    private static final String FIELD_ARRIVING = "arriving";
    private static final String FIELD_BUSY = "busy";
    private static final String FIELD_CLOSING = "closing";
    private static final String FIELD_DATA = "data";
    private static final String FIELD_FORCED = "forced";
    private static final String FIELD_GENDER_REPARTITIONS = "genderRepartitions";
    private static final String FIELD_INDEX = "index";
    private static final String FIELD_INDEX_TEAM = "indexTeam";
    private static final String FIELD_ITEMS = "items";
    private static final String FIELD_LANGUAGE = "language";
    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_PLACE = "place";
    private static final String FIELD_POKEMON = "pokemon";
    private static final String FIELD_PSEUDO = "pseudo";
    private static final String FIELD_READY = "ready";
    private static final String FIELD_SERVER = "server";
    private static final String FIELD_TEAM = "team";
    private static final String FIELD_TOO_MANY_PLAYERS = "tooManyPlayers";
    private static final String FIELD_TRADABLE_POKEMON = "tradablePokemon";
    private static final String TYPE_EXCHANGED_DATA = "ExchangedData";
    private static final String TYPE_BYE = "Bye";
    private static final String TYPE_NEW_PLAYER = "NewPlayer";
    private static final String TYPE_PLAYER_ACTION_BEFORE_GAME = "PlayerActionBeforeGame";
    private static final String TYPE_PLAYER_ACTION_GAME = "PlayerActionGame";
    private static final String TYPE_QUIT = "Quit";
    private static final String TYPE_READY = "Ready";

    public static String tagName(Element _elt) {
        String tagName_ = _elt.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT) + 1);
        return tagName_;
    }
    public static Document getDoc(String _input) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_input);
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = elt_.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringUtil.quickEq(tagName_, TYPE_POKEMON_PLAYER) || StringUtil.quickEq(tagName_, TYPE_EXCHANGED_DATA) || StringUtil.quickEq(tagName_, TYPE_OK) || StringUtil.quickEq(tagName_, TYPE_INIT_TRADING) || StringUtil.quickEq(tagName_, TYPE_BYE) || StringUtil.quickEq(tagName_, TYPE_CHECK_COMPATIBILITY) || StringUtil.quickEq(tagName_, TYPE_INDEX_OF_ARRIVING) || StringUtil.quickEq(tagName_, TYPE_NET_POKEMON) || StringUtil.quickEq(tagName_, TYPE_NEW_PLAYER) || StringUtil.quickEq(tagName_, TYPE_PLAYER_ACTION_BEFORE_GAME) || StringUtil.quickEq(tagName_, TYPE_PLAYER_ACTION_GAME) || StringUtil.quickEq(tagName_, TYPE_QUIT) || StringUtil.quickEq(tagName_, TYPE_READY) || StringUtil.quickEq(tagName_, TYPE_SENT_POKEMON)) {
            return doc_;
        }
        return null;
    }
    public static Exiting getExiting(Document _input) {
        Element elt_ = _input.getDocumentElement();
        String tagName_ = elt_.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringUtil.quickEq(tagName_, TYPE_BYE)) {
            return getBye(elt_);
        }
        return null;
    }

    public static ExchangedData getExchangedData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ExchangedData object_ = new ExchangedData();
        for (Element c: childElements_) {
            getExchangedData(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getExchangedData(ExchangedData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_GENDER_REPARTITIONS)) {
            _object.setGenderRepartitions(DocumentReaderAikiCoreUtil.getStringMapGenderRepartition(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX_TEAM)) {
            _object.setIndexTeam(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static Exiting getBye(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Exiting object_ = new Exiting();
        for (Element c: childElements_) {
            getBye(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getBye(Exiting _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_FORCED)) {
            _object.setForced(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TOO_MANY_PLAYERS)) {
            _object.setTooManyPlayers(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_BUSY)) {
            _object.setBusy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static CheckCompatibility getCheckCompatibility(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CheckCompatibility object_ = new CheckCompatibility();
        for (Element c: childElements_) {
            getCheckCompatibility(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getCheckCompatibility(CheckCompatibility _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_DATA)) {
            _object.setData(getExchangedData(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(DocumentReaderAikiCoreUtil.getListUsablePokemon(_element));
            return;
        }
    }

    public static NetPokemon getNetPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        NetPokemon object_ = new NetPokemon();
        for (Element c: childElements_) {
            getNetPokemon(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getNetPokemon(NetPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_TRADABLE_POKEMON)) {
            _object.setTradablePokemon(DocumentReaderAikiCoreUtil.getMapBytePokemonPlayer(_element));
            return;
        }
    }

    private static void getNewPlayer(NewPlayerAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ARRIVING)) {
            _object.setArriving(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LANGUAGE)) {
            _object.setLanguage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_ACCEPTABLE)) {
            _object.setAcceptable(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static PlayerActionBeforeGameAiki getPlayerActionBeforeGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringUtil.quickEq(tagName_,TYPE_INDEX_OF_ARRIVING)) {
            IndexOfArrivingAiki object_ = new IndexOfArrivingAiki();
            for (Element c: childElements_) {
                getPlayerActionBeforeGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_NEW_PLAYER)) {
            NewPlayerAiki object_ = new NewPlayerAiki();
            for (Element c: childElements_) {
                getNewPlayer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,TYPE_READY)) {
            ReadyAiki object_ = new ReadyAiki();
            for (Element c: childElements_) {
                getReady(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionBeforeGame(PlayerActionBeforeGameAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static PlayerActionGameAiki getPlayerActionGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringUtil.quickEq(tagName_,TYPE_QUIT)) {
            QuitAiki object_ = new QuitAiki();
            for (Element c: childElements_) {
                getQuit(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return null;
    }

    private static void getPlayerActionGame(PlayerActionGameAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_LOCALE)) {
            _object.setLocale(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getQuit(QuitAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getReady(ReadyAiki _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_READY)) {
            _object.setReady(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    public static SentPokemon getSentPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        SentPokemon object_ = new SentPokemon();
        for (Element c: childElements_) {
            getSentPokemon(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getSentPokemon(SentPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
    }

}