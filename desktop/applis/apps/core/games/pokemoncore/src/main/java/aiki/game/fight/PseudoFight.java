package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.items.ItemForBattle;
import aiki.fight.pokemon.NameLevel;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PkTrainer;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public class PseudoFight {

    private CustList<PseudoFoeFighter> foes;

    private CustList<PseudoPlayerFighter> playerFighters;

    private byte mult;

    private CustList<ByteMap<Byte>> actions;

    public PseudoFight(CustList<PkTrainer> _foes,
            PseudoPlayer _pseudoPlayer,
            int _mult,
            CustList<ByteMap<Byte>> _actions) {
        foes = new CustList<PseudoFoeFighter>();
        for (PkTrainer p: _foes) {
            foes.add(new PseudoFoeFighter(p));
        }
        playerFighters = new CustList<PseudoPlayerFighter>();
        actions = new CustList<ByteMap<Byte>>();
        for (ByteMap<Byte> a: _actions) {
            ByteMap<Byte> map_;
            map_ = new ByteMap<Byte>(a);
            actions.add(map_);
        }
        byte index_ = CustList.FIRST_INDEX;
        for (PseudoPokemonPlayer p: _pseudoPlayer.getTeam()) {
            boolean front_;
            front_ = !Numbers.eq(actions.first().getVal(index_), Fighter.BACK);
            CustList<NameLevel> copy_ = new CustList<NameLevel>();
            for (NameLevel e2_: _pseudoPlayer.getEvolutions().get(index_)) {
                copy_.add(new NameLevel(e2_));
            }
            playerFighters.add(new PseudoPlayerFighter(p, front_, copy_));
            index_++;
        }
        mult = (byte) _mult;
        for (PseudoPlayerFighter p: playerFighters) {
            if (!p.isFront()) {
                continue;
            }
            for (byte i = CustList.FIRST_INDEX; i < mult; i++) {
                p.getFoes().add(i);
            }
        }
    }

    void presimulateFight(Difficulty _diff, DataBase _data) {
        int indexAction_ = CustList.SECOND_INDEX;
        byte indexRound_ = CustList.FIRST_INDEX;
        while (true) {
            /*CustList<Byte> sent_ = new CustList<>();
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
            for (Byte k: sent_) {
                PseudoPlayerFighter s_ = playerFighters.get(k);
                s_.setFront(true);
            }*/
            int maxIndex_ = indexRound_ + mult;
            for (byte i = indexRound_; i < maxIndex_; i++) {
                if (i >= foes.size()) {
                    continue;
                }
                Bytes playerFighters_;
                playerFighters_ = new Bytes();
                byte indexPlayer_ = CustList.FIRST_INDEX;
                for (PseudoPlayerFighter p: playerFighters) {
                    if (p.getFoes().containsObj(i)) {
                        playerFighters_.add(indexPlayer_);
                    }
                    indexPlayer_++;
                }
                addExpFighters(playerFighters_, i, _diff, _data);
                for (PseudoPlayerFighter p: playerFighters) {
                    p.setFront(false);
                    p.getFoes().removeObj(i);
                }
                foes.get(i).setFought(true);
            }
            for (PseudoPlayerFighter p: playerFighters) {
                p.calculateNewLevel(indexRound_, _data);
            }
            byte indexFoe_ = CustList.INDEX_NOT_FOUND_ELT;
            int nbFoes_ = foes.size();
            for (byte i = CustList.FIRST_INDEX; i < nbFoes_; i++) {
                if (foes.get(i).isFought()) {
                    continue;
                }
                indexFoe_ = i;
                break;
            }
            if (indexFoe_ < CustList.FIRST_INDEX) {
                break;
            }
            Bytes foes_ = new Bytes();
            maxIndex_ = indexFoe_ + mult;
            for (byte i = indexFoe_; i < maxIndex_; i++) {
                if (i >= foes.size()) {
                    continue;
                }
                foes_.add(i);
            }
            Bytes fronts_ = new Bytes();
            for (byte k: actions.get(indexAction_).getKeys()) {
                if (Numbers.eq(actions.get(indexAction_).getVal(k), Fighter.BACK)) {
                    continue;
                }
                fronts_.add(k);
            }
            for (byte k: fronts_) {
                PseudoPlayerFighter f_ = playerFighters.get(k);
                f_.setFront(true);
                f_.getFoes().addAllElts(foes_);
                f_.getFoes().removeDuplicates();
            }
            indexAction_++;
            indexRound_ += mult;
        }
    }

    void addExpFighters(Bytes _membres,byte _adv,Difficulty _diff,DataBase _import){
        Bytes fightersBelongingToUser_ = new Bytes();
        int nbFighters_ = playerFighters.size();
        for (byte i = CustList.FIRST_INDEX; i < nbFighters_; i++) {
            fightersBelongingToUser_.add(i);
        }
        Bytes porteursMultExp_ = fightersWearingExpObject(fightersBelongingToUser_, _import);
        PseudoFoeFighter foe_ = foes.get(_adv);
        Rate points_ = foe_.pointsFoe(mult, _diff, _import);
        Rate sumMaxLevel_ = Rate.zero();
        short levelMax_ = (short) _import.getMaxLevel();
        short nbMax_ = 0;
        for (byte c: fightersBelongingToUser_) {
            if (playerFighters.get(c).getLevel() != levelMax_) {
                continue;
            }
            nbMax_++;
            addExp(c, _membres, porteursMultExp_, _adv, points_, _diff, _import);
            sumMaxLevel_.addNb(playerFighters.get(c).getWonExp());
            playerFighters.get(c).getWonExp().affectZero();
        }
        for(byte c: fightersBelongingToUser_){
            if (playerFighters.get(c).getLevel() == levelMax_) {
                continue;
            }
            if (nbMax_ != 0) {
                playerFighters.get(c).getWonExp().addNb(Rate.divide(sumMaxLevel_, new Rate(nbMax_)));
            }
            addExp(c, _membres, porteursMultExp_, _adv, points_, _diff, _import);
        }
    }

    Bytes fightersWearingExpObject(
            Bytes _list, DataBase _import) {
        Bytes list_ = new Bytes();
        for (byte f: _list) {
            PseudoPlayerFighter membre_= playerFighters.get(f);
            if (membre_.getItem().isEmpty()) {
                continue;
            }
            if (!_import.isObjectUsedForExp(membre_.getItem())) {
                continue;
            }
            ItemForBattle objet_=(ItemForBattle) _import.getItem(membre_.getItem());
            if(!objet_.getBoostExp()){
                continue;
            }
            list_.add(f);
        }
        return list_;
    }

    void addExp(byte _fighter,
            Bytes _membres, Bytes _porteursMultExp,
            byte _foe, Rate _points,
            Difficulty _diff, DataBase _import) {
        byte nbPorteursMultExp_=(byte) _porteursMultExp.size();
        PseudoPlayerFighter membre_=playerFighters.get(_fighter);
        byte presCbt_=0;
        if(_membres.containsObj(_fighter)){
            presCbt_=1;
        }
        byte portMultExp_=0;
        if(_porteursMultExp.containsObj(_fighter)){
            portMultExp_=1;
        }
        Rate a_;
        if(nbPorteursMultExp_>0){
            a_=new Rate(portMultExp_,2*nbPorteursMultExp_);
        } else {
            a_=Rate.zero();
        }
        Rate b_;
        if(!_membres.isEmpty()){
            if(nbPorteursMultExp_>0){
                b_=new Rate(presCbt_,_membres.size()*2);
            } else {
                b_=new Rate(presCbt_,_membres.size());
            }
        } else {
            b_=Rate.zero();
        }
        Rate gainBase_=Rate.plus(a_,b_);
        gainBase_.multiplyBy(_points);
        Rate rate_ = rateWonPoint(_fighter, _foe, _diff, _import);
        gainBase_.multiplyBy(rate_);
        if (_import.isObjectUsedForExp(membre_.getItem())) {
            ItemForBattle obj_ = (ItemForBattle) _import.getItem(membre_.getItem());
            if (!obj_.getMultWinningExp().isZero()) {
                gainBase_.multiplyBy(obj_.getMultWinningExp());
            }
        }
        membre_.getWonExp().addNb(gainBase_);
    }

    Rate rateWonPoint(byte _winner, byte _looser, Difficulty _diff, DataBase _import) {
        PseudoPlayerFighter winner_ = playerFighters.get(_winner);
        PseudoFoeFighter looser_ = foes.get(_looser);
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LEVEL_WINNER),Integer.toString(winner_.getLevel()));
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LEVEL_LOOSER),Integer.toString(looser_.getLevel()));
        String exp_ = _import.getRates().getVal(_diff.getDiffWinningExpPtsFight());
        return _import.evaluatePositiveExp(exp_, vars_, Rate.one());
    }

    CustList<PseudoFoeFighter> getFoes() {
        return foes;
    }

    CustList<PseudoPlayerFighter> getPlayerFighters() {
        return playerFighters;
    }

    byte getMult() {
        return mult;
    }

    CustList<ByteMap<Byte>> getActions() {
        return actions;
    }
}
