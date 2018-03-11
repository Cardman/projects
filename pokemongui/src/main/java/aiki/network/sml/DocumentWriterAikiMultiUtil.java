package aiki.network.sml;
import aiki.ExchangedData;
import aiki.map.pokemon.PokemonPlayer;
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
import aiki.sml.DocumentWriterAikiCoreUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentWriterCoreUtil;
import code.sml.Element;
public final class DocumentWriterAikiMultiUtil {

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


    public static String setObject(Object _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        if (_object instanceof PokemonPlayer) {
            doc_.appendChild(DocumentWriterAikiCoreUtil.setPokemonPlayer((PokemonPlayer)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof ExchangedData) {
            doc_.appendChild(setExchangedData((ExchangedData)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Bye) {
            doc_.appendChild(setBye((Bye)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof CheckCompatibility) {
            doc_.appendChild(setCheckCompatibility((CheckCompatibility)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof IndexOfArriving) {
            doc_.appendChild(setPlayerActionBeforeGame((IndexOfArriving)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof NetPokemon) {
            doc_.appendChild(setNetPokemon((NetPokemon)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof NewPlayer) {
            doc_.appendChild(setPlayerActionBeforeGame((NewPlayer)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayerActionBeforeGame) {
            doc_.appendChild(setPlayerActionBeforeGame((PlayerActionBeforeGame)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof PlayerActionGame) {
            doc_.appendChild(setPlayerActionGame((PlayerActionGame)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Quit) {
            doc_.appendChild(setPlayerActionGame((Quit)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof Ready) {
            doc_.appendChild(setPlayerActionBeforeGame((Ready)_object, "", doc_));
            return doc_.export();
        }
        if (_object instanceof SentPokemon) {
            doc_.appendChild(setSentPokemon((SentPokemon)_object, "", doc_));
            return doc_.export();
        }
        return null;
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
        _element.appendChild(DocumentWriterAikiCoreUtil.setStringMapGenderRepartition(_object.getGenderRepartitions(),FIELD_GENDER_REPARTITIONS,_document));
        _element.appendChild(DocumentWriterAikiCoreUtil.setPokemonPlayer(_object.getPokemon(),FIELD_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndexTeam(),FIELD_INDEX_TEAM,_document));
    }

    private static Element setBye(Bye _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BYE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBye(_object,element_,_document);
        return element_;
    }

    private static void setBye(Bye _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isForced(),FIELD_FORCED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTooManyPlayers(),FIELD_TOO_MANY_PLAYERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBusy(),FIELD_BUSY,_document));
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
        _element.appendChild(DocumentWriterAikiCoreUtil.setMapBytePokemonPlayer(_object.getTradablePokemon(),FIELD_TRADABLE_POKEMON,_document));
    }

    private static void setNewPlayer(NewPlayer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPseudo(),FIELD_PSEUDO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isArriving(),FIELD_ARRIVING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLanguage(),FIELD_LANGUAGE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAcceptable(),FIELD_ACCEPTABLE,_document));
        setPlayerActionBeforeGame(_object, _element, _document);
    }

    private static Element setPlayerActionBeforeGame(PlayerActionBeforeGame _object, String _fieldName, Document _document) {
        if (_object instanceof IndexOfArriving) {
            Element element_ = _document.createElement(TYPE_INDEX_OF_ARRIVING);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPlayerActionBeforeGame(_object,element_,_document);
            return element_;
        }
        if (_object instanceof NewPlayer) {
            Element element_ = _document.createElement(TYPE_NEW_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setNewPlayer((NewPlayer)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Ready) {
            Element element_ = _document.createElement(TYPE_READY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setReady((Ready)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_BEFORE_GAME);
    }

    private static void setPlayerActionBeforeGame(PlayerActionBeforeGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndex(),FIELD_INDEX,_document));
    }

    private static Element setPlayerActionGame(PlayerActionGame _object, String _fieldName, Document _document) {
        if (_object instanceof Quit) {
            Element element_ = _document.createElement(TYPE_QUIT);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setQuit((Quit)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLAYER_ACTION_GAME);
    }

    private static void setPlayerActionGame(PlayerActionGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlace(),FIELD_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLocale(),FIELD_LOCALE,_document));
    }

    private static void setQuit(Quit _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClosing(),FIELD_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isServer(),FIELD_SERVER,_document));
        setPlayerActionGame(_object, _element, _document);
    }

    private static void setReady(Ready _object, Element _element, Document _document) {
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

}
