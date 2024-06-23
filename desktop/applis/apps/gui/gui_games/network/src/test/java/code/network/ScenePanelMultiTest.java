package code.network;

import aiki.db.*;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.StatBaseEv;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.instances.*;
import aiki.map.pokemon.enums.Gender;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.MockCustComponent;
import code.mock.MockSocket;
import code.mock.MockThreadFactory;
import code.threads.AbstractThread;
import code.util.*;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class ScenePanelMultiTest extends EquallableNetworkUtil {
    public static final String FR = "fr";
    public static final String EN = "en";
    public static final String LANGUAGE = EN;
    public static final String ELECTRICK = "ELECTRICK";
    @Test
    public void intro1() {
        WindowNetWork server_ = frameSingle(new MockDataBaseStreamNet());
        serverPk(server_);
        retrievedSocket(server_, server_, 0);
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        loopServer1(server_.getSockets());
        WindowNetWork client_ = frameSingle(new MockDataBaseStreamNet());
        clientPk(server_,client_);
        retrievedSocket(server_, client_, 1);
        sendClientPk(server_.getSockets(), client_,1);
        loopClient(server_.getSockets(),client_);
        loopServer2(server_.getSockets());
        sendClientPk(server_.getSockets(),server_,0);
        loopClient(server_.getSockets(),server_);
        sendClientPk(server_.getSockets(),client_,1);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, tr_.size());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
    }
    public static DataBase sample() {
        DataBase db_ = init();
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        db_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        db_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        db_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        db_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        db_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        db_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        db_.getTranslatedGenders().addEntry(LANGUAGE,new IdMap<Gender, String>());
        trsTypes_.put(ELECTRICK,"elec");
        db_ = withPk(withPk(db_,"P1",trsPk_),"P2",trsPk_);
        db_ = withMv(withMv(db_,"M1",trsMv_),"M2",trsMv_);
        db_ = withAb(withAb(db_,"A1",trsAb_),"A2",trsAb_);
        db_ = withIt(withIt(db_,"I1",trsIt_,trsDesc_),"I2",trsIt_,trsDesc_);
        db_.getMap().getFirstPokemon().setName("P1");
        return db_;
    }

    public static DataBase init() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initTranslations();
        data_.initializeMembers();
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getMap().setSideLength(1);
        return data_;
    }
    public static DataBase withPk(DataBase _data, String _key, StringMap<String> _trs) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getStatistics().addEntry(Statistic.ATTACK, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.DEFENSE, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE, new StatBaseEv((short) 1,(short)1));
        pk_.getStatistics().addEntry(Statistic.HP, new StatBaseEv((short) 1,(short)1));
        _data.completeQuickMembers(_key, pk_);
        _data.getMiniPk().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkBack().addEntry(_key,new int[][]{new int[1]});
        _data.getMaxiPkFront().addEntry(_key,new int[][]{new int[1]});
        _trs.addEntry(_key,_key);
        return _data;
    }
    public static DataBase withMv(DataBase _data, String _key, StringMap<String> _trs) {
        _data.completeQuickMembers(_key,Instances.newDamagingMoveData());
        _trs.addEntry(_key,_key);
        return _data;
    }

    public static DataBase withAb(DataBase _data, String _key, StringMap<String> _trs) {
        _data.completeQuickMembers(_key,Instances.newAbilityData());
        _trs.addEntry(_key,_key);
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        return withIt(_data, _key, _trs, ball_);
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, Item _it) {
        _data.completeQuickMembers(_key, _it);
        _trs.addEntry(_key,_key);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        return _data;
    }

    public static DataBase withIt(DataBase _data, String _key, StringMap<String> _trs, StringMap<String> _trsDesc) {
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        _data.completeQuickMembers(_key, ball_);
        _trs.addEntry(_key,_key);
        _data.getMiniItems().addEntry(_key,new int[][]{new int[1]});
        _trsDesc.addEntry(ball_.getItemType(),ball_.getItemType());
        return _data;
    }
}
