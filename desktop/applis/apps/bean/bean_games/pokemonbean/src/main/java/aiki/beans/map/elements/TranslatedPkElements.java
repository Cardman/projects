package aiki.beans.map.elements;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
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
    public TranslatedPkElements(DataBase _db, Pokemon _pk, String _lg) {
        this(_db,_pk,_lg,getMovesAtLevel(_db, _pk));
    }
    public TranslatedPkElements(DataBase _db, PkTrainer _pk, String _lg) {
        this(_db,_pk,_lg,_pk.getMoves());
    }
    public TranslatedPkElements(DataBase _db, Pokemon _pk, String _lg, StringList _moves) {
        name = CommonBean.buildPk(_db.getTranslatedPokemon().getVal(_lg),_pk.getName());
        image = _db.getMaxiPkFront().getVal(_pk.getName()).getImage();
        level = _pk.getLevel();
        gender = CommonBean.buildGender(_db.getTranslatedGenders().getVal(_lg),_pk.getGender());
        ability = CommonBean.buildAb(_db.getTranslatedAbilities().getVal(_lg),_pk.getAbility());
        item = CommonBean.buildIt(_db,_db.getTranslatedItems().getVal(_lg),_pk.getItem());
        moves = retrieveMoves(_db,_lg,_moves);
        trained = new PkTrainer(_pk,_moves);
    }

    private static CustList<TranslatedKey> retrieveMoves(DataBase _db, String _lg, CustList<String> _movesAtLevel) {
        CustList<TranslatedKey> mvs_ = new CustList<TranslatedKey>();
        StringMap<String> trMoves_ = _db.getTranslatedMoves().getVal(_lg);
        for (String m: _movesAtLevel) {
            mvs_.add(CommonBean.buildMv(trMoves_,m));
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
