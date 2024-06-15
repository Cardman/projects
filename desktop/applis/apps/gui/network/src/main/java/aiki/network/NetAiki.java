package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.*;
import cards.network.threads.Net;
import code.gui.initialize.AbstractSocket;
import code.maths.litteralcom.MathExpUtil;
import code.network.Exiting;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.util.*;

public final class NetAiki {

    public static final int NB_PLAYERS = 2;
    public static final int CLIENT_INDEX_ARRIVE = 0;
    public static final int CLIENT_INIT_TRADING = 1;
    public static final int CLIENT_READY = 2;
    public static final int SERVER_NEW_PLAYER = 0;
    public static final int SERVER_READY = 1;
    public static final char AIKI_SEP_0 = ':';
    public static final char AIKI_SEP_1 = ';';
    public static final char AIKI_SEP_2 = ',';
    public static final char AIKI_SEP_3 = '!';
    public static final char AIKI_SEP_4 = '*';
    public static final char AIKI_SEP_5 = '$';
    public static final char AIKI_SEP_6 = '=';
    public static final char AIKI_SEP_7 = '@';
    public static final char AIKI_SEP_8 = '&';
    public static final char AIKI_SEP_9 = '|';
    /** A used port for connections*/
    private static final int PORT = 668;

    private static final String POKEMON = "POKEMON";
    private final IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

    private final CustList<IntClientActLoopAiki> clientAct = new CustList<IntClientActLoopAiki>();
    private final CustList<IntServerActLoopAiki> serverActLoopAiki = new CustList<IntServerActLoopAiki>();
    public NetAiki(){
        clientAct.add(new ClientActLoopAikiIndexArrive());
        clientAct.add(new ClientActLoopAikiInitTrading());
        serverActLoopAiki.add(new ServerActLoopAikiPlayer());
    }

    public static String exportNewPlayer(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_NEW_PLAYER);
        out_.append(AIKI_SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportInitTrading() {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_INIT_TRADING);
        out_.append(AIKI_SEP_0);
        return out_.toString();
    }
    public static int getPort() {
        return PORT;
    }

    public static String getPokemon() {
        return POKEMON;
    }
    public static void loopClient(WindowNetWork _window, String _info, AbstractSocket _socket) {
        NetAikiRetrievedInfos ret_ = new NetAikiRetrievedInfos(_info);
        loopClient(_window, ret_, _socket);
    }

    public static void loopClient(WindowNetWork _window, NetAikiRetrievedInfos _ret, AbstractSocket _socket) {
        _window.getNetAiki().clientAct.get(_ret.getIndexAct()).loop(_window, _ret.getParts(), _socket);
    }
    public static void loopServer(String _info, NetAiki _instance, NetCommon _common) {
        NetAikiRetrievedInfos ret_ = new NetAikiRetrievedInfos(_info);
        if (ret_.getIndexAct() < 0) {
            return;
        }
        loopServer(ret_, _instance, _common);
    }

    public static void loopServer(NetAikiRetrievedInfos _ret, NetAiki _instance, NetCommon _common) {
        _instance.serverActLoopAiki.get(_ret.getIndexAct()).loop(_ret.getParts(),_instance, _common);
    }

    public static String exportIndexArrive(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_INDEX_ARRIVE);
        out_.append(AIKI_SEP_0);
        out_.append(_index);
        out_.append(AIKI_SEP_0);
        return out_.toString();
    }
    public static String escapeId(String _str) {
        StringBuilder sb_ = new StringBuilder();
        for (char c: _str.toCharArray()) {
            if (MathExpUtil.isWordChar(c)) {
                sb_.append(c);
            } else {
                sb_.append('\\');
                sb_.append(c);
            }
        }
        return sb_.toString();
    }
    public static String unscapeId(String _str) {
        StringBuilder sb_ = new StringBuilder();
        int len_ = _str.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _str.charAt(i_);
            if (ch_ == '\\') {
                i_++;
                sb_.append(_str.charAt(i_));
            } else {
                sb_.append(ch_);
            }
            i_++;
        }
        return sb_.toString();
    }
    public static void sendObjectInitTrading(AbstractSocket _socket) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.initTrading(), _socket);
    }

    public static void sendObject(AbstractSocket _socket, NetPokemon _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.netPokemon(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, PokemonPlayer _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.pokemonPlayer(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, Exiting _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.bye(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, IndexOfArrivingAiki _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.indexOfArrivingAiki(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, NewPlayerAiki _serializable) {
        if (Net.QUICK) {
            NetGroupFrame.trySendString(NetAiki.exportNewPlayer(_serializable.getIndex()), _socket);
            return;
        }
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.newPlayerAiki(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, CheckCompatibility _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.checkCompatibility(_serializable), _socket);
    }
    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(NetAiki _instance) {
        return _instance.checkCompatibility;
    }

}
