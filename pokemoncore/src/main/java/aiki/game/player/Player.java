package aiki.game.player;
import aiki.DataBase;
import aiki.ExchangedData;
import aiki.Resources;
import aiki.comments.Comment;
import aiki.exceptions.GameLoadException;
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
import code.serialize.CheckedData;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.annot.RwXml;
import code.util.comparators.ComparatorTreeMapValue;
import code.util.comparators.NaturalComparator;
import code.util.consts.Constants;

@RwXml
public class Player {

    private static StringMap<String> _messages_ = new StringMap<String>();

    private static final String PLAYER = "aiki.game.player.Player";

    private static final String DEFAULT_NICKNAME_PREFIX = "TRUMP_";

    private static final String CANNOT_EVOLVE = "cannotEvolve";

    private static final String EVOLVE_INTO = "evolveInto";

    private static final String BAD_NUMBER_MOVES = "badNumberMoves";

    private static final String BETWEEN_NUMBER_MOVES = "betweenNumberMoves";

    private static final String MISS_MOVES = "missMoves";

    private static final String LEARN_MOVES = "learnMoves";

    private static final String FORGET_MOVES = "forgetMoves";

    private static final String KEEP_MOVES = "keepMoves";

    private static final String HATCH = "hatch";

    private static final String REPEL_OFF = "repelOff";

    private static final String NEW_PK = "newPk";

    private static final String NEW_PK_ADDED_BOX = "newPkAddedBox";

    private static final String LEARN_MOVE = "learnMove";

    private static final String LEARN_MOVE_FORGET = "learnMoveForget";

    private static final String RESTORED_HP = "restoredHp";

    private static final String RESTORED_MOVE = "restoredMove";

    private static final String BOOSTED_MOVE = "boostedMove";

    private static final String BOOSTED_STATISTIC = "boostedStatistic";

    private static final String HEAL_STATUS = "healStatus";

    private static final String ENABLE_REPEL = "enableRepel";

    private static final String TAKEN_ITEM = "takenItem";

    /**nom du héros*/
    @CheckedData
    private String nickname;

    /**sexe du héros*/
    @CheckedData
    private Sex sex;

    /**Ensemble des pokemon et des oeufs presents dans l'equipe. (maximum 6)
    equipe de l'utilisateur autre que les oeufs (maximum 6)*/
    private CustList<UsablePokemon> team;

    /**Ensemble des boites contenant les pokemon et les oeufs (maximum 256 boites de 256 pokemon)*/
    private CustList<UsablePokemon> box;

    /**Inventaire des objets de l'utilisateur.*/
    private Inventory inventory;

    /**Ensemble des pokemon attrapes.*/
    private StringMap<Boolean> caughtPk;

    /***/
    @CheckedData
    private LgInt money;

    /***/
    @CheckedData
    private int remainingRepelSteps;

    /***/
    private transient Comment commentGame = new Comment();

    private transient String selectedObject = DataBase.EMPTY_STRING;

    private transient String selectedMove = DataBase.EMPTY_STRING;

    private transient StringMap<Short> chosenMoves = new StringMap<Short>();

    private transient StringMap<Boolean> selectedMoves = new StringMap<Boolean>();

    private transient short chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;

    private transient String chosenAbilityForEvolution = DataBase.EMPTY_STRING;

    private transient Numbers<Byte> indexesOfPokemonTeam = new Numbers<Byte>();

    //values are true <==> a move has to be forgotten
    private transient NumberMap<Byte,Boolean> indexesOfPokemonTeamMoves = new NumberMap<Byte,Boolean>();

    @RwXml
    Player(){
    }

    public Player(String _pseudo,Sex _sexeHeros,Difficulty _diff,boolean _avecPkIni,DataBase _import){
        nickname=_pseudo;
        if (nickname.isEmpty()) {
            if (_sexeHeros == null) {
                nickname = DEFAULT_NICKNAME_PREFIX;
            } else {
                nickname = StringList.concat(DEFAULT_NICKNAME_PREFIX, _sexeHeros.name());
            }
        }
        sex=_sexeHeros;
        team = new CustList<UsablePokemon>();
        box = new CustList<UsablePokemon>();
        caughtPk = new StringMap<Boolean>();
        if(_avecPkIni){
            DataMap donnees_=_import.getMap();
            initTeam(_sexeHeros, _diff, donnees_.getFirstPokemon(), _import);
        }
        money=_import.getDefaultMoney().intPart();
        inventory=new Inventory(_import);
        remainingRepelSteps=0;
    }

    public static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), PLAYER);
        PokemonPlayer.initMessages();
    }

    void initTeam(Sex _sexeHeros,Difficulty _diff, WildPk _firstPk,DataBase _import){
        PokemonData fPk_=_import.getPokemon(_firstPk.getName());
        PokemonPlayer userPk_;
        if (fPk_.getGenderRep() == GenderRepartition.MIXED) {
            userPk_ = new PokemonPlayer(_firstPk,_import,_sexeHeros);
        } else {
            userPk_ = new PokemonPlayer(_firstPk,_import,fPk_.getGenderRep().getPossibleGenders().first());
        }
        userPk_.initIv(_diff);
        userPk_.initPvRestants(_import);
        team.add(userPk_);
        for(String p:_import.getPokedex().getKeys()){
            caughtPk.put(p,false);
        }
        caughtPk.put(_firstPk.getName(),true);
    }

    public void initIv(Difficulty _diff, DataBase _data){
        commentGame = new Comment();
        selectedObject = DataBase.EMPTY_STRING;
        selectedMove = DataBase.EMPTY_STRING;
        chosenMoves = new StringMap<Short>();
        selectedMoves = new StringMap<Boolean>();
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        chosenAbilityForEvolution = DataBase.EMPTY_STRING;
        indexesOfPokemonTeam = new Numbers<Byte>();
        indexesOfPokemonTeamMoves = new NumberMap<Byte,Boolean>();
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

    public void validate(DataBase _data) {
        if (nickname == null) {
            throw new GameLoadException();
        }
        if (sex == null) {
            throw new GameLoadException();
        }
        if (team.isEmpty()) {
            throw new GameLoadException();
        }
        if (team.size() > _data.getNbMaxTeam()) {
            throw new GameLoadException();
        }
        int nbPkPlayers_ = CustList.SIZE_EMPTY;
        for (UsablePokemon e: team) {
            if (e instanceof PokemonPlayer) {
                nbPkPlayers_++;
            }
//            if (!(e instanceof PokemonPlayer)) {
//                if (!(e instanceof Egg)) {
//                    throw new GameLoadException();
//                }
//            } else {
//                nbPkPlayers_ ++;
//            }
            e.validate(_data);
//            if (!e.isValid(_data)) {
//                throw new GameLoadException();
//            }
        }
        if (Numbers.eq(nbPkPlayers_, CustList.SIZE_EMPTY)) {
            throw new GameLoadException();
        }
        for (UsablePokemon p: box) {
            if (p instanceof PokemonPlayer) {
                ((PokemonPlayer)p).fullHeal(_data);
            }
//            if (!(p instanceof PokemonPlayer)) {
//                if (!(p instanceof Egg)) {
//                    throw new GameLoadException();
//                }
//            } else {
//                ((PokemonPlayer)p).fullHeal(_data);
//            }
            p.validate(_data);
//            if (!p.isValid(_data)) {
//                throw new GameLoadException();
//            }
        }
        for (Object o: caughtPk.values()) {
            if (!(o instanceof Boolean)) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getPokedex().getKeys(), caughtPk.getKeys())) {
            throw new GameLoadException();
        }
        if (!money.isZeroOrGt()) {
            throw new GameLoadException();
        }
        inventory.validate(_data);
        if (remainingRepelSteps < 0) {
            throw new GameLoadException();
        }
    }

    public boolean existBall(DataBase _import) {
        boolean existBall_ = false;
        for (String o: _import.getItems().getKeys()) {
            Item obj_ = _import.getItem(o);
            if (!(obj_ instanceof Ball)) {
                continue;
            }
            if (getInventory().getNumber(o).isZero()) {
                continue;
            }
            existBall_ = true;
            break;
        }
        return existBall_;
    }

    StringList nouveauxNes(DataBase _import){
        StringList nouveauxNes_=new StringList();
        for(UsablePokemon u: team){
            if (!(u instanceof Egg)) {
                continue;
            }
            int nbPas_=((Egg) u).getSteps();
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
        for (int i = CustList.FIRST_INDEX; i < _nbSteps; i++) {
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
            AbilityData fCapac_=_import.getAbility(((PokemonPlayer) e).getAbility());
            coeff_+=fCapac_.getDecreaseNecStepsHatch();
        }
        for(UsablePokemon e:team){
            if (!(e instanceof Egg)) {
                continue;
            }
            ((Egg) e).versEclosion((short) coeff_);
        }
        eclosionOeuf(_diff,_import);
        if(remainingRepelSteps>0){
            remainingRepelSteps--;
            if (remainingRepelSteps == 0) {
                commentGame.addMessage(_messages_.getVal(REPEL_OFF));
            }
        }
    }

    void eclosionOeuf(Difficulty _diff,DataBase _import){
        int nbPks_ = team.size();
        for(short k=CustList.FIRST_INDEX;k<nbPks_;k++){
            UsablePokemon usPk_=team.get(k);
            if (!(usPk_ instanceof Egg)) {
                continue;
            }
            Egg oeuf_=(Egg)usPk_;
            PokemonData fPk_ = _import.getPokemon(oeuf_.getName());
            if (oeuf_.getSteps() < fPk_.getHatchingSteps().ll()) {
                continue;
            }
            PokemonPlayer pk_ = new PokemonPlayer(oeuf_,_import);
            pk_.initIv(_diff);
            pk_.initPvRestants(_import);
//            team.set(k, new PokemonPlayer(oeuf_,_import));
            team.set(k, pk_);
            commentGame.addMessage(_messages_.getVal(HATCH), _import.translatePokemon(oeuf_.getName()));
            boolean alreadyCaught_ = estAttrape(oeuf_.getName());
            attrapePk(oeuf_.getName());
            addMessageNewPk(oeuf_.getName(), alreadyCaught_, _import);
        }
    }

    public void doRevivingFossil(String _nomFossile,Difficulty _diff,DataBase _import){
        commentGame.clearMessages();
        if (inventory.getNumber(_nomFossile).isZero()) {
            return;
        }
        short pos_=(short) (team.size());
        Fossil fossile_=(Fossil)_import.getItem(_nomFossile);
        if(pos_<_import.getNbMaxTeam()){
            PokemonPlayer lasPk_ = new PokemonPlayer(fossile_,_import);
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(_import);
            team.add(lasPk_);
            boolean alreadyCaught_ = estAttrape(lasPk_.getName());
            attrapePk(lasPk_.getName());
            inventory.use(_nomFossile);
            addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
            return;
        }
        PokemonPlayer lasPk_ = new PokemonPlayer(fossile_,_import);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_import);
        box.add(lasPk_);
        boolean alreadyCaught_ = estAttrape(lasPk_.getName());
        attrapePk(lasPk_.getName());
        inventory.use(_nomFossile);
        commentGame.addMessage(_messages_.getVal(NEW_PK_ADDED_BOX));
        addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
    }

    void addMessageNewPk(String _name, boolean _alreadyCaught,DataBase _import) {
        if (!_alreadyCaught) {
            String name_ = _import.translatePokemon(_name);
            commentGame.addMessage(_messages_.getVal(NEW_PK), name_);
        }
    }

    public boolean enabledSwitchObjectsTeamBox() {
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        if (!(team.get(chosenTeamPokemon) instanceof PokemonPlayer)) {
            return false;
        }
        return true;
    }

    public void switchObjectsTeamBox(int _box){
        Pokemon usPk_ = (Pokemon) team.get(chosenTeamPokemon);
        Pokemon boxPk_ = (Pokemon) box.get(_box);
        String itemOne_ = usPk_.getItem();
        String itemTwo_ = boxPk_.getItem();
        usPk_.setItem(itemTwo_);
        boxPk_.setItem(itemOne_);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
    }

    public void switchObjectsBox(int _chosenPokemonFirstBox,int _chosenPokemonSecondBox){
        if (_chosenPokemonFirstBox == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        if (_chosenPokemonSecondBox == CustList.INDEX_NOT_FOUND_ELT) {
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
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return true;
    }

    public void switchPokemon(int _box, DataBase _import){
        UsablePokemon tmp_ = box.get(_box);
        UsablePokemon other_ = team.get(chosenTeamPokemon);
        if (other_ instanceof PokemonPlayer) {
            ((PokemonPlayer)other_).fullHeal(_import);
        }
        box.set(_box, other_);
        team.set(chosenTeamPokemon,tmp_);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledStorePokemonBox() {
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return true;
    }

    public void storeIntoBox(DataBase _import){
        UsablePokemon pk_ = team.get(chosenTeamPokemon);
        team.removeAt(chosenTeamPokemon);
//        UsablePokemon pk_=team.removeAtAndGet(chosenTeamPokemon);
        if (pk_ instanceof PokemonPlayer) {
            ((PokemonPlayer) pk_).fullHeal(_import);
        }
        box.add(pk_);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledTakeObjectTeam() {
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return true;
    }

    public void takePokemonFromBox(int _box, DataBase _import){
        short pos_=(short) team.size();
        if(pos_>=_import.getNbMaxTeam()){
            return;
        }
        UsablePokemon pk_ = box.get(_box);
        box.removeAt(_box);
        team.add(pk_);
//        team.add(box.removeAtAndGet(_box));
    }

    public boolean enabledSwitchTeamOrder() {
        if (team.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return false;
        }
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return true;
    }

    public void switchTeamOrder(short _other){
        if(Numbers.eq(chosenTeamPokemon, _other)){
            return;
        }
        team.swapIndexes(chosenTeamPokemon, _other);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
    }

    public boolean enabledSwitchObjectsTeam() {
        if (team.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return false;
        }
        if (chosenTeamPokemon == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        if (!(team.get(chosenTeamPokemon) instanceof PokemonPlayer)) {
            return false;
        }
        return true;
    }

    public void switchObjectsTeam(short _other) {
        if (Numbers.eq(chosenTeamPokemon, _other)) {
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
            if(fPk_.getGenderRep() == GenderRepartition.LEGENDARY){
                return false;
            }
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
        box.removeAt(_box);
    }

    public void setPokemonAbleToHoldObject() {
        indexesOfPokemonTeam.clear();
        int nbPks_ = team.size();
        for (byte k=CustList.FIRST_INDEX;k<nbPks_;k++) {
            UsablePokemon us_ = team.get(k);
            if (!(us_ instanceof Pokemon)) {
                continue;
            }
            Pokemon pk_ = (Pokemon) us_;
            if (!pk_.getItem().isEmpty()) {
                continue;
            }
            indexesOfPokemonTeam.add(k);
        }
    }

    public void giveObject(short _indice){
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

    void heal(short _chosenTeamPokemon, DataBase _import){
        if (!indexesOfPokemonTeam.contains(_chosenTeamPokemon)) {
            return;
        }
        boolean consommer_=false;
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(_chosenTeamPokemon);
        Item objet_=_import.getItem(selectedObject);
        if(objet_ instanceof Berry){
            Berry baie_=(Berry)objet_;
            if(!baie_.getHealHp().isZero()||!baie_.getHealHpRate().isZero()||!baie_.getHealHpBySuperEffMove().isZero()){
                Rate pvRestaures_=pkSoigne_.pvSoignesBaie(baie_,_import);
                pkSoigne_.variationPvRestants(pvRestaures_);
                if(!pvRestaures_.isZero()){
                    consommer_=true;
                    String pk_ = _import.translatePokemon(pkSoigne_.getName());
                    commentGame.addMessage(_messages_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
                }
            }
            StringList statuts_=new StringList(pkSoigne_.getStatus());
            pkSoigne_.soinStatuts(baie_.getHealStatus());
            if(!StringList.equalsSet(statuts_,pkSoigne_.getStatus())){
                statuts_.removeAllElements(pkSoigne_.getStatus());
                for (String s: statuts_) {
                    String st_ = _import.translateStatus(s);
                    String pk_ = _import.translatePokemon(pkSoigne_.getName());
                    commentGame.addMessage(_messages_.getVal(HEAL_STATUS), st_, pk_);
                }
                consommer_=true;
            }
        }
        if(objet_ instanceof HealingPp){
            HealingPp soin_=(HealingPp)objet_;
            StringMap<Short> attaquesRestaures_=pkSoigne_.ppSoignesAttaques(soin_);
            if (soin_.isHealingAllMovesPp()) {
                pkSoigne_.soinPpAttaques(attaquesRestaures_);
            } else if (soin_.getHealingAllMovesFullpp() > 0) {
                pkSoigne_.soinPpAttaques(attaquesRestaures_);
            }
            for(String c:attaquesRestaures_.getKeys()){
                if (attaquesRestaures_.getVal(c) > 0) {
                    String move_ = _import.translateMove(c);
                    String pk_ = _import.translatePokemon(pkSoigne_.getName());
                    commentGame.addMessage(_messages_.getVal(RESTORED_MOVE), move_, pk_, Long.toString(attaquesRestaures_.getVal(c)));
                }
            }
            for(String c:attaquesRestaures_.getKeys()){
                if(attaquesRestaures_.getVal(c)>0){
                    consommer_=true;
                    break;
                }
            }
        }
        if(objet_ instanceof HealingHp){
            HealingHp soin_=(HealingHp)objet_;
            Rate pvRestaures_=pkSoigne_.pvSoignesSansStatut(soin_,_import);
            pkSoigne_.variationPvRestants(pvRestaures_);
            short happinessIncrease_ = pkSoigne_.pointBonheurGagnes(soin_, _import);
            pkSoigne_.variationBonheur(happinessIncrease_, _import);
            commentGame.addComment(pkSoigne_.getCommentPk());
            if(!pvRestaures_.isZero()){
                consommer_=true;
                String pk_ = _import.translatePokemon(pkSoigne_.getName());
                commentGame.addMessage(_messages_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
            }
        }
        if(objet_ instanceof HealingStatus){
            if(objet_ instanceof HealingHpStatus){
                HealingHpStatus soin_=(HealingHpStatus)objet_;
                Rate pvRestaures_=pkSoigne_.pvSoignesAvecStatut(soin_,_import);
                pkSoigne_.variationPvRestants(pvRestaures_);
                if(!pvRestaures_.isZero()){
                    consommer_=true;
                    String pk_ = _import.translatePokemon(pkSoigne_.getName());
                    commentGame.addMessage(_messages_.getVal(RESTORED_HP), pk_, pvRestaures_.toNumberString());
                }
            }
            HealingStatus soin_=(HealingStatus)objet_;
            StringList statuts_=new StringList(pkSoigne_.getStatus());
            pkSoigne_.soinStatuts(soin_.getStatus());
            if(!StringList.equalsSet(statuts_,pkSoigne_.getStatus())){
                statuts_.removeAllElements(pkSoigne_.getStatus());
                for (String s: statuts_) {
                    String st_ = _import.translateStatus(s);
                    String pk_ = _import.translatePokemon(pkSoigne_.getName());
                    commentGame.addMessage(_messages_.getVal(HEAL_STATUS), st_, pk_);
                }
                consommer_=true;
            }
        }
        if(consommer_){
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
        }
    }

    void initializeMovesToBeHealed(short _chosenTeamPokemon, DataBase _import) {
        chosenTeamPokemon = _chosenTeamPokemon;
        chosenMoves.clear();
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        Item objet_ = _import.getItem(selectedObject);
        if(objet_ instanceof Berry){
            Berry baie_=(Berry)objet_;
            for (String m: pkSoigne_.getMoves().getKeys()) {
                short pp_ = pkSoigne_.ppSoignesAttaqueBaie(baie_,m);
                if (pp_ > 0) {
                    chosenMoves.put(m, pp_);
                }
            }
        }
        if(objet_ instanceof HealingPp){
            HealingPp soin_=(HealingPp)objet_;
            for (String m: pkSoigne_.getMoves().getKeys()) {
                short pp_ = pkSoigne_.ppSoignesAttaque(soin_,m);
                if (pp_ > 0) {
                    chosenMoves.put(m, pp_);
                }
            }
        }
        if (chosenMoves.isEmpty()) {
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        }
    }

    public void healMove(String _move, DataBase _import) {
        clearComments();
        if (_move.isEmpty()) {
            selectedObject = DataBase.EMPTY_STRING;
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
            chosenMoves.clear();
            return;
        }
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        short ppRest_ = chosenMoves.getVal(_move);
        String move_ = _import.translateMove(_move);
        String pk_ = _import.translatePokemon(pkSoigne_.getName());
        commentGame.addMessage(_messages_.getVal(RESTORED_MOVE), move_, pk_, Long.toString(ppRest_));
        chosenMoves.clear();
        chosenMoves.put(_move, ppRest_);
        pkSoigne_.soinPpAttaques(chosenMoves);
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
    }

    void initializeMovesToBeBoosted(short _chosenTeamPokemon, DataBase _import) {
        chosenTeamPokemon = _chosenTeamPokemon;
        chosenMoves.clear();
        PokemonPlayer pkSoigne_=(PokemonPlayer) team.get(chosenTeamPokemon);
        short maxPp_=(short) _import.getMaxPp();
        Boost boost_ = (Boost)_import.getItem(selectedObject);
        for (String m: pkSoigne_.getMoves().getKeys()) {
            short currentMax_ = pkSoigne_.getMoves().getVal(m).getMax();
            if (currentMax_ >= maxPp_) {
                continue;
            }
            chosenMoves.put(m, pkSoigne_.wonPp(boost_, m, maxPp_));
        }
        if (chosenMoves.isEmpty()) {
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        }
    }

    public void gainPpMax(String _move,DataBase _import){
        clearComments();
        if (_move.isEmpty()) {
            selectedObject = DataBase.EMPTY_STRING;
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
            chosenMoves.clear();
            return;
        }
        Boost boost_=(Boost)_import.getItem(selectedObject);
        PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
        short ppMax_ = pk_.getMoves().getVal(_move).getMax();
        pk_.boostPp(_move, chosenMoves.getVal(_move));
        short happinessIncrease_ = pk_.pointBonheurGagnes(boost_, _import);
        pk_.variationBonheur(happinessIncrease_, _import);
        if (happinessIncrease_ > 0) {
            commentGame.addComment(pk_.getCommentPk());
        }
        if (ppMax_ != pk_.getMoves().getVal(_move).getMax()) {
            String move_ = _import.translateMove(_move);
            String pkName_ = _import.translatePokemon(pk_.getName());
            int diff_ = pk_.getMoves().getVal(_move).getMax();
            diff_ -= ppMax_;
            commentGame.addMessage(_messages_.getVal(BOOSTED_MOVE), move_, pkName_, Long.toString(diff_));
        }
        if (ppMax_ != pk_.getMoves().getVal(_move).getMax() || happinessIncrease_ > 0) {
            inventory.use(selectedObject);
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
            selectedObject = DataBase.EMPTY_STRING;
            chosenMoves.clear();
        }
    }


    void boostStatistique(short _chosenTeamPokemon,DataBase _import){
        PokemonPlayer pk_ = (PokemonPlayer) team.get(_chosenTeamPokemon);
        boolean increase_ = false;
        Boost boost_ = (Boost) _import.getItem(selectedObject);
        for (Statistic s: boost_.getEvs().getKeys()) {
            short increment_ = boost_.getEvs().getVal(s);
            short var_=pk_.evGagnes(increment_, s, (short) _import.getMaxEv());
            pk_.gainEv(selectedObject,var_,_import);
            if (var_ > 0) {
                increase_ = true;
                String stat_ = _import.translateStatistics(s);
                String pkName_ = _import.translatePokemon(pk_.getName());
                commentGame.addMessage(_messages_.getVal(BOOSTED_STATISTIC), stat_, pkName_, Long.toString(var_));
            }
        }
        short happinessIncrease_ = pk_.pointBonheurGagnes(_import.getItem(selectedObject), _import);
        pk_.variationBonheur(happinessIncrease_, _import);
        if (happinessIncrease_ > 0) {
            commentGame.addComment(pk_.getCommentPk());
        }
        if(increase_ || happinessIncrease_ > 0){
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
        }
    }

    public void choosePokemonForMoveTutors(short _indice, DataBase _import) {
        Numbers<Byte> keys_ = new Numbers<Byte>(getPokemonPlayerList().getKeys());
        chosenTeamPokemon = keys_.get(_indice);
        chosenMoves.clear();
        selectedMoves.clear();
        for (String m: ((PokemonPlayer) team.get(chosenTeamPokemon)).getMoves().getKeys()) {
            selectedMoves.put(m, true);
        }
        for (String m: ((PokemonPlayer) team.get(chosenTeamPokemon)).moveTutors(_import)) {
            MoveData fMove_ = _import.getMove(m);
            chosenMoves.put(m, fMove_.getPp());
            selectedMoves.put(m, false);
        }
    }

    /**For move tutors*/
    public boolean canKeepAllOldMoves(DataBase _import) {
        return ((PokemonPlayer) getSelectedPkTeam()).getMoves().size() < _import.getNbMaxMoves();
    }

    public StringList currentMovesPokemon() {
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
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMoves.clear();
    }

    public void learnMovesByMoveTutor(DataBase _import) {
        commentGame.clearMessages();
        if (selectedMoves.size() >= _import.getNbMaxMoves()) {
//            if (!Numbers.eq(selectedMoves.getKeys(true).size(), _import.getNbMaxMoves()))
            if (!Numbers.eq(getCheckedMoves().size(), _import.getNbMaxMoves())) {
                String name_ = ((PokemonPlayer) team.get(chosenTeamPokemon)).getName();
                name_ = _import.translatePokemon(name_);
//                commentGame.addMessage(_messages_.getVal(BAD_NUMBER_MOVES), name_, selectedMoves.getKeys(true).size());
                commentGame.addMessage(_messages_.getVal(BAD_NUMBER_MOVES), name_, Long.toString(getCheckedMoves().size()));
                return;
            }
        } else {
//            if (!selectedMoves.getKeys(false).isEmpty())
            if (!getUnCheckedMoves().isEmpty()) {
                String name_ = ((PokemonPlayer) team.get(chosenTeamPokemon)).getName();
                name_ = _import.translatePokemon(name_);
                commentGame.addMessage(_messages_.getVal(MISS_MOVES), name_);
                return;
            }
        }
//        StringList moves_ = new StringList(selectedMoves.getKeys(true));
        StringList moves_ = getCheckedMoves();
        PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
        String name_ = _import.translatePokemon(pk_.getName());
        StringList oldMoves_ = pk_.getMoves().getKeys();
        StringList forgottenMoves_;
        forgottenMoves_ = new StringList(oldMoves_);
        forgottenMoves_.removeAllElements(moves_);
        StringList keptMoves_;
        keptMoves_ = new StringList(oldMoves_);
//        keptMoves_.removeAllElements(selectedMoves.getKeys(false));
        keptMoves_.removeAllElements(getUnCheckedMoves());
        StringList learntMoves_;
        learntMoves_ = new StringList(moves_);
        learntMoves_.removeAllElements(oldMoves_);
        for (String m: forgottenMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(_messages_.getVal(FORGET_MOVES), name_, move_);
        }
        for (String m: keptMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(_messages_.getVal(KEEP_MOVES), name_, move_);
        }
        for (String m: learntMoves_) {
            String move_ = _import.translateMove(m);
            commentGame.addMessage(_messages_.getVal(LEARN_MOVES), name_, move_);
        }
        pk_.learnMovesAfterForgettingAll(moves_, _import);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMoves.clear();
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
    }

    private StringList getCheckedMoves() {
        StringList l_;
        l_ = new StringList();
        for (EntryCust<String,Boolean> e: selectedMoves.entryList()) {
            if (e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    private StringList getUnCheckedMoves() {
        StringList l_;
        l_ = new StringList();
        for (EntryCust<String,Boolean> e: selectedMoves.entryList()) {
            if (!e.getValue()) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public void chooseMoveByObject(String _move, DataBase _import) {
        selectedMove = _move;
//        if (_import.getHm().values().containsObj(_move))
        if (!_import.getHmByMove(_move).isEmpty()) {
            for (byte i: getPokemonPlayerList().getKeys()) {
                PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
                if (pk_.getMoves().contains(_move)) {
                    continue;
                }
                PokemonData fPk_ = _import.getPokemon(pk_.getName());
//                for (short c: _import.getHm().getKeys(_move))
                for (short c: _import.getHmByMove(_move)) {
                    if (fPk_.getHiddenMoves().containsObj(c)) {
                        indexesOfPokemonTeam.add(i);
                        break;
                    }
                }
            }
        }
//        if (_import.getTm().values().containsObj(_move))
        if (!_import.getTmByMove(_move).isEmpty()) {
            for (byte i: getPokemonPlayerList().getKeys()) {
                PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
                if (pk_.getMoves().contains(_move)) {
                    continue;
                }
                PokemonData fPk_ = _import.getPokemon(pk_.getName());
//                for (short c: _import.getTm().getKeys(_move))
                for (short c: _import.getTmByMove(_move)) {
                    if (fPk_.getTechnicalMoves().containsObj(c)) {
                        indexesOfPokemonTeam.add(i);
                        break;
                    }
                }
            }
        }
        indexesOfPokemonTeam.removeDuplicates();
        indexesOfPokemonTeamMoves.clear();
        int maxMoves_;
        maxMoves_ = _import.getNbMaxMoves();
        for (byte i: indexesOfPokemonTeam) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(i);
//            indexesOfPokemonTeamMoves.put(i, Numbers.eq(pk_.getMoves().size(), maxMoves_));
            indexesOfPokemonTeamMoves.put(i, pk_.getMoves().size() >= maxMoves_);
        }
        if (indexesOfPokemonTeam.isEmpty()) {
            selectedMove = DataBase.EMPTY_STRING;
        }
    }

    public void choosePokemonForLearningMove(byte _position, DataBase _import) {
        clearComments();
        chosenMoves.clear();
        chosenTeamPokemon = indexesOfPokemonTeam.get(_position);
        if (indexesOfPokemonTeamMoves.getVal((byte) chosenTeamPokemon)) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(chosenTeamPokemon);
            for (String m: pk_.getMoves().getKeys()) {
                chosenMoves.put(m, pk_.getMoves().getVal(m).getMax());
            }
            return;
        }
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        String name_ = _import.translatePokemon(((PokemonPlayer) team.get(chosenTeamPokemon)).getName());
        commentGame.addMessage(_messages_.getVal(LEARN_MOVE), name_, _import.translateMove(selectedMove));
        ((PokemonPlayer) team.get(chosenTeamPokemon)).learnMove(selectedMove, _import);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        selectedMove = DataBase.EMPTY_STRING;
    }

    public void learnMove(String _ancienneAttaque,DataBase _import){
        clearComments();
        indexesOfPokemonTeam.clear();
        indexesOfPokemonTeamMoves.clear();
        String name_ = _import.translatePokemon(((PokemonPlayer) team.get(chosenTeamPokemon)).getName());
        commentGame.addMessage(_messages_.getVal(LEARN_MOVE_FORGET), name_, _import.translateMove(selectedMove), _import.translateMove(_ancienneAttaque));
        ((PokemonPlayer) team.get(chosenTeamPokemon)).learnMove(selectedMove,_ancienneAttaque,_import);
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
        chosenMoves.clear();
        selectedMove = DataBase.EMPTY_STRING;
    }

    public void recevoirPokemon(Pokemon _pokemonDonne,Difficulty _diff,DataBase _import){
        short pos_=(short) (team.size());
        if(pos_<_import.getNbMaxTeam()){
            PokemonPlayer lasPk_ = new PokemonPlayer(_pokemonDonne,_import);
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(_import);
            boolean alreadyCaught_ = estAttrape(lasPk_.getName());
            attrapePk(lasPk_.getName());
            addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
            team.add(lasPk_);
            return;
        }
        PokemonPlayer lasPk_ = new PokemonPlayer(_pokemonDonne,_import);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_import);
        boolean alreadyCaught_ = estAttrape(lasPk_.getName());
        attrapePk(lasPk_.getName());
        addMessageNewPk(lasPk_.getName(), alreadyCaught_, _import);
        box.add(lasPk_);
        commentGame.addMessage(_messages_.getVal(NEW_PK_ADDED_BOX));
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
        commentGame.addMessage(_messages_.getVal(TAKEN_ITEM), item_);
    }

    public void takeObjectFromBox(int _box, DataBase _data) {
        String obj_ = ((PokemonPlayer) box.get(_box)).getItem();
        if(obj_.isEmpty()){
            return;
        }
        getItem(obj_);
        ((Pokemon) box.get(_box)).setItem(DataBase.EMPTY_STRING);
        String item_ = _data.translateItem(obj_);
        commentGame.addMessage(_messages_.getVal(TAKEN_ITEM), item_);
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

    public boolean canBeBought(Numbers<Short> _tm,DataBase _import) {
        LgInt prixTotal_=LgInt.zero();
        for(short o:_tm){
            prixTotal_.addNb(_import.getTmPrice().getVal(o));
        }
        return LgInt.greaterEq(money, prixTotal_);
    }

    void achatCt(short _ct,DataBase _import){
        if (inventory.getTm().containsObj(_ct)) {
            return;
        }
        getTm(_ct);
        money.removeNb(_import.getTmPrice().getVal(_ct));
    }

    public void achatCts(Numbers<Short> _cts,DataBase _import){
        for(short c:_cts){
            achatCt(c,_import);
        }
    }

    public void getHm(short _cs){
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
        if (info_ instanceof HealingItem) {
            if (((HealingItem)info_).getHealingTeam()) {
                healTeam(selectedObject, _import);
                selectedObject = DataBase.EMPTY_STRING;
                return;
            }
        }
        indexesOfPokemonTeam.addAllElts(getPokemonPlayerList().getKeys());
    }

    void useObjectForBoosting() {
        indexesOfPokemonTeam.addAllElts(getPokemonPlayerList().getKeys());
    }

    void useObjectForEvolving(DataBase _import) {
        for (byte i: getPokemonPlayerList().getKeys()) {
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
        remainingRepelSteps=(int) repouse_.getSteps();
        String it_ = _import.translateItem(selectedObject);
        commentGame.addMessage(_messages_.getVal(ENABLE_REPEL), it_, Long.toString(remainingRepelSteps));
        inventory.use(selectedObject);
        selectedObject = DataBase.EMPTY_STRING;
    }

    boolean usedObjectForRepel(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        boolean usableObject_ = false;
        if (info_ instanceof Repel) {
            usableObject_ = true;
        }
        return usableObject_;
    }

    public boolean usedObjectForBoosting(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        boolean usableObject_ = false;
        if (info_ instanceof Boost) {
            usableObject_ = true;
        }
        return usableObject_;
    }

    public boolean usedObjectForBoostingMove(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        boolean usableObject_ = false;
        if (info_ instanceof Boost) {
            usableObject_ = !((Boost)info_).getWinPp().isZero();
        }
        return usableObject_;
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
        boolean usableObject_ = false;
        if (info_ instanceof Berry) {
            usableObject_ = true;
        }
        if (info_ instanceof HealingItem) {
            usableObject_ = true;
        }
        return usableObject_;
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
            if (berry_.getHealPp() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean usedObjectForEvolving(DataBase _import) {
        if (selectedObject.isEmpty()) {
            return false;
        }
        Item info_ = _import.getItem(selectedObject);
        boolean usableObject_ = false;
        if (info_ instanceof EvolvingStone) {
            usableObject_ = true;
        }
        return usableObject_;
    }

    public boolean usableObject(DataBase _import) {
        if (usedObjectForHealing(_import)) {
            return true;
        }
        if (usedObjectForEvolving(_import)) {
            return true;
        }
        if (usedObjectForBoosting(_import)) {
            return true;
        }
        return false;
    }

    public void choosePokemonForUsingObject(short _index,DataBase _import) {
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
        chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
    }

    public void clearComments() {
        commentGame.clearMessages();
    }

    void choosePokemonForEvolution(short _chosenTeamPokemon,DataBase _data) {
        if (!indexesOfPokemonTeam.contains(_chosenTeamPokemon)) {
            PokemonPlayer pk_ = (PokemonPlayer) team.get(_chosenTeamPokemon);
            commentGame.addMessage(_messages_.getVal(CANNOT_EVOLVE), _data.translatePokemon(pk_.getName()));
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
            commentGame.addMessage(_messages_.getVal(EVOLVE_INTO), oldName_, newName_);
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
        if (pk_.getMovesToBeKeptEvo().isEmpty() && !pk_.getNewAbilitiesToBeChosen().isEmpty()) {
            pk_.obtainAbilityAfterEvolving(chosenAbilityForEvolution, _data);
            chosenAbilityForEvolution = DataBase.EMPTY_STRING;
        } else {
            //!pk_.getMovesToBeKeptEvo().isEmpty()
            if (StringList.quickEq(chosenAbilityForEvolution, DataBase.EMPTY_STRING)) {
                pk_.learnMovesAfterEvolvingWithOneAbility(_data);
            } else {
                pk_.learnMovesAfterEvolving(chosenAbilityForEvolution, _data);
                chosenAbilityForEvolution = DataBase.EMPTY_STRING;
            }
        }
        if (pk_.getMovesToBeKeptEvo().isEmpty()) {
            chosenTeamPokemon = CustList.INDEX_NOT_FOUND_ELT;
            //comment += pk_.getName();
            indexesOfPokemonTeam.clear();
            inventory.use(selectedObject);
            selectedObject = DataBase.EMPTY_STRING;
        }
        String newKey_ = pk_.getName();
        String newName_ = _data.translatePokemon(newKey_);
        if (!StringList.quickEq(oldKey_, newKey_)) {
            //!StringList.eq(oldName_, newName_)
            commentGame.addMessage(_messages_.getVal(EVOLVE_INTO), oldName_, newName_);
            boolean alreadyCaught_ = estAttrape(pk_.getName());
            attrapePk(pk_.getName());
            addMessageNewPk(pk_.getName(), alreadyCaught_, _data);
        } else {
            commentGame.addMessage(_messages_.getVal(BETWEEN_NUMBER_MOVES), oldName_, Long.toString(pk_.getMoves().size()), Long.toString(_data.getNbMaxMoves()));
        }
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

    public void catchWildPokemon(Fighter _pokemonSauvage,String _pseudo,String _ballCapture,Difficulty _diff,DataBase _import){
        clearComments();
        short pos_=(short) team.size();
        if(pos_>=_import.getNbMaxTeam()){
            PokemonPlayer lastPk_ = new PokemonPlayer(_pokemonSauvage,_pseudo,_ballCapture,_import);
            lastPk_.initIv(_diff);
            lastPk_.fullHeal(_import);
            box.add(lastPk_);
            boolean alreadyCaught_ = estAttrape(lastPk_.getName());
            attrapePk(lastPk_.getName());
            addMessageNewPk(lastPk_.getName(), alreadyCaught_, _import);
            commentGame.addMessage(_messages_.getVal(NEW_PK_ADDED_BOX));
            return;
        }
        PokemonPlayer lastPk_ = new PokemonPlayer(_pokemonSauvage,_pseudo,_ballCapture,_import);
        lastPk_.initIv(_diff);
        lastPk_.fullHeal(_import);
        team.add(lastPk_);
        boolean alreadyCaught_ = estAttrape(lastPk_.getName());
        attrapePk(lastPk_.getName());
        addMessageNewPk(lastPk_.getName(), alreadyCaught_, _import);
    }

    public void reindexAfterStoringToHost(short _pos1,short _pos2){
        team.removeAt(Math.max(_pos1,_pos2));
        team.removeAt(Math.min(_pos1,_pos2));
    }

    public void takeHostedPokemon(PokemonPlayer _pk1,PokemonPlayer _pk2){
        team.add(_pk1);
        team.add(_pk2);
    }

    public void recupererOeufPensions(Egg _oeuf){
        team.add(_oeuf);
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

    public void getTm(short _ct){
        inventory.getTm(_ct);
    }
    private void attrapePk(String _name) {
        caughtPk.put(_name, true);
    }

    public boolean estAttrape(String _name) {
        return caughtPk.contains(_name) && caughtPk.getVal(_name);
    }

    public NatTreeMap<Byte, PokemonPlayer> getPokemonPlayerList() {
        NatTreeMap<Byte, PokemonPlayer> indexes_ = new NatTreeMap<Byte, PokemonPlayer>();
        int nbPks_ = team.size();
        for (byte p=CustList.FIRST_INDEX; p<nbPks_; p++) {
            if (team.get(p) instanceof PokemonPlayer) {
                indexes_.put(p, (PokemonPlayer) team.get(p));
            }
        }
        return indexes_;
    }

    public NatTreeMap<Byte, Egg> getEggsList() {
        NatTreeMap<Byte, Egg> indexes_ = new NatTreeMap<Byte, Egg>();
        int nbPks_ = team.size();
        for (byte p=CustList.FIRST_INDEX; p<nbPks_; p++) {
            if (team.get(p) instanceof Egg) {
                indexes_.put(p, (Egg) team.get(p));
            }
        }
        return indexes_;
    }

    public boolean getRepousseActif() {
        return remainingRepelSteps > 0;
    }

    public void restore(Numbers<Byte> _indexes) {
        TreeMap<Byte, Byte> map_;
        map_ = new TreeMap<Byte, Byte>(new NaturalComparator<Byte>());
        int nbIndexes_ = _indexes.size();
        for (byte i = CustList.FIRST_INDEX; i < nbIndexes_; i++) {
            map_.put(i, _indexes.get(i));
        }
        TreeMap<Byte,Byte> copy_;
        copy_ = new TreeMap<Byte, Byte>(new ComparatorTreeMapValue<Byte, Byte>(map_));
        copy_.putAllMap(map_);
        Numbers<Byte> indexes_;
        indexes_ = new Numbers<Byte>(copy_.getKeys());
//        Numbers<Byte> values_ = new Numbers<>(map_.values());
//        values_.sort();
//        for (byte k: values_) {
//            indexes_.add(map_.getKeys(k).first());
//        }
        swap(indexes_);
    }

    public void swap(Numbers<Byte> _indexes) {
        CustList<UsablePokemon> team_;
        team_ = new CustList<UsablePokemon>();
        for (byte i: _indexes) {
            team_.add(team.get(i));
        }
        team.clear();
        team.addAllElts(team_);
    }

    public Gender getOppositeGenderForPokemon() {
        if (sex == null) {
            return Gender.NO_GENDER;
        }
        return sex.getOppositeSex().getGender();
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

    public StringMap<Boolean> getCaughtPk() {
        return caughtPk;
    }

    public void setCaughtPk(StringMap<Boolean> _caughtPk) {
        caughtPk = _caughtPk;
    }

    public LgInt getMoney() {
        return money;
    }

    public void setMoney(LgInt _money) {
        money = _money;
    }

    public int getRemainingRepelSteps() {
        return remainingRepelSteps;
    }

    public void setRemainingRepelSteps(int _remainingRepelSteps) {
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

    public StringMap<Short> getChosenMoves() {
        return chosenMoves;
    }

    public void setChosenMoves(StringMap<Short> _chosenMoves) {
        chosenMoves = _chosenMoves;
    }

    public StringMap<Boolean> getSelectedMoves() {
        return selectedMoves;
    }

    public short getChosenTeamPokemon() {
        return chosenTeamPokemon;
    }

    public void setChosenTeamPokemon(short _chosenTeamPokemon) {
        chosenTeamPokemon = _chosenTeamPokemon;
    }

    public String getChosenAbilityForEvolution() {
        return chosenAbilityForEvolution;
    }

    public void setChosenAbilityForEvolution(String _chosenAbilityForEvolution) {
        chosenAbilityForEvolution = _chosenAbilityForEvolution;
    }

    public Numbers<Byte> getIndexesOfPokemonTeam() {
        return indexesOfPokemonTeam;
    }

    public void setIndexesOfPokemonTeam(Numbers<Byte> _indexesOfPokemonTeam) {
        indexesOfPokemonTeam = _indexesOfPokemonTeam;
    }

    public NumberMap<Byte,Boolean> getIndexesOfPokemonTeamMoves() {
        return indexesOfPokemonTeamMoves;
    }

    public void setIndexesOfPokemonTeamMoves(NumberMap<Byte,Boolean> _indexesOfPokemonTeamMoves) {
        indexesOfPokemonTeamMoves = _indexesOfPokemonTeamMoves;
    }

    public StringMap<Boolean> getMovesToBeKeptEvo() {
        return ((PokemonPlayer) team.get(chosenTeamPokemon)).getMovesToBeKeptEvo();
    }

    public String getPossibleEvolution() {
        return ((PokemonPlayer) team.get(chosenTeamPokemon)).getPossibleEvolution();
    }

    public StringList getNewAbilitiesToBeChosen() {
        return ((PokemonPlayer) team.get(chosenTeamPokemon)).getNewAbilitiesToBeChosen();
    }
}
