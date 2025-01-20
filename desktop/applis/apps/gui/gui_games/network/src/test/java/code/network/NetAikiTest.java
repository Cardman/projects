package code.network;

import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.enums.*;
import aiki.game.*;
import aiki.instances.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.network.*;
import aiki.network.stream.*;
import code.maths.Rate;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class NetAikiTest extends EquallableNetworkUtil {

    @Test
    public void ready1(){
        ReadyAiki r_ = saveServerReady(1,false);
        assertEq(1,r_.getIndex());
        assertFalse(r_.getContent().isReady());
    }
    @Test
    public void ready2(){
        ReadyAiki r_ = saveServerReady(1,true);
        assertEq(1,r_.getIndex());
        assertTrue(r_.getContent().isReady());
    }
    @Test
    public void pkPlayer1() {
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = savePokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("ITEM",out_.getItem());
        assertEq("NICKNAME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void pkPlayer2() {
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("");
        pp_.setNickname("NIC\\KN:AME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = savePokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("",out_.getItem());
        assertEq("NIC\\KN:AME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void pkPlayer3() {
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = saveClientPokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("ITEM",out_.getItem());
        assertEq("NICKNAME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void pkPlayer4() {
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("");
        pp_.setNickname("NIC\\KN:AME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = saveClientPokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("",out_.getItem());
        assertEq("NIC\\KN:AME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void pkPlayer5() {
        Gender g_ = Gender.getGendersWithSex().get(0);
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(g_);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = savePokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq(g_, out_.getGender());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("ITEM",out_.getItem());
        assertEq("NICKNAME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void pkPlayer6() {
        Gender g_ = Gender.getGendersWithSex().get(1);
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(g_);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        PokemonPlayer out_ = savePokemonPlayer(pp_);
        assertEq(2, out_.getLevel());
        assertEq(g_, out_.getGender());
        assertEq("NAME",out_.getName());
        assertEq("ABILITY",out_.getAbility());
        assertEq("ITEM",out_.getItem());
        assertEq("NICKNAME",out_.getNickname());
        assertEq(1,out_.getMoves().size());
        assertEq("MOVE",out_.getMoves().getKey(0));
        assertEq(1,out_.getEv().size());
        assertEq(Statistic.SPEED, out_.getEv().getKey(0));
        assertEq(4, out_.getEv().getValue(0));
        assertEq(new Rate("5/6"), out_.getWonExpSinceLastLevel());
        assertEq("BALL", out_.getUsedBallCatching());
        assertEq(8, out_.getNbStepsTeamLead());
        assertEq(7, out_.getHappiness());
    }
    @Test
    public void checkCompatibility1() {
        CheckCompatibility c_ = new CheckCompatibility();
        ExchangedData exc_ = new ExchangedData();
        exc_.setItems(new StringList("ITEM1","ITEM2"));
        exc_.setAbilities(new StringList("ABILITY1","ABILITY2"));
        StringMap<GenderRepartition> g_ = new StringMap<GenderRepartition>();
        g_.addEntry("PK1",GenderRepartition.LEGENDARY);
        g_.addEntry("PK2",GenderRepartition.NO_GENDER);
        exc_.setGenderRepartitions(g_);
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        exc_.setPokemon(pp_);
        PokemonPlayer pp2_ = Instances.newPokemonPlayer();
        pp2_.setName("NAME_");
        pp2_.setLevel(12);
        pp2_.setGender(Gender.NO_GENDER);
        pp2_.setAbility("ABILITY_");
        pp2_.setItem("ITEM_");
        pp2_.setNickname("NICKNAME_");
        pp2_.getMoves().addEntry("MOVE_",new UsesOfMove(1,3));
        pp2_.getEv().addEntry(Statistic.SPEED,14L);
        pp2_.setWonExpSinceLastLevel(new Rate("15/16"));
        pp2_.setUsedBallCatching("BALL_");
        pp2_.setNbStepsTeamLead(18);
        pp2_.setHappiness(71);
        exc_.setIndexTeam(1);
        c_.setData(exc_);
        CustList<UsablePokemon> team_ = new CustList<UsablePokemon>();
        team_.add(pp2_);
        team_.add(new Egg("__"));
        c_.setTeam(team_);
        c_.setIndex(2);
        CheckCompatibility check_ = saveCheckCompatibility(c_);
        assertEq(2,check_.getIndex());
        assertEq(1,check_.getData().getIndexTeam());
        assertEq(1,check_.getTeam().size());
        PokemonPlayer outTeam_ = (PokemonPlayer) check_.getTeam().get(0);
        assertEq(12, outTeam_.getLevel());
        assertEq("NAME_",outTeam_.getName());
        assertEq("ABILITY_",outTeam_.getAbility());
        assertEq("ITEM_",outTeam_.getItem());
        assertEq("NICKNAME_",outTeam_.getNickname());
        assertEq(1,outTeam_.getMoves().size());
        assertEq("MOVE_",outTeam_.getMoves().getKey(0));
        assertEq(1,outTeam_.getEv().size());
        assertEq(Statistic.SPEED, outTeam_.getEv().getKey(0));
        assertEq(14, outTeam_.getEv().getValue(0));
        assertEq(new Rate("15/16"), outTeam_.getWonExpSinceLastLevel());
        assertEq("BALL_", outTeam_.getUsedBallCatching());
        assertEq(18, outTeam_.getNbStepsTeamLead());
        assertEq(71, outTeam_.getHappiness());
        assertEq(2,check_.getData().getAbilities().size());
        assertEq("ABILITY1",check_.getData().getAbilities().get(0));
        assertEq("ABILITY2",check_.getData().getAbilities().get(1));
        assertEq(2,check_.getData().getItems().size());
        assertEq("ITEM1",check_.getData().getItems().get(0));
        assertEq("ITEM2",check_.getData().getItems().get(1));
        assertEq(2,check_.getData().getGenderRepartitions().size());
        assertEq("PK1",check_.getData().getGenderRepartitions().getKey(0));
        assertEq(GenderRepartition.LEGENDARY,check_.getData().getGenderRepartitions().getValue(0));
        assertEq("PK2",check_.getData().getGenderRepartitions().getKey(1));
        assertEq(GenderRepartition.NO_GENDER,check_.getData().getGenderRepartitions().getValue(1));
    }
    @Test
    public void checkCompatibility2() {
        CheckCompatibility c_ = new CheckCompatibility();
        ExchangedData exc_ = new ExchangedData();
        exc_.setItems(new StringList("ITEM1","ITEM2"));
        exc_.setAbilities(new StringList("ABILITY1","ABILITY2"));
        StringMap<GenderRepartition> g_ = new StringMap<GenderRepartition>();
        g_.addEntry("PK1",GenderRepartition.LEGENDARY);
        g_.addEntry("PK2",GenderRepartition.NO_GENDER);
        exc_.setGenderRepartitions(g_);
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.setName("NAME");
        pp_.setLevel(2);
        pp_.setGender(Gender.NO_GENDER);
        pp_.setAbility("ABILITY");
        pp_.setItem("ITEM");
        pp_.setNickname("NICKNAME");
        pp_.getMoves().addEntry("MOVE",new UsesOfMove(1,3));
        pp_.getEv().addEntry(Statistic.SPEED,4L);
        pp_.setWonExpSinceLastLevel(new Rate("5/6"));
        pp_.setUsedBallCatching("BALL");
        pp_.setNbStepsTeamLead(8);
        pp_.setHappiness(7);
        exc_.setPokemon(pp_);
        PokemonPlayer pp2_ = Instances.newPokemonPlayer();
        pp2_.setName("NAME_");
        pp2_.setLevel(12);
        pp2_.setGender(Gender.NO_GENDER);
        pp2_.setAbility("ABILITY_");
        pp2_.setItem("ITEM_");
        pp2_.setNickname("NICKNAME_");
        pp2_.getMoves().addEntry("MOVE_",new UsesOfMove(1,3));
        pp2_.getEv().addEntry(Statistic.SPEED,14L);
        pp2_.setWonExpSinceLastLevel(new Rate("15/16"));
        pp2_.setUsedBallCatching("BALL_");
        pp2_.setNbStepsTeamLead(18);
        pp2_.setHappiness(71);
        exc_.setIndexTeam(1);
        c_.setData(exc_);
        c_.setTeam(new CustList<UsablePokemon>());
        c_.setIndex(2);
        CheckCompatibility check_ = saveCheckCompatibility(c_);
        assertEq(2,check_.getIndex());
        assertEq(1,check_.getData().getIndexTeam());
        assertEq(0,check_.getTeam().size());
        assertEq(2,check_.getData().getAbilities().size());
        assertEq("ABILITY1",check_.getData().getAbilities().get(0));
        assertEq("ABILITY2",check_.getData().getAbilities().get(1));
        assertEq(2,check_.getData().getItems().size());
        assertEq("ITEM1",check_.getData().getItems().get(0));
        assertEq("ITEM2",check_.getData().getItems().get(1));
        assertEq(2,check_.getData().getGenderRepartitions().size());
        assertEq("PK1",check_.getData().getGenderRepartitions().getKey(0));
        assertEq(GenderRepartition.LEGENDARY,check_.getData().getGenderRepartitions().getValue(0));
        assertEq("PK2",check_.getData().getGenderRepartitions().getKey(1));
        assertEq(GenderRepartition.NO_GENDER,check_.getData().getGenderRepartitions().getValue(1));
    }
    @Test
    public void netPokemon() {
        NetPokemon c_ = new NetPokemon();
        PokemonPlayer pp2_ = Instances.newPokemonPlayer();
        pp2_.setName("NAME_");
        pp2_.setLevel(12);
        pp2_.setGender(Gender.NO_GENDER);
        pp2_.setAbility("ABILITY_");
        pp2_.setItem("ITEM_");
        pp2_.setNickname("NICKNAME_");
        pp2_.getMoves().addEntry("MOVE_",new UsesOfMove(1,3));
        pp2_.getEv().addEntry(Statistic.SPEED,14L);
        pp2_.setWonExpSinceLastLevel(new Rate("15/16"));
        pp2_.setUsedBallCatching("BALL_");
        pp2_.setNbStepsTeamLead(18);
        pp2_.setHappiness(71);
        IntTreeMap< PokemonPlayer> team_ = new IntTreeMap< PokemonPlayer>();
        team_.addEntry(2,pp2_);
        c_.setTradablePokemon(team_);
        NetPokemon check_ = saveNetPokemon(c_);
        assertEq(1,check_.getTradablePokemon().size());
        assertEq(2,check_.getTradablePokemon().getKey(0));
        PokemonPlayer outTeam_ = check_.getTradablePokemon().getValue(0);
        assertEq(12, outTeam_.getLevel());
        assertEq("NAME_",outTeam_.getName());
        assertEq("ABILITY_",outTeam_.getAbility());
        assertEq("ITEM_",outTeam_.getItem());
        assertEq("NICKNAME_",outTeam_.getNickname());
        assertEq(1,outTeam_.getMoves().size());
        assertEq("MOVE_",outTeam_.getMoves().getKey(0));
        assertEq(1,outTeam_.getEv().size());
        assertEq(Statistic.SPEED, outTeam_.getEv().getKey(0));
        assertEq(14, outTeam_.getEv().getValue(0));
        assertEq(new Rate("15/16"), outTeam_.getWonExpSinceLastLevel());
        assertEq("BALL_", outTeam_.getUsedBallCatching());
        assertEq(18, outTeam_.getNbStepsTeamLead());
        assertEq(71, outTeam_.getHappiness());
    }

    @Test
    public void sentPokemon() {
        SentPokemon c_ = new SentPokemon();
        PokemonPlayer pp2_ = Instances.newPokemonPlayer();
        pp2_.setName("NAME_");
        pp2_.setLevel(12);
        pp2_.setGender(Gender.NO_GENDER);
        pp2_.setAbility("ABILITY_");
        pp2_.setItem("ITEM_");
        pp2_.setNickname("NICKNAME_");
        pp2_.getMoves().addEntry("MOVE_",new UsesOfMove(1,3));
        pp2_.getEv().addEntry(Statistic.SPEED,14L);
        pp2_.setWonExpSinceLastLevel(new Rate("15/16"));
        pp2_.setUsedBallCatching("BALL_");
        pp2_.setNbStepsTeamLead(18);
        pp2_.setHappiness(71);
        c_.setIndex(2);
        c_.setPokemon(pp2_);
        SentPokemon check_ = saveSentPokemon(c_);
        assertEq(2,check_.getIndex());
        PokemonPlayer outTeam_ = check_.getPokemon();
        assertEq(12, outTeam_.getLevel());
        assertEq("NAME_",outTeam_.getName());
        assertEq("ABILITY_",outTeam_.getAbility());
        assertEq("ITEM_",outTeam_.getItem());
        assertEq("NICKNAME_",outTeam_.getNickname());
        assertEq(1,outTeam_.getMoves().size());
        assertEq("MOVE_",outTeam_.getMoves().getKey(0));
        assertEq(1,outTeam_.getEv().size());
        assertEq(Statistic.SPEED, outTeam_.getEv().getKey(0));
        assertEq(14, outTeam_.getEv().getValue(0));
        assertEq(new Rate("15/16"), outTeam_.getWonExpSinceLastLevel());
        assertEq("BALL_", outTeam_.getUsedBallCatching());
        assertEq(18, outTeam_.getNbStepsTeamLead());
        assertEq(71, outTeam_.getHappiness());
    }
    @Test
    public void quitAiki1() {
        QuitAiki q_ = new QuitAiki();
        q_.setPlace(5);
        q_.getContent().setClosing(false);
        q_.getContent().setServer(false);
        QuitAiki o_ = saveQuitAiki(q_);
        assertEq(5,o_.getPlace());
        assertFalse(o_.getContent().isClosing());
        assertFalse(o_.getContent().isServer());
    }

    @Test
    public void quitAiki2() {
        QuitAiki q_ = new QuitAiki();
        q_.setPlace(5);
        q_.getContent().setClosing(true);
        q_.getContent().setServer(true);
        QuitAiki o_ = saveQuitAiki(q_);
        assertEq(5,o_.getPlace());
        assertTrue(o_.getContent().isClosing());
        assertTrue(o_.getContent().isServer());
    }

    public static QuitAiki saveQuitAiki(QuitAiki _pk) {
        return NetAiki.importQuitAiki(parseParts(NetAiki.exportQuitAiki(_pk)));
    }

    public static ReadyAiki saveServerReady(int _index, boolean _value) {
        ReadyAiki r_ = new ReadyAiki();
        r_.getContent().setReady(_value);
        r_.setIndex(_index);
        return NetAiki.importReadyAiki(parseParts(NetAiki.exportReadyAiki(r_)));
    }
    public static CheckCompatibility saveCheckCompatibility(CheckCompatibility _pk) {
        return NetAiki.importCheckCompatibility(parseParts(NetAiki.exportCheckCompatibility(_pk)));
    }

    public static NetPokemon saveNetPokemon(NetPokemon _pk) {
        return NetAiki.importNetPokemon(parseParts(NetAiki.exportNetPokemon(_pk)));
    }

    public static SentPokemon saveSentPokemon(SentPokemon _pk) {
        return NetAiki.importSentPokemon(parseParts(NetAiki.exportSentPokemon(_pk)));
    }

    public static PokemonPlayer saveClientPokemonPlayer(PokemonPlayer _pk) {
        return NetAiki.importPokemonPlayer(parseParts(NetAiki.exportPokemonPlayer(_pk)));
    }
    public static PokemonPlayer savePokemonPlayer(PokemonPlayer _pk) {
        return NetAiki.importPokemonPlayer(NetAiki.exportPokemonPlayer(_pk,NetAiki.AIKI_SEP_1,NetAiki.AIKI_SEP_2,NetAiki.AIKI_SEP_3).toString(),NetAiki.AIKI_SEP_1,NetAiki.AIKI_SEP_2,NetAiki.AIKI_SEP_3);
    }
    private static CustList<String> parseParts(String _in) {
        FirstSeparatorFind cs_ = new FirstSeparatorFind(_in, NetAiki.AIKI_SEP_0);
        return StringUtil.partsStr(_in, index(cs_), _in.length(), NetAiki.AIKI_SEP_0);
    }

    private static int index(FirstSeparatorFind _cs) {
        assertTrue(_cs.isFound());
        return _cs.getIndex();
    }
}
