package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.pokemon.NameLevel;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.util.LevelMove;
import aiki.game.fight.util.LevelExpPoints;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class PseudoPlayerFighter extends PseudoFighter {

    private String item;

    private Rate wonExp;

    private final Rate wonExpSinceLastLevel;

    private boolean front;

    private final Ints foes;

    private final CustList<NameLevel> evoLevels;

    private final CustList<StringList> moves;

    private final CustList<StringList> abilities;

    private final IntMap<String> evolutions;

    private final CustList<NameLevel> infosRealEvolutions;

    public PseudoPlayerFighter(PseudoPokemonPlayer _pseudo, boolean _front, CustList<NameLevel> _evoLevels) {
        super(_pseudo.getName(), _pseudo.getLevel());
        item = _pseudo.getItem();
        wonExp = Rate.zero();
        wonExpSinceLastLevel = _pseudo.copyWonPointsSinceLastLevel();
        front = _front;
        foes = new Ints();
        evoLevels = _evoLevels;
        moves = new CustList<StringList>();
        abilities = new CustList<StringList>();
        evolutions = new IntMap<String>();
        infosRealEvolutions = new CustList<NameLevel>();
    }

    void calculateNewLevel(int _round,DataBase _import) {
        LevelExpPoints levelWonPoints_ = newLevelWonPoints(_import);
        int achievedLevel_ = levelWonPoints_.getLevel();
        Rate sommeDiffNiveaux_ = levelWonPoints_.getExpPoints();
        int maxNiveau_=_import.getMaxLevel();
        if (achievedLevel_ != maxNiveau_) {
            changeWonPoints(achievedLevel_, sommeDiffNiveaux_, _import);
        }
        StringList moves_ = newMoves(achievedLevel_, _import);
        String oldName_ = getName();
        setLevel(achievedLevel_);
        changeLevelsEvolutions(_import);
        boolean evolve_ = !StringUtil.quickEq(oldName_, getName());
        StringList abilities_ = new StringList();
        if (evolve_) {
            infosRealEvolutions.add(new NameLevel(getName(), achievedLevel_));
            moves_.addAllElts(newMovesEvolution(_import));
            moves_.removeDuplicates();
            moves_.sort();
            PokemonData fPk_=_import.getPokemon(getName());
            abilities_.addAllElts(fPk_.getAbilities());
            abilities_.sort();
            evolutions.put(_round, getName());
        }
        abilities.add(abilities_);
        moves.add(moves_);
    }

    LevelExpPoints newLevelWonPoints(DataBase _import) {
        return Fighter.newLevelWonPoints(_import, getName(), getLevel(), getWonExp(), getWonExpSinceLastLevel());
    }

    Rate numberNecessaryPointsForGrowingLevel(int _niveau,DataBase _import){
        return FightFacade.numberNecessaryPointsForGrowingLevel(getName(),_niveau,_import);
    }

    void changeWonPoints(int _niveauTmp,Rate _sommeDiffNiveaux, DataBase _import) {
        Fighter.changeWonPoints(_niveauTmp, _sommeDiffNiveaux, _import, getName(), getWonExp(), getWonExpSinceLastLevel());
    }

    StringList newMoves(int _tmpLevel, DataBase _import) {
        StringList newMoves_ = new StringList();
        PokemonData fPk_ = _import.getPokemon(getName());
        for(LevelMove nivAtt_: fPk_.getLevMoves()){
            if (nivAtt_.getLevel() > _tmpLevel || nivAtt_.getLevel() < getLevel()) {
                continue;
            }
            newMoves_.add(nivAtt_.getMove());
        }
        return newMoves_;
    }

    void changeLevelsEvolutions(DataBase _import) {
        int index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        PokemonData data_ = _import.getPokemon(getName());
        for (NameLevel p: evoLevels) {
            index_++;
            if (data_.getEvolutions().contains(p.getName())) {
                break;
            }
        }
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        if (evoLevels.get(index_).getLevel() <= getLevel()) {
            evoLevels.get(index_).setLevel(getLevel());
            if (!(data_.getEvolutions().getVal(evoLevels.get(index_).getName()) instanceof EvolutionStone)) {
                setName(evoLevels.get(index_).getName());
            }
            index_++;
            int evoLevels_ = evoLevels.size();
            for (int i = index_; i < evoLevels_; i++) {
                evoLevels.get(i).setLevel(NumberUtil.max(getLevel(), evoLevels.get(i).getLevel()));
            }
        }
    }

    StringList newMovesEvolution(DataBase _import) {
        StringList newMoves_ = new StringList();
        PokemonData fPk_ = _import.getPokemon(getName());
        for(LevelMove nivAtt_:fPk_.getLevMoves()){
            if(nivAtt_.getLevel() > getLevel()){
                continue;
            }
            newMoves_.add(nivAtt_.getMove());
        }
        return newMoves_;
    }

    public CustList<NameLevel> getEvoLevels() {
        return evoLevels;
    }

    public CustList<StringList> getMoves() {
        return moves;
    }

    public CustList<StringList> getAbilities() {
        return abilities;
    }

    public IntMap<String> getEvolutions() {
        return evolutions;
    }

    public CustList<NameLevel> getInfosRealEvolutions() {
        return infosRealEvolutions;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

    public Rate getWonExp() {
        return wonExp;
    }

    public void setWonExp(Rate _wonExp) {
        wonExp = _wonExp;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public boolean isFront() {
        return front;
    }

    public void setFront(boolean _front) {
        front = _front;
    }

    public Ints getFoes() {
        return foes;
    }
}
