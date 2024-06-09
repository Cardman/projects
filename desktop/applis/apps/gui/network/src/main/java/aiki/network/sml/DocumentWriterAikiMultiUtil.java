package aiki.network.sml;
import aiki.db.ExchangedData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.map.pokemon.PokemonPlayer;
import aiki.network.stream.*;
import aiki.sml.DocumentWriterAikiCoreUtil;
import code.network.Exiting;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.ByteTreeMap;
import code.util.EntryCust;
import code.util.StringMap;

public final class DocumentWriterAikiMultiUtil {

    public static final String FIELD_ABILITIES = "0";
    public static final String FIELD_ACCEPTABLE = "1";
    public static final String FIELD_ARRIVING = "2";
    public static final String FIELD_BUSY = "3";
    public static final String FIELD_CLOSING = "4";
    public static final String FIELD_DATA = "5";
    public static final String FIELD_FORCED = "6";
    public static final String FIELD_GENDER_REPARTITIONS = "7";
    public static final String FIELD_INDEX = "8";
    public static final String FIELD_INDEX_TEAM = "9";
    public static final String FIELD_ITEMS = "10";
    public static final String FIELD_LANGUAGE = "11";
    public static final String FIELD_LOCALE = "12";
    public static final String FIELD_PLACE = "13";
    public static final String FIELD_POKEMON = "14";
    public static final String FIELD_PSEUDO = "15";
    public static final String FIELD_READY = "16";
    public static final String FIELD_SERVER = "17";
    public static final String FIELD_TEAM = "18";
    public static final String FIELD_TOO_MANY_PLAYERS = "19";
    public static final String FIELD_TRADABLE_POKEMON = "20";
    public static final String TYPE_BYE = "Bye";
    public static final String TYPE_CHECK_COMPATIBILITY = "CheckCompatibility";
    public static final String TYPE_EXCHANGED_DATA = "ExchangedData";
    public static final String TYPE_INDEX_OF_ARRIVING = "IndexOfArriving";
    public static final String TYPE_INIT_TRADING = "InitTrading";
    public static final String TYPE_NET_POKEMON = "NetPokemon";
    public static final String TYPE_NEW_PLAYER = "NewPlayer";
    public static final String TYPE_OK = "Ok";
    public static final String TYPE_PLAYER_ACTION_BEFORE_GAME = "PlayerActionBeforeGame";
    public static final String TYPE_PLAYER_ACTION_GAME = "PlayerActionGame";
    public static final String TYPE_QUIT = "Quit";
    public static final String TYPE_READY = "Ready";
    public static final String TYPE_SENT_POKEMON = "SentPokemon";
    private DocumentWriterAikiMultiUtil() {
    }


    public static String initTrading() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_INIT_TRADING);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static String ok() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(TYPE_OK);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static String sentPokemon(SentPokemon _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setSentPokemon(_object, "", doc_));
        return doc_.export();
    }

    public static String playerActionGameAiki(PlayerActionGameAiki _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionGame(_object, "", doc_));
        return doc_.export();
    }

    public static String playerActionBeforeGameAiki(PlayerActionBeforeGameAiki _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionBeforeGame(_object, "", doc_));
        return doc_.export();
    }

    public static String newPlayerAiki(NewPlayerAiki _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionBeforeGame(_object, "", doc_));
        return doc_.export();
    }

    public static String netPokemon(NetPokemon _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setNetPokemon(_object, "", doc_));
        return doc_.export();
    }

    public static String indexOfArrivingAiki(IndexOfArrivingAiki _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPlayerActionBeforeGame(_object, "", doc_));
        return doc_.export();
    }

    public static String checkCompatibility(CheckCompatibility _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setCheckCompatibility(_object, "", doc_));
        return doc_.export();
    }

    public static String bye(Exiting _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setBye(_object, "", doc_));
        return doc_.export();
    }

    public static String pokemonPlayer(PokemonPlayer _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(DocumentWriterAikiCoreUtil.setPokemonPlayer(_object, "", doc_,DocumentWriterAikiCoreUtil.TYPE_POKEMON_PLAYER));
        return doc_.export();
    }

    private static Element setExchangedData(ExchangedData _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EXCHANGED_DATA);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setExchangedData(_object,element_,_document);
        return element_;
    }

    private static void setExchangedData(ExchangedData _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAbilities(),FIELD_ABILITIES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(setStringMapGenderRepartition(_object.getGenderRepartitions(),FIELD_GENDER_REPARTITIONS,_document));
        _element.appendChild(DocumentWriterAikiCoreUtil.setPokemonPlayer(_object.getPokemon(),FIELD_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndexTeam(),FIELD_INDEX_TEAM,_document));
    }

    private static Element setBye(Exiting _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BYE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBye(_object,element_,_document);
        return element_;
    }

    private static void setBye(Exiting _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isForced(),FIELD_FORCED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTooManyPlayers(),FIELD_TOO_MANY_PLAYERS,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBusy(),FIELD_BUSY,_document));
    }

    private static Element setCheckCompatibility(CheckCompatibility _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_CHECK_COMPATIBILITY);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setCheckCompatibility(_object,element_,_document);
        return element_;
    }

    private static void setCheckCompatibility(CheckCompatibility _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(setExchangedData(_object.getData(),FIELD_DATA,_document));
        _element.appendChild(DocumentWriterAikiCoreUtil.setListUsablePokemon(_object.getTeam(),FIELD_TEAM,_document));
    }

    private static Element setNetPokemon(NetPokemon _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_NET_POKEMON);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setNetPokemon(_object,element_,_document);
        return element_;
    }

    private static void setNetPokemon(NetPokemon _object, Element _element, Document _document) {
        _element.appendChild(setMapBytePokemonPlayer(_object.getTradablePokemon(),FIELD_TRADABLE_POKEMON,_document));
    }

    private static void setNewPlayer(NewPlayerAiki _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isArriving(),FIELD_ARRIVING,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLanguage(),FIELD_LANGUAGE,_document));
//        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAcceptable(),FIELD_ACCEPTABLE,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setPlayerActionBeforeGame(PlayerActionBeforeGameAiki _object, String _fieldName, Document _document) {
        if (_object instanceof IndexOfArrivingAiki) {
            Element element_ = _document.createElement(TYPE_INDEX_OF_ARRIVING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionBeforeGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof NewPlayerAiki) {
            Element element_ = _document.createElement(TYPE_NEW_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setNewPlayer((NewPlayerAiki)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ReadyAiki) {
            Element element_ = _document.createElement(TYPE_READY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setReady((ReadyAiki)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_BEFORE_GAME);
    }

    private static void setPlayerActionBeforeGame(PlayerActionBeforeGameAiki _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndex(),FIELD_INDEX,_document));
    }

    private static Element setPlayerActionGame(PlayerActionGameAiki _object, String _fieldName, Document _document) {
        if (_object instanceof QuitAiki) {
            Element element_ = _document.createElement(TYPE_QUIT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setQuit((QuitAiki)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_GAME);
    }

    private static void setPlayerActionGame(PlayerActionGameAiki _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlace(),FIELD_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLocale(),FIELD_LOCALE,_document));
    }

    private static void setQuit(QuitAiki _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setReady(ReadyAiki _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReady(),FIELD_READY,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setSentPokemon(SentPokemon _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_SENT_POKEMON);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setSentPokemon(_object,element_,_document);
        return element_;
    }

    private static void setSentPokemon(SentPokemon _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getIndex(),FIELD_INDEX,_document));
        _element.appendChild(DocumentWriterAikiCoreUtil.setPokemonPlayer(_object.getPokemon(),FIELD_POKEMON,_document));
    }

    public static Element setMapBytePokemonPlayer(ByteTreeMap<PokemonPlayer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, PokemonPlayer> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), DocumentWriterAikiCoreUtil.EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterAikiCoreUtil.setPokemonPlayer(s.getValue(), DocumentWriterAikiCoreUtil.EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapGenderRepartition(StringMap<GenderRepartition> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, GenderRepartition> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), DocumentWriterAikiCoreUtil.EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterAikiCoreUtil.setGenderRepartition(s.getValue(), DocumentWriterAikiCoreUtil.EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }
}
