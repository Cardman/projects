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
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class PseudoPlayerFighter extends PseudoFighter {

    private String item;

    private Rate wonExp;

    private final Rate wonExpSinceLastLevel;

    private boolean front;

    private final Bytes foes;

    private final CustList<NameLevel> evoLevels;

    private final CustList<StringList> moves;

    private final CustList<StringList> abilities;

    private final ByteMap<String> evolutions;

    private final CustList<NameLevel> infosRealEvolutions;

    public PseudoPlayerFighter(PseudoPokemonPlayer _pseudo, boolean _front, CustList<NameLevel> _evoLevels) {
        super(_pseudo.getName(), _pseudo.getLevel());
        item = _pseudo.getItem();
        wonExp = Rate.zero();
        wonExpSinceLastLevel = new Rate(_pseudo.getWonPointsSinceLastLevel());
        front = _front;
        foes = new Bytes();
        evoLevels = _evoLevels;
        moves = new CustList<StringList>();
        abilities = new CustList<StringList>();
        evolutions = new ByteMap<String>();
        infosRealEvolutions = new CustList<NameLevel>();
    }

    void calculateNewLevel(int _round,DataBase _import) {
        LevelExpPoints levelWonPoints_ = newLevelWonPoints(_import);
        short achievedLevel_ = levelWonPoints_.getLevel();
        Rate sommeDiffNiveaux_ = levelWonPoints_.getExpPoints();
        short maxNiveau_=(short) _import.getMaxLevel();
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
            evolutions.put((byte) _round, getName());
        }
        abilities.add(abilities_);
        moves.add(moves_);
    }

    LevelExpPoints newLevelWonPoints(DataBase _import) {
        short niveauTmp_=getLevel();
        niveauTmp_++;
        Rate sommeDiffNiveaux_=numberNecessaryPointsForGrowingLevel(niveauTmp_,_import);
        niveauTmp_--;
        short maxNiveau_=(short) _import.getMaxLevel();
        while(true){
            Rate sum_ = Rate.plus(getWonExp(), getWonExpSinceLastLevel());
            if (Rate.strLower(sum_, sommeDiffNiveaux_)){
                break;
            }
            niveauTmp_++;
            if (niveauTmp_ >= maxNiveau_) {
                if (niveauTmp_ > maxNiveau_) {
                    niveauTmp_--;
                }
                break;
            }
            sommeDiffNiveaux_.addNb(numberNecessaryPointsForGrowingLevel((short) (niveauTmp_+1),_import));
        }
        return new LevelExpPoints(niveauTmp_,sommeDiffNiveaux_);
    }

    Rate numberNecessaryPointsForGrowingLevel(short _niveau,DataBase _import){
        PokemonData fPk_=_import.getPokemon(getName());
        String expLitt_=_import.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(_niveau));
        Rate next_;
        next_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(_niveau - 1L));
        current_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        return _import.evaluatePositiveExp(Rate.minus(next_, current_).toNumberString(), vars_, Rate.one());
    }

    void changeWonPoints(short _niveauTmp,Rate _sommeDiffNiveaux, DataBase _import) {
        short maxNiveau_=(short) _import.getMaxLevel();
        if(NumberUtil.eq(_niveauTmp,maxNiveau_)){
            //cas gainExperience+gainExperienceDepuisDernierNiveau>=sommeDiffNiveaux_:
            //==> gainExperience+gainExperienceDepuisDernierNiveau-sommeDiffNiveaux_>=0
            //==> apres affectation gainExperience>=0
            //gainExperience+gainExperienceDepuisDernierNiveau-sommeDiffNiveaux_>=0
            getWonExp().addNb(Rate.minus(getWonExpSinceLastLevel(), _sommeDiffNiveaux));
            getWonExpSinceLastLevel().affectZero();
        }else{
            //cas niveauTmp_ ne change pas:
            //gainExperience>=0 et sommeDiffNiveaux_==nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)
            //==> gainExperience-sommeDiffNiveaux_+nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)>=0
            //cas niveauTmp_ change:
            //gainExperienceDepuisDernierNiveau+gainExperience<sommeDiffNiveaux_(n_i,niveauTmp_) fin
            //==> gainExperienceDepuisDernierNiveau+gainExperience>=sommeDiffNiveaux_(n_i,niveauTmp_-1)
            //==> gainExperienceDepuisDernierNiveau+gainExperience-sommeDiffNiveaux_(n_i,niveauTmp_)>=sommeDiffNiveaux_(n_i,niveauTmp_-1)-sommeDiffNiveaux_(n_i,niveauTmp_)
            //==> gainExperienceDepuisDernierNiveau+gainExperience-sommeDiffNiveaux_(n_i,niveauTmp_)>=-nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)
            //==> gainExperienceDepuisDernierNiveau+gainExperience-sommeDiffNiveaux_(n_i,niveauTmp_)+nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)>=0
            //==> apres affectation gainExperienceDepuisDernierNiveau>=0
            getWonExpSinceLastLevel().addNb(Rate.minus(getWonExp(), _sommeDiffNiveaux));
            getWonExpSinceLastLevel().addNb(numberNecessaryPointsForGrowingLevel((short) (_niveauTmp+1),_import));
            getWonExp().affectZero();
        }
    }

    StringList newMoves(short _tmpLevel, DataBase _import) {
        StringList newMoves_ = new StringList();
        PokemonData fPk_ = _import.getPokemon(getName());
        for(LevelMove nivAtt_: fPk_.getLevMoves()){
            if(nivAtt_.getLevel()>_tmpLevel){
                continue;
            }
            if(nivAtt_.getLevel()<getLevel()){
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
                evoLevels.get(i).setLevel((short) Math.max(getLevel(), evoLevels.get(i).getLevel()));
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

    public ByteMap<String> getEvolutions() {
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

    public Bytes getFoes() {
        return foes;
    }
}
