package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.items.Fossil;
import aiki.game.Game;
import aiki.game.params.Difficulty;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class PokemonPlayerBeanTest extends InitDbBean {
    static final String NICKNAME = "CARDTEAM";
    @Test
    public void getName1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.setGame(game(init_));
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void getName2() {
        DataBase init_ = one();
        addFossile(init_);
        FacadeGame fac_ = fac(init_);
        fac_.setGame(game(init_));
        revive(fac_);
        fac_.openMenu();
        fac_.searchPokemonFirstBox();
        fac_.checkLinePokemonFirstBox(0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
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
        return fac_;
    }
}
