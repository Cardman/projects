package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.Fossil;
import aiki.game.Game;
import aiki.game.HostPokemonDuo;
import aiki.game.UsesOfMove;
import aiki.game.params.Difficulty;
import aiki.instances.Instances;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class PokemonPlayerBeanTest extends InitDbBean {
    static final String NICKNAME = "CARDTEAM";
    @Test
    public void getName1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void getName2() {
        DataBase init_ = one();
        addFossile(init_);
        FacadeGame fac_ = fac(init_);
        revive(fac_);
        fac_.openMenu();
        fac_.searchPokemonFirstBox();
        fac_.checkLinePokemonFirstBox(0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getName3() {
        DataBase init_ = one();
        setAbilities(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.initTrading();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, PARATONNERRE, Gender.NO_GENDER, NULL_REF);
        fac_.receivePokemonPlayer(pk_);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getName4() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        addHost(fac_.getGame());
        fac_.setHostedPokemon(true,newCoords(0,0,0,0));
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getUsedBallCatching1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(NULL_REF,callPokemonPlayerBeanUsedBallCatchingGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getUsedBallCatching2() {
        DataBase init_ = one();
        setAbilities(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.initTrading();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, PARATONNERRE, Gender.NO_GENDER, NULL_REF);
        fac_.receivePokemonPlayer(pk_);
        assertEq(POKE_BALL_TR,callPokemonPlayerBeanUsedBallCatchingGet(displaying(beanPk(EN, fac_))));
    }

    private void revive(FacadeGame _fac) {
        Game game_ = _fac.getGame();
        game_.getPlayer().getItem(FOSSIL);
        game_.getPlayer().doRevivingFossil(FOSSIL, game_.getDifficulty(), _fac.getData());
    }

    private void addFossile(DataBase _db) {
        Fossil fo_ = Instances.newFossil();
        fo_.setLevel((short) 2);
        fo_.setPokemon(PIKACHU);
        _db.completeMembers(FOSSIL, fo_);
    }


    private void setAbilities(DataBase _db) {
        _db.getPokemon(PIKACHU).setAbilities(new StringList(PARATONNERRE));
        _db.completeMembers(PARATONNERRE,Instances.newAbilityData());
    }

    private void addHost(Game _game) {
        HostPokemonDuo h_ = Instances.newHostPokemonDuo();
        h_.setFirstPokemon((PokemonPlayer) _game.getPlayer().getTeam().get(0));
        h_.setSecondPokemon((PokemonPlayer) _game.getPlayer().getTeam().get(0));
        _game.getHostedPk().addEntry(newCoords(0,0,0,0), h_);
    }

    private Game game(DataBase _init) {
        Game game_ = new Game(_init);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, _init);
        return game_;
    }

    private FacadeGame fac(DataBase _init) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(new StringList(EN));
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(_init);
        _init.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(_init);
        fac_.setLanguage(EN);
        fac_.setGame(game(_init));
        return fac_;
    }


    private static PokemonPlayer newPokemonPlayer(String _name, String _ability, Gender _gender, String _item) {
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(_name);
        sent_.setLevel((short) 1);
        sent_.setAbility(_ability);
        sent_.setItem(_item);
        sent_.setGender(_gender);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove((short) 10));
        sent_.setHappiness((short) 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        return sent_;
    }
}
