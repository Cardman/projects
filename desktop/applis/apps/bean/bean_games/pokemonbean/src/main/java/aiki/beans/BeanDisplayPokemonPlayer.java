package aiki.beans;

import aiki.game.fight.*;
import aiki.map.pokemon.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public class BeanDisplayPokemonPlayer implements BeanDisplayEltGrid<PokemonPlayer> {
    @Override
    public int displayEltGrid(CommonBean _rend, PokemonPlayer _info) {
        displayLine(_rend, _info);
        return 11;
    }

    public static void displayLine(CommonBean _rend, PokemonPlayer _info) {
        _rend.initLine();
        _rend.addImg(_rend.getDataBase().getMiniPk(_info.getName()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_rend.getFacade().getTranslatedPokemon().getVal(_info.getName()));
        _rend.formatMessageDirCts(Long.toString(_info.getLevel()));
        _rend.formatMessageDirCts(_rend.getFacade().getTranslatedAbilities().getVal(_info.getAbility()));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedGenders().getVal(_info.getGender())));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_rend.getFacade().getTranslatedItems().getVal(_info.getItem())));
        StringList list_ = new StringList();
        for (String m: _info.getMoves().getKeys()) {
            list_.add(_rend.getFacade().getTranslatedMoves().getVal(m));
        }
        list_.sort();
        _rend.formatMessageDirCts(StringUtil.join(list_," - "));
        _rend.formatMessageDirCts(_info.rateRemainHp(_rend.getDataBase()).toNumberString());
        _rend.formatMessageDirCts(_info.getWonExpSinceLastLevel().toNumberString());
        long level_ = _info.getLevel();
        Rate diff_ = FightFacade.numberNecessaryPointsForGrowingLevel(_info.getName(),level_+1L,_rend.getDataBase());
        diff_.removeNb(_info.getWonExpSinceLastLevel());
        _rend.formatMessageDirCts(diff_.toNumberString());
        _rend.formatMessageDirCts(Long.toString(_info.getHappiness()));
    }
}
