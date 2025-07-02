package aiki.game.fight;

import aiki.comparators.ComparatorGroundPlaceKey;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.util.ListEffectCombo;
import aiki.game.UsesOfMove;
import aiki.game.fight.util.ListActivityOfMove;
import aiki.game.fight.util.ListActivityOfMoves;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.util.TeamPositionList;
import code.maths.LgInt;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class Team {

    public static final String CANCEL_USE_ITEM = "0";
    public static final String USE_ITEM = "1";

    /***/
    private ListActivityOfMoves enabledMovesByGroup;

    /***/
    private StringMap<ActivityOfMove> enabledMoves;

    /***/
    private StringMap<BoolVal> enabledMovesWhileSendingFoe;

    /***/
    private StringMap<LgInt> enabledMovesWhileSendingFoeUses;

    /***/
    private StringMap<Long> nbUsesMoves;

    /***/
    private StringMap<Long> nbUsesMovesRound;

    /***/
//    private Map<Pair<String,Integer>,Pair<Integer,Pair<Boolean,Boolean> > > healAfter;
    private StringMap<IntMap<StacksOfUses>> healAfter;

    /***/
    //private Map<Pair<String,Integer>,Anticipation> movesAnticipation;
    private StringMap<IntMap<Anticipation>> movesAnticipation;

    /***/
    private IntMap<Fighter> members;

    /***/
    private IntMap<CustList<Integer>> playerFightersAgainstFoe;

    /***/
    private long nbKoRound;

    /***/
    private long nbKoPreviousRound;

    /***/
    private StringList successfulMovesRound;

    /***/
//    private Comment comment = new Comment();

    public Team(){

    }

    public Team(DataBase _import){
        enabledMoves = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectTeam()){
            enabledMoves.put(e,new ActivityOfMove());
        }
        enabledMovesWhileSendingFoe = new StringMap<BoolVal>();
        enabledMovesWhileSendingFoeUses = new StringMap<LgInt>();
        for(String e:_import.getMovesEffectWhileSending()){
            enabledMovesWhileSendingFoe.put(e, BoolVal.FALSE);
            enabledMovesWhileSendingFoeUses.put(e,LgInt.zero());
        }
        enabledMovesByGroup = new ListActivityOfMoves();
        for(ListEffectCombo c:_import.getCombos().getEffects()){
            enabledMovesByGroup.add(new ListActivityOfMove(c.getList(),new ActivityOfMove()));
        }
        nbKoRound=0;
        nbKoPreviousRound=0;
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_import.getVarParamsMove(_import.equipeNbUtilisation()));
        attaques_.addAllElts(_import.getVarParamsMove(_import.equipeAdvNbUtilisation()));
        attaques_.removeDuplicates();
        nbUsesMoves = new StringMap<Long>();
        for(String e:attaques_){
            nbUsesMoves.put(e,0L);
        }
        attaques_.clear();
        attaques_.addAllElts(_import.getVarParamsMove(_import.nbUtiliAttEqTour()));
        nbUsesMovesRound = new StringMap<Long>();
        for(String e:attaques_){
            nbUsesMovesRound.put(e,0L);
        }
        members = new IntMap<Fighter>();
        healAfter = new StringMap<IntMap<StacksOfUses>>();
        movesAnticipation = new StringMap<IntMap<Anticipation>>();
        successfulMovesRound = new StringList();
    }

    static void replace(CustList<StringMap<Integer>> _moves, Team _team) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (StringMap<Integer> m: _moves) {
            if (m.isEmpty()) {
                i_++;
                continue;
            }
            Fighter f_ = _team.getMembers().getVal(i_);
            f_.getMoves().clear();
            f_.getCurrentMoves().clear();
            for (String k: m.getKeys()) {
                f_.getMoves().put(k, new UsesOfMove(m.getVal(k)));
                f_.getCurrentMoves().put(k, new UsesOfMove(m.getVal(k)));
            }
            i_++;
        }
    }

    void initEquipeUtilisateur(Player _utilisateur, Difficulty _diff,
                               int _maxNumberFrontFighters,
                               int _mult,
            DataBase _import, CustList<PkTrainer> _team) {
        playerFightersAgainstFoe = new IntMap<CustList<Integer>>();
        int nbPks_= _utilisateur.getTeam().size();
        int i_= IndexConstants.FIRST_INDEX;
        int max_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        for(int i = IndexConstants.FIRST_INDEX; i<nbPks_; i++){
            if (!(_utilisateur.getTeam().get(i) instanceof PokemonPlayer)){
                continue;
            }
            PokemonPlayer posPk_=(PokemonPlayer) _utilisateur.getTeam().get(i);
            int place_ = place(_maxNumberFrontFighters, i_, posPk_);
            i_ = incrIfPossible(i_, place_);
            Fighter creatureCbt_= new Fighter(posPk_,_import,place_);
            creatureCbt_.initIvUt(_diff);
            creatureCbt_.initHp();
            max_ = i;
            members.put(i,creatureCbt_);
            playerFightersAgainstFoe.put(i,new CustList<Integer>());
        }
        nbPks_= _team.size();
        i_= IndexConstants.FIRST_INDEX;
        int dec_ = max_ + 1;
        int diff_ = _mult - _maxNumberFrontFighters;
        for(int i = IndexConstants.FIRST_INDEX; i<nbPks_; i++){
            PkTrainer posPk_= _team.get(i);
            int place_;
            if(i_<diff_){
                place_= i_+_maxNumberFrontFighters;
            }else{
                place_=Fighter.BACK;
            }
            i_ = incrIfPossible(i_, place_);
            Fighter creatureCbt_= new Fighter(posPk_,_import,place_);
            int nbPossibleGenders_ = _import.getPokemon(creatureCbt_.getName()).getGenderRep().getPossibleGenders().size();
            if (nbPossibleGenders_ > DataBase.ONE_POSSIBLE_CHOICE) {
                creatureCbt_.setGender(_utilisateur.getOppositeGenderForPokemon());
                creatureCbt_.setCurrentGender(_utilisateur.getOppositeGenderForPokemon());
            }
            creatureCbt_.initIvUt(_diff);
            creatureCbt_.setRemainingHp(creatureCbt_.pvMax());
            members.put(i+dec_,creatureCbt_);
        }
        initHealAfterMovesAnticipation(_import, _mult);
    }

    private int incrIfPossible(int _i, int _place) {
        int i_ = _i;
        if (_place != Fighter.BACK) {
            i_++;
        }
        return i_;
    }

    void initEquipeUtilisateur(Player _utilisateur,Difficulty _diff,int _multiplicite,DataBase _import){
        initEquipeUtilisateur(_utilisateur,_diff,_multiplicite,_multiplicite,_import,new CustList<PkTrainer>());
    }

    private int place(int _multiplicite, int _i, PokemonPlayer _posPk) {
        if (_i < _multiplicite && !_posPk.isKo()) {
            return _i;
        }
        return Fighter.BACK;
    }

    void initPokemonSauvage(Player _utilisateur, Difficulty _diff, CustList<WildPk> _pokemon, DataBase _import){
        playerFightersAgainstFoe = new IntMap<CustList<Integer>>();
        for (WildPk w: _pokemon) {
            int s_ = members.size();
            Fighter creatureCbt_= new Fighter(w,_import, s_);
            if(_utilisateur.estAttrape(w.getName())){

                creatureCbt_.initIvAdv(_diff, _import.getBallDef());
            }else{
                creatureCbt_.initIvAdv(_diff,DataBase.EMPTY_STRING);
            }
            creatureCbt_.setRemainingHp(creatureCbt_.pvMax());
            members.put(s_,creatureCbt_);
        }
        initHealAfterMovesAnticipation(_import, _pokemon.size());
    }

    void initEquipeAdversaire(Player _utilisateur,CustList<PkTrainer> _equipe,Difficulty _diff, int _multiplicite,DataBase _import){
        playerFightersAgainstFoe = new IntMap<CustList<Integer>>();
        int nbPks_=_equipe.size();
        int back_ = Fighter.BACK;
        for(int i = IndexConstants.FIRST_INDEX; i<nbPks_; i++){
            PkTrainer p_=_equipe.get(i);
            int place_;
            if(i<_multiplicite){
                place_=i;
            }else{
                place_=back_;
            }
            Fighter creatureCbt_= new Fighter(p_,_import,place_);
            if(_utilisateur.estAttrape(creatureCbt_.getName())){

                creatureCbt_.initIvAdv(_diff, _import.getBallDef());
            }else{
                creatureCbt_.initIvAdv(_diff,DataBase.EMPTY_STRING);
            }
            creatureCbt_.setRemainingHp(creatureCbt_.pvMax());
            members.put(i,creatureCbt_);
        }
        initHealAfterMovesAnticipation(_import, _multiplicite);
    }

    private void initHealAfterMovesAnticipation(DataBase _import, int _mult) {
        for (String e: _import.getMovesHealingAfter()) {
            IntMap<StacksOfUses> map_;
            map_ = new IntMap<StacksOfUses>();
            for(int i = IndexConstants.FIRST_INDEX; i< _mult; i++){
                map_.put(i,new StacksOfUses());
            }
            healAfter.put(e, map_);
        }
        for (String e: _import.getMovesAnticipation()) {
            IntMap<Anticipation> map_;
            map_ = new IntMap<Anticipation>();
            for(int i = IndexConstants.FIRST_INDEX; i< _mult; i++){
                Anticipation ant_ = new Anticipation();
                ant_.setTargetPosition(TargetCoords.def());
                map_.put(i,ant_);
            }
            movesAnticipation.put(e, map_);
        }
    }


    void initRelationsCombattant(TeamPositionList _cbts, DataBase _import){
        for(Fighter f:members.values()){
            f.initCreatureRelationsAutre(_cbts,_import);
        }
    }

    //some tests with a clean data base with all type of move, ability, item, tree pokemon
    //This class is full tested
    boolean validate(DataBase _data, int _numberTeam, Fight _fight) {
        int mult_ = _fight.getMult();
        if (koMembers(_data, _numberTeam, _fight)) {
            return false;
        }
        //later places_.getMin >= 0 && places_.getMax < _fight.getMult()
        // && places_.duplicates == 0 => places_.size <= _fight.getMult()
        //later placesSubst_.getMin >= 0 && placesSubst_.getMax <= _fight.getMult()
        // && placesSubst_.duplicates == 0 => placesSubst_.size <= _fight.getMult()
        if (koEnabledMoves(_data)) {
            return false;
        }
        if (!StringUtil.equalsSet(_data.getMovesEffectWhileSending(), enabledMovesWhileSendingFoe.getKeys())) {
            return false;
        }
        if (koEnabledMovesWhileSendingFoeUses(_data)) {
            return false;
        }
        if (koEnabledMovesByGroup(_data)) {
            return false;
        }
        if (nbKoRound < 0) {
            return false;
        }
        if (nbKoPreviousRound < 0) {
            return false;
        }
        if (koNbUsesMoves(_data)) {
            return false;
        }
        if (koNbUsesMovesRound(_data)) {
            return false;
        }
        if (koTeamMoves(_data, mult_)) {
            return false;
        }
        if (koPlayerFightersAgainstFoe(_numberTeam, _fight)) {
            return false;
        }
        for (String m: successfulMovesRound) {
            if (!_data.getMoves().contains(m)) {
                return false;
            }
        }
        return !this.members.isEmpty();
    }

    private boolean koPlayerFightersAgainstFoe(int _numberTeam, Fight _fight) {
        IntMap< Fighter> members_ = _fight.getFoeTeam().getMembers();
        if (_numberTeam == Fight.CST_PLAYER) {
            for (int b: _fight.getUserTeam().getMembers().getKeys()) {
                if (!_fight.getUserTeam().getMembers().getVal(b).isBelongingToPlayer()) {
                    continue;
                }
                if (!members_.containsAllAsKeys(playerFightersAgainstFoe.getVal(b))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean koMembers(DataBase _data, int _numberTeam, Fight _fight) {
        for (int k: members.getKeys()) {
            if (!members.getVal(k).validate(_data, _numberTeam, _fight)) {
                return true;
            }
            if (members.getVal(k).getGroundPlaceSubst() >= _fight.getMult()) {
                return true;
            }
            if (members.getVal(k).getGroundPlace() >= _fight.getMult()) {
                return true;
            }
        }
        return false;
    }

    private boolean koEnabledMoves(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getMovesEffectTeam(), enabledMoves.getKeys())) {
            return true;
        }
        for (String m: enabledMoves.getKeys()) {
            if (enabledMoves.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koEnabledMovesWhileSendingFoeUses(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getMovesEffectWhileSending(), enabledMovesWhileSendingFoeUses.getKeys())) {
            return true;
        }
        for (String m: enabledMovesWhileSendingFoeUses.getKeys()) {
            if (!enabledMovesWhileSendingFoeUses.getVal(m).isZeroOrGt()) {
                return true;
            }
        }
        return false;
    }

    private boolean koEnabledMovesByGroup(DataBase _data) {
        if (!StringUtil.equalsStringListSet(_data.getCombos().getEffects().getKeys(), enabledMovesByGroup.getKeys())) {
            return true;
        }
        for (StringList m: enabledMovesByGroup.getKeys()) {
            if (enabledMovesByGroup.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koNbUsesMoves(DataBase _data) {
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_data.getVarParamsMove(_data.equipeNbUtilisation()));
        attaques_.addAllElts(_data.getVarParamsMove(_data.equipeAdvNbUtilisation()));
        if (!StringUtil.equalsSet(attaques_, nbUsesMoves.getKeys())) {
            return true;
        }
        for (String m: nbUsesMoves.getKeys()) {
            if (nbUsesMoves.getVal(m) < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koNbUsesMovesRound(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getVarParamsMove(_data.nbUtiliAttEqTour()), nbUsesMovesRound.getKeys())) {
            return true;
        }
        for (String m: nbUsesMovesRound.getKeys()) {
            if (nbUsesMovesRound.getVal(m) < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koTeamMoves(DataBase _data, int _mult) {
        Ints keysMovesLatter_ = keysMovesLatter(_mult);
        if (koHealAfter(_data, keysMovesLatter_)) {
            return true;
        }
        if (koAnticipation(_data, keysMovesLatter_)) {
            return true;
        }
        for (IntMap<StacksOfUses> k: healAfter.values()) {
            for (StacksOfUses s: k.values()) {
                if (!s.isValid()) {
                    return true;
                }
            }
        }
        for (IntMap<Anticipation> k: movesAnticipation.values()) {
            for (Anticipation s: k.values()) {
                if (!s.isValid() || koEnabled(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean koEnabled(Anticipation _s) {
        return _s.isEnabled() && TargetCoords.ko(_s.getTargetPosition());
    }

    private boolean koHealAfter(DataBase _data, Ints _keysMovesLatter) {
        CustList<MoveUsesTeam> relMovesTh_;
        relMovesTh_ = relMovesTh(_keysMovesLatter, _data.getMovesHealingAfter());
        CustList<MoveUsesTeam> relMovesExp_ = new CustList<MoveUsesTeam>();
        for (EntryCust<String,IntMap<StacksOfUses>> k: healAfter.entryList()) {
            for (int b: k.getValue().getKeys()) {
                relMovesExp_.add(new MoveUsesTeam(k.getKey(),b));
            }
        }
        return !MoveUsesTeam.equalsSet(relMovesTh_, relMovesExp_);
    }

    private boolean koAnticipation(DataBase _data, Ints _keysMovesLatter) {
        CustList<MoveUsesTeam> relMovesExp_;
        CustList<MoveUsesTeam> relMovesTh_;
        relMovesExp_ = new CustList<MoveUsesTeam>();
        relMovesTh_ = relMovesTh(_keysMovesLatter, _data.getMovesAnticipation());
        for (EntryCust<String,IntMap<Anticipation>> k: movesAnticipation.entryList()) {
            for (int b: k.getValue().getKeys()) {
                relMovesExp_.add(new MoveUsesTeam(k.getKey(),b));
            }
        }
        return !MoveUsesTeam.equalsSet(relMovesTh_, relMovesExp_);
    }

    private CustList<MoveUsesTeam> relMovesTh(Ints _keysMovesLatter, StringList _movesTeam) {
        CustList<MoveUsesTeam> relMovesTh_ = new CustList<MoveUsesTeam>();
        for (int f: _keysMovesLatter) {
            for (String m: _movesTeam) {
                relMovesTh_.add(new MoveUsesTeam(m, f));
            }
        }
        return relMovesTh_;
    }

    static Ints keysMovesLatter(int _mult) {
        Ints keysMovesLatter_ = new Ints();
        for (int i = IndexConstants.FIRST_INDEX; i< _mult; i++) {
            keysMovesLatter_.add(i);
        }
        return keysMovesLatter_;
    }

    void initRoundTeam() {
        clearSuccessfuleMovesRound();
        chooseMoves();
        setNbKoRound();
    }

    void move(int _decalage){
        CustList<GroundPlaceKey> combattantsPositions_ = new CustList<GroundPlaceKey>();
        for(int c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if(!membre_.estArriere()){
                int place_=membre_.getGroundPlace();
                combattantsPositions_.add(new GroundPlaceKey(place_,c));
            }
        }
        combattantsPositions_.sortElts(new ComparatorGroundPlaceKey());
        int i_=0;
        for(GroundPlaceKey e:combattantsPositions_){
            int cle_= e.getKey();
            members.getVal(cle_).setGroundPlace(i_+_decalage);
            i_++;
        }
    }

    void activerEffetEquipe(String _attaque){
        enabledMoves.getVal(_attaque).enableReset();
    }

    void desactiverEffetEquipe(String _attaque){
        enabledMoves.getVal(_attaque).disable();
        enabledMoves.getVal(_attaque).reset();
    }

    void ajouterEffetEquipeEntreeAdv(String _attaque){
        enabledMovesWhileSendingFoe.put(_attaque, BoolVal.TRUE);
        enabledMovesWhileSendingFoeUses.getVal(_attaque).increment();
    }

    void supprimerEffetEquipeEntreeAdv(String _attaque){
        enabledMovesWhileSendingFoe.put(_attaque, BoolVal.FALSE);
        enabledMovesWhileSendingFoeUses.put(_attaque,LgInt.zero());
    }

    void addSuccessfulMoveRound(String _attaque){
        successfulMovesRound.add(_attaque);
        for(StringList c:enabledMovesByGroup.getKeys()){
            if(!successfulMovesRound.containsAllObj(c)){
                continue;
            }
            enabledMovesByGroup.getVal(c).enableReset();
        }
    }

    void clearSuccessfuleMovesRound(){
        successfulMovesRound.clear();
        for(String e:nbUsesMovesRound.getKeys()){
            nbUsesMovesRound.put(e, 0L);
        }
        for (Fighter f: members.values()) {
            f.setSuccessfulMove(false);
        }
    }

    void ajouterCombattantsContreAdv(int _membre,int _adv){
        EntryCust<Integer, CustList<Integer>> e_ = playerFightersAgainstFoe.getEntryByKey(_membre);
        Ints tmp_ = new Ints(e_.getValue());
        tmp_.add(_adv);
        tmp_.removeDuplicates();
        e_.setValue(tmp_);
    }

    void toutSupprimerCombattantsContreAdvMembre(int _membre){
        playerFightersAgainstFoe.getVal(_membre).clear();
    }

    void toutSupprimerCombattantsContreAdv(int _adv){
        for(EntryCust<Integer, CustList<Integer>> c:playerFightersAgainstFoe.entryList()){
            Ints tmp_ = new Ints(c.getValue());
            tmp_.removeOneNumber(_adv);
            c.setValue(tmp_);
        }
    }

    Ints notKoPartnersWithoutStatus(int _position) {
        Ints list_ = new Ints();
        for (int c: members.getKeys()) {
            if (NumberUtil.eq(c, _position)) {
                continue;
            }
            Fighter fighter_ = members.getVal(c);
            if (!fighter_.estKo() && withoutStatus(fighter_)) {
                list_.add(c);
            }
        }
        return list_;
    }

    private static boolean withoutStatus(Fighter _fighter) {
        boolean ok_=true;
        for(String c2_: _fighter.getStatusSet()){
            if(!NumberUtil.eq(_fighter.getStatusNbRoundShort(c2_), 0)){
                ok_=false;
                break;
            }
        }
        return ok_;
    }

    void useItemsEndRound(DataBase _import, TransientFight _temp) {
        boolean cancelUsingItems_ = false;
        for (int f_: members.getKeys()) {
            Fighter fighter_= refPartMembres(f_);
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            if (!fighter_.estArriere() && ab_ != null && ab_.isGiveItemToAllyHavingUsed()) {
                cancelUsingItems_ = true;
                break;
            }
        }
        StringMap<String> mess_ = _import.getMessagesTeam();
        for(int c2_: getMembers().getKeys()){
            Fighter creature_= refPartMembres(c2_);
            creature_.setLastUsedMove();
            creature_.cancelActions();
            String name_ = _import.translatePokemon(creature_.getName());
            if (!cancelUsingItems_) {
                if (creature_.isUsingItem()) {
                    _temp.addMessage(mess_.getVal(USE_ITEM), name_);
                }
                creature_.tossLastUsedObject();
            } else {
                _temp.addMessage(mess_.getVal(CANCEL_USE_ITEM), name_);
            }
            creature_.setUsingItem(false);
        }
    }

    void setNbKoRound(){
        nbKoPreviousRound=nbKoRound;
        nbKoRound=0;
    }

    void chooseMoves(){
        for(int c:members.getKeys()){
            members.getVal(c).choisirAttaqueFin();
        }
    }

    Ints fightersAtCurrentPlace(TargetCoords _place){
        return fightersAtCurrentPlace(_place.getPosition());
    }
    Ints fightersAtCurrentPlace(int _place){
        Ints cbts_ = new Ints();
        for (EntryCust<Integer, Fighter> e: fightersListAtCurrentPlace(_place).entryList()) {
            cbts_.add(e.getKey());
        }
        return cbts_;
    }

    IntMap<Fighter> fightersListAtCurrentPlace(TargetCoords _place){
        return fightersListAtCurrentPlace(_place.getPosition());
    }
    IntMap<Fighter> fightersListAtCurrentPlace(int _place){
        IntMap<Fighter> cbts_ = new IntMap<Fighter>();
        for(int c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if(NumberUtil.eq(membre_.getGroundPlace(),_place)){
                cbts_.addEntry(c,membre_);
            }
        }
        return cbts_;
    }

    Ints otherFighterAtIndex(int _index) {
        IntTreeMap<Integer> cbts_ = new IntTreeMap<Integer>();
        for(int c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if (membre_.estArriere()) {
                continue;
            }
            cbts_.put(membre_.getGroundPlace(), c);
        }
        Ints res_ = new Ints();
        if (cbts_.isValidIndex(_index)) {
            res_.add(cbts_.getValue(_index));
        }
        return res_;
    }

    CustList<FighterPosition> playerFighterAtIndex(int _index) {
        IntTreeMap<FighterPosition> cbts_ = getFrontTeam();
        CustList<FighterPosition> res_ = new CustList<FighterPosition>();
        if (cbts_.isValidIndex(_index)) {
            res_.add(cbts_.getValue(_index));
        }
        return res_;
    }

    CustList<FighterPosition> fighterTeamAtIndex(int _index) {
        CustList<FighterPosition> cbts_ = getAllTeamList();
        CustList<FighterPosition> res_ = new CustList<FighterPosition>();
        if (cbts_.isValidIndex(_index)) {
            res_.add(cbts_.get(_index));
        }
        return res_;
    }

    CustList<FighterPosition> substituteAtIndex(int _index) {
//        byte substitute_ = Fighter.BACK;
//        byte i_ = IndexConstants.FIRST_INDEX;
//        Ints list_ = new Ints(members.getKeys());
//        list_.sort();
//        for (byte k: list_) {
//            Fighter fighter_ = members.getVal(k);
//            if (!fighter_.estArriere()) {
//                continue;
//            }
//            if (NumberUtil.eq(i_, _index)) {
//                substitute_ = k;
//                break;
//            }
//            i_++;
//        }
        IntMap<FighterPosition> backTeam_ = getBackTeam();
        if (backTeam_.isValidIndex(_index)) {
            CustList<FighterPosition> bs_ = new CustList<FighterPosition>();
            bs_.add(backTeam_.getValue(_index));
            return bs_;
        }
        return new CustList<FighterPosition>();
    }

//    Ints substituteAtIndexSw(short _index) {
//        IntMap<Fighter> backTeam_ = getBackTeamSubs();
//        if (backTeam_.isValidIndex(_index)) {
//            Integer key_ = backTeam_.getKey(_index);
//            Ints bs_ = new Ints();
//            bs_.add(key_);
//            return bs_;
//        }
//        return new Ints();
//    }
    IntTreeMap<FighterPosition> getFrontTeam() {
        IntTreeMap<FighterPosition> tree_ = new IntTreeMap<FighterPosition>();
        for (EntryCust<Integer, Fighter> k: members.entryList()) {
            Fighter f_ = k.getValue();
            if (!f_.isBelongingToPlayer() || NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            tree_.put(f_.getGroundPlaceSubst(), new FighterPosition(k.getValue(),k.getKey()));
        }
        return tree_;
    }
    CustList<FighterPosition> getAllTeamList() {
        CustList<FighterPosition> tree_ = new CustList<FighterPosition>();
        for (EntryCust<Integer, Fighter> k: members.entryList()) {
            tree_.add(new FighterPosition(k.getValue(),k.getKey()));
        }
        return tree_;
    }
//    IntTreeMap<FighterPosition> getFoeFrontTeam() {
//        IntTreeMap<FighterPosition> tree_ = new IntTreeMap<FighterPosition>();
//        for (EntryCust<Integer,Fighter> k: members.entryList()) {
//            Fighter f_ = k.getValue();
//            tree_.put(f_.getGroundPlaceSubst(), new FighterPosition(k.getValue(),k.getKey()));
//        }
//        return tree_;
//    }
//    public IntMap<Fighter> getBackTeamSubs() {
//        IntMap<Fighter> fs_ = new IntMap<Fighter>();
//        Ints list_ = new Ints(members.getKeys());
//        list_.sort();
//        for (byte k: list_) {
//            Fighter f_ = members.getVal(k);
//            if (!f_.isBelongingToPlayer() || !NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
//            fs_.addEntry(k,f_);
//        }
//        return fs_;
//    }
    IntMap<FighterPosition> getBackTeam() {
        IntMap<FighterPosition> fs_ = new IntMap<FighterPosition>();
        Ints list_ = new Ints(members.getKeys());
        list_.sort();
        for (int k: list_) {
            Fighter f_ = members.getVal(k);
            if (!f_.isBelongingToPlayer() || !NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            fs_.addEntry(k,new FighterPosition(f_,k));
        }
        return fs_;
    }
    static int indexOfSubstitute(IntMap<FighterPosition> _list,int _sub) {
        int s_ = _list.size();
        for (int i = 0; i < s_; i++) {
            if (_list.getValue(i).getFirstPosit() == _sub) {
                return i;
            }
        }
        return -1;
    }

    int fighterAtIndex(int _index) {
        int index_ = Fighter.BACK;
        int i_ = IndexConstants.FIRST_INDEX;
        Ints list_ = new Ints(members.getKeys());
        list_.sort();
        for (int k: list_) {
            if (members.getVal(k).isBelongingToPlayer()) {
                if (NumberUtil.eq(i_, _index)) {
                    index_ = k;
                    break;
                }
                i_++;
            }
        }
        return index_;
    }

    boolean estKo(){
        for(int c:members.getKeys()){
            if(!members.getVal(c).estKo()){
                return false;
            }
        }
        return true;
    }

    StringList enabledTeamMoves() {
        StringList list_ = new StringList();
        for(String m:enabledMoves.getKeys()){
            if(!enabledMoves.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    CustList<StringList> enabledTeamGroupMoves() {
        CustList<StringList> list_ = new CustList<StringList>();
        for(StringList m:enabledMovesByGroup.getKeys()){
            if(!enabledMovesByGroup.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    Ints frontFighters(){
        Ints cbts_ = new Ints();
        for(int c2_: members.getKeys()){
            if(!members.getVal(c2_).estArriere()){
                cbts_.add(c2_);
            }
        }
        return cbts_;
    }

    public Fighter refPartMembres(int _position) {
        return members.getVal(_position);
    }

    public ListActivityOfMoves getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }

    public void setEnabledMovesByGroup(ListActivityOfMoves _enabledMoves) {
        enabledMovesByGroup = _enabledMoves;
    }

    public StringMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public void setEnabledMoves(StringMap<ActivityOfMove> _enabledMoves) {
        enabledMoves = _enabledMoves;
    }

    public StringMap<BoolVal> getEnabledMovesWhileSendingFoe() {
        return enabledMovesWhileSendingFoe;
    }

    public void setEnabledMovesWhileSendingFoe(StringMap<BoolVal> _enabledMovesWhileSendingFoe) {
        enabledMovesWhileSendingFoe = _enabledMovesWhileSendingFoe;
    }

    public StringMap<LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public void setEnabledMovesWhileSendingFoeUses(StringMap<LgInt> _enabledMovesWhileSendingFoeUses) {
        enabledMovesWhileSendingFoeUses = _enabledMovesWhileSendingFoeUses;
    }

    public StringMap<Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public void setNbUsesMoves(StringMap<Long> _nbUsesMoves) {
        nbUsesMoves = _nbUsesMoves;
    }

    public StringMap<Long> getNbUsesMovesRound() {
        return nbUsesMovesRound;
    }

    public void setNbUsesMovesRound(StringMap<Long> _nbUsesMovesRound) {
        nbUsesMovesRound = _nbUsesMovesRound;
    }

    public CustList<String> getHealAfterSet() {
        return healAfter.getKeys();
    }

    public CustList<Integer> getHealAfterSet(String _move) {
        return healAfter.getVal(_move).getKeys();
    }

    public StacksOfUses getHealAfterVal(String _move, int _key) {
        return healAfter.getVal(_move).getVal(_key);
    }

    public StringMap<IntMap<StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public void setHealAfter(StringMap<IntMap<StacksOfUses>> _healAfter) {
        healAfter = _healAfter;
    }

    public CustList<String> getMovesAnticipationSet() {
        return movesAnticipation.getKeys();
    }

    public CustList<Integer> getMovesAnticipationSet(String _move) {
        return movesAnticipation.getVal(_move).getKeys();
    }

    public Anticipation getMovesAnticipationVal(String _move, int _key) {
        return movesAnticipation.getVal(_move).getVal(_key);
    }

    /**Only for checking player actions*/
    CustList<IntMap<Anticipation>> getMovesAnticipationValues() {
        return movesAnticipation.values();
    }

    public StringMap<IntMap<Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public void setMovesAnticipation(StringMap<IntMap<Anticipation>> _movesAnticipation) {
        movesAnticipation = _movesAnticipation;
    }

    public IntMap<Fighter> getMembers() {
        return members;
    }

    public void setMembers(IntMap<Fighter> _members) {
        members = _members;
    }

    public CustList<Integer> getPlayerFightersAgainstFoeSet() {
        return playerFightersAgainstFoe.getKeys();
    }

    public CustList<Integer> getPlayerFightersAgainstFoeVal(int _key) {
        return playerFightersAgainstFoe.getVal(_key);
    }

    public boolean playerFightersAgainstFoeHas(int _key, int _value) {
        return NumberUtil.containsInt(playerFightersAgainstFoe.getVal(_key),_value);
    }

    public IntMap<CustList<Integer>> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }

    public void setPlayerFightersAgainstFoe(IntMap<CustList<Integer>> _playerFightersAgainstFoe) {
        playerFightersAgainstFoe = _playerFightersAgainstFoe;
    }

    public long getNbKoRound() {
        return nbKoRound;
    }

    public void setNbKoRound(long _nbKoRound) {
        nbKoRound = _nbKoRound;
    }

    public long getNbKoPreviousRound() {
        return nbKoPreviousRound;
    }

    public void setNbKoPreviousRound(long _nbKoPreviousRound) {
        nbKoPreviousRound = _nbKoPreviousRound;
    }

    public StringList getSuccessfulMovesRound() {
        return successfulMovesRound;
    }

    public void setSuccessfulMovesRound(StringList _successfulMovesRound) {
        successfulMovesRound = _successfulMovesRound;
    }
}
