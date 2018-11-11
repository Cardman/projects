package aiki.game.fight;
import aiki.DataBase;
import aiki.comments.Comment;
import aiki.fight.abilities.AbilityData;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import code.maths.LgInt;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.PairNumber;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.comparators.ComparatorPairNumber;
import code.util.ints.Listable;

@RwXml
public final class Team {

    public static final String EQUIPE_NB_UTILISATION = "EQUIPE_NB_UTILISATION";
    public static final String EQUIPE_ADV_NB_UTILISATION = "EQUIPE_ADV_NB_UTILISATION";
    public static final String NB_UTILI_ATT_EQ_TOUR = "NB_UTILI_ATT_EQ_TOUR";

    public static final String TEAM = "aiki.game.fight.Team";

    private static final String CANCEL_USE_ITEM = "cancelUseItem";

    private static final String USE_ITEM = "useItem";

    /***/
    private ObjectMap<StringList,ActivityOfMove> enabledMovesByGroup;

    /***/
    private StringMap<ActivityOfMove> enabledMoves;

    /***/
    private StringMap<Boolean> enabledMovesWhileSendingFoe;

    /***/
    private StringMap<LgInt> enabledMovesWhileSendingFoeUses;

    /***/
    private StringMap<Integer> nbUsesMoves;

    /***/
    private StringMap<Integer> nbUsesMovesRound;

    /***/
//    private Map<Pair<String,Byte>,Pair<Byte,Pair<Boolean,Boolean> > > healAfter;
    private StringMap<NumberMap<Byte,StacksOfUses>> healAfter;

    /***/
    //private Map<Pair<String,Byte>,Anticipation> movesAnticipation;
    private StringMap<NumberMap<Byte,Anticipation>> movesAnticipation;

    /***/
    private NumberMap<Byte,Fighter> members;

    /***/
    private NumberMap<Byte,Numbers<Byte>> playerFightersAgainstFoe;

    /***/
    private byte nbKoRound;

    /***/
    private byte nbKoPreviousRound;

    /***/
    private StringList successfulMovesRound;

    /***/
    private Comment comment = new Comment();

    public Team(){

    }

    public Team(DataBase _import){
        enabledMoves = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectTeam()){
            enabledMoves.put(e,new ActivityOfMove());
        }
        enabledMovesWhileSendingFoe = new StringMap<Boolean>();
        enabledMovesWhileSendingFoeUses = new StringMap<LgInt>();
        for(String e:_import.getMovesEffectWhileSending()){
            enabledMovesWhileSendingFoe.put(e,false);
            enabledMovesWhileSendingFoeUses.put(e,LgInt.zero());
        }
        enabledMovesByGroup = new ObjectMap<StringList,ActivityOfMove>();
        for(StringList c:_import.getCombos().getEffects().getKeys()){
            enabledMovesByGroup.put(c,new ActivityOfMove());
        }
        nbKoRound=0;
        nbKoPreviousRound=0;
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_import.getVarParamsMove(EQUIPE_NB_UTILISATION));
        attaques_.addAllElts(_import.getVarParamsMove(EQUIPE_ADV_NB_UTILISATION));
        attaques_.removeDuplicates();
        nbUsesMoves = new StringMap<Integer>();
        for(String e:attaques_){
            nbUsesMoves.put(e,0);
        }
        attaques_.clear();
        attaques_.addAllElts(_import.getVarParamsMove(NB_UTILI_ATT_EQ_TOUR));
        attaques_.removeDuplicates();
        nbUsesMovesRound = new StringMap<Integer>();
        for(String e:attaques_){
            nbUsesMovesRound.put(e,0);
        }
        members = new NumberMap<Byte,Fighter>();
        healAfter = new StringMap<NumberMap<Byte,StacksOfUses>>();
        movesAnticipation = new StringMap<NumberMap<Byte,Anticipation>>();
        successfulMovesRound = new StringList();
    }

    void initEquipeUtilisateur(Player _utilisateur, Difficulty _diff,
            byte _maxNumberFrontFighters,
            byte _mult,
            DataBase _import, CustList<PkTrainer> _team) {
        playerFightersAgainstFoe = new NumberMap<Byte,Numbers<Byte>>();
        byte nbPks_=(byte) _utilisateur.getTeam().size();
        byte i_=CustList.FIRST_INDEX;
        byte place_=CustList.FIRST_INDEX;
        byte back_ = Fighter.BACK;
        for(byte i=CustList.FIRST_INDEX;i<nbPks_;i++){
            if (!(_utilisateur.getTeam().get(i) instanceof PokemonPlayer)){
                continue;
            }
            PokemonPlayer posPk_=(PokemonPlayer) _utilisateur.getTeam().get(i);
            if(!posPk_.isKo()){
                if (i_<_maxNumberFrontFighters) {
                    place_=i_;
                    i_++;
                }else{
                    place_=back_;
                }
            }else{
                place_=back_;
            }
            Fighter creatureCbt_= new Fighter(posPk_,_import,place_);
            creatureCbt_.initIvUt(_diff);
            members.put(i,creatureCbt_);
            playerFightersAgainstFoe.put(i,new Numbers<Byte>());
        }
        nbPks_=(byte) _team.size();
        i_=CustList.FIRST_INDEX;
        place_=CustList.FIRST_INDEX;
        Numbers<Byte> keys_ = new Numbers<Byte>(members.getKeys());
        int dec_ = keys_.getMaximum() + 1;
        byte diff_ = (byte) (_mult - _maxNumberFrontFighters);
        for(byte i=CustList.FIRST_INDEX;i<nbPks_;i++){
            PkTrainer posPk_= _team.get(i);
            if(i_<diff_){
                place_=(byte) (i_+_maxNumberFrontFighters);
                i_++;
            }else{
                place_=back_;
            }
            Fighter creatureCbt_= new Fighter(posPk_,_import,place_);
            int nbPossibleGenders_ = _import.getPokemon(creatureCbt_.getName()).getGenderRep().getPossibleGenders().size();
            if (nbPossibleGenders_ > DataBase.ONE_POSSIBLE_CHOICE) {
                creatureCbt_.setGender(_utilisateur.getOppositeGenderForPokemon());
                creatureCbt_.setCurrentGender(_utilisateur.getOppositeGenderForPokemon());
            }
            creatureCbt_.initIvUt(_diff);
            creatureCbt_.setRemainingHp(creatureCbt_.pvMax());
            members.put((byte) (i+dec_),creatureCbt_);
        }
        for (String e: _import.getMovesHealingAfter()) {
            NumberMap<Byte,StacksOfUses> map_;
            map_ = new NumberMap<Byte,StacksOfUses>();
            for(byte i=CustList.FIRST_INDEX;i<_mult;i++){
                map_.put(i,new StacksOfUses());
            }
            healAfter.put(e, map_);
        }
        for (String e: _import.getMovesAnticipation()) {
            NumberMap<Byte,Anticipation> map_;
            map_ = new NumberMap<Byte,Anticipation>();
            for(byte i=CustList.FIRST_INDEX;i<_mult;i++){
                Anticipation ant_ = new Anticipation();
                ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
                map_.put(i,ant_);
            }
            movesAnticipation.put(e, map_);
        }
    }

    void initEquipeUtilisateur(Player _utilisateur,Difficulty _diff,short _multiplicite,DataBase _import){
        playerFightersAgainstFoe = new NumberMap<Byte,Numbers<Byte>>();
        byte nbPks_=(byte) _utilisateur.getTeam().size();
        byte i_=CustList.FIRST_INDEX;
        byte place_=CustList.FIRST_INDEX;
        byte back_ = Fighter.BACK;
        for(byte i=CustList.FIRST_INDEX;i<nbPks_;i++){
            if (!(_utilisateur.getTeam().get(i) instanceof PokemonPlayer)){
                continue;
            }
            PokemonPlayer posPk_=(PokemonPlayer) _utilisateur.getTeam().get(i);
            if(i_<_multiplicite&&!posPk_.isKo()){
                place_=i_;
                i_++;
            }else{
                place_=back_;
            }
            Fighter creatureCbt_= new Fighter(posPk_,_import,place_);
            creatureCbt_.initIvUt(_diff);
            members.put(i,creatureCbt_);
            playerFightersAgainstFoe.put(i,new Numbers<Byte>());
        }
        for (String e: _import.getMovesHealingAfter()) {
            NumberMap<Byte,StacksOfUses> map_;
            map_ = new NumberMap<Byte,StacksOfUses>();
//            for(byte i=CustList.FIRST_INDEX;i<i_;i++){
//                map_.put(i,new StacksOfUses());
//            }
            for(byte i=CustList.FIRST_INDEX;i<_multiplicite;i++){
                map_.put(i,new StacksOfUses());
            }
            healAfter.put(e, map_);
        }
        for (String e: _import.getMovesAnticipation()) {
            NumberMap<Byte,Anticipation> map_;
            map_ = new NumberMap<Byte,Anticipation>();
//            for(byte i=CustList.FIRST_INDEX;i<i_;i++){
//                Anticipation ant_ = new Anticipation();
//                ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
//                map_.put(i,ant_);
//            }
            for(byte i=CustList.FIRST_INDEX;i<_multiplicite;i++){
                Anticipation ant_ = new Anticipation();
                ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
                map_.put(i,ant_);
            }
            movesAnticipation.put(e, map_);
        }
    }

    void initPokemonSauvage(Player _utilisateur,Difficulty _diff, int _index, WildPk _pokemon,DataBase _import){
        playerFightersAgainstFoe = new NumberMap<Byte,Numbers<Byte>>();
        Fighter creatureCbt_= new Fighter(_pokemon,_import, (byte) _index);
        if(_utilisateur.estAttrape(_pokemon.getName())){
            creatureCbt_.initIvAdv(_diff,_import.getDefaultBall());
        }else{
            creatureCbt_.initIvAdv(_diff,DataBase.EMPTY_STRING);
        }
        members.put((byte)_index,creatureCbt_);
        for (String e: _import.getMovesHealingAfter()) {
            NumberMap<Byte,StacksOfUses> map_;
            map_ = new NumberMap<Byte,StacksOfUses>();
            map_.put((byte)_index,new StacksOfUses());
            healAfter.put(e, map_);
        }
        for (String e: _import.getMovesAnticipation()) {
            NumberMap<Byte,Anticipation> map_;
            map_ = new NumberMap<Byte,Anticipation>();
            Anticipation ant_ = new Anticipation();
            ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
            map_.put((byte)_index,ant_);
            movesAnticipation.put(e, map_);
        }
    }

    void initEquipeAdversaire(Player _utilisateur,CustList<PkTrainer> _equipe,Difficulty _diff, short _multiplicite,DataBase _import){
        playerFightersAgainstFoe = new NumberMap<Byte,Numbers<Byte>>();
        byte nbPks_=(byte) _equipe.size();
        //byte i_=CustList.FIRST_INDEX;
        byte back_ = Fighter.BACK;
        for(byte i=CustList.FIRST_INDEX;i<nbPks_;i++){
            PkTrainer p_=_equipe.get(i);
            byte place_;
            if(i<_multiplicite){
                place_=i;
                //i_++;
            }else{
                place_=back_;
            }
            Fighter creatureCbt_= new Fighter(p_,_import,place_);
            if(_utilisateur.estAttrape(creatureCbt_.getName())){
                creatureCbt_.initIvAdv(_diff,_import.getDefaultBall());
            }else{
                creatureCbt_.initIvAdv(_diff,DataBase.EMPTY_STRING);
            }
            members.put(i,creatureCbt_);
        }
        for (String e: _import.getMovesHealingAfter()) {
            NumberMap<Byte,StacksOfUses> map_;
            map_ = new NumberMap<Byte,StacksOfUses>();
//            for(byte i=CustList.FIRST_INDEX;i<i_;i++){
//                map_.put(i,new StacksOfUses());
//            }
            for(byte i=CustList.FIRST_INDEX;i<_multiplicite;i++){
                map_.put(i,new StacksOfUses());
            }
            healAfter.put(e, map_);
        }
        for (String e: _import.getMovesAnticipation()) {
            NumberMap<Byte,Anticipation> map_;
            map_ = new NumberMap<Byte,Anticipation>();
//            for(byte i=CustList.FIRST_INDEX;i<i_;i++){
//                Anticipation ant_ = new Anticipation();
//                ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
//                map_.put(i,ant_);
//            }
            for(byte i=CustList.FIRST_INDEX;i<_multiplicite;i++){
                Anticipation ant_ = new Anticipation();
                ant_.setTargetPosition(new TargetCoords((short) 0, Fighter.BACK));
                map_.put(i,ant_);
            }
            movesAnticipation.put(e, map_);
        }
    }


    void initRelationsCombattant(EqList<TeamPosition> _cbts,DataBase _import){
        for(Fighter f:members.values()){
            f.initCreatureRelationsAutre(_cbts,_import);
        }
    }

    //some tests with a clean data base with all type of move, ability, item, tree pokemon
    //This class is full tested
    public boolean validate(DataBase _data, byte _numberTeam, Fight _fight) {
        int mult_ = _fight.getMult();
        for (byte k: members.getKeys()) {
            if (!members.getVal(k).validate(_data, _numberTeam, _fight)) {
                return false;
            }
            if (members.getVal(k).getGroundPlaceSubst() >= _fight.getMult()) {
                return false;
            }
            if (members.getVal(k).getGroundPlace() >= _fight.getMult()) {
                return false;
            }
        }
        //later places_.getMin >= 0 && places_.getMax < _fight.getMult()
        // && places_.duplicates == 0 => places_.size <= _fight.getMult()
        //later placesSubst_.getMin >= 0 && placesSubst_.getMax <= _fight.getMult()
        // && placesSubst_.duplicates == 0 => placesSubst_.size <= _fight.getMult()
        if (!StringList.equalsSet(_data.getMovesEffectTeam(), enabledMoves.getKeys())) {
            return false;
        }
        for (String m: enabledMoves.getKeys()) {
            if (enabledMoves.getVal(m).getNbTurn() < 0) {
                return false;
            }
        }
        for (Object o: enabledMovesWhileSendingFoe.values()) {
            if (!(o instanceof Boolean)) {
                return false;
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectWhileSending(), enabledMovesWhileSendingFoe.getKeys())) {
            return false;
        }
        if (!StringList.equalsSet(_data.getMovesEffectWhileSending(), enabledMovesWhileSendingFoeUses.getKeys())) {
            return false;
        }
        for (String m: enabledMovesWhileSendingFoeUses.getKeys()) {
            if (!enabledMovesWhileSendingFoeUses.getVal(m).isZeroOrGt()) {
                return false;
            }
        }
        if (!StringList.equalsStringListSet(_data.getCombos().getEffects().getKeys(), enabledMovesByGroup.getKeys())) {
            return false;
        }
        for (StringList m: enabledMovesByGroup.getKeys()) {
            if (enabledMovesByGroup.getVal(m).getNbTurn() < 0) {
                return false;
            }
        }
        if (nbKoRound < 0) {
            return false;
        }
        if (nbKoPreviousRound < 0) {
            return false;
        }
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_data.getVarParamsMove(EQUIPE_NB_UTILISATION));
        attaques_.addAllElts(_data.getVarParamsMove(EQUIPE_ADV_NB_UTILISATION));
        attaques_.removeDuplicates();
        if (!StringList.equalsSet(attaques_, nbUsesMoves.getKeys())) {
            return false;
        }
        for (String m: nbUsesMoves.getKeys()) {
            if (nbUsesMoves.getVal(m) < 0) {
                return false;
            }
        }
        attaques_.clear();
        attaques_.addAllElts(_data.getVarParamsMove(NB_UTILI_ATT_EQ_TOUR));
        attaques_.removeDuplicates();
        if (!StringList.equalsSet(attaques_, nbUsesMovesRound.getKeys())) {
            return false;
        }
        for (String m: nbUsesMovesRound.getKeys()) {
            if (nbUsesMovesRound.getVal(m) < 0) {
                return false;
            }
        }
        Numbers<Byte> keysMovesLatter_ = new Numbers<Byte>();
        for (byte i=CustList.FIRST_INDEX;i<mult_;i++) {
            keysMovesLatter_.add(i);
        }
        StringList movesTeam_ = new StringList();
        EqList<MoveUsesTeam> relMovesTh_;
        EqList<MoveUsesTeam> relMovesExp_;
        relMovesTh_ = new EqList<MoveUsesTeam>();
        movesTeam_.addAllElts(_data.getMovesHealingAfter());
        for (byte f: keysMovesLatter_) {
            for (String m: movesTeam_) {
                relMovesTh_.add(new MoveUsesTeam(m, f));
            }
        }
        relMovesExp_ = new EqList<MoveUsesTeam>();
        for (EntryCust<String,NumberMap<Byte,StacksOfUses>> k: healAfter.entryList()) {
            for (byte b: k.getValue().getKeys()) {
                relMovesExp_.add(new MoveUsesTeam(k.getKey(),b));
            }
        }
        if (!MoveUsesTeam.equalsSet(relMovesTh_, relMovesExp_)) {
            return false;
        }
        relMovesTh_ = new EqList<MoveUsesTeam>();
        relMovesExp_ = new EqList<MoveUsesTeam>();
        movesTeam_.clear();
        movesTeam_.addAllElts(_data.getMovesAnticipation());
        for (byte f: keysMovesLatter_) {
            for (String m: movesTeam_) {
                relMovesTh_.add(new MoveUsesTeam(m, f));
            }
        }
        for (EntryCust<String,NumberMap<Byte,Anticipation>> k: movesAnticipation.entryList()) {
            for (byte b: k.getValue().getKeys()) {
                relMovesExp_.add(new MoveUsesTeam(k.getKey(),b));
            }
        }
        if (!MoveUsesTeam.equalsSet(relMovesTh_, relMovesExp_)) {
            return false;
        }
        for (NumberMap<Byte,StacksOfUses> k: healAfter.values()) {
            for (StacksOfUses s: k.values()) {
                if (!s.isValid()) {
                    return false;
                }
            }
        }
        for (NumberMap<Byte,Anticipation> k: movesAnticipation.values()) {
            for (Anticipation s: k.values()) {
                if (!s.isValid()) {
                    return false;
                }
                if (!Numbers.eq(s.getTargetPosition().getPosition(),Fighter.BACK)) {
                    if (s.getTargetPosition().getPosition() < 0) {
                        return false;
                    }
                }
            }
        }
        Numbers<Byte> foes_ = new Numbers<Byte>(_fight.getFoeTeam().getMembers().getKeys());
        if (_numberTeam == Fight.PLAYER) {
            for (byte b:_fight.getUserTeam().getMembers().getKeys()) {
                if (!_fight.getUserTeam().getMembers().getVal(b).isBelongingToPlayer()) {
                    continue;
                }
                if (!foes_.containsAllObj(playerFightersAgainstFoe.getVal(b))) {
                    return false;
                }
            }
        }
        for (String m: successfulMovesRound) {
            if (!_data.getMoves().contains(m)) {
                return false;
            }
        }
        if (members.isEmpty()) {
            return false;
        }
        return true;
    }

    void initRoundTeam() {
        clearSuccessfuleMovesRound();
        chooseMoves();
        setNbKoRound();
    }

    void move(byte _decalage){
        EqList<PairNumber<Byte,Byte>> combattantsPositions_ = new EqList<PairNumber<Byte,Byte>>();
        for(byte c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if(!membre_.estArriere()){
                byte place_=membre_.getGroundPlace();
                combattantsPositions_.add(new PairNumber<Byte, Byte>(place_,c));
            }
        }
//        combattantsPositions_.sort(new Comparator<Pair<Byte,Byte>>(){
//            public int compare(Pair<Byte,Byte> _elt1,Pair<Byte,Byte> _elt2) {
//                return Byte.compare(_elt1.getFirst(), _elt2.getFirst());
//            }
//        });
        combattantsPositions_.sortElts(new ComparatorPairNumber<Byte,Byte>());
        short i_=0;
        for(PairNumber<Byte,Byte> e:combattantsPositions_){
            byte cle_=e.getSecond();
            members.getVal(cle_).setGroundPlace((byte) (i_+_decalage));
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

    void desactiverEffetEquipeParGroupe(StringList _attaques){
        enabledMovesByGroup.getVal(_attaques).enableReset();
    }

    void ajouterEffetEquipeEntreeAdv(String _attaque){
        enabledMovesWhileSendingFoe.put(_attaque,true);
        enabledMovesWhileSendingFoeUses.getVal(_attaque).increment();
    }

    void supprimerEffetEquipeEntreeAdv(String _attaque){
        enabledMovesWhileSendingFoe.put(_attaque,false);
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
            nbUsesMovesRound.put(e, 0);
        }
        for (Fighter f: members.values()) {
            f.setSuccessfulMove(false);
        }
    }

    void ajouterCombattantsContreAdv(byte _membre,byte _adv){
        playerFightersAgainstFoe.getVal(_membre).add(_adv);
        playerFightersAgainstFoe.getVal(_membre).removeDuplicates();
    }

    void toutSupprimerCombattantsContreAdvMembre(byte _membre){
        playerFightersAgainstFoe.getVal(_membre).clear();
    }

    void toutSupprimerCombattantsContreAdv(byte _adv){
        for(byte c:playerFightersAgainstFoe.getKeys()){
            playerFightersAgainstFoe.getVal(c).removeOneNumber(_adv);
        }
    }

    Numbers<Byte> notKoPartnersWithoutStatus(byte _position) {
        Numbers<Byte> list_ = new Numbers<Byte>();
        for (byte c: members.getKeys()) {
            if (Numbers.eq(c, _position)) {
                continue;
            }
            Fighter fighter_ = members.getVal(c);
            if (fighter_.estKo()) {
                continue;
            }
            boolean ok_=true;
            for(String c2_:fighter_.getStatusSet()){
                if(!Numbers.eq(fighter_.getStatusNbRoundShort(c2_), 0)){
                    ok_=false;
                    break;
                }
            }
            if(!ok_){
                continue;
            }
            list_.add(c);
        }
        return list_;
    }

//    void dupliquerCreaturePourEvolution(Byte _membre,Byte _nbOeufs,Byte _multiplicite,Byte _arriere,String _nomEvo,String _capacite,StringList _attaquesRetenues,Difficulty _diff,DataBase _import){
//        Fighter evolution_=members.getVal(_membre);
//        Byte total_=(byte) (members.size()+_nbOeufs);
//        Byte place_;
//        if(total_<_multiplicite){
//            place_=total_;
//        }else{
//            place_=_arriere;
//        }
//        evolution_.setGroundPlace(place_);
//        evolution_.evoluer(_nomEvo,_capacite,_attaquesRetenues,_import);
//        evolution_.initIvUt(_diff);
//        //positionsChangees.put(total_,place_);
//        members.put(total_,evolution_);
//        playerFightersAgainstFoe.put(total_,new Numbers<Byte>());
//    }
//
//    void dupliquerCreaturePourEvolutionSansApprendreAttaque(Byte _membre,Byte _nbOeufs,Byte _multiplicite,Byte _arriere,String _nomEvo,String _capacite,Difficulty _diff,DataBase _import){
//        Fighter evolution_=members.getVal(_membre);
//        Byte total_=(byte) (members.size()+_nbOeufs);
//        Byte place_;
//        if(total_<_multiplicite){
//            place_=total_;
//        }else{
//            place_=_arriere;
//        }
//        evolution_.setGroundPlace(place_);
//        evolution_.evoluerSansApprendreAttaque(_nomEvo,_capacite,_import);
//        evolution_.initIvUt(_diff);
//        //positionsChangees.put(total_,place_);
//        members.put(total_,evolution_);
//        playerFightersAgainstFoe.put(total_,new Numbers<Byte>());
//    }
//
//    void dupliquerCreaturePourEvolutionSansApprendreCapacite(Byte _membre,Byte _nbOeufs,Byte _multiplicite,Byte _arriere,String _nomEvo,StringList _attaquesRetenues,Difficulty _diff,DataBase _import){
//        Fighter evolution_=members.getVal(_membre);
//        Byte total_=(byte) (members.size()+_nbOeufs);
//        Byte place_;
//        if(total_<_multiplicite){
//            place_=total_;
//        }else{
//            place_=_arriere;
//        }
//        evolution_.setGroundPlace(place_);
//        evolution_.evoluerSansApprendreCapacite(_nomEvo,_attaquesRetenues,_import);
//        evolution_.initIvUt(_diff);
//        //positionsChangees.put(total_,place_);
//        members.put(total_,evolution_);
//        playerFightersAgainstFoe.put(total_,new Numbers<Byte>());
//    }
//
//    void dupliquerCreaturePourEvolutionSansApprendreAttaqueCapacite(Byte _membre,Byte _nbOeufs,Byte _multiplicite,Byte _arriere,String _nomEvo,Difficulty _diff,DataBase _import){
//        Fighter evolution_=members.getVal(_membre);
//        Byte total_=(byte) (members.size()+_nbOeufs);
//        Byte place_;
//        if(total_<_multiplicite){
//            place_=total_;
//        }else{
//            place_=_arriere;
//        }
//        evolution_.setGroundPlace(place_);
//        evolution_.evoluerSansApprendreAttaqueCapacite(_nomEvo,_import);
//        evolution_.initIvUt(_diff);
//        //positionsChangees.put(total_,place_);
//        members.put(total_,evolution_);
//        playerFightersAgainstFoe.put(total_,new Numbers<Byte>());
//    }

    void useItemsEndRound(DataBase _import) {
        boolean cancelUsingItems_ = false;
        for (byte f_: members.getKeys()) {
            Fighter fighter_= refPartMembres(f_);
            if (fighter_.estArriere()) {
                continue;
            }
            if (!fighter_.capaciteActive()) {
                continue;
            }
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            if (ab_.isGiveItemToAllyHavingUsed()) {
                cancelUsingItems_ = true;
                break;
            }
        }
        StringMap<String> mess_ = _import.getMessagesTeam();
        for(byte c2_: getMembers().getKeys()){
            Fighter creature_= refPartMembres(c2_);
            creature_.setLastUsedMove();
            creature_.cancelActions();
            String name_ = _import.translatePokemon(creature_.getName());
            if (!cancelUsingItems_) {
                if (creature_.isUsingItem()) {
                    comment.addMessage(mess_.getVal(USE_ITEM), name_);
                }
                creature_.tossLastUsedObject();
            } else {
                comment.addMessage(mess_.getVal(CANCEL_USE_ITEM), name_);
            }
            creature_.setUsingItem(false);
        }
    }

    void setNbKoRound(){
        nbKoPreviousRound=nbKoRound;
        nbKoRound=0;
    }

    void chooseMoves(){
        for(byte c:members.getKeys()){
            members.getVal(c).choisirAttaqueFin();
        }
    }

    Numbers<Byte> fightersAtCurrentPlace(short _place){
        Numbers<Byte> cbts_ = new Numbers<Byte>();
        for(byte c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if(Numbers.eq(membre_.getGroundPlace(),_place)){
                cbts_.add(c);
            }
        }
        return cbts_;
    }

    Numbers<Byte> fightersAtCurrentPlaceIndex(short _index, boolean _belongToPlayer){
        NatTreeMap<Byte,Byte> cbts_ = new NatTreeMap<Byte,Byte>();
        for(byte c:members.getKeys()){
            Fighter membre_=members.getVal(c);
            if (membre_.estArriere()) {
                continue;
            }
            if (_belongToPlayer) {
                if (!membre_.isBelongingToPlayer()) {
                    continue;
                }
            }
            cbts_.put(membre_.getGroundPlace(), c);
        }
        Numbers<Byte> res_ = new Numbers<Byte>();
        if (cbts_.getKeys().isValidIndex(_index)) {
            res_.add(cbts_.getValue(_index));
        }
        return res_;
    }

    byte substituteAtIndex(short _index) {
        byte substitute_ = Fighter.BACK;
        byte i_ = CustList.FIRST_INDEX;
        Numbers<Byte> list_ = new Numbers<Byte>(members.getKeys());
//        list_.sort(new NaturalComparator<Byte>() {
//            @Override
//            public int compare(Byte _o1, Byte _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
        list_.sort();
        for (byte k: list_) {
            Fighter fighter_ = members.getVal(k);
            if (!fighter_.estArriere()) {
                continue;
            }
            if (Numbers.eq(i_, _index)) {
                substitute_ = k;
                break;
            }
            i_++;
        }
        return substitute_;
    }

    int indexOfSubstitute(byte _sub) {
        Numbers<Byte> list_ = new Numbers<Byte>(members.getKeys());
//        list_.sort(new NaturalComparator<Byte>() {
//            @Override
//            public int compare(Byte _o1, Byte _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
        list_.sort();
        int ind_ = CustList.INDEX_NOT_FOUND_ELT;
        int i_ = CustList.FIRST_INDEX;
        for (byte b: list_) {
            Fighter f_ = members.getVal(b);
            if (!f_.estArriere()) {
                continue;
            }
            if (Numbers.eq(b, _sub)) {
                ind_ = i_;
                break;
            }
            i_++;
        }
        return ind_;
    }

    byte fighterAtIndex(short _index) {
        byte index_ = Fighter.BACK;
        byte i_ = CustList.FIRST_INDEX;
        Numbers<Byte> list_ = new Numbers<Byte>(members.getKeys());
//        list_.sort(new NaturalComparator<Byte>() {
//            @Override
//            public int compare(Byte _o1, Byte _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
        list_.sort();
        for (byte k: list_) {
            if (!members.getVal(k).isBelongingToPlayer()) {
                continue;
            }
            if (Numbers.eq(i_, _index)) {
                index_ = k;
                break;
            }
            i_++;
        }
        return index_;
    }

    boolean estKo(){
        for(byte c:members.getKeys()){
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

    EqList<StringList> enabledTeamGroupMoves() {
        EqList<StringList> list_ = new EqList<StringList>();
        for(StringList m:enabledMovesByGroup.getKeys()){
            if(!enabledMovesByGroup.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    Numbers<Byte> frontFighters(){
        Numbers<Byte> cbts_ = new Numbers<Byte>();
        for(byte c2_: members.getKeys()){
            if(!members.getVal(c2_).estArriere()){
                cbts_.add(c2_);
            }
        }
        return cbts_;
    }

    public Fighter refPartMembres(byte _position) {
        return members.getVal(_position);
    }

    public Comment getComment() {
        return comment;
    }

    void setComment(Comment _comment) {
        comment = _comment;
    }

    public ObjectMap<StringList,ActivityOfMove> getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }

    public void setEnabledMovesByGroup(ObjectMap<StringList,ActivityOfMove> _enabledMoves) {
        enabledMovesByGroup = _enabledMoves;
    }

    public StringMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public void setEnabledMoves(StringMap<ActivityOfMove> _enabledMoves) {
        enabledMoves = _enabledMoves;
    }

    public StringMap<Boolean> getEnabledMovesWhileSendingFoe() {
        return enabledMovesWhileSendingFoe;
    }

    public void setEnabledMovesWhileSendingFoe(StringMap<Boolean> _enabledMovesWhileSendingFoe) {
        enabledMovesWhileSendingFoe = _enabledMovesWhileSendingFoe;
    }

    public StringMap<LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public void setEnabledMovesWhileSendingFoeUses(StringMap<LgInt> _enabledMovesWhileSendingFoeUses) {
        enabledMovesWhileSendingFoeUses = _enabledMovesWhileSendingFoeUses;
    }

    public StringMap<Integer> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public void setNbUsesMoves(StringMap<Integer> _nbUsesMoves) {
        nbUsesMoves = _nbUsesMoves;
    }

    public StringMap<Integer> getNbUsesMovesRound() {
        return nbUsesMovesRound;
    }

    public void setNbUsesMovesRound(StringMap<Integer> _nbUsesMovesRound) {
        nbUsesMovesRound = _nbUsesMovesRound;
    }

    public StringList getHealAfterSet() {
        return healAfter.getKeys();
    }

    public Listable<Byte> getHealAfterSet(String _move) {
        return healAfter.getVal(_move).getKeys();
    }

    public StacksOfUses getHealAfterVal(String _move, byte _key) {
        return healAfter.getVal(_move).getVal(_key);
    }

    public StringMap<NumberMap<Byte,StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public void setHealAfter(StringMap<NumberMap<Byte,StacksOfUses>> _healAfter) {
        healAfter = _healAfter;
    }

    public StringList getMovesAnticipationSet() {
        return movesAnticipation.getKeys();
    }

    public Listable<Byte> getMovesAnticipationSet(String _move) {
        return movesAnticipation.getVal(_move).getKeys();
    }

    public Anticipation getMovesAnticipationVal(String _move, byte _key) {
        return movesAnticipation.getVal(_move).getVal(_key);
    }

    /**Only for checking player actions*/
    Listable<NumberMap<Byte,Anticipation>> getMovesAnticipationValues() {
        return movesAnticipation.values();
    }

    public StringMap<NumberMap<Byte,Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public void setMovesAnticipation(StringMap<NumberMap<Byte,Anticipation>> _movesAnticipation) {
        movesAnticipation = _movesAnticipation;
    }

    public NumberMap<Byte,Fighter> getMembers() {
        return members;
    }

    public void setMembers(NumberMap<Byte,Fighter> _members) {
        members = _members;
    }

    public Listable<Byte> getPlayerFightersAgainstFoeSet() {
        return playerFightersAgainstFoe.getKeys();
    }

    public Numbers<Byte> getPlayerFightersAgainstFoeVal(byte _key) {
        return playerFightersAgainstFoe.getVal(_key);
    }

    public boolean playerFightersAgainstFoeHas(byte _key, byte _value) {
        return playerFightersAgainstFoe.getVal(_key).containsObj(_value);
    }

    public NumberMap<Byte,Numbers<Byte>> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }

    public void setPlayerFightersAgainstFoe(NumberMap<Byte,Numbers<Byte>> _playerFightersAgainstFoe) {
        playerFightersAgainstFoe = _playerFightersAgainstFoe;
    }

    public byte getNbKoRound() {
        return nbKoRound;
    }

    public void setNbKoRound(byte _nbKoRound) {
        nbKoRound = _nbKoRound;
    }

    public byte getNbKoPreviousRound() {
        return nbKoPreviousRound;
    }

    public void setNbKoPreviousRound(byte _nbKoPreviousRound) {
        nbKoPreviousRound = _nbKoPreviousRound;
    }

    public StringList getSuccessfulMovesRound() {
        return successfulMovesRound;
    }

    public void setSuccessfulMovesRound(StringList _successfulMovesRound) {
        successfulMovesRound = _successfulMovesRound;
    }
}
