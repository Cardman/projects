package aiki.network.sml;
import aiki.db.ExchangedData;
import aiki.network.stream.Bye;
import aiki.network.stream.CheckCompatibility;
import aiki.network.stream.IndexOfArriving;
import aiki.network.stream.NetPokemon;
import aiki.network.stream.NewPlayer;
import aiki.network.stream.PlayerActionBeforeGame;
import aiki.network.stream.PlayerActionGame;
import aiki.network.stream.Quit;
import aiki.network.stream.Ready;
import aiki.network.stream.SentPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.StringList;
public final class DocumentReaderAikiMultiUtil {

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
    private static final String TYPE_CHECK_COMPATIBILITY = "CheckCompatibility";
    private static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    private static final String TYPE_NET_POKEMON = "NetPokemon";
    private static final String TYPE_NEW_PLAYER = "NewPlayer";
    private static final String TYPE_PLAYER_ACTION_BEFORE_GAME = "PlayerActionBeforeGame";
    private static final String TYPE_PLAYER_ACTION_GAME = "PlayerActionGame";
    private static final String TYPE_QUIT = "Quit";
    private static final String TYPE_READY = "Ready";
    private static final String TYPE_SENT_POKEMON = "SentPokemon";
    private static final String TYPE_POKEMON_PLAYER = "PokemonPlayer";

    public static Object getObject(String _input) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_input);
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = elt_.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_, TYPE_POKEMON_PLAYER)) {
            return DocumentReaderAikiCoreUtil.getPokemonPlayer(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_EXCHANGED_DATA)) {
            return getExchangedData(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_BYE)) {
            return getBye(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_CHECK_COMPATIBILITY)) {
            return getCheckCompatibility(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_INDEX_OF_ARRIVING)) {
            return getPlayerActionBeforeGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_NET_POKEMON)) {
            return getNetPokemon(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_NEW_PLAYER)) {
            return getPlayerActionBeforeGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_PLAYER_ACTION_BEFORE_GAME)) {
            return getPlayerActionBeforeGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_PLAYER_ACTION_GAME)) {
            return getPlayerActionGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_QUIT)) {
            return getPlayerActionGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_READY)) {
            return getPlayerActionBeforeGame(elt_);
        }
        if (StringList.quickEq(tagName_, TYPE_SENT_POKEMON)) {
            return getSentPokemon(elt_);
        }
        return new Bye();
    }

    private static ExchangedData getExchangedData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ExchangedData object_ = new ExchangedData();
        for (Element c: childElements_) {
            getExchangedData(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getExchangedData(ExchangedData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER_REPARTITIONS)) {
            _object.setGenderRepartitions(DocumentReaderAikiCoreUtil.getStringMapGenderRepartition(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INDEX_TEAM)) {
            _object.setIndexTeam(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static Bye getBye(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Bye object_ = new Bye();
        for (Element c: childElements_) {
            getBye(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getBye(Bye _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FORCED)) {
            _object.setForced(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TOO_MANY_PLAYERS)) {
            _object.setTooManyPlayers(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BUSY)) {
            _object.setBusy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static CheckCompatibility getCheckCompatibility(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CheckCompatibility object_ = new CheckCompatibility();
        for (Element c: childElements_) {
            getCheckCompatibility(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getCheckCompatibility(CheckCompatibility _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DATA)) {
            _object.setData(getExchangedData(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(DocumentReaderAikiCoreUtil.getListUsablePokemon(_element));
            return;
        }
    }

    private static NetPokemon getNetPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        NetPokemon object_ = new NetPokemon();
        for (Element c: childElements_) {
            getNetPokemon(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getNetPokemon(NetPokemon _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TRADABLE_POKEMON)) {
            _object.setTradablePokemon(DocumentReaderAikiCoreUtil.getMapBytePokemonPlayer(_element));
            return;
        }
    }

    private static void getNewPlayer(NewPlayer _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PSEUDO)) {
            _object.setPseudo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ARRIVING)) {
            _object.setArriving(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LANGUAGE)) {
            _object.setLanguage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACCEPTABLE)) {
            _object.setAcceptable(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    private static PlayerActionBeforeGame getPlayerActionBeforeGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_INDEX_OF_ARRIVING)) {
            IndexOfArriving object_ = new IndexOfArriving();
            for (Element c: childElements_) {
                getPlayerActionBeforeGame(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_NEW_PLAYER)) {
            NewPlayer object_ = new NewPlayer();
            for (Element c: childElements_) {
                getNewPlayer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_READY)) {
            Ready object_ = new Ready();
            for (Element c: childElements_) {
                getReady(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return new Ready();
    }

    private static void getPlayerActionBeforeGame(PlayerActionBeforeGame _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static PlayerActionGame getPlayerActionGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_QUIT)) {
            Quit object_ = new Quit();
            for (Element c: childElements_) {
                getQuit(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return new Quit();
    }

    private static void getPlayerActionGame(PlayerActionGame _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOCALE)) {
            _object.setLocale(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getQuit(Quit _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CLOSING)) {
            _object.setClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SERVER)) {
            _object.setServer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionGame(_object, _fieldName, _element);
    }

    private static void getReady(Ready _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_READY)) {
            _object.setReady(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getPlayerActionBeforeGame(_object, _fieldName, _element);
    }

    private static SentPokemon getSentPokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        SentPokemon object_ = new SentPokemon();
        for (Element c: childElements_) {
            getSentPokemon(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getSentPokemon(SentPokemon _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_INDEX)) {
            _object.setIndex(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderAikiCoreUtil.getPokemonPlayer(_element));
            return;
        }
    }

}