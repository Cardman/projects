package aiki.game.player;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.db.ExchangedData;
import aiki.facade.SexListInt;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.Repel;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorBoolean;
import code.util.comparators.ComparatorTreeMapValue;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class Player {

    public static final String DEFAULT_NICKNAME_PREFIX = "0_";

    public static final String BAD_NUMBER_MOVES = "0";
    public static final String BETWEEN_NUMBER_MOVES = "1";
    public static final String BOOSTED_MOVE = "2";
    public static final String BOOSTED_STATISTIC = "3";
    public static final String CANNOT_EVOLVE = "4";
    public static final String ENABLE_REPEL = "5";
    public static final String EVOLVE_INTO = "6";
    public static final String FORGET_MOVES = "7";
    public static final String HATCH = "8";
    public static final String HEAL_STATUS = "9";
    public static final String KEEP_MOVES = "10";
    public static final String LEARN_MOVE = "11";
    public static final String LEARN_MOVES = "12";
    public static final String LEARN_MOVE_FORGET = "13";
    public static final String MISS_MOVES = "14";
    public static final String NEW_PK = "15";
    public static final String NEW_PK_ADDED_BOX = "16";
    public static final String REPEL_OFF = "17";
    public static final String RESTORED_HP = "18";
    public static final String RESTORED_MOVE = "19";
    public static final String TAKEN_ITEM = "20";

    /**nom du heros*/
    private String nickname;

    /**sexe du heros*/
    private Sex sex = Sex.NO;

    /**Ensemble des pokemon et des oeufs presents dans l'equipe. (maximum 6)
    equipe de l'utilisateur autre que les oeufs (maximum 6)*/
    private CustList<UsablePokemon> team;

    /**Ensemble des boites contenant les pokemon et les oeufs (maximum 256 boites de 256 pokemon)*/
    private CustList<UsablePokemon> box;

    /**Inventaire des objets de l'utilisateur.*/
    private Inventory inventory;

    /**Ensemble des pokemon attrapes.*/
    private StringMap<BoolVal> caughtPk;

    /***/
    private LgInt money;

    /***/
    private long remainingRepelSteps;

    /***/
    private Comment commentGame = new Comment();

    private String selectedObject = DataBase.EMPTY_STRING;

    private String selectedMove = DataBase.EMPTY_STRING;

    private StringMap<Long> chosenMoves = new StringMap<Long>();

    private StringMap<BoolVal> selectedMoves = new StringMap<BoolVal>();

    private int chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;

    private String chosenAbilityForEvolution = DataBase.EMPTY_STRING;

    private Ints indexesOfPokemonTeam = new Ints();

    //values are true <==> a move has to be forgotten
    private IntMap<BoolVal> indexesOfPokemonTeamMoves = new IntMap<BoolVal>();

    public Player(){
    }

    public Player(String _pseudo,Sex _sexeHeros,Difficulty _diff,boolean _avecPkIni,DataBase _import){
        nickname=_pseudo;
        if (nickname.isEmpty()) {
            if (_sexeHeros == Sex.NO) {
                nickname = DEFAULT_NICKNAME_PREFIX;
            } else {
                nickname = StringUtil.concat(DEFAULT_NICKNAME_PREFIX, _sexeHeros.getSexName());
            }
        }
        sex=_sexeHeros;
        team = new CustList<UsablePokemon>();
        box = new CustList<UsablePokemon>();
        caughtPk = new StringMap<BoolVal>();
        if(_avecPkIni){
            DataMap donnees_=_import.getMap();
            initTeam(_sexeHeros, _diff, donnees_.getFirstPokemon(), _import);
        }
        setMoney(_import.getDefaultMoney().intPart());
        setInventory(new Inventory(_import));
        remainingRepelSteps=0;
    }
    public static Player build(Difficulty _diff,boolean _avecPkIni,DataBase _import) {
        return build(DataBase.EMPTY_STRING,_diff,_avecPkIni,_import);
    }
    public static Player build(String _nickname,Difficulty _diff,boolean _avecPkIni,DataBase _import) {
        return new Player(_nickname,Sex.NO,_diff,_avecPkIni,_import);
    }

    void initTeam(Sex _sexeHeros,Difficulty _diff, WildPk _firstPk,DataBase _import){
        PokemonData fPk_=_import.getPokemon(_firstPk.getName());
        PokemonPlayer userPk_;
        if (fPk_.getGenderRep() == GenderRepartition.MIXED) {
            userPk_ = new PokemonPlayer(_firstPk,_import,_sexeHeros, _diff);
        } else {
            userPk_ = new PokemonPlayer(_firstPk,_import,fPk_.getGenderRep().getPossibleGenders().first(), _diff);
        }
        team.add(userPk_);
        for(String p:_import.getPokedex().getKeys()){
            caughtPk.put(p, BoolVal.FALSE);
        }
        caughtPk.put(_firstPk.getName(),BoolVal.TRUE);
    }

    public void initIv(Difficulty _diff, DataBase _data){
        commentGame = new Comment();
        selectedObject = DataBase.EMPTY_STRING;
        selectedMove = DataBase.EMPTY_STRING;
        chosenMoves = new StringMap<Long>();
        selectedMoves = new StringMap<BoolVal>();
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        chosenAbilityForEvolution = DataBase.EMPTY_STRING;
        indexesOfPokemonTeam = new Ints();
        indexesOfPokemonTeamMoves = new IntMap<BoolVal>();
        for(UsablePokemon e:team){
            if (!(e instanceof PokemonPlayer)) {
                continue;
            }
            ((PokemonPlayer) e).initIv(_diff);
            ((PokemonPlayer) e).initHp(_data);
            commentGame.addComment(((PokemonPlayer) e).getCommentPk());
        }
        for(UsablePokemon e:box){
            if (!(e instanceof PokemonPlayer)) {
                continue;
            }
            ((PokemonPlayer) e).initIv(_diff);
            ((PokemonPlayer) e).initHp(_data);
        }
    }

    public boolean validate(DataBase _data, SexListInt _sexList) {
        if (badSexTeamCount(_data,_sexList)) {
            return false;
        }
        int nbPkPlayers_ = IndexConstants.SIZE_EMPTY;
        for (UsablePokemon e: team) {
            if (e instanceof PokemonPlayer) {
                nbPkPlayers_++;
            }
            if (!e.validate(_data)){
                return false;
            }
        }
        if (NumberUtil.eq(nbPkPlayers_, IndexConstants.SIZE_EMPTY)) {
            return false;
        }
        for (UsablePokemon p: box) {
            if (p instanceof PokemonPlayer) {
                ((PokemonPlayer)p).fullHeal(_data);
            }
            if (!p.validate(_data)) {
                return false;
            }
        }
        if (!StringUtil.equalsSet(_data.getPokedex().getKeys(), caughtPk.getKeys())) {
            return false;
        }
        if (!money.isZeroOrGt()) {
            return false;
        }
        if (!inventory.validate(_data)) {
            return false;
        }
        return remainingRepelSteps >= 0;
    }

    private boolean badSexTeamCount(DataBase _data, SexListInt _sexList) {
        return badSex(_sexList) || badTeamCount(_data);
    }

    private boolean badSex(SexListInt _sexList) {
        boolean ok_ = false;
        for (Sex s: _sexList.all()) {
            if (s == sex) {
                ok_ = true;
                break;
            }
        }
        return !ok_;
    }

    private boolean badTeamCount(DataBase _data) {
        return team.isEmpty() || team.size() > _data.getNbMaxTeam();
    }

    public boolean existBall(DataBase _import) {
        boolean existBall_ = false;
        for (String o: _import.getItems().getKeys()) {
            Item obj_ = _import.getItem(o);
            if (obj_ instanceof Ball && !getInventory().getNumber(o).isZero()) {
                existBall_ = true;
                break;
            }
        }
        return existBall_;
    }

    StringList nouveauxNes(DataBase _import){
        StringList nouveauxNes_=new StringList();
        for(UsablePokemon u: team){
            if (!(u instanceof Egg)) {
                continue;
            }
            long nbPas_=((Egg) u).getSteps();
            String nom_=((Egg) u).getName();
            PokemonData fPk_=_import.getPokemon(nom_);
            LgInt pas_=fPk_.getHatchingSteps();
            if(LgInt.greaterEq(new LgInt(nbPas_),pas_)){
                nouveauxNes_.add(nom_);
            }
        }
        return nouveauxNes_;
    }

    public void moveLoop(int _nbSteps,Difficulty _diff,DataBase _import) {
        clearComments();
        for (int i = IndexConstants.FIRST_INDEX; i < _nbSteps; i++) {
            deplacement(_diff,_import);
        }
    }

    void deplacement(Difficulty _diff,DataBase _import){
        int coeff_=1;
        boolean firstPk_ = true;
        for(UsablePokemon e:team){
            if (!(e instanceof PokemonPlayer)) {
                continue;
            }
            if (firstPk_) {
                ((PokemonPlayer)e).deplacement(_import);
                firstPk_ = false;
            }
            AbilityData fCapac_=_import.getSafeAbility(((PokemonPlayer) e).getAbility());
            coeff_+=fCapac_.getDecreaseNecStepsHatch();
        }
        for(UsablePokemon e:team){
            if (!(e instanceof Egg)) {
                continue;
            }
            ((Egg) e).versEclosion(coeff_);
        }
        eclosionOeuf(_diff,_import);
        StringMap<String> mess_ = _import.getMessagesPlayer();
        if(remainingRepelSteps>0){
            remainingRepelSteps--;
            if (remainingRepelSteps == 0) {
                commentGame.addMessage(mess_.getVal(REPEL_OFF));
            }
        }
    }

    void eclosionOeuf(Difficulty _diff,DataBase _import){
        int nbPks_ = team.size();
        for(int k = IndexConstants.FIRST_INDEX; k<nbPks_; k++){
            UsablePokemon usPk_=team.get(k);
            if (!(usPk_ instanceof Egg)) {
                continue;
            }
            Egg oeuf_=(Egg)usPk_;
            PokemonData fPk_ = _import.getPokemon(oeuf_.getName());
            if (oeuf_.getSteps() >= fPk_.getHatchingSteps().ll()) {
                PokemonPlayer pk_ = new PokemonPlayer(oeuf_, _import, _diff);

//            team.set(k, new PokemonPlayer(oeuf_,_import));
                team.set(k, pk_);
                StringMap<String> mess_ = _import.getMessagesPlayer();
                commentGame.addMessage(mess_.getVal(HATCH), _import.translatePokemon(oeuf_.getName()));
                boolean alreadyCaught_ = estAttrape(oeuf_.getName());
                attrapePk(oeuf_.getName());
                addMessageNewPk(oeuf_.getName(), alreadyCaught_, _import);
            }
        }
    }

    public void doRevivingFossil(String _nomFossile,Difficulty _diff,DataBase _import){
        commentGame.clearMessages();
        if (inventory.getNumber(_nomFossile).isZero()) {
            return;
        }
        int pos_= team.size();
        Fossil fossile_=(Fossil)_import.getItem(_nomFossile);
        if(pos_<_import.getNbMaxTeam()){
            PokemonPlayer lasPk_ = new PokemonPlayer(fossile_,_import, _diff);
            team.add(lasPk_);
            boolean alreadyCaught_ = estAttrape(lasPk_.getName());
            attrapePk(lasPk_.getName());
            inventory.use(_nomFossile);
            addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
            return;
        }
        PokemonPlayer lasPk_ = new PokemonPlayer(fossile_,_import, _diff);
        box.add(lasPk_);
        boolean alreadyCaught_ = estAttrape(lasPk_.getName());
        attrapePk(lasPk_.getName());
        inventory.use(_nomFossile);
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(NEW_PK_ADDED_BOX));
        addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
    }

    void addMessageNewPk(String _name, boolean _alreadyCaught,DataBase _import) {
        if (!_alreadyCaught) {
            String name_ = _import.translatePokemon(_name);
            StringMap<String> mess_ = _import.getMessagesPlayer();
            commentGame.addMessage(mess_.getVal(NEW_PK), name_);
        }
    }

    public boolean enabledSwitchObjectsTeamBox() {
        if (chosenTeamPokemon == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return team.get(chosenTeamPokemon) instanceof PokemonPlayer;
    }

    public void switchObjectsTeamBox(int _box){
        Pokemon usPk_ = (Pokemon) team.get(chosenTeamPokemon);
        Pokemon boxPk_ = (Pokemon) box.get(_box);
        String itemOne_ = usPk_.getItem();
        String itemTwo_ = boxPk_.getItem();
        usPk_.setItem(itemTwo_);
        boxPk_.setItem(itemOne_);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void switchObjectsBox(int _chosenPokemonFirstBox,int _chosenPokemonSecondBox){
        if (_chosenPokemonFirstBox == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        if (_chosenPokemonSecondBox == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        Pokemon firstBoxPk_ = (Pokemon) box.get(_chosenPokemonFirstBox);
        Pokemon secondBoxPk_ = (Pokemon) box.get(_chosenPokemonSecondBox);
        String itemOne_ = firstBoxPk_.getItem();
        String itemTwo_ = secondBoxPk_.getItem();
        firstBoxPk_.setItem(itemTwo_);
        secondBoxPk_.setItem(itemOne_);
    }

    public boolean enabledSwitchPokemonBoxTeam() {
        return chosenTeamPokemon != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void switchPokemon(int _box, DataBase _import){
        UsablePokemon tmp_ = box.get(_box);
        UsablePokemon other_ = team.get(chosenTeamPokemon);
        if (other_ instanceof PokemonPlayer) {
            ((PokemonPlayer)other_).fullHeal(_import);
        }
        box.set(_box, other_);
        team.set(chosenTeamPokemon,tmp_);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledStorePokemonBox() {
        return chosenTeamPokemon != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void storeIntoBox(DataBase _import){
        UsablePokemon pk_ = team.get(chosenTeamPokemon);
        team.remove(chosenTeamPokemon);
        //        UsablePokemon pk_=team.removeAtAndGet(chosenTeamPokemon);
        if (pk_ instanceof PokemonPlayer) {
            ((PokemonPlayer) pk_).fullHeal(_import);
        }
        box.add(pk_);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledTakeObjectTeam() {
        return chosenTeamPokemon != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void takePokemonFromBox(int _box, DataBase _import){
        int pos_=team.size();
        if(pos_>=_import.getNbMaxTeam()){
            return;
        }
        UsablePokemon pk_ = box.get(_box);
        box.remove(_box);
        team.add(pk_);
//        team.add(box.removeAtAndGet(_box));
    }

    public boolean enabledSwitchTeamOrder() {
        if (team.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return false;
        }
        return chosenTeamPokemon != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void switchTeamOrder(int _other){
        if(NumberUtil.eq(chosenTeamPokemon, _other)){
            return;
        }
        team.swapIndexes(chosenTeamPokemon, _other);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledSwitchObjectsTeam() {
        if (team.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return false;
        }
        if (chosenTeamPokemon == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return team.get(chosenTeamPokemon) instanceof PokemonPlayer;
    }

    public void switchObjectsTeam(int _other) {
        if (NumberUtil.eq(chosenTeamPokemon, _other)) {
            return;
        }
        if (!(team.get(_other) instanceof PokemonPlayer)) {
            return;
        }
        String itemOne_=((Pokemon) team.get(chosenTeamPokemon)).getItem();
        String itemTwo_=((Pokemon) team.get(_other)).getItem();
        ((Pokemon) team.get(_other)).setItem(itemOne_);
        ((Pokemon) team.get(chosenTeamPokemon)).setItem(itemTwo_);
    }

    public boolean isReleasable(int _box, DataBase _import){
        UsablePokemon usPk_ = box.get(_box);
        if (usPk_ instanceof PokemonPlayer) {
            PokemonData fPk_=_import.getPokemon(((PokemonPlayer) usPk_).getName());
            return fPk_.getGenderRep() != GenderRepartition.LEGENDARY;
        }
        return true;
    }

    public void releasePokemon(int _box, DataBase _import){
        UsablePokemon usPk_ = box.get(_box);
        if (usPk_ instanceof PokemonPlayer) {
            PokemonData fPk_=_import.getPokemon(((PokemonPlayer) usPk_).getName());
            if(fPk_.getGenderRep() == GenderRepartition.LEGENDARY){
                return;
            }
            String obj_=((PokemonPlayer) usPk_).getItem();
            if(!obj_.isEmpty()){
                getItem(obj_);
            }
        }
        box.remove(_box);
    }

    public void setPokemonAbleToHoldObject() {
        indexesOfPokemonTeam.clear();
        int nbPks_ = team.size();
        for (int k = IndexConstants.FIRST_INDEX; k<nbPks_; k++) {
            UsablePokemon us_ = team.get(k);
            if (!(us_ instanceof Pokemon) || !((Pokemon) us_).getItem().isEmpty()) {
                continue;
            }
            indexesOfPokemonTeam.add(k);
        }
    }

    public void giveObject(int _indice){
        String obj_=((PokemonPlayer) team.get(_indice)).getItem();
        if(!obj_.isEmpty()){
            return;
        }
        indexesOfPokemonTeam.clear();
        ((PokemonPlayer) team.get(_indice)).setItem(selectedObject);
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
    }

    public void getItem(String _obj) {
        inventory.getItem(_obj);
    }

    public void healTeamWithoutUsingObject(DataBase _import){
        for (UsablePokemon u: team) {
            if(!(u instanceof PokemonPlayer)) {
                continue;
            }
            ((PokemonPlayer)u).fullHeal(_import);
        }
    }

    void healTeam(String _nomObjet,DataBase _import){
        inventory.use(_nomObjet);
        healTeamWithoutUsingObject(_import);
    }

    void heal(int _chosenTeamPokemon, DataBase _import){
        if (!indexesOfPokemonTeam.contains(_chosenTeamPokemon)) {
            return;
        }
        boolean consommer_=false;
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(_chosenTeamPokemon);
        Item objet_=_import.getItem(selectedObject);
        if(objet_ instanceof Berry){
            consommer_ = healByBerry(_import, pkSoigne_, (Berry) objet_);
        }
        if(objet_ instanceof HealingPp){
            consommer_ = healByPp(_import, pkSoigne_, (HealingPp) objet_);
        }
        if(objet_ instanceof HealingHp){
            consommer_ = healByHp(_import, pkSoigne_, (HealingHp) objet_);
        }
        if(objet_ instanceof HealingStatus){
            consommer_ = healByByStatus(_import, pkSoigne_, objet_);
        }
        if(consommer_){
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
        }
    }

    private boolean healByByStatus(DataBase _import, PokemonPlayer _pkSoigne, Item _objet) {
        StringMap<String> mess_ = _import.getMessagesPlayer();
        boolean consommer_=false;
        if(_objet instanceof HealingHpStatus){
            HealingHpStatus soin_=(HealingHpStatus) _objet;
            Rate pvRestaures_= _pkSoigne.pvSoignesAvecStatut(soin_, _import);
            _pkSoigne.variationPvRestants(pvRestaures_);
            if(!pvRestaures_.isZero()){
                consommer_ =true;
                String pk_ = _import.translatePokemon(_pkSoigne.getName());
                commentGame.addMessage(mess_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
            }
        }
        HealingStatus soin_=(HealingStatus) _objet;
        StringList statuts_=new StringList(_pkSoigne.getStatus());
        _pkSoigne.soinStatuts(soin_.getStatus());
        if(!StringUtil.equalsSet(statuts_, _pkSoigne.getStatus())){
            StringUtil.removeAllElements(statuts_, _pkSoigne.getStatus());
            for (String s: statuts_) {
                String st_ = _import.translateStatus(s);
                String pk_ = _import.translatePokemon(_pkSoigne.getName());
                commentGame.addMessage(mess_.getVal(HEAL_STATUS), st_, pk_);
            }
            consommer_ =true;
        }
        return consommer_;
    }

    private boolean healByHp(DataBase _import, PokemonPlayer _pkSoigne, HealingHp _objet) {
        StringMap<String> mess_ = _import.getMessagesPlayer();
        boolean consommer_=false;
        Rate pvRestaures_= _pkSoigne.pvSoignesSansStatut(_objet, _import);
        _pkSoigne.variationPvRestants(pvRestaures_);
        long happinessIncrease_ = _pkSoigne.pointBonheurGagnes(_objet, _import);
        _pkSoigne.variationBonheur(happinessIncrease_, _import);
        commentGame.addComment(_pkSoigne.getCommentPk());
        if(!pvRestaures_.isZero()){
            consommer_ =true;
            String pk_ = _import.translatePokemon(_pkSoigne.getName());
            commentGame.addMessage(mess_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
        }
        return consommer_;
    }

    private boolean healByPp(DataBase _import, PokemonPlayer _pkSoigne, HealingPp _objet) {
        StringMap<String> mess_ = _import.getMessagesPlayer();
        boolean consommer_=false;
        StringMap<Long> attaquesRestaures_= _pkSoigne.ppSoignesAttaques(_objet);
        if (_objet.isHealingAllMovesPp() || _objet.getHealingAllMovesFullpp() > 0) {
            _pkSoigne.soinPpAttaques(attaquesRestaures_);
        }
        for(String c:attaquesRestaures_.getKeys()){
            if (attaquesRestaures_.getVal(c) > 0) {
                String move_ = _import.translateMove(c);
                String pk_ = _import.translatePokemon(_pkSoigne.getName());
                commentGame.addMessage(mess_.getVal(RESTORED_MOVE), move_, pk_, Long.toString(attaquesRestaures_.getVal(c)));
            }
        }
        for(String c:attaquesRestaures_.getKeys()){
            if(attaquesRestaures_.getVal(c)>0){
                consommer_ =true;
                break;
            }
        }
        return consommer_;
    }

    private boolean healByBerry(DataBase _import, PokemonPlayer _pkSoigne, Berry _objet) {
        StringMap<String> mess_ = _import.getMessagesPlayer();
        boolean consommer_=false;
        if(!_objet.getHealHp().isZero()||!_objet.getHealHpRate().isZero()||!_objet.getHealHpBySuperEffMove().isZero()){
            Rate pvRestaures_= _pkSoigne.pvSoignesBaie(_objet, _import);
            _pkSoigne.variationPvRestants(pvRestaures_);
            if(!pvRestaures_.isZero()){
                consommer_ =true;
                String pk_ = _import.translatePokemon(_pkSoigne.getName());
                commentGame.addMessage(mess_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
            }
        }
        StringList statuts_=new StringList(_pkSoigne.getStatus());
        _pkSoigne.soinStatuts(_objet.getHealStatus());
        if(!StringUtil.equalsSet(statuts_, _pkSoigne.getStatus())){
            StringUtil.removeAllElements(statuts_, _pkSoigne.getStatus());
            for (String s: statuts_) {
                String st_ = _import.translateStatus(s);
                String pk_ = _import.translatePokemon(_pkSoigne.getName());
                commentGame.addMessage(mess_.getVal(HEAL_STATUS), st_, pk_);
            }
            consommer_ =true;
        }
        return consommer_;
    }

    void initializeMovesToBeHealed(int _chosenTeamPokemon, DataBase _import) {
        chosenTeamPokemon = _chosenTeamPokemon;
        chosenMoves.clear();
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        Item objet_ = _import.getItem(selectedObject);
        if(objet_ instanceof Berry){
            Berry baie_=(Berry)objet_;
            for (String m: pkSoigne_.getMoves().getKeys()) {
                long pp_ = pkSoigne_.ppSoignesAttaqueBaie(baie_,m);
                if (pp_ > 0) {
                    chosenMoves.put(m, pp_);
                }
            }
        }
        if(objet_ instanceof HealingPp){
            HealingPp soin_=(HealingPp)objet_;
            for (String m: pkSoigne_.getMoves().getKeys()) {
                long pp_ = pkSoigne_.ppSoignesAttaque(soin_,m);
                if (pp_ > 0) {
                    chosenMoves.put(m, pp_);
                }
            }
        }
        if (chosenMoves.isEmpty()) {
            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        }
    }

    public void healMove(String _move, DataBase _import) {
        clearComments();
        if (_move.isEmpty()) {
            selectedObject = DataBase.EMPTY_STRING;
            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
            chosenMoves.clear();
            return;
        }
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        long ppRest_ = chosenMoves.getVal(_move);
        String move_ = _import.translateMove(_move);
        String pk_ = _import.translatePokemon(pkSoigne_.getName());
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(RESTORED_MOVE), move_, pk_, Long.toString(ppRest_));
        chosenMoves.clear();
        chosenMoves.put(_move, ppRest_);
        pkSoigne_.soinPpAttaques(chosenMoves);
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
    }

    void initializeMovesToBeBoosted(int _chosenTeamPokemon, DataBase _import) {
        chosenTeamPokemon = _chosenTeamPokemon;
        chosenMoves.clear();
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        long maxPp_= _import.getMaxPp();
        Boost boost_ = (Boost)_import.getItem(selectedObject);
        for (String m: pkSoigne_.getMoves().getKeys()) {
            long currentMax_ = pkSoigne_.getMoves().getVal(m).getMax();
            if (currentMax_ >= maxPp_) {
                continue;
            }
            chosenMoves.put(m, pkSoigne_.wonPp(boost_, m, maxPp_));
        }
        if (chosenMoves.isEmpty()) {
            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        }
    }

    public void gainPpMax(String _move,DataBase _import){
        clearComments();
        if (_move.isEmpty()) {
            selectedObject = DataBase.EMPTY_STRING;
            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
            chosenMoves.clear();
            return;
        }
        Boost boost_=(Boost)_import.getItem(selectedObject);
        PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
        long ppMax_ = pk_.getMoves().getVal(_move).getMax();
        pk_.boostPp(_move, chosenMoves.getVal(_move));
        long happinessIncrease_ = pk_.pointBonheurGagnes(boost_, _import);
        pk_.variationBonheur(happinessIncrease_, _import);
        if (happinessIncrease_ > 0) {
            commentGame.addComment(pk_.getCommentPk());
        }
        StringMap<String> mess_ = _import.getMessagesPlayer();
        if (ppMax_ != pk_.getMoves().getVal(_move).getMax()) {
            String move_ = _import.translateMove(_move);
            String pkName_ = _import.translatePokemon(pk_.getName());
            long diff_ = pk_.getMoves().getVal(_move).getMax();
            diff_ -= ppMax_;
            commentGame.addMessage(mess_.getVal(BOOSTED_MOVE), move_, pkName_, Long.toString(diff_));
        }
        if (ppMax_ != pk_.getMoves().getVal(_move).getMax() || happinessIncrease_ > 0) {
            inventory.use(selectedObject);
            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
            selectedObject = DataBase.EMPTY_STRING;
            chosenMoves.clear();
        }
    }


    void boostStatistique(int _chosenTeamPokemon,DataBase _import){
        PokemonPlayer pk_ = (PokemonPlayer) team.get(_chosenTeamPokemon);
        boolean increase_ = false;
        Boost boost_ = (Boost) _import.getItem(selectedObject);
        for (Statistic s: boost_.getEvs().getKeys()) {
            long increment_ = boost_.getEvs().getVal(s);
            long var_=pk_.evGagnes(increment_, s, _import.getMaxEv());
            pk_.gainEv(selectedObject,var_,_import);
            if (var_ > 0) {
                increase_ = true;
                String stat_ = _import.translateStatistics(s);
                String pkName_ = _import.translatePokemon(pk_.getName());
                StringMap<String> mess_ = _import.getMessagesPlayer();
                commentGame.addMessage(mess_.getVal(BOOSTED_STATISTIC), stat_, pkName_, Long.toString(var_));
            }
        }
        long happinessIncrease_ = pk_.pointBonheurGagnes(_import.getItem(selectedObject), _import);
        pk_.variationBonheur(happinessIncrease_, _import);
        if (happinessIncrease_ > 0) {
            commentGame.addComment(pk_.getCommentPk());
        }
        if(increase_ || happinessIncrease_ > 0){
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
        }
    }

    public void choosePokemonForMoveTutors(int _indice, DataBase _import) {
        Ints keys_ = new Ints(getPokemonPlayerList().getKeys());
        chosenTeamPokemon = keys_.get(_indice);
        chosenMoves.clear();
        selectedMoves.clear();
        for (String m: ((PokemonPlayer) team.get(chosenTeamPokemon)).getMoves().getKeys()) {
            selectedMoves.put(m, BoolVal.TRUE);
        }
        for (String m: ((PokemonPlayer) team.get(chosenTeamPokemon)).moveTutors(_import)) {
            MoveData fMove_ = _import.getMove(m);
            chosenMoves.put(m, fMove_.getPp());
            selectedMoves.put(m, BoolVal.FALSE);
        }
    }

    /**For move tutors*/
    public boolean canKeepAllOldMoves(DataBase _import) {
        return ((PokemonPlayer) getSelectedPkTeam()).getMoves().size() < _import.getNbMaxMoves();
    }

    public CustList<String> currentMovesPokemon() {
        return ((PokemonPlayer) getSelectedPkTeam()).getMoves().getKeys();
    }

    public boolean isValidPkPlayerChoice() {
        if (!team.isValidIndex(chosenTeamPokemon)) {
            return false;
        }
        return team.get(chosenTeamPokemon) instanceof PokemonPlayer;
    }

    public UsablePokemon getSelectedPkTeam() {
        return team.get(chosenTeamPokemon);
    }

    public void cancelLearningMove() {
        cancelLearningMoveOnPokemon();
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        selectedMove = DataBase.EMPTY_STRING;
    }

    public void cancelLearningMoveOnPokemon() {
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMoves.clear();
    }

    public void learnMovesByMoveTutor(DataBase _import) {
        commentGame.clearMessages();
        StringMap<String> mess_ = _import.getMessagesPlayer();
        if (selectedMoves.size() >= _import.getNbMaxMoves()) {
//            if (!Numbers.eq(selectedMoves.getKeys(true).size(), _import.getNbMaxMoves()))
            if (!NumberUtil.eq(getCheckedMoves().size(), _import.getNbMaxMoves())) {
                String name_ = ((PokemonPlayer) team.get(chosenTeamPokemon)).getName();
                name_ = _import.translatePokemon(name_);
                commentGame.addMessage(mess_.getVal(BAD_NUMBER_MOVES), name_, Long.toString(getCheckedMoves().size()));
                return;
            }
        } else {
//            if (!selectedMoves.getKeys(false).isEmpty())
            if (getCheckedMoves().isEmpty()||!getUnCheckedMoves().isEmpty()) {
                String name_ = ((PokemonPlayer) team.get(chosenTeamPokemon)).getName();
                name_ = _import.translatePokemon(name_);
                commentGame.addMessage(mess_.getVal(MISS_MOVES), name_);
                return;
            }
        }
//        StringList moves_ = new StringList(selectedMoves.getKeys(true));
        StringList moves_ = getCheckedMoves();
        PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
        String name_ = _import.translatePokemon(pk_.getName());
        CustList<String> oldMoves_ = pk_.getMoves().getKeys();
        StringList forgottenMoves_;
        forgottenMoves_ = new StringList(oldMoves_);
        StringUtil.removeAllElements(forgottenMoves_, moves_);
        StringList keptMoves_;
        keptMoves_ = new StringList(oldMoves_);
//        keptMoves_.removeAllElements(selectedMoves.getKeys(false));
        StringUtil.removeAllElements(keptMoves_, getUnCheckedMoves());
        StringList learntMoves_;
        learntMoves_ = new StringList(moves_);
        StringUtil.removeAllElements(learntMoves_, oldMoves_);
        for (String m: forgottenMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(mess_.getVal(FORGET_MOVES), name_, move_);
        }
        for (String m: keptMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(mess_.getVal(KEEP_MOVES), name_, move_);
        }
        for (String m: learntMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(mess_.getVal(LEARN_MOVES), name_, move_);
        }
        pk_.learnMovesAfterForgettingAll(moves_, _import);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMoves.clear();
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
    }

    private StringList getCheckedMoves() {
        StringList l_;
        l_ = new StringList();
        for (EntryCust<String,BoolVal> e: selectedMoves.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    private StringList getUnCheckedMoves() {
        StringList l_;
        l_ = new StringList();
        for (EntryCust<String,BoolVal> e: selectedMoves.entryList()) {
            if (e.getValue() != BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public void chooseMoveByObject(String _move, DataBase _import) {
        selectedMove = _move;
//        if (_import.getHm().values().containsObj(_move))
        if (!_import.getHmByMove(_move).isEmpty()) {
            chooseMoveByHm(_move, _import);
        }
//        if (_import.getTm().values().containsObj(_move))
        if (!_import.getTmByMove(_move).isEmpty()) {
            chooseMoveByTm(_move, _import);
        }
        indexesOfPokemonTeam.removeDuplicates();
        indexesOfPokemonTeamMoves.clear();
        long maxMoves_;
        maxMoves_ = _import.getNbMaxMoves();
        for (int i: indexesOfPokemonTeam) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
//            indexesOfPokemonTeamMoves.put(i, Numbers.eq(pk_.getMoves().size(), maxMoves_));
            indexesOfPokemonTeamMoves.put(i, ComparatorBoolean.of(pk_.getMoves().size() >= maxMoves_));
        }
        if (indexesOfPokemonTeam.isEmpty()) {
            selectedMove = DataBase.EMPTY_STRING;
        }
    }

    private void chooseMoveByTm(String _move, DataBase _import) {
        for (int i: getPokemonPlayerList().getKeys()) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
            if (pk_.getMoves().contains(_move)) {
                continue;
            }
            PokemonData fPk_ = _import.getPokemon(pk_.getName());
//                for (short c: _import.getTm().getKeys(_move))
            for (int c: _import.getTmByMove(_move)) {
                if (fPk_.getTechnicalMoves().containsObj(c)) {
                    indexesOfPokemonTeam.add(i);
                    break;
                }
            }
        }
    }

    private void chooseMoveByHm(String _move, DataBase _import) {
        for (int i: getPokemonPlayerList().getKeys()) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
            if (pk_.getMoves().contains(_move)) {
                continue;
            }
            PokemonData fPk_ = _import.getPokemon(pk_.getName());
//                for (short c: _import.getHm().getKeys(_move))
            for (int c: _import.getHmByMove(_move)) {
                if (fPk_.getHiddenMoves().containsObj(c)) {
                    indexesOfPokemonTeam.add(i);
                    break;
                }
            }
        }
    }

    public void choosePokemonForLearningMove(int _position, DataBase _import) {
        clearComments();
        chosenMoves.clear();
        chosenTeamPokemon = indexesOfPokemonTeam.get(_position);
        if (indexesOfPokemonTeamMoves.getVal(chosenTeamPokemon) == BoolVal.TRUE) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
            for (String m: pk_.getMoves().getKeys()) {
                chosenMoves.put(m, pk_.getMoves().getVal(m).getMax());
            }
            return;
        }
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        String name_ = _import.translatePokemon(((PokemonPlayer) team.get(chosenTeamPokemon)).getName());
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(LEARN_MOVE), name_, _import.translateMove(selectedMove));
        ((PokemonPlayer) team.get(chosenTeamPokemon)).learnMove(selectedMove, _import);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedMove = DataBase.EMPTY_STRING;
    }

    public void learnMove(String _ancienneAttaque,DataBase _import){
        clearComments();
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        String name_ = _import.translatePokemon(((PokemonPlayer) team.get(chosenTeamPokemon)).getName());
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(LEARN_MOVE_FORGET), name_, _import.translateMove(selectedMove), _import.translateMove(_ancienneAttaque));
        ((PokemonPlayer) team.get(chosenTeamPokemon)).learnMove(selectedMove,_ancienneAttaque,_import);
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMove = DataBase.EMPTY_STRING;
    }

    public void recevoirPokemon(Pokemon _pokemonDonne,Difficulty _diff,DataBase _import){
        int pos_=team.size();
        if(pos_<_import.getNbMaxTeam()){
            PokemonPlayer lasPk_ = new PokemonPlayer(_pokemonDonne,_import, _diff);
            boolean alreadyCaught_ = estAttrape(lasPk_.getName());
            attrapePk(lasPk_.getName());
            addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
            team.add(lasPk_);
            return;
        }
        PokemonPlayer lasPk_ = new PokemonPlayer(_pokemonDonne,_import, _diff);
        boolean alreadyCaught_ = estAttrape(lasPk_.getName());
        attrapePk(lasPk_.getName());
        addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
        box.add(lasPk_);
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(NEW_PK_ADDED_BOX));
    }

    public void takeObjectFromTeam(DataBase _data) {
        clearComments();
        String obj_ = ((PokemonPlayer) team.get(chosenTeamPokemon)).getItem();
        if(obj_.isEmpty()){
            return;
        }
        getItem(obj_);
        ((Pokemon) team.get(chosenTeamPokemon)).setItem(DataBase.EMPTY_STRING);
        String item_ = _data.translateItem(obj_);
        StringMap<String> mess_ = _data.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(TAKEN_ITEM), item_);
    }

    public void takeObjectFromBox(int _box, DataBase _data) {
        String obj_ = ((PokemonPlayer) box.get(_box)).getItem();
        if(obj_.isEmpty()){
            return;
        }
        getItem(obj_);
        ((Pokemon) box.get(_box)).setItem(DataBase.EMPTY_STRING);
        String item_ = _data.translateItem(obj_);
        StringMap<String> mess_ = _data.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(TAKEN_ITEM), item_);
    }

    public boolean canBeBought(StringMap<LgInt> _objets,DataBase _import) {
        LgInt prixTotal_=LgInt.zero();
        for(String o:_objets.getKeys()){
            Item obj_=_import.getItem(o);
            LgInt quantite_=_objets.getVal(o);
            LgInt prix_=LgInt.multiply(new LgInt(obj_.getPrice()), quantite_);
            prixTotal_.addNb(prix_);
        }
        return LgInt.greaterEq(money, prixTotal_);
    }

    public void achatObjets(StringMap<LgInt> _objets,DataBase _import){
        LgInt prixTotal_=LgInt.zero();
        for(String o:_objets.getKeys()){
            Item obj_=_import.getItem(o);
            LgInt quantite_=_objets.getVal(o);
            inventory.buy(o, quantite_);
            LgInt prix_=LgInt.multiply(new LgInt(obj_.getPrice()), quantite_);
            prixTotal_.addNb(prix_);
        }
        money.removeNb(prixTotal_);
    }

    public void venteObjets(StringMap<LgInt> _objets,DataBase _import){
        LgInt prixTotal_=LgInt.zero();
        for(String o:_objets.getKeys()){
            Item obj_=_import.getItem(o);
            LgInt quantite_=_objets.getVal(o);
            if (LgInt.strLower(inventory.getNumber(o), quantite_)) {
                continue;
            }
            inventory.sell(o, quantite_);
            LgInt prix_=LgInt.multiply(new LgInt(obj_.getPrice()), quantite_);
            prixTotal_.addNb(prix_);
        }
        money.addNb(prixTotal_);
    }

    public boolean canBeBought(Ints _tm,DataBase _import) {
        LgInt prixTotal_=LgInt.zero();
        for(int o:_tm){
            prixTotal_.addNb(_import.getTmPrice().getVal(o));
        }
        return LgInt.greaterEq(money, prixTotal_);
    }

    void achatCt(int _ct,DataBase _import){
        if (inventory.gotTm().containsObj(_ct)) {
            return;
        }
        getTm(_ct);
        money.removeNb(_import.getTmPrice().getVal(_ct));
    }

    public void achatCts(Ints _cts,DataBase _import){
        for(int c:_cts){
            achatCt(c,_import);
        }
    }

    public void getHm(int _cs){
        inventory.getHm(_cs);
    }

    public void chooseObject(String _object) {
        if (inventory.getNumber(_object).isZero()) {
            return;
        }
        selectedObject = _object;
    }


    public void useObject(DataBase _import) {
        if (usedObjectForHealing(_import)) {
            useObjectForHealing(_import);
        } else if (usedObjectForBoosting(_import)) {
            useObjectForBoosting();
        } else if (usedObjectForEvolving(_import)) {
            useObjectForEvolving(_import);
        } else {
            //if (usedObjectForRepel(_import))
            activerRepousse(_import);
        }
    }

    void useObjectForHealing(DataBase _import) {
        Item info_ = _import.getItem(selectedObject);
        if (info_ instanceof HealingItem && ((HealingItem) info_).getHealingTeam()) {
            healTeam(selectedObject, _import);
            selectedObject = DataBase.EMPTY_STRING;
            return;
        }
        indexesOfPokemonTeam.addAllElts(getPokemonPlayerList().getKeys());
    }

    void useObjectForBoosting() {
        indexesOfPokemonTeam.addAllElts(getPokemonPlayerList().getKeys());
    }

    void useObjectForEvolving(DataBase _import) {
        for (int i: getPokemonPlayerList().getKeys()) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
            StringList evos_ = pk_.possibleEvolutions(selectedObject, _import);
            if (!evos_.isEmpty()) {
                indexesOfPokemonTeam.add(i);
            }
        }
        if (indexesOfPokemonTeam.isEmpty()) {
            selectedObject = DataBase.EMPTY_STRING;
        }
    }

    void activerRepousse(DataBase _import){
        if(getRepousseActif()){
            selectedObject = DataBase.EMPTY_STRING;
            return;
        }
        Repel repouse_=(Repel)_import.getItem(selectedObject);
        remainingRepelSteps=repouse_.getSteps();
        String it_ = _import.translateItem(selectedObject);
        StringMap<String> mess_ = _import.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(ENABLE_REPEL), it_, Long.toString(remainingRepelSteps));
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
    }

    boolean usedObjectForRepel(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        return info_ instanceof Repel;
    }

    public boolean usedObjectForBoosting(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        return info_ instanceof Boost;
    }

    public boolean usedObjectForBoostingMove(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        return info_ instanceof Boost && !((Boost) info_).getWinPp().isZero();
    }

    public boolean usedObject(DataBase _import) {
        if (usedObjectForHealing(_import)) {
            return true;
        }
        if (usedObjectForBoosting(_import)) {
            return true;
        }
        if (usedObjectForEvolving(_import)) {
            return true;
        }
        return usedObjectForRepel(_import);
    }

    public boolean usedObjectForHealing(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        return info_ instanceof HealingItem || info_ instanceof Berry;
    }

    public boolean usedObjectForHealingAmove(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        if (info_ instanceof HealingPp) {
            HealingPp healingPp_ = (HealingPp) info_;
            if (healingPp_.healOneMove()) {
                return true;
            }
//            if (healingPp_.getHealingMoveFullpp()) {
//                return true;
//            }
//            if (healingPp_.getHealedMovePp() > 0) {
//                return true;
//            }
        }
        if (info_ instanceof Berry) {
            Berry berry_ = (Berry) info_;
            return berry_.getHealPp() > 0;
        }
        return false;
    }

    public boolean usedObjectForEvolving(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        return info_ instanceof EvolvingStone;
    }

    public boolean usableObject(DataBase _import) {
        if (usedObjectForHealing(_import)) {
            return true;
        }
        if (usedObjectForEvolving(_import)) {
            return true;
        }
        return usedObjectForBoosting(_import);
    }

    public void choosePokemonForUsingObject(int _index,DataBase _import) {
        clearComments();
        if (usedObjectForHealing(_import)) {
            if (usedObjectForHealingAmove(_import)) {
                initializeMovesToBeHealed(_index, _import);
            } else {
                heal(_index,_import);
            }
        } else if (usedObjectForEvolving(_import)) {
            choosePokemonForEvolution(_index, _import);
        } else {
            //if (usedObjectForBoosting(_import))
            if (usedObjectForBoostingMove(_import)) {
                initializeMovesToBeBoosted(_index, _import);
            } else {
                boostStatistique(_index, _import);
            }
        }
    }

    public void cancelUseObject() {
        selectedObject = DataBase.EMPTY_STRING;
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        cancelUsingObjectOnPokemon();
    }

    public void cancelUsingObjectOnPokemon() {
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void clearComments() {
        commentGame.clearMessages();
    }

    void choosePokemonForEvolution(int _chosenTeamPokemon,DataBase _data) {
        StringMap<String> mess_ = _data.getMessagesPlayer();
        if (!indexesOfPokemonTeam.contains(_chosenTeamPokemon)) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(_chosenTeamPokemon);
            commentGame.addMessage(mess_.getVal(CANNOT_EVOLVE), _data.translatePokemon(pk_.getName()));
            return;
        }
        PokemonPlayer pk_ = (PokemonPlayer) team.get(_chosenTeamPokemon);
        String oldName_ = _data.translatePokemon(pk_.getName());
        pk_.evolve(selectedObject, _data);
        if (pk_.getMovesToBeKeptEvo().isEmpty() && pk_.getNewAbilitiesToBeChosen().isEmpty()) {
            indexesOfPokemonTeam.clear();
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
            String newName_ = _data.translatePokemon(pk_.getName());
            commentGame.addMessage(mess_.getVal(EVOLVE_INTO), oldName_, newName_);
            boolean alreadyCaught_ = estAttrape(pk_.getName());
            attrapePk(pk_.getName());
            addMessageNewPk(pk_.getName(), alreadyCaught_, _data);
        } else {
            setChosenTeamPokemon(_chosenTeamPokemon);
        }
    }

    public void evolvePokemon(DataBase _data) {
        PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
        String oldKey_ = pk_.getName();
        String oldName_ = _data.translatePokemon(oldKey_);
        if (pk_.getMovesToBeKeptEvo().isEmpty()) {
//        if (pk_.getMovesToBeKeptEvo().isEmpty() && !pk_.getNewAbilitiesToBeChosen().isEmpty()) {
            pk_.obtainAbilityAfterEvolving(chosenAbilityForEvolution, _data);
            chosenAbilityForEvolution = DataBase.EMPTY_STRING;
        } else {
            //!pk_.getMovesToBeKeptEvo().isEmpty()
            if (StringUtil.quickEq(chosenAbilityForEvolution, DataBase.EMPTY_STRING)) {
                pk_.learnMovesAfterEvolvingWithOneAbility(_data);
            } else {
                pk_.learnMovesAfterEvolving(chosenAbilityForEvolution, _data);
                chosenAbilityForEvolution = DataBase.EMPTY_STRING;
            }
        }
        String newKey_ = pk_.getName();
        if (StringUtil.quickEq(oldKey_, newKey_)) {
            StringMap<String> mess_ = _data.getMessagesPlayer();
            commentGame.addMessage(mess_.getVal(BETWEEN_NUMBER_MOVES), oldName_, Long.toString(pk_.getMoves().size()), Long.toString(_data.getNbMaxMoves()));
            return;
        }
        chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
        //comment += pk_.getName();
        indexesOfPokemonTeam.clear();
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
//        if (pk_.getMovesToBeKeptEvo().isEmpty()) {
//            chosenTeamPokemon = IndexConstants.INDEX_NOT_FOUND_ELT;
//            //comment += pk_.getName();
//            indexesOfPokemonTeam.clear();
//            inventory.use(selectedObject);
//            selectedObject = DataBase.EMPTY_STRING;
//        }
        String newName_ = _data.translatePokemon(newKey_);
        StringMap<String> mess_ = _data.getMessagesPlayer();
        commentGame.addMessage(mess_.getVal(EVOLVE_INTO), oldName_, newName_);
        boolean alreadyCaught_ = estAttrape(pk_.getName());
        attrapePk(pk_.getName());
        addMessageNewPk(pk_.getName(), alreadyCaught_, _data);
//        if (!StringUtil.quickEq(oldKey_, newKey_)) {
//            //!StringList.eq(oldName_, newName_)
//            commentGame.addMessage(mess_.getVal(EVOLVE_INTO), oldName_, newName_);
//            boolean alreadyCaught_ = estAttrape(pk_.getName());
//            attrapePk(pk_.getName());
//            addMessageNewPk(pk_.getName(), alreadyCaught_, _data);
//        } else {
//            commentGame.addMessage(mess_.getVal(BETWEEN_NUMBER_MOVES), oldName_, Long.toString(pk_.getMoves().size()), Long.toString(_data.getNbMaxMoves()));
//        }
    }

    public void winMoneyFight(LgInt _gain){
        money.addNb(_gain);
        if (!money.isZeroOrGt()) {
            money.affectZero();
        }
    }

    public void affectEndFight(Fight _fight,Difficulty _diff, DataBase _import){
        for (String o: _fight.getLostObjects()) {
            inventory.getItem(o);
        }
        for (String p: _fight.getCaughtEvolutions()) {
            boolean alreadyCaught_ = estAttrape(p);
            attrapePk(p);
            addMessageNewPk(p, alreadyCaught_, _import);
        }
        Team equipe_=_fight.getUserTeam();
        for(TeamPosition c:FightFacade.fightersBelongingToUser(_fight,true)){
            Fighter membre_=equipe_.getMembers().getVal(c.getPosition());
            StringMap<UsesOfMove> moves_;
            moves_ = FightFacade.movesAfterFight(_fight, c, _diff);
            ((PokemonPlayer) team.get(c.getPosition())).endFight(membre_,moves_,_import);
            boolean alreadyCaught_ = estAttrape(membre_.getName());
            attrapePk(membre_.getName());
            addMessageNewPk(membre_.getName(), alreadyCaught_, _import);
        }
    }

    public void catchWildPokemon(Fighter _pokemonSauvage, String _pseudo, String _ballCapture, Difficulty _diff, DataBase _import, boolean _team){
        clearComments();
        long pos_;
        if (_team) {
            pos_= team.size();
        } else {
            pos_ = _import.getNbMaxTeam();
        }
        if(pos_>=_import.getNbMaxTeam()){
            PokemonPlayer lastPk_ = new PokemonPlayer(_pokemonSauvage,_pseudo,_ballCapture,_import, _diff);
            lastPk_.fullHeal(_import);
            box.add(lastPk_);
            boolean alreadyCaught_ = estAttrape(lastPk_.getName());
            attrapePk(lastPk_.getName());
            addMessageNewPk(lastPk_.getName(), alreadyCaught_, _import);
            StringMap<String> mess_ = _import.getMessagesPlayer();
            commentGame.addMessage(mess_.getVal(NEW_PK_ADDED_BOX));
            return;
        }
        PokemonPlayer lastPk_ = new PokemonPlayer(_pokemonSauvage,_pseudo,_ballCapture,_import, _diff);
        lastPk_.fullHeal(_import);
        team.add(lastPk_);
        boolean alreadyCaught_ = estAttrape(lastPk_.getName());
        attrapePk(lastPk_.getName());
        addMessageNewPk(lastPk_.getName(), alreadyCaught_, _import);
    }

    public void reindexAfterStoringToHost(int _pos1,int _pos2){
        team.remove(NumberUtil.max(_pos1,_pos2));
        team.remove(NumberUtil.min(_pos1,_pos2));
    }

    public void takeHostedPokemon(PokemonPlayer _pk1,PokemonPlayer _pk2){
        team.add(_pk1);
        team.add(_pk2);
    }

    public void recupererOeufPensions(Egg _oeuf){
        team.add(_oeuf);
    }

    public String nickname(){
        if (chosenTeamPokemon < 0) {
            return "";
        }
        if (!(team.get(chosenTeamPokemon) instanceof PokemonPlayer)) {
            return "";
        }
        PokemonPlayer pkUt_=(PokemonPlayer) team.get(chosenTeamPokemon);
        return pkUt_.getNickname();
    }

    public void nickname(String _pseudo, DataBase _data){
        PokemonPlayer pkUt_=(PokemonPlayer) team.get(chosenTeamPokemon);
        if(_pseudo.isEmpty()) {
            pkUt_.setNickname(_data.translatePokemon(pkUt_.getName()));
        } else {
            pkUt_.setNickname(_pseudo);
        }
    }

    public void setTeamAfterTrading(ExchangedData _exchangeData) {
        team.set(_exchangeData.getIndexTeam(), _exchangeData.getPokemon());
        attrapePk(_exchangeData.getPokemon().getName());
    }

    public void useInInventory(String _objet){
        inventory.use(_objet);
    }

    public void getTm(int _ct){
        inventory.getTm(_ct);
    }
    private void attrapePk(String _name) {
        caughtPk.put(_name, BoolVal.TRUE);
    }

    public boolean estAttrape(String _name) {
        return caughtPk.contains(_name) && caughtPk.getVal(_name) == BoolVal.TRUE;
    }

    public IntTreeMap< PokemonPlayer> getPokemonPlayerList() {
        IntTreeMap< PokemonPlayer> indexes_ = new IntTreeMap< PokemonPlayer>();
        int nbPks_ = team.size();
        for (int p = IndexConstants.FIRST_INDEX; p<nbPks_; p++) {
            if (team.get(p) instanceof PokemonPlayer) {
                indexes_.put(p, (PokemonPlayer) team.get(p));
            }
        }
        return indexes_;
    }

    public IntTreeMap< Egg> getEggsList() {
        IntTreeMap< Egg> indexes_ = new IntTreeMap< Egg>();
        int nbPks_ = team.size();
        for (int p = IndexConstants.FIRST_INDEX; p<nbPks_; p++) {
            if (team.get(p) instanceof Egg) {
                indexes_.put(p, (Egg) team.get(p));
            }
        }
        return indexes_;
    }

    public boolean getRepousseActif() {
        return remainingRepelSteps > 0;
    }

    public void restore(Ints _indexes) {
        IntTreeMap< Integer> map_;
        map_ = new IntTreeMap< Integer>();
        int nbIndexes_ = _indexes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbIndexes_; i++) {
            map_.put(i, _indexes.get(i));
        }
        TreeMap<Integer,Integer> copy_;
        copy_ = new TreeMap<Integer, Integer>(new ComparatorTreeMapValue<Integer>(map_));
        copy_.putAllMap(map_);
        swap(copy_.getKeys());
    }

    public void swap(CustList<Integer> _indexes) {
        CustList<UsablePokemon> team_;
        team_ = new CustList<UsablePokemon>();
        for (int i: _indexes) {
            team_.add(team.get(i));
        }
        team.clear();
        team.addAllElts(team_);
    }

    public Gender getOppositeGenderForPokemon() {
        Sex opp_ = getOppositeSex();
        return opp_.getGender();
    }

    public Sex getOppositeSex() {
        return sex.getOppositeSex();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex _sex) {
        sex = _sex;
    }

    public CustList<UsablePokemon> getTeam() {
        return team;
    }

    public void setTeam(CustList<UsablePokemon> _team) {
        team = _team;
    }

    public CustList<UsablePokemon> getBox() {
        return box;
    }

    public void setBox(CustList<UsablePokemon> _box) {
        box = _box;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    public StringMap<BoolVal> getCaughtPk() {
        return caughtPk;
    }

    public void setCaughtPk(StringMap<BoolVal> _caughtPk) {
        caughtPk = _caughtPk;
    }

    public LgInt getMoney() {
        return money;
    }

    public void setMoney(LgInt _money) {
        money = _money;
    }

    public long getRemainingRepelSteps() {
        return remainingRepelSteps;
    }

    public void setRemainingRepelSteps(long _remainingRepelSteps) {
        remainingRepelSteps = _remainingRepelSteps;
    }

    public Comment getCommentGame() {
        return commentGame;
    }

    public String getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(String _selectedObject) {
        selectedObject = _selectedObject;
    }

    public String getSelectedMove() {
        return selectedMove;
    }

    public void setSelectedMove(String _selectedMove) {
        selectedMove = _selectedMove;
    }

    public StringMap<Long> getChosenMoves() {
        return chosenMoves;
    }

    public StringMap<BoolVal> getSelectedMoves() {
        return selectedMoves;
    }

    public int getChosenTeamPokemon() {
        return chosenTeamPokemon;
    }

    public void setChosenTeamPokemon(int _chosenTeamPokemon) {
        chosenTeamPokemon = _chosenTeamPokemon;
    }

    public void setChosenAbilityForEvolution(String _chosenAbilityForEvolution) {
        chosenAbilityForEvolution = _chosenAbilityForEvolution;
    }

    public Ints getIndexesOfPokemonTeam() {
        return indexesOfPokemonTeam;
    }

    public IntMap<BoolVal> getIndexesOfPokemonTeamMoves() {
        return indexesOfPokemonTeamMoves;
    }

    public StringList getNewAbilitiesToBeChosen() {
        return ((PokemonPlayer) team.get(chosenTeamPokemon)).getNewAbilitiesToBeChosen();
    }
}
