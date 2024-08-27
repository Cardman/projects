package aiki.network;

import aiki.db.ExchangedData;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.UsesOfMove;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.network.stream.*;
import code.gui.initialize.AbstractSocket;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class NetAiki {

    public static final int NB_PLAYERS = 2;
    public static final int CLIENT_INDEX_ARRIVE = 0;
    public static final int CLIENT_INIT_TRADING = 1;
    public static final int CLIENT_NET_PK = 2;
    public static final int CLIENT_POKEMON_PLAYER = 3;
    public static final int CLIENT_OK = 4;
    public static final int SERVER_NEW_PLAYER = 0;
    public static final int SERVER_CHECK_COMPATIBILITY = 1;
    public static final int SERVER_SENT_POKEMON = 2;
    public static final int SERVER_READY = 3;
    public static final int SERVER_OK = 4;
    public static final int SERVER_QUIT = 5;
    public static final char AIKI_SEP_0 = ':';
    public static final char AIKI_SEP_1 = ';';
    public static final char AIKI_SEP_2 = ',';
    public static final char AIKI_SEP_3 = '!';
    public static final char AIKI_SEP_4 = '*';
    public static final char AIKI_SEP_5 = '@';
    public static final char AIKI_SEP_6 = '=';
    public static final char AIKI_SEP_7 = '^';
    public static final char AIKI_SEP_8 = '&';
    public static final char AIKI_SEP_9 = '|';
    /** A used port for connections*/
    private static final int PORT = 668;

    private final IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

    private final CustList<IntClientActLoopAiki> clientAct = new CustList<IntClientActLoopAiki>();
    private final CustList<IntServerActLoopAiki> serverActLoopAiki = new CustList<IntServerActLoopAiki>();
    public NetAiki(){
        clientAct.add(new ClientActLoopAikiIndexArrive());
        clientAct.add(new ClientActLoopAikiInitTrading());
        clientAct.add(new ClientActLoopAikiNetPokemon());
        clientAct.add(new ClientActLoopAikiPokemonPlayer());
        clientAct.add(new ClientActLoopAikiOk());
        serverActLoopAiki.add(new ServerActLoopAikiPlayer());
        serverActLoopAiki.add(new ServerActLoopAikiCheckCompatibility());
        serverActLoopAiki.add(new ServerActLoopAikiSentPokemon());
        serverActLoopAiki.add(new ServerActLoopAikiReady());
        serverActLoopAiki.add(new ServerActLoopAikiOk());
        serverActLoopAiki.add(new ServerActLoopAikiQuitAiki());
    }

    public static String exportNewPlayer(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_NEW_PLAYER);
        out_.append(AIKI_SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportClientOk() {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_OK);
        out_.append(AIKI_SEP_0);
        return out_.toString();
    }

    public static String exportServerOk() {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_OK);
        out_.append(AIKI_SEP_0);
        return out_.toString();
    }
    public static String exportReadyAiki(ReadyAiki _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_READY);
        out_.append(AIKI_SEP_0);
        out_.append(NetCommon.exportBool(_index.getContent().isReady()));
        out_.append(_index.getIndex());
        return out_.toString();
    }

    public static ReadyAiki importReadyAiki(CustList<String> _index) {
        ReadyAiki r_ = new ReadyAiki();
        r_.getContent().setReady(NetCommon.toBoolEquals(_index.get(0),0));
        r_.setIndex(NumberUtil.parseInt(_index.get(0).substring(1)));
        return r_;
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

//    public static void loopClient(WindowNetWork _window, String _info, AbstractSocket _socket) {
//        NetAikiRetrievedInfos ret_ = new NetAikiRetrievedInfos(_info);
//        loopClient(_window, ret_, _socket);
//    }

    public static void loopClient(WindowNetWork _window, NetAikiRetrievedInfos _ret, AbstractSocket _socket) {
        _window.getNetAiki().clientAct.get(_ret.getIndexAct()).loop(_window, _ret.getParts(), _socket);
    }
    public static void loopServer(String _info, NetAiki _instance, NetCommon _common) {
        NetAikiRetrievedInfos ret_ = new NetAikiRetrievedInfos(_info);
//        if (ret_.getIndexAct() < 0) {
//            return;
//        }
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
    public static String exportSentPokemon(SentPokemon _check) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_SENT_POKEMON);
        out_.append(AIKI_SEP_0);
        out_.append(_check.getIndex());
        out_.append(AIKI_SEP_0);
        out_.append(exportPokemonPlayer(_check.getPokemon(),AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3));
        return out_.toString();
    }
    public static SentPokemon importSentPokemon(CustList<String> _check) {
        SentPokemon n_ = new SentPokemon();
        n_.setIndex((byte) NumberUtil.parseInt(_check.get(0)));
        n_.setPokemon(importPokemonPlayer(_check.get(1),AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3));
        return n_;
    }
    public static String exportNetPokemon(NetPokemon _check) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_NET_PK);
        out_.append(AIKI_SEP_0);
        CustList<String> pks_ = new CustList<String>();
        for (EntryCust<Byte, PokemonPlayer> m: _check.getTradablePokemon().entryList()) {
            pks_.add(""+m.getKey()+AIKI_SEP_2+exportPokemonPlayer(m.getValue(),AIKI_SEP_3,AIKI_SEP_4,AIKI_SEP_5));
        }
        out_.append(StringUtil.join(pks_,AIKI_SEP_1));
        return out_.toString();
    }
    public static NetPokemon importNetPokemon(CustList<String> _check) {
        NetPokemon n_ = new NetPokemon();
        n_.setTradablePokemon(new ByteTreeMap<PokemonPlayer>());
        for (String m: StringUtil.partsStr(_check.get(0),0,_check.get(0).length(),AIKI_SEP_1)) {
            CustList<String> kv_ = StringUtil.partsStr(m, 0, m.length(), AIKI_SEP_2);
            n_.getTradablePokemon().addEntry((byte)NumberUtil.parseInt(kv_.first()),importPokemonPlayer(kv_.last(),AIKI_SEP_3,AIKI_SEP_4,AIKI_SEP_5));
        }
        return n_;
    }
    public static String exportCheckCompatibility(CheckCompatibility _check) {
        ExchangedData data_ = _check.getData();
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_CHECK_COMPATIBILITY);
        out_.append(AIKI_SEP_0);
        out_.append(_check.getIndex());
        out_.append(AIKI_SEP_0);
        out_.append(data_.getIndexTeam());
        out_.append(AIKI_SEP_0);
        out_.append(StringUtil.join(data_.getAbilities(),AIKI_SEP_1));
        out_.append(AIKI_SEP_0);
        out_.append(StringUtil.join(data_.getItems(),AIKI_SEP_1));
        out_.append(AIKI_SEP_0);
        CustList<String> moves_ = new CustList<String>();
        for (EntryCust<String, GenderRepartition> m: data_.getGenderRepartitions().entryList()) {
            moves_.add(m.getKey()+AIKI_SEP_2+m.getValue().getGenderRep());
        }
        out_.append(StringUtil.join(moves_,AIKI_SEP_1));
//        out_.append(AIKI_SEP_0);
//        out_.append(exportPokemonPlayer(data_.getPokemon(),AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3));
        CustList<String> pk_ = new CustList<String>();
        for (UsablePokemon m: _check.getTeam()) {
            if (m instanceof PokemonPlayer) {
                pk_.add(exportPokemonPlayer((PokemonPlayer) m,AIKI_SEP_2,AIKI_SEP_3,AIKI_SEP_4).toString());
            }
        }
        out_.append(AIKI_SEP_0);
        out_.append(StringUtil.join(pk_,AIKI_SEP_1));
        return out_.toString();
    }
    public static CheckCompatibility importCheckCompatibility(CustList<String> _check) {
        CheckCompatibility ch_ = new CheckCompatibility();
        ch_.setData(new ExchangedData());
        ch_.setTeam(new CustList<UsablePokemon>());
        ch_.setIndex(NumberUtil.parseInt(_check.get(0)));
        ch_.getData().setIndexTeam(NumberUtil.parseInt(_check.get(1)));
        ch_.getData().setAbilities(StringUtil.splitChar(_check.get(2),AIKI_SEP_1));
        ch_.getData().setItems(StringUtil.splitChar(_check.get(3),AIKI_SEP_1));
        ch_.getData().setGenderRepartitions(new StringMap<GenderRepartition>());
        for (String m: StringUtil.splitChar(_check.get(4),AIKI_SEP_1)) {
            StringList kv_ = StringUtil.splitChar(m, AIKI_SEP_2);
            ch_.getData().getGenderRepartitions().addEntry(kv_.first(),GenderRepartition.getGenderRepartitionByName(kv_.last()));
        }
//        ch_.getData().setPokemon(importPokemonPlayer(_check.get(5),AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3));
        for (String m: StringUtil.partsStr(_check.get(5),0,_check.get(5).length(),AIKI_SEP_1)) {
            if (m.isEmpty()) {
                continue;
            }
            ch_.getTeam().add(importPokemonPlayer(m,AIKI_SEP_2,AIKI_SEP_3,AIKI_SEP_4));
        }
        return ch_;
    }
    public static String exportPokemonPlayer(PokemonPlayer _pk) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_POKEMON_PLAYER);
        out_.append(AIKI_SEP_0);
        out_.append(exportPokemonPlayer(_pk,AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3));
        return out_.toString();
    }
    public static PokemonPlayer importPokemonPlayer(CustList<String> _infos) {
        return importPokemonPlayer(_infos.get(0),AIKI_SEP_1,AIKI_SEP_2,AIKI_SEP_3);
    }
    public static StringBuilder exportPokemonPlayer(PokemonPlayer _pk, char _sep, char _sec, char _th) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_pk.getName());
        out_.append(_sep);
        out_.append(_pk.getLevel());
        out_.append(_sep);
        out_.append(_pk.getGender().getGenderName());
        out_.append(_sep);
        out_.append(_pk.getAbility());
        out_.append(_sep);
        out_.append(_pk.getItem());
        out_.append(_sep);
        out_.append(escapeId(_pk.getNickname()));
        CustList<String> moves_ = new CustList<String>();
        for (EntryCust<String, UsesOfMove> m: _pk.getMoves().entryList()) {
            moves_.add(m.getKey());
        }
        out_.append(_sep);
        out_.append(StringUtil.join(moves_,_sec));
        CustList<String> stats_ = new CustList<String>();
        for (EntryCust<Statistic, Short> m: _pk.getEv().entryList()) {
            stats_.add(m.getKey().getStatName()+_th+m.getValue());
        }
        out_.append(_sep);
        out_.append(StringUtil.join(stats_,_sec));
        out_.append(_sep);
        out_.append(_pk.getWonExpSinceLastLevel().toNumberString());
        out_.append(_sep);
        out_.append(_pk.getHappiness());
        out_.append(_sep);
        out_.append(_pk.getUsedBallCatching());
        out_.append(_sep);
        out_.append(_pk.getNbStepsTeamLead());
        return out_;
    }
    public static PokemonPlayer importPokemonPlayer(String _part, char _sep, char _sec, char _th) {
        CustList<String> infos_ = StringUtil.partsStr(_part, 0, _part.length(), _sep);
        return importPokemonPlayer(infos_, _sec, _th);
    }

    public static PokemonPlayer importPokemonPlayer(CustList<String> _infos, char _sec, char _th) {
        PokemonPlayer p_ = new PokemonPlayer();
        p_.setName(_infos.get(0));
        p_.setLevel((short) NumberUtil.parseInt(_infos.get(1)));
        p_.setGender(Gender.getGenderByName(_infos.get(2)));
        p_.setAbility(_infos.get(3));
        p_.setItem(_infos.get(4));
        p_.setNickname(unescapeId(_infos.get(5)));
        for (String m: StringUtil.splitChar(_infos.get(6), _sec)) {
            p_.getMoves().addEntry(m, new UsesOfMove((short) 0));
        }
        for (String m: StringUtil.splitChar(_infos.get(7), _sec)) {
            StringList kv_ = StringUtil.splitChar(m, _th);
            p_.getEv().addEntry(Statistic.getStatisticByName(kv_.first()),(short)NumberUtil.parseInt(kv_.last()));
        }
        p_.setWonExpSinceLastLevel(new Rate(_infos.get(8)));
        p_.setHappiness((short)NumberUtil.parseInt(_infos.get(9)));
        p_.setUsedBallCatching(_infos.get(10));
        p_.setNbStepsTeamLead((short)NumberUtil.parseInt(_infos.get(11)));
        return p_;
    }
//    public static StringBuilder exportEgg(Egg _pk, char _sep) {
//        StringBuilder out_ = new StringBuilder();
//        out_.append(_pk.getName());
//        out_.append(_sep);
//        out_.append(_pk.getSteps());
//        return out_;
//    }
//    public static Egg importEgg(String _part, char _sep) {
//        CustList<String> infos_ = NetAikiRetrievedInfos.partsStr(_part, 0, _part.length(), _sep);
//        return new Egg(infos_.get(0)+';'+infos_.get(1));
//    }
    public static String exportQuitAiki(QuitAiki _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_QUIT);
        out_.append(AIKI_SEP_0);
        out_.append(NetCommon.exportBool(_index.getContent().isClosing()));
        out_.append(NetCommon.exportBool(_index.getContent().isServer()));
        out_.append(_index.getPlace());
        return out_.toString();
    }

    public static QuitAiki importQuitAiki(CustList<String> _index) {
        QuitAiki r_ = new QuitAiki();
        r_.getContent().setClosing(NetCommon.toBoolEquals(_index.get(0),0));
        r_.getContent().setServer(NetCommon.toBoolEquals(_index.get(0),1));
        r_.setPlace((byte) NumberUtil.parseInt(_index.get(0).substring(2)));
        return r_;
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
    public static String unescapeId(String _str) {
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
    public static void sendObject(AbstractSocket _socket, int _index) {
        NetGroupFrame.trySendString(NetAiki.exportNewPlayer(_index), _socket);
    }

    public static void sendObject(AbstractSocket _socket, CheckCompatibility _serializable) {
        NetGroupFrame.trySendString(NetAiki.exportCheckCompatibility(_serializable), _socket);
    }
    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(NetAiki _instance) {
        return _instance.checkCompatibility;
    }

}
