package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.items.ItemForBattle;
import aiki.fight.pokemon.NameLevel;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PkTrainer;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public class PseudoFight {

    private final CustList<PseudoFoeFighter> foes;

    private final CustList<PseudoPlayerFighter> playerFighters;

    private final int mult;

    private final CustList<IntMap<Integer>> actions;

    public PseudoFight(CustList<PkTrainer> _foes,
            PseudoPlayer _pseudoPlayer,
            int _mult,
            CustList<IntMap<Integer>> _actions) {
        foes = new CustList<PseudoFoeFighter>();
        for (PkTrainer p: _foes) {
            foes.add(new PseudoFoeFighter(p));
        }
        playerFighters = new CustList<PseudoPlayerFighter>();
        actions = new CustList<IntMap<Integer>>();
        for (IntMap<Integer> a: _actions) {
            IntMap<Integer> map_;
            map_ = new IntMap<Integer>(a);
            actions.add(map_);
        }
        CustList<PseudoPokemonPlayer> team_ = _pseudoPlayer.getTeam();
        int nb_ = team_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            boolean front_ = !NumberUtil.eq(actions.first().getVal(i), Fighter.BACK);
            CustList<NameLevel> copy_ = new CustList<NameLevel>();
            for (NameLevel e2_: _pseudoPlayer.getEvolutions().get(i)) {
                copy_.add(new NameLevel(e2_));
            }
            playerFighters.add(new PseudoPlayerFighter(team_.get(i), front_, copy_));
        }
        mult = _mult;
        for (PseudoPlayerFighter p: playerFighters) {
            if (!p.isFront()) {
                continue;
            }
            for (int i = IndexConstants.FIRST_INDEX; i < mult; i++) {
                p.getFoes().add(i);
            }
        }
    }

    void presimulateFight(Difficulty _diff, DataBase _data) {
        int indexAction_ = IndexConstants.SECOND_INDEX;
        int indexRound_ = IndexConstants.FIRST_INDEX;
        while (true) {
            /*CustList<Integer> sent_ = new CustList<>();
            for (PseudoPlayerFighter p: playerFighters) {
                if (!p.isFront()) {
                    continue;
                }
                if (Numbers.eq(p.getSubstitute(), Fighter.BACK)) {
                    continue;
                }
                byte sub_ = p.getSubstitute();
                sent_.add(sub_);
                PseudoPlayerFighter s_ = playerFighters.get(sub_);
                s_.getFoes().addAll(p.getFoes());
                s_.getFoes().removeDuplicates();
                p.setSubstitute(Fighter.BACK);
                p.setFront(false);
            }
            for (Integer k: sent_) {
                PseudoPlayerFighter s_ = playerFighters.get(k);
                s_.setFront(true);
            }*/
            int maxIndexRound_ = indexRound_ + mult;
            for (int i = indexRound_; i < maxIndexRound_; i++) {
                if (i >= foes.size()) {
                    continue;
                }
                useFoeFightAndRemoveIt(_diff, _data, i);
            }
            for (PseudoPlayerFighter p: playerFighters) {
                p.calculateNewLevel(indexRound_, _data);
            }
            Ints foes_ = nextFoesFrom();
            if (indexAction_ >= actions.size()) {
                break;
            }
            Ints fronts_ = nextFronts(indexAction_);
            for (int k: fronts_) {
                PseudoPlayerFighter f_ = playerFighters.get(k);
                f_.setFront(true);
                f_.getFoes().addAllElts(foes_);
                f_.getFoes().removeDuplicates();
            }
            indexAction_++;
            indexRound_ += mult;
        }
    }

    private Ints nextFoesFrom() {
        int nbFoes_ = foes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbFoes_; i++) {
            if (!foes.get(i).isFought()) {
                return nextFoesFrom(i);
            }
        }
        return new Ints();
    }

    private void useFoeFightAndRemoveIt(Difficulty _diff, DataBase _data, int _i) {
        Ints playerFighters_ = playerFightersContainingFoeIndex(_i);
        addExpFighters(playerFighters_, _i, _diff, _data);
        for (PseudoPlayerFighter p: playerFighters) {
            p.setFront(false);
            p.getFoes().removeObj(_i);
        }
        foes.get(_i).setFought(true);
    }

    private Ints nextFronts(int _indexAction) {
        Ints fronts_ = new Ints();
        for (int k: actions.get(_indexAction).getKeys()) {
            if (NumberUtil.eq(actions.get(_indexAction).getVal(k), Fighter.BACK)) {
                continue;
            }
            fronts_.add(k);
        }
        return fronts_;
    }

    private Ints nextFoesFrom(int _indexFoe) {
        Ints foes_ = new Ints();
        int maxIndexFoe_ = _indexFoe + mult;
        for (int i = _indexFoe; i < maxIndexFoe_; i++) {
            if (i >= foes.size()) {
                continue;
            }
            foes_.add(i);
        }
        return foes_;
    }

    private Ints playerFightersContainingFoeIndex(int _i) {
        Ints playerFighters_ = new Ints();
        int nb_ = playerFighters.size();
        for (int indexPlayer_ = IndexConstants.FIRST_INDEX; indexPlayer_ < nb_; indexPlayer_++) {
            if (playerFighters.get(indexPlayer_).getFoes().containsObj(_i)) {
                playerFighters_.add(indexPlayer_);
            }
        }
        return playerFighters_;
    }

    void addExpFighters(Ints _membres,int _adv,Difficulty _diff,DataBase _import){
        Ints fightersBelongingToUser_ = new Ints();
        int nbFighters_ = playerFighters.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbFighters_; i++) {
            fightersBelongingToUser_.add(i);
        }
        Ints porteursMultExp_ = fightersWearingExpObject(fightersBelongingToUser_, _import);
        PseudoFoeFighter foe_ = foes.get(_adv);
        Rate points_ = foe_.pointsFoe(mult, _diff, _import);
        Rate sumMaxLevel_ = Rate.zero();
        long levelMax_ = _import.getMaxLevel();
        int nbMax_ = 0;
        for (int c: fightersBelongingToUser_) {
            if (playerFighters.get(c).getLevel() != levelMax_) {
                continue;
            }
            nbMax_++;
            addExp(c, _membres, porteursMultExp_, _adv, points_, _diff, _import);
            sumMaxLevel_.addNb(playerFighters.get(c).getWonExp());
            playerFighters.get(c).getWonExp().affectZero();
        }
        for(int c: fightersBelongingToUser_){
            if (playerFighters.get(c).getLevel() == levelMax_) {
                continue;
            }
            if (nbMax_ != 0) {
                playerFighters.get(c).getWonExp().addNb(Rate.divide(sumMaxLevel_, new Rate(nbMax_)));
            }
            addExp(c, _membres, porteursMultExp_, _adv, points_, _diff, _import);
        }
    }

    Ints fightersWearingExpObject(
            Ints _list, DataBase _import) {
        Ints list_ = new Ints();
        for (int f: _list) {
            PseudoPlayerFighter membre_= playerFighters.get(f);
            if (membre_.getItem().isEmpty()) {
                continue;
            }
            ItemForBattle obj_ = _import.usedObjectUsedForExp(membre_.getItem());
            if (obj_ != null && obj_.getBoostExp()) {
                list_.add(f);
            }
        }
        return list_;
    }

    void addExp(int _fighter,
            Ints _membres, Ints _porteursMultExp,
            int _foe, Rate _points,
            Difficulty _diff, DataBase _import) {
        PseudoPlayerFighter winner_ = playerFighters.get(_fighter);
        PseudoFoeFighter looser_ = foes.get(_foe);
        Rate gainBase_ = FightFacade.gainBase(new PointFoeExpObject(_membres,_porteursMultExp,_points,_foe),_diff,_import,winner_.getItem(),winner_.getLevel(), looser_.getLevel(),_fighter);
        winner_.getWonExp().addNb(gainBase_);
    }

    Rate rateWonPoint(int _winner, int _looser, Difficulty _diff, DataBase _import) {
        PseudoPlayerFighter winner_ = playerFighters.get(_winner);
        PseudoFoeFighter looser_ = foes.get(_looser);
        return FightFacade.rateWonPoint(_diff,_import,winner_.getLevel(),looser_.getLevel());
    }

    CustList<PseudoFoeFighter> getFoes() {
        return foes;
    }

    CustList<PseudoPlayerFighter> getPlayerFighters() {
        return playerFighters;
    }

    int getMult() {
        return mult;
    }

    CustList<IntMap<Integer>> getActions() {
        return actions;
    }
}
