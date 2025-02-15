package aiki.beans.map.elements;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.map.pokemon.*;
import code.util.*;

public final class TranslatedPkElements {
    private final TranslatedKey name;
    private final int[][] image;
    private final long level;
    private final TranslatedKey gender;
    private final TranslatedKey ability;
    private final TranslatedKey item;
    private final CustList<TranslatedKey> moves;
    private final PkTrainer trained;
    public TranslatedPkElements(FacadeGame _db, Pokemon _pk) {
        this(_db,_pk, getMovesAtLevel(_db.getData(), _pk));
    }
    public TranslatedPkElements(FacadeGame _db, PkTrainer _pk) {
        this(_db,_pk, _pk.getMoves());
    }
    public TranslatedPkElements(FacadeGame _db, Pokemon _pk, StringList _moves) {
        name = CommonBean.buildPk(_db,_pk.getName());
        image = _db.getData().getMaxiPkFront().getVal(_pk.getName()).getImage();
        level = _pk.getLevel();
        gender = CommonBean.buildGender(_db,_pk.getGender());
        ability = CommonBean.buildAb(_db,_pk.getAbility());
        item = CommonBean.buildIt(_db, _pk.getItem());
        moves = retrieveMoves(_db, _moves);
        trained = new PkTrainer(_pk,_moves);
    }

    private static CustList<TranslatedKey> retrieveMoves(FacadeGame _db, CustList<String> _movesAtLevel) {
        CustList<TranslatedKey> mvs_ = new CustList<TranslatedKey>();
        for (String m: _movesAtLevel) {
            mvs_.add(CommonBean.buildMv(_db,m));
        }
        mvs_.sortElts(new ComparingTranslatedKey());
        return mvs_;
    }

    private static StringList getMovesAtLevel(DataBase _db, Pokemon _pk) {
        return _db.getPokemon(_pk.getName()).getMovesAtLevel(_pk.getLevel(), _db.getNbMaxMoves());
    }


    public TranslatedKey getName() {
        return name;
    }

    public int[][] getImage() {
        return image;
    }

    public long getLevel() {
        return level;
    }

    public TranslatedKey getAbility() {
        return ability;
    }

    public TranslatedKey getGender() {
        return gender;
    }

    public TranslatedKey getItem() {
        return item;
    }

    public CustList<TranslatedKey> getMoves() {
        return moves;
    }

    public PkTrainer getTrained() {
        return trained;
    }
}
