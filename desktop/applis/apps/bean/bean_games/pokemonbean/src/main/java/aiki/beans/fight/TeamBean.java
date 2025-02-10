package aiki.beans.fight;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import code.maths.LgInt;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.MessagesFightTeam;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class TeamBean extends CommonFightBean {
    private DictionaryComparator<StringList,ActivityOfMove> enabledMovesByGroup;
    private NatStringTreeMap<ActivityOfMoveStill> enabledMoves;
    private NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses;
    private NatStringTreeMap<Long> nbUsesMoves;
    private NatStringTreeMap<IntTreeMap<StacksOfUses>> healAfter;
    private NatStringTreeMap<IntTreeMap<Anticipation>> movesAnticipation;
    private IntTreeMap<FighterAgainstFoes > playerFightersAgainstFoe;
    private boolean foeTeam;
    private IntMap<String> members;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade,_form);
        if (getFoeTeam()) {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightTeam.M_P_92_TITLE_FOE)));
        } else {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightTeam.M_P_92_TITLE_PLAYER)));
        }
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,this), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_FIGHT);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,this),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_REFRESH);
        for (EntryCust<Integer,String> e:getMembers().entryList()) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDirAnc(e.getValue(),new BeanAnchorToFighterEvent(e.getKey(),this));
            feedParents();
        }
        feedParents();
        new BeanDisplayMap<String,ActivityOfMoveStill>(new BeanDisplayString(),new BeanDisplayActivityOfMoveStill(file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_Y),file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_N),file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_NO))).displayGrid(this, getEnabledMoves(),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_ENBALED_MOVES, MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        displayHead(getEnabledMovesByGroup(),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_ENBALED_MOVES, MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        int lenGr_ = getEnabledMovesByGroup().size();
        for (int i = 0; i < lenGr_; i++) {
            formatMessageDirCts(getKey(i));
            displayActivityOfMoveEnabled(MessagesPkBean.TEAM,getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_Y,MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_N);
            displayActivityOfMoveNbRound(MessagesPkBean.TEAM,getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_NO);
        }
        feedParents();
        new BeanDisplayMap<String, LgInt>(new BeanDisplayString(),new BeanDisplayLgInt()).displayGrid(this, getEnabledMovesWhileSendingFoeUses(),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND, MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_VALUE);
        new BeanDisplayMap<String, Long>(new BeanDisplayString(),new BeanDisplayLong()).displayGrid(this, getNbUsesMoves(),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_ENBALED_MOVES_USES, MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_VALUE);
        healPart();
        antPart();
        playerFoePart();
    }

    private void healPart() {
        displayHead(getHealAfter(),MessagesPkBean.TEAM, MessagesFightTeam.M_P_92_HEAL_AFTER, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_ONE, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_TWO, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_RD, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_CURRENT, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_LAST);
        for (EntryCust<String,IntTreeMap<StacksOfUses>> e:getHealAfter().entryList()) {
            for (EntryCust<Integer, StacksOfUses> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey());
                formatMessageDirCts(Long.toString(f.getKey()));
                formatMessageDirCts(Long.toString(f.getValue().getNbRounds()));
                if (f.getValue().isFirstStacked()) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
                if (f.getValue().isLastStacked()) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
            }
        }
        feedParents();
    }

    private void antPart() {
        displayHead(getMovesAnticipation(),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT, MessagesFightTeam.M_P_92_MOVE_ANT_KEY_ONE, MessagesFightTeam.M_P_92_MOVE_ANT_KEY_TWO, MessagesFightTeam.M_P_92_MOVE_ANT_TEAM, MessagesFightTeam.M_P_92_MOVE_ANT_GROUND, MessagesFightTeam.M_P_92_MOVE_ANT_DAMAGE, MessagesFightTeam.M_P_92_MOVE_ANT_NB_ROUND);
        for (EntryCust<String, IntTreeMap<Anticipation>> e:getMovesAnticipation().entryList()) {
            for (EntryCust<Integer, Anticipation> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey());
                formatMessageDirCts(Long.toString(f.getKey()));
                if (f.getValue().getTargetPosition().getTeam() == Fight.CST_FOE) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_FOE);
                } else if (f.getValue().getTargetPosition().getTeam() == Fight.CST_PLAYER) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_PLAYER);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
                enabledAnt(f.getValue());
                formatMessageDirCts(f.getValue().getDamage().toNumberString());
                if (f.getValue().isIncrementing()) {
                    formatMessageDirCts(Long.toString(f.getValue().getNbRounds()));
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
            }
        }
        feedParents();
    }

    private void enabledAnt(Anticipation _f) {
        if (_f.getTargetPosition().isEnabled()) {
            formatMessageDirCts(Long.toString(_f.getTargetPosition().getPosition()));
        } else {
            formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
    }

    private void playerFoePart() {
        display(MessagesPkBean.TEAM,getPlayerFightersAgainstFoe(),MessagesFightTeam.M_P_92_PLAYER_FOE);
        initPage();
        for (EntryCust<Integer, FighterAgainstFoes> e:getPlayerFightersAgainstFoe().entryList()) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(e.getValue().getName());
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,e.getValue().getFoes().values());
            feedParents();
        }
        feedParents();
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.TEAM).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        foeTeam = noTeam_ == Fight.CST_FOE;
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_);
        members = initMembers();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        NatStringTreeMap<ActivityOfMoveStill> enabledMoves_;
        enabledMoves_ = new NatStringTreeMap<ActivityOfMoveStill>();
        for (String m: team_.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), new ActivityOfMoveStill(team_.getEnabledMoves().getVal(m)));
        }
        enabledMoves = enabledMoves_;
        NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses_;
        enabledMovesWhileSendingFoeUses_ = new NatStringTreeMap<LgInt>();
        for (String m: team_.getEnabledMovesWhileSendingFoeUses().getKeys()) {
            enabledMovesWhileSendingFoeUses_.put(translationsMoves_.getVal(m), team_.getEnabledMovesWhileSendingFoeUses().getVal(m));
        }
        enabledMovesWhileSendingFoeUses = enabledMovesWhileSendingFoeUses_;
        NatStringTreeMap<Long> nbUsesMoves_;
        nbUsesMoves_ = new NatStringTreeMap<Long>();
        for (String m: team_.getNbUsesMoves().getKeys()) {
            nbUsesMoves_.put(translationsMoves_.getVal(m), team_.getNbUsesMoves().getVal(m));
        }
        nbUsesMoves = nbUsesMoves_;
        DictionaryComparator<StringList,ActivityOfMove> enabledMovesByGroup_;
//        enabledMovesByGroup_ = new TreeMap<new>(new NaturalComparator<StringList>(){
//            public int compare(StringList _key1, StringList _key2) {
//                int lenOne_ = _key1.size();
//                int lenTwo_ = _key2.size();
//                int minLen_ = Math.min(lenOne_, lenTwo_);
//                int diff_ = lenOne_ - lenTwo_;
//                if (diff_ != 0) {
//                    return diff_;
//                }
//                for (int i = CustList.FIRST_INDEX; i < minLen_; i++) {
//                    int res_ = _key1.get(i).compareTo(_key2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return 0;
//            }
//        });
        enabledMovesByGroup_ = DictionaryComparatorUtil.buildActivities(data_, getLanguage());
        for (StringList s: team_.getEnabledMovesByGroup().getKeys()) {
            StringList key_ = new StringList();
            for (String m: s) {
                key_.add(translationsMoves_.getVal(m));
            }
            key_.sort();
            enabledMovesByGroup_.put(key_, new ActivityOfMove(team_.getEnabledMovesByGroup().getVal(s)));
        }
        enabledMovesByGroup = enabledMovesByGroup_;
        NatStringTreeMap<IntTreeMap<StacksOfUses>> healAfter_;
        healAfter_ = new NatStringTreeMap<IntTreeMap<StacksOfUses>>();
        for (String m: team_.getHealAfterSet()) {
            IntTreeMap<StacksOfUses> h_;
            h_ = new IntTreeMap<StacksOfUses>();
            for (int k: team_.getHealAfterSet(m)) {
                StacksOfUses stack_;
                stack_ = new StacksOfUses();
                StacksOfUses st_ = team_.getHealAfterVal(m, k);
                stack_.setFirstStacked(st_.isFirstStacked());
                stack_.setLastStacked(st_.isLastStacked());
                stack_.setNbRounds(st_.getNbRounds());
                h_.put(k, stack_);
            }
            healAfter_.put(translationsMoves_.getVal(m), h_);
        }
        healAfter = healAfter_;
        NatStringTreeMap<IntTreeMap<Anticipation>> movesAnticipation_;
        movesAnticipation_ = new NatStringTreeMap<IntTreeMap<Anticipation>>();
        for (String m: team_.getMovesAnticipationSet()) {
            IntTreeMap<Anticipation> a_;
            a_ = new IntTreeMap<Anticipation>();
            for (int k: team_.getMovesAnticipationSet(m)) {
                Anticipation ant_;
                ant_ = new Anticipation();
                Anticipation anticip_ = team_.getMovesAnticipationVal(m, k);
                TargetCoords tar_ = anticip_.getTargetPosition();
                ant_.setTargetPosition(new TargetCoords(tar_.getTeam(),tar_.getPosition()));
                ant_.setIncrementing(anticip_.isIncrementing());
                ant_.setDamage(anticip_.getDamage());
                ant_.setNbRounds(anticip_.getNbRounds());
                a_.put(k, ant_);
            }
            movesAnticipation_.put(translationsMoves_.getVal(m), a_);
        }
        movesAnticipation = movesAnticipation_;
        playerFightersAgainstFoe = new IntTreeMap<FighterAgainstFoes >();
        CustList<Integer> mem_ = team_.getPlayerFightersAgainstFoeSet();
        int len_ = mem_.size();
        for (int i = 0; i < len_; i++) {
            Ints numbers_ = new Ints();
            numbers_.addAllElts(team_.getPlayerFightersAgainstFoeVal(mem_.get(i)));
            numbers_.sort();
            playerFightersAgainstFoe.put(mem_.get(i), new FighterAgainstFoes(name(i, mem_.get(i), data_, team_),initMembers(data_,dataBaseFight_.getGame().getFight().getFoeTeam(),numbers_)));
        }
    }
    private IntMap<String> initMembers() {
        FacadeGame dataBaseFight_ = facade();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_);
        Ints mem_ = getMembers(dataBaseFight_, noTeam_);
        return initMembers(data_, team_, mem_);
    }

    private IntMap<String> initMembers(DataBase _data, Team _team, Ints _mem) {
        IntMap<String> members_ = new IntMap<String>();
        int len_ = _mem.size();
        for (int i = 0; i < len_; i++) {
            members_.addEntry(_mem.get(i),name(i, _mem.get(i), _data, _team));
        }
        return members_;
    }

    public IntMap<String> getMembers() {
        return members;
    }
    public String getTrPokemonLink(int _index) {
        return getMembers().getValue(_index);
    }

    public String clickFighter(int _index) {
        getForms().put(NO_FIGHTER, _index);
        return PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML;
    }
    public String getKey(int _index) {
        return StringUtil.join(enabledMovesByGroup.getKey(_index), MOVES_SEPARATOR);
    }
    public boolean isFoeMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return movesAnticipation.getValue(_indexOne).getValue(_indexTwo).getTargetPosition().getTeam() == Fight.CST_FOE;
    }
    public boolean isPlayerMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return movesAnticipation.getValue(_indexOne).getValue(_indexTwo).getTargetPosition().getTeam() == Fight.CST_PLAYER;
    }
    public boolean isBackMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return !movesAnticipation.getValue(_indexOne).getValue(_indexTwo).isEnabled();
    }
    public String getPlayerFigtherAgainstFoe(int _index) {
        return playerFightersAgainstFoe.getValue(_index).getName();
    }
    public String getFoeFigtherAgainstFoe(int _indexOne, int _indexTwo) {
        return playerFightersAgainstFoe.getValue(_indexOne).getFoes().getValue(_indexTwo);
    }

    private String name(int _index, int _second, DataBase _data, Team _team) {
        IntMap<Fighter> members_ = _team.getMembers();
        Fighter fighter_ = members_.getVal(_second);
        int nb_ = number(_team, _second, _index, members_.getKeys());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return _data.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(_data.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }
    public boolean getFoeTeam() {
        return foeTeam;
    }

    public NatStringTreeMap<ActivityOfMoveStill> getEnabledMoves() {
        return enabledMoves;
    }

    public DictionaryComparator<StringList,ActivityOfMove> getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }

    public NatStringTreeMap<LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public NatStringTreeMap<Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public NatStringTreeMap<IntTreeMap<StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public NatStringTreeMap<IntTreeMap<Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public IntTreeMap<FighterAgainstFoes> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }
}