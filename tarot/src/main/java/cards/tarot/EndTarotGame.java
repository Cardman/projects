package cards.tarot;

import cards.consts.EndGameState;
import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.comparators.HandfulComparator;
import cards.tarot.comparators.MiseresComparator;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.EndDealTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.TreeMap;

public final class EndTarotGame {

    public static final int NO_OUDLER_PTS = 56;
    public static final int ONE_OUDLER_PTS = 51;
    public static final int TWO_OUDLERS_PTS = 41;
    public static final int ALL_OUDLERS_PTS = 36;

    public static final int PTS_BASE = 25;
    private GameTarotTeamsRelation relations;
    private CustList<TrickTarot> tricks;
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private CustList<EnumList<Handfuls>> declaresHandfuls;
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private EqList<EnumList<Miseres>> declaresMiseres;
    /** Ce sont les primes annoncees par le(s) joueur(s) */
    private BooleanList declaresSlam;
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private BooleanList smallBound;

    public EndTarotGame(GameTarotTeamsRelation _relations, CustList<TrickTarot> _tricks,
                        CustList<EnumList<Handfuls>> _declaresHandfuls,
                        EqList<EnumList<Miseres>> _declaresMiseres,
                        BooleanList _declaresSlam, BooleanList _smallBound) {
        relations = _relations;
        tricks = _tricks;
        declaresHandfuls = _declaresHandfuls;
        declaresMiseres = _declaresMiseres;
        declaresSlam = _declaresSlam;
        smallBound = _smallBound;
    }

    public short scorePreneurPlisDouble(BidTarot _bid) {
        short nbPointsAtt_ = 0;
        for (CardTarot c: getWonCardsTaker(_bid)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }

    public byte nombreBoutsPreneur(BidTarot _bid) {
        byte nombreBouts_ = 0;
        for (CardTarot c: getWonCardsTaker(_bid)) {
            if (c.estUnBout()) {
                nombreBouts_++;
            }
        }
        return nombreBouts_;
    }

    HandTarot getWonCardsTaker(BidTarot _bid) {
        return getWonCardsPlayer(relations.getTaker(),_bid);
    }

    HandTarot getWonCardsPlayer(byte _player,BidTarot _bid) {
        HandTarot nbPointsAtt_ = new HandTarot();
        boolean chelemAttaque_ =false;
        boolean chelemDefense_ =false;
        if(aucunPliAdverseFin(_player, tricks)) {
            chelemAttaque_ = true;
        }
        if(!chelemAttaque_ && slamTeam()) {
            chelemDefense_ = true;
        }
        boolean excuseEcartee_ = tricks.first().contient(CardTarot.excuse());
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (_bid.getJeuChien() != PlayingDog.AGAINST) {
            tricks_.add(tricks.first());
        }
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            tricks_.add(t);
        }
        for (TrickTarot pli_ : tricks_) {
            for (CardTarot carte_ : pli_) {
                nbPointsAtt_.ajouter(carte_);
            }
        }
        if(excuseEcartee_) {
            return nbPointsAtt_;
        }
        boolean excuseDansPlisAttaque_ = false;
        for (TrickTarot pli_ : tricks_) {
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
        }
        CustList<TrickTarot> otherTricks_ = new CustList<TrickTarot>();
        for (TrickTarot t: tricks) {
            CardTarot cardTarot_ = t.premiereCarte();
            boolean other_ = true;
            for (TrickTarot pli_ : tricks_) {
                if (cardTarot_ == pli_.premiereCarte()) {
                    other_ = false;
                }
            }
            if (other_) {
                otherTricks_.add(t);
            }
        }
        CustList<TrickTarot> excuseTrick_;
        if(chelemAttaque_) {
            excuseTrick_ = getExcuseTrick(tricks_);
        } else if(chelemDefense_) {
            excuseTrick_ = getExcuseTrick(otherTricks_);
        } else {
            if(excuseDansPlisAttaque_) {
                excuseTrick_ = getExcuseTrick(tricks_);
            } else {
                excuseTrick_ = getExcuseTrick(otherTricks_);
            }
        }
        for (TrickTarot pli_ : excuseTrick_) {
            byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
            nbPointsAtt_.removeCardIfPresent(CardTarot.EXCUSE);
            if(relations.memeEquipe(_player, joueurExcuse_)) {
                nbPointsAtt_.ajouter(CardTarot.EXCUSE);
            }
        }
        return nbPointsAtt_;
    }

    private static CustList<TrickTarot> getExcuseTrick(CustList<TrickTarot> _trs) {
        CustList<TrickTarot> t_ = new CustList<TrickTarot>();
        if (_trs.last().contient(CardTarot.EXCUSE)) {
            return t_;
        }
        for (TrickTarot pli_ : _trs) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.contient(CardTarot.EXCUSE)) {
                t_.add(pli_);
            }
        }
        return t_;
    }
    public short scorePreneurPlis(short _scorePreneurPlisDouble,
                                  short _scoreNecessairePreneur) {
        short scorePreneurPlis_ = (short) (_scorePreneurPlisDouble / 2);
        if (scorePreneurPlis_ >= _scoreNecessairePreneur) {
            if (_scorePreneurPlisDouble % 2 == 1) {
                scorePreneurPlis_++;
            }
        } else if (scorePreneurPlis_ + 1 == _scoreNecessairePreneur) {
            if (relations.getRules().getEndDealTarot() == EndDealTarot.ATTACK_WIN) {
                if (_scorePreneurPlisDouble % 2 == 1) {
                    scorePreneurPlis_++;
                }
            }
        }
        return scorePreneurPlis_;
    }
    public short scoreNecessairePreneur(BidTarot _bid) {
        byte nombreBouts_ = nombreBoutsPreneur(_bid);
        if (nombreBouts_ == 0) {
            return NO_OUDLER_PTS;
        }
        if (nombreBouts_ == 1) {
            return ONE_OUDLER_PTS;
        }
        if (nombreBouts_ == 2) {
            return TWO_OUDLERS_PTS;
        }
        return ALL_OUDLERS_PTS;
    }
    public short base(short _scorePreneurPlisDouble,
                      short _differenceScorePreneur) {
        if (_differenceScorePreneur >= 0) {
            return PTS_BASE;
        }
        if (_differenceScorePreneur == -1
                && _scorePreneurPlisDouble % 2 == 1) {
            if (relations.getRules().getEndDealTarot() == EndDealTarot.ATTACK_LOOSE) {
                return -PTS_BASE;
            }
            if (relations.getRules().getEndDealTarot() == EndDealTarot.ZERO) {
                return 0;
            }
            return PTS_BASE;
        }
        return -PTS_BASE;
    }

    public short scorePreneurSansAnnonces(short _differenceScorePreneur,
                                          short _base) {
        short scorePreneurSansAnnonces_ = 0;
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        if (_base != 0) {
            scorePreneurSansAnnonces_ = (short) (_base + _differenceScorePreneur);
            if (smallBound.get(relations.getTaker())) {
                scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                        .getPoints();
            }
            if (relations.existeAppele()) {
                boolean appelePetitAuBout_ = false;
                for (byte a: relations.getCalledPlayers()) {
                    if (smallBound.get(a)) {
                        appelePetitAuBout_ = true;
                    }
                }
                if(appelePetitAuBout_) {
                    scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                            .getPoints();
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ != relations.getTaker() && !relations.getCalledPlayers().containsObj(joueur_)
                        && smallBound.get(joueur_)) {
                    scorePreneurSansAnnonces_ -= BonusTarot.SMALL_BOUND
                            .getPoints();
                }
            }
        }
        return scorePreneurSansAnnonces_;
    }

    public Shorts calculateScores(EnumMap<Status,Rate> _coefficientsRepartition,
                                short _sommeTemporaire, short _scorePreneurSansAnnonces) {
        Shorts scores_ = new Shorts();
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.add((short) 0);
        }
        byte parite_;
        if (_sommeTemporaire == 0) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                scores_.set( joueur_, (short) 0);
            }
        } else {
            if (_sommeTemporaire / 2 * 2 == _sommeTemporaire) {
                parite_ = 0;
            } else {
                parite_ = 1;
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                Status st_ = relations.statutDe(joueur_);
                Rate rate_ = _coefficientsRepartition.getVal(st_);
                if (st_ == Status.DEFENDER) {
                    scores_.set(joueur_,
                            (short) Rate.multiply(rate_, new Rate(_sommeTemporaire)).ll());
                } else if (st_ == Status.CALLED_PLAYER) {

                    Rate mult_ = Rate.multiply(new Rate(rate_.getNumeratorCopy()), new Rate(_sommeTemporaire));
                    if (_scorePreneurSansAnnonces > 0) {
                        mult_.removeNb(new Rate(parite_));
                    } else {
                        mult_.addNb(new Rate(parite_));
                    }
                    mult_.divideBy(new Rate(rate_.getDenominatorCopy()));
                    scores_.set(joueur_, (short) mult_.ll());
                } else {
                    Rate mult_ = Rate.multiply(new Rate(rate_.getNumeratorCopy()), new Rate(_sommeTemporaire));
                    if (_scorePreneurSansAnnonces > 0) {
                        mult_.addNb(new Rate(parite_));
                    } else {
                        mult_.removeNb(new Rate(parite_));
                    }
                    mult_.divideBy(new Rate(rate_.getDenominatorCopy()));
                    scores_.set(joueur_, (short) mult_.ll());
                }
            }
        }
        return scores_;
    }

    public byte joueurPetitAuBout() {
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (smallBound.get(joueur_)) {
                return joueur_;
            }
        }
        return -1;
    }

    public CustList<TreeMap<Miseres,Short>> getMiseresPointsForTaker() {

        CustList<TreeMap<Miseres,Short>> scores1_ = new CustList<TreeMap<Miseres,Short>>();
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            TreeMap<Miseres, Short> miseresPlayer_ = new TreeMap<Miseres, Short>(new MiseresComparator());
            scores1_.add(miseresPlayer_);
            if (relations.aPourDefenseur(joueur_)) {
                feedMiseres(miseresPlayer_,joueur_,-1);
            } else {
                feedMiseres(miseresPlayer_,joueur_,1);
            }
        }
        return scores1_;

    }
    private void feedMiseres(TreeMap<Miseres, Short> _miseres,int _player, int _rate) {
        for (Miseres m : declaresMiseres.get(_player)) {
            _miseres.put(m,
                    (short) (_rate*m.getPoints()));
        }
    }
    public CustList<TreeMap<Handfuls,Short>> getHandfulsPointsForTaker(short _pointsTakerWithoutDeclaring) {

        CustList<TreeMap<Handfuls,Short>> scores1_ = new CustList<TreeMap<Handfuls,Short>>();
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            scores1_.add(new TreeMap<Handfuls,Short>(new HandfulComparator()));
            for (Handfuls poignee_ : declaresHandfuls.get(joueur_)) {
                if (_pointsTakerWithoutDeclaring >= 0) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                } else {
                    scores1_.last().put(poignee_,
                            (short) (-poignee_.getPoints()));
                }
            }
        }
        return scores1_;

    }

    public short additionnalBonusesAttack(BidTarot _bid) {
        short primesSupplementaires_ =0;
        CustList<TrickTarot> plisDefense_ = getPlisDefense(_bid);
        if (_bid == BidTarot.SLAM || declaresSlam.get(relations.getTaker())) {
            if (plisDefense_.isEmpty()
                    || !plisDefense_.last().getVuParToutJoueur()) {
                primesSupplementaires_ = (short) BonusTarot.SLAM
                        .getPoints();
            } else {
                primesSupplementaires_ = (short) (-BonusTarot.SLAM
                        .getPoints() / 2);
            }
            return primesSupplementaires_;
        }
        if (plisDefense_.isEmpty()
                || !plisDefense_.last().getVuParToutJoueur()) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }
    public short additionnalBonusesDefense(BidTarot _bid) {
        short primesSupplementaires_ = 0;
        CustList<TrickTarot> plisAttaque_ = getPlisAttaque(_bid);
        if (plisAttaque_.isEmpty()
                || !plisAttaque_.last().getVuParToutJoueur()) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }

    public short temporarySum(BidTarot _bid,short _scorePreneurSansAnnonces,
                              CustList<TreeMap<Miseres,Short>> _miseres,
                              CustList<TreeMap<Handfuls,Short>> _handfuls, short _primesSupplementairesAttack,
                              short _primesSupplementairesDefense) {
        short sommeTemporaire_ = 0;
        for (TreeMap<Miseres,Short> m: _miseres) {
            for (short p: m.values()) {
                sommeTemporaire_+=p;
            }
        }
        for (TreeMap<Handfuls,Short> h: _handfuls) {
            for (short p: h.values()) {
                sommeTemporaire_+=p;
            }
        }
        sommeTemporaire_ += _primesSupplementairesAttack
                - _primesSupplementairesDefense;
        if (_scorePreneurSansAnnonces != 0) {
            return (short) (_bid.getCoefficient() * _scorePreneurSansAnnonces + sommeTemporaire_);
        }
        return 0;
    }

    public EnumMap<Status,Rate> coefficientsRepartition() {
        EnumMap<Status,Rate> coefficientsRepartition_;
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        coefficientsRepartition_ = new EnumMap<Status,Rate>();
        if (!relations.existeAppele()) {
            coefficientsRepartition_.put(Status.TAKER,new Rate(nombreJoueurs_ - 1));
            coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
        } else {
            if (nombreJoueurs_ == 4) {
                if (relations.getRules().getRepartition().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(1));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(3,2));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1,2));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                }
            } else if (nombreJoueurs_ == 5) {
                coefficientsRepartition_.put(Status.TAKER,new Rate(2));
                coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
            } else if (nombreJoueurs_ == 6) {
                if (relations.getRules().getRepartition().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(2));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(2));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(3));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                }
            }
        }
        return coefficientsRepartition_;
    }


    public EndGameState getUserState(short _scorePreneurSansAnnonces, byte _user) {
        if (!relations.aPourDefenseur(_user)) {
            if (_scorePreneurSansAnnonces > 0) {
                return EndGameState.WIN;
            }
            if (_scorePreneurSansAnnonces == 0) {
                return EndGameState.EQUALLITY;
            }
            return EndGameState.LOOSE;
        }
        if (_scorePreneurSansAnnonces < 0) {
            return EndGameState.WIN;
        }
        if (_scorePreneurSansAnnonces == 0) {
            return EndGameState.EQUALLITY;
        }
        return EndGameState.LOOSE;
    }

    public short scoreJoueurPlisDouble(byte _joueur) {
        short nbPointsAtt_ = CustList.SIZE_EMPTY;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }
    boolean slamTeam() {
        CustList<Bytes> teams_ = relations.teams();
        int noTrick_ = 0;
        for (Bytes t: teams_) {
            if (getWonTricksListTeam(tricks,t).isEmpty()) {
                noTrick_++;
            }
        }
        return noTrick_ == teams_.size() -1;
    }

    public short scoreNecessaireJoueur(byte _joueur) {


        short nombreBouts_ = nombreDeBoutsJoueur(_joueur);
        if (nombreBouts_ == 0) {
            return NO_OUDLER_PTS;
        }
        if (nombreBouts_ == 1) {
            return ONE_OUDLER_PTS;
        }
        if (nombreBouts_ == 2) {
            return TWO_OUDLERS_PTS;
        }
        return ALL_OUDLERS_PTS;
    }

    public short nombreDeBoutsJoueur(byte _joueur) {
        byte nombreBouts_ = 0;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            if (c.estUnBout()) {
                nombreBouts_++;
            }
        }
        return nombreBouts_;
    }

    public static short differenceJoueurDouble(short _scoreNecessaireJoueur,
                                               short _scoreJoueurPlisDouble) {
        return (short) (_scoreJoueurPlisDouble - 2 * _scoreNecessaireJoueur);
    }

    public static short differenceJoueurDoubleMisere(
            short _scoreNecessaireJoueur, short _scoreJoueurPlisDouble) {
        return (short) (2 * _scoreNecessaireJoueur - _scoreJoueurPlisDouble);
    }

    public static Shorts positionsDifference(Shorts _differences) {
        Shorts positions_ = new Shorts();
        int nbDiff_ = _differences.size();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbDiff_; joueur_++) {
            positions_.add((short) 1);
            for (short difference_ : _differences) {
                if (difference_ > _differences.get(joueur_)) {
                    positions_.set(joueur_, (short) (positions_.get(joueur_)+1));
                }
            }
        }
        return positions_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Shorts changePositionsOne(Shorts _positions, boolean _pasJeuMisere) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_ = new CustList<Shorts>();
        Shorts positionsDistinctes_ = new Shorts();
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        byte nombreBouts2_;
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Shorts());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Shorts> ensemblesPluriels_ = new CustList<Shorts>();
        for (Shorts g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        for (short joueur2_ : groupe_) {
                            main2_ = getWonTricksTeam((byte) joueur2_);
                            nombreBouts2_ = (byte) main2_.nombreDeBouts();
                            if (nombreBouts2_ > nombreBouts_) {
                                positionTemporaire_++;
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        } else {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        for (short joueur2_ : groupe_) {
                            main2_ = getWonTricksTeam((byte)joueur2_);
                            nombreBouts2_ = (byte) main2_.nombreDeBouts();
                            if (nombreBouts2_ < nombreBouts_) {
                                positionTemporaire_++;
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        }
        return positions_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Shorts changePositionsTwo(Shorts _positions, boolean _pasJeuMisere) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_;
        Shorts positionsDistinctes_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        byte nombreBouts_;
        byte nombreFigures_;
        byte nombreFigures2_;
        byte positionTemporaire_;
        CardTarot bout_;
        CardTarot bout2_;
        groupes_ = new CustList<Shorts>();
        positionsDistinctes_ = new Shorts();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Shorts());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Shorts> ensemblesPluriels_ = new CustList<Shorts>();
        for (Shorts g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        nombreFigures_ = (byte) main_.nombreDeFigures();
                        if (nombreBouts_ == 0) {
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                nombreFigures2_ = (byte) main2_
                                        .nombreDeFigures();
                                if (nombreFigures2_ > nombreFigures_) {
                                    positionTemporaire_++;
                                }
                            }
                        } else {
                            bout_ = main_.bouts().premiereCarte();
                            if (CardTarot.eq(bout_,CardTarot.excuse())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_,CardTarot.vingtEtUn())) {
                                        positionTemporaire_++;
                                    }
                                }
                            } else if (CardTarot.eq(bout_,CardTarot.petit())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_,CardTarot.excuse())
                                            || CardTarot.eq(bout2_,CardTarot
                                            .vingtEtUn())) {
                                        positionTemporaire_++;
                                    }
                                }
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        } else {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        nombreFigures_ = (byte) main_.nombreDeFigures();
                        if (nombreBouts_ == 0) {
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                nombreFigures2_ = (byte) main2_
                                        .nombreDeFigures();
                                if (nombreFigures2_ < nombreFigures_) {
                                    positionTemporaire_++;
                                }
                            }
                        } else {
                            bout_ = main_.bouts().premiereCarte();
                            if (CardTarot.eq(bout_, CardTarot.excuse())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_, CardTarot.petit())) {
                                        positionTemporaire_++;
                                    }
                                }
                            } else if (CardTarot.eq(bout_, CardTarot.vingtEtUn())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_, CardTarot.excuse())
                                            || CardTarot.eq(bout2_, CardTarot.petit())) {
                                        positionTemporaire_++;
                                    }
                                }
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        }
        return positions_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Shorts changePositionsThree(Shorts _positions, boolean _pasJeuMisere) {
        Shorts positions_ = new Shorts(_positions);

        CustList<Shorts> groupes_;
        Shorts positionsDistinctes_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        HandTarot figures_;
        HandTarot figures2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        groupes_ = new CustList<Shorts>();
        positionsDistinctes_ = new Shorts();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Shorts());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Shorts> ensemblesPluriels_ = new CustList<Shorts>();
        for (Shorts g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                positionTemporaire_ = incrementPosByLowerPoints(
                                        figures_, figures2_, positionTemporaire_);
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        } else {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                positionTemporaire_ = incrementPosByGreaterPoints(
                                        figures_, figures2_, positionTemporaire_);
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        }
        return positions_;
    }

    private static byte incrementPosByGreaterPoints(HandTarot _charactersOne,
                                                    HandTarot _charactersTwo, byte _positionTmp) {
        byte positionTmp_ = _positionTmp;
        int nbCharacters_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCharacters_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() < _charactersOne
                    .carte(indiceFigure_).points()) {
                positionTmp_++;
                break;
            } else if (_charactersTwo.carte(indiceFigure_)
                    .points() > _charactersOne.carte(
                    indiceFigure_).points()) {
                break;
            }
        }
        return positionTmp_;
    }

    private static byte incrementPosByLowerPoints(HandTarot _charactersOne,
                                                  HandTarot _charactersTwo, byte _positionTmp) {
        byte positionTmp_ = _positionTmp;
        int nbCharacters_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCharacters_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() > _charactersOne
                    .carte(indiceFigure_).points()) {
                positionTmp_++;
                break;
            } else if (_charactersTwo.carte(indiceFigure_)
                    .points() < _charactersOne.carte(
                    indiceFigure_).points()) {
                break;
            }
        }
        return positionTmp_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Shorts changePositionsFour(Shorts _positions, boolean _pasJeuMisere) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_;
        Shorts positionsDistinctes_;
        boolean egaliteFigures_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        HandTarot figures_;
        HandTarot figures2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        groupes_ = new CustList<Shorts>();
        positionsDistinctes_ = new Shorts();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Shorts());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Shorts> ensemblesPluriels_ = new CustList<Shorts>();
        for (Shorts g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                egaliteFigures_ = equalChars(figures_, figures2_);
                                int indexOne_ = -1;
                                int indexTwo_ = -1;
                                int index_ = 0;
                                for (TrickTarot t: tricks) {
                                    if (!t.getVuParToutJoueur()) {
                                        index_++;
                                        continue;
                                    }
                                    if (relations.memeEquipe(t.getRamasseur(), (byte) joueur_)) {
                                        if (indexOne_ == -1) {
                                            indexOne_ = index_;
                                        }
                                    }
                                    if (relations.memeEquipe(t.getRamasseur(), (byte) joueur2_)) {
                                        if (indexTwo_ == -1) {
                                            indexTwo_ = index_;
                                        }
                                    }
                                    index_++;
                                }
                                if (egaliteFigures_
                                        && indexTwo_ <
                                        indexOne_) {
                                    positionTemporaire_++;
                                }
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        } else {
            for (Shorts groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                egaliteFigures_ = equalChars(figures_, figures2_);
                                int indexOne_ = -1;
                                int indexTwo_ = -1;
                                int index_ = 0;
                                for (TrickTarot t: tricks) {
                                    if (!t.getVuParToutJoueur()) {
                                        index_++;
                                        continue;
                                    }
                                    if (relations.memeEquipe(t.getRamasseur(), (byte) joueur_)) {
                                        if (indexOne_ == -1) {
                                            indexOne_ = index_;
                                        }
                                    }
                                    if (relations.memeEquipe(t.getRamasseur(), (byte) joueur2_)) {
                                        if (indexTwo_ == -1) {
                                            indexTwo_ = index_;
                                        }
                                    }
                                    index_++;
                                }
                                if (egaliteFigures_
                                        && indexTwo_ >
                                        indexOne_) {
                                    positionTemporaire_++;
                                }
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        }
        return positions_;
    }

    private static boolean equalChars(HandTarot _charactersOne,
                                      HandTarot _charactersTwo) {
        boolean egaliteFigures_;
        egaliteFigures_ = true;
        int nbCards_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCards_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() != _charactersOne
                    .carte(indiceFigure_).points()) {
                egaliteFigures_ = false;
                break;
            }
        }
        return egaliteFigures_;
    }

    public Shorts coefficients(Shorts _positions) {
        byte maxPosition_ = 0;
        byte nombreLitiges_;
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            maxPosition_ = (byte) Math.max(position_, maxPosition_);
        }
        nombreLitiges_ = (byte) (nombreJoueurs_ - maxPosition_ + 1);
        if (relations.getRules().getRepartition() == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (relations.getRules().getRepartition() == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            Shorts positionsDist_ = new Shorts(_positions);
            positionsDist_.removeDuplicates();
            if (positionsDist_.size() == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 3) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 4) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 3);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 6);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 5) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 4) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 3);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 6);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 8);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -1);
                } else if (position_ == 5) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 4);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 4) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 8);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add((short) 10);
            } else {
                coefficients_.add((short) -2);
            }
        }
        return coefficients_;
    }

    public Shorts coefficientsMisere(Shorts _positions) {
        byte maxPosition_ = 0;
        byte nombreLitiges_;
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            maxPosition_ = (byte) Math.max(position_, maxPosition_);
        }
        nombreLitiges_ = (byte) (nombreJoueurs_ - maxPosition_ + 1);
        if (relations.getRules().getRepartition() == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (relations.getRules().getRepartition() == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            Shorts positionsDist_ = new Shorts(_positions);
            positionsDist_.removeDuplicates();
            if (positionsDist_.size() == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 3) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 4) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -3);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -6);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 5) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 4) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -3);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -6);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -8);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -1);
                } else if (position_ == 5) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else if (position_ == 3) {
                    coefficients_.add((short) -1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else if (position_ == 3) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -4);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 4) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -8);
                }
            }
            return coefficients_;
        }
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add((short) 2);
            } else {
                coefficients_.add((short) -10);
            }
        }
        return coefficients_;
    }

    public CustList<EnumMap<Handfuls,Short>> calculHandfulsScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        CustList<EnumMap<Handfuls,Short>> scores1_ = new CustList<EnumMap<Handfuls,Short>>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Handfuls,Short>());

            if (joueur2_ == _player) {
                for (Handfuls poignee_ : declaresHandfuls.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            } else {
                for (Handfuls poignee_ : declaresHandfuls.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) -poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }
    public CustList<EnumMap<Miseres,Short>> calculMiseresScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        CustList<EnumMap<Miseres,Short>> scores1_ = new CustList<EnumMap<Miseres,Short>>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Miseres,Short>());

            if (joueur2_ == _player) {
                for (Miseres poignee_ : declaresMiseres.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            } else {
                for (Miseres poignee_ : declaresMiseres.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) -poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }
    public CustList<Shorts> calculSmallLastTurnScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        CustList<Shorts> scores1_ = new CustList<Shorts>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new Shorts());

            if (joueur2_ == _player) {
                if (smallBound.get(joueur2_)) {
                    scores1_.last().add(
                            (short) BonusTarot.SMALL_BOUND.getPoints());
                }
            } else {
                if (smallBound.get(joueur2_)) {
                    scores1_.last().add(
                            (short) -BonusTarot.SMALL_BOUND.getPoints());
                }
            }
        }
        return scores1_;
    }

    public short primeSupplementaire(byte _joueur) {
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        CustList<TrickTarot> plisAdversaires_ = new CustList<TrickTarot>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreJoueurs_; joueur2_++) {
            if (!relations.memeEquipe(_joueur, joueur2_)) {
                plisAdversaires_.addAllElts(getWonTricksListTeam(joueur2_));
            }
        }
        if (plisAdversaires_.isEmpty()) {
            return (short) (BonusTarot.SLAM.getPoints() / 2);
        }
        return 0;
    }

    public Shorts calculerScoresJoueurs(Shorts _coefficients,
                                      short _differenceMaxDouble) {
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        Shorts scores_ = new Shorts();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.add((short)0);
        }
        byte nombrePointsChien_ = 0;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.set(joueur_,
                    (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + (_differenceMaxDouble
                            + nombrePointsChien_ + parite_) / 2))));
        }
        return scores_;


    }

    public short differenceMax(short _differenceMaxDouble) {
        byte nombrePointsChien_ = 0;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        return (short) ((_differenceMaxDouble + nombrePointsChien_ + parite_) / 2);
    }

    public Shorts calculerScoresJoueurs(Shorts _coefficients,
                                      short _differenceMaxDouble, Shorts _primeSupplementaire) {

        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        Shorts scores_ = new Shorts();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.add((short)0);
        }
        byte nombrePointsChien_ = CustList.SIZE_EMPTY;
        byte joueur2_;
        short pointsAnnoncesJoueur_;
        short pointsAnnoncesAutresJoueurs_;
        short sommePrimeSupplementaire_;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            pointsAnnoncesJoueur_ = 0;
            pointsAnnoncesAutresJoueurs_ = 0;
            joueur2_ = 0;
            sommePrimeSupplementaire_ = 0;
            for (EnumMap<Handfuls,Short> annoncesJoueur_ : calculHandfulsScorePlayer(joueur_)) {
                if (!relations.memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            joueur2_ = 0;
            for (EnumMap<Miseres,Short> annoncesJoueur_ : calculMiseresScorePlayer(joueur_)) {
                if (!relations.memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            joueur2_ = 0;
            for (Shorts annoncesJoueur_ : calculSmallLastTurnScorePlayer(joueur_)) {
                if (!relations.memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            scores_.set(joueur_,
                    (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + (_differenceMaxDouble
                            + nombrePointsChien_ + parite_) / 2)
                            + (nombreJoueurs_ - 1) * (pointsAnnoncesJoueur_ + _primeSupplementaire.get(joueur_))
                            - pointsAnnoncesAutresJoueurs_ - sommePrimeSupplementaire_)));
        }
        return scores_;
    }

    CustList<TrickTarot> getPlisAttaque(BidTarot _bid) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (_bid.getJeuChien() != PlayingDog.AGAINST) {
            tricks_.add(tricks.first());
        }
        for (TrickTarot t: getWonTricksListTeam(relations.getTaker())) {
            tricks_.add(t);
        }
        return tricks_;
    }

    CustList<TrickTarot> getPlisDefense(BidTarot _bid) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (_bid.getJeuChien() == PlayingDog.AGAINST) {
            tricks_.add(tricks.first());
        }
        Bytes defs_ = relations.adversaires(relations.getTaker(),GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        tricks_.addAllElts(getWonTricksListTeam(tricks,defs_));
        return tricks_;
    }
    private CustList<TrickTarot> getWonTricksListTeam(byte _player) {
        Bytes team_ = relations.coequipiers(_player, GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        team_.add(_player);
        return getWonTricksListTeam(tricks,team_);
    }

    private boolean aucunPliAdverseFin(byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        Bytes partenaires_ = relations.coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return plisTousFaitsParFin(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    private static boolean plisTousFaitsParFin(Bytes _joueurs,
                                               CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Bytes autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
        return getWonTricksListTeam(_unionPlis,autresJoueurs_).isEmpty();
    }

    HandTarot getWonTricksTeam(byte _player) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            cards_.ajouterCartes(t.getCartes());
        }
        return cards_;
    }
    static CustList<TrickTarot> getWonTricksListTeam(CustList<TrickTarot> _tricks, Bytes _players) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        TrickTarot lastTrick_ = _tricks.last();
        byte indExc_ = lastTrick_.joueurAyantJoue(CardTarot.EXCUSE);
        int maxExclude_ = _tricks.size();
        if (indExc_ > -1) {
            maxExclude_--;
        }
        int nbOther_ = 0;
        for (TrickTarot t: _tricks.sub(0,maxExclude_)) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            if (!_players.containsObj(t.getRamasseur())) {
                nbOther_++;
                continue;
            }
            tricks_.add(t);
        }
        if (indExc_ < 0) {
            return tricks_;
        }
        if (nbOther_ == 0) {
            if (_players.containsObj(indExc_) || _players.containsObj(lastTrick_.getRamasseur())) {
                tricks_.add(lastTrick_);
            }
        } else {
            if (_players.containsObj(lastTrick_.getRamasseur())) {
                if (!tricks_.isEmpty()) {
                    tricks_.add(lastTrick_);
                } else if (_players.containsObj(indExc_)) {
                    tricks_.add(lastTrick_);
                }
            }
        }
        return tricks_;
    }

    public GameTarotTeamsRelation getRelations() {
        return relations;
    }
}
