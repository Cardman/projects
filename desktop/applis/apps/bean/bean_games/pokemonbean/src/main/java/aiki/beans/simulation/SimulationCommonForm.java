package aiki.beans.simulation;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.moves.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public class SimulationCommonForm {
    public static final String CONFIRM = "\u2611";
    private final SimulationBean bean;
    private final FightSimulation simulation;

    public SimulationCommonForm(SimulationBean _b) {
        this.bean = _b;
        simulation = _b.getSimulation();
    }

    static IntMap<String> ids(int _max) {
        IntMap<String> m_ = new IntMap<String>();
        m_.addEntry(Fighter.BACK,Long.toString(Fighter.BACK));
        for (int i = 0; i < _max; i++) {
            m_.addEntry(i, Long.toString(i));
        }
        return m_;
    }

    protected SimulationBeanUpdateEntryValues<Integer,Integer> posit(IntMap<Integer> _map, String _title) {
        bean.initPage();
        bean.setTitledBorder(_title);
        bean.initGrid();
        bean.getBuilder().colCount(2);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KEY_FIGHTER);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_GROUND);
        IntMap<IntBeanChgValue<Integer>> ins_ = new IntMap<IntBeanChgValue<Integer>>();
        for (EntryCust<Integer,Integer> e: _map.entryList()) {
            bean.formatMessageDirCts(Long.toString(e.getKey()));
            IntBeanChgInt chgPl_ = bean.getBuilder().getGenInput().newInt(ids(simulation.getGame().getFight().getMult()));
            chgPl_.valueInt(e.getValue());
            ins_.addEntry(e.getKey(),chgPl_);
        }
        bean.feedParents();
        bean.feedParents();
        return new SimulationBeanUpdateEntryValues<Integer,Integer>(_map,ins_);
    }

    protected SimulationBeanUpdateEntryValues<String,BoolVal> stillEnMoves(AbsMap<TranslatedKey, BoolVal> _map, StringMap<BoolVal> _info, String _key) {
        bean.initPage();
        bean.setTitledBorder(bean.messageRend(MessagesPkBean.SIMU, _key));
        bean.initGrid();
        bean.getBuilder().colCount(2);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_INC_ACCURACY_ENABLED);
        StringMap<IntBeanChgValue<BoolVal>> o_ = new StringMap<IntBeanChgValue<BoolVal>>();
        for (EntryCust<TranslatedKey,BoolVal> e: _map.entryList()) {
            bean.formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgBool chgPl_ = bean.getBuilder().getGenInput().newBool();
            SimulationBean.selectCheck(chgPl_,e.getValue());
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        bean.feedParents();
        bean.feedParents();
        return new SimulationBeanUpdateEntryValues<String, BoolVal>(_info,o_);
    }

    protected SimulationBeanUpdateEntryValues<String,ActivityOfMove> enMoves(AbsMap<TranslatedKey, ActivityOfMove> _map, StringMap<ActivityOfMove> _info, String _key) {
        bean.initPage();
        bean.setTitledBorder(bean.messageRend(MessagesPkBean.SIMU, _key));
        bean.initGrid();
        bean.getBuilder().colCount(3);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ENABLED);
        bean.headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_NB_ROUND);
        StringMap<IntBeanChgValue<ActivityOfMove>> o_ = new StringMap<IntBeanChgValue<ActivityOfMove>>();
        for (EntryCust<TranslatedKey,ActivityOfMove> e: _map.entryList()) {
            bean.formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgActivityOfMove chgPl_ = bean.getBuilder().getGenInput().newAc();
            chgPl_.valueActivity(e.getValue());
            bean.getBuilder().nextPart();
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        bean.feedParents();
        bean.feedParents();
        return new SimulationBeanUpdateEntryValues<String, ActivityOfMove>(_info,o_);
    }
    protected DictionaryComparator<TranslatedKey,BoolVal> sorted(StringMap<BoolVal> _from) {
        DictionaryComparator<TranslatedKey,BoolVal> o_ = new DictionaryComparator<TranslatedKey, BoolVal>(new ComparingTranslatedKey());
        for (EntryCust<String,BoolVal> e: _from.entryList()) {
            o_.put(CommonBean.buildMv(bean.getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }
    protected DictionaryComparator<TranslatedKey,Long> sortedLg(StringMap<Long> _from) {
        DictionaryComparator<TranslatedKey,Long> o_ = new DictionaryComparator<TranslatedKey, Long>(new ComparingTranslatedKey());
        for (EntryCust<String,Long> e: _from.entryList()) {
            o_.put(CommonBean.buildMv(bean.getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    protected DictionaryComparator<TranslatedKey,ActivityOfMove> sortedAc(StringMap<ActivityOfMove> _from) {
        DictionaryComparator<TranslatedKey,ActivityOfMove> o_ = new DictionaryComparator<TranslatedKey, ActivityOfMove>(new ComparingTranslatedKey());
        for (EntryCust<String,ActivityOfMove> e: _from.entryList()) {
            o_.put(CommonBean.buildMv(bean.getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    public DictionaryComparator<TranslatedKey,Long> sortedUsedItems() {
        DictionaryComparator<TranslatedKey,Long> o_ = new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
        for (EntryCust<String, Long> e:simulation.getGame().getFight().getUsedItemsWhileRound().entryList()) {
            o_.put(CommonBean.buildIt(bean.getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    protected CustList<MoveTarget> allKeys() {
        CustList<MoveTarget> mt_ = new CustList<MoveTarget>();
        noMoveCase(mt_);
        for (EntryCust<String, MoveData> m: bean.getDataBase().getMoves().entryList()) {
            for (TargetCoords t: targets(true)) {
                mt_.add(new MoveTarget(m.getKey(),t));
            }
        }
        return mt_;
    }
    protected CustList<MoveTarget> allValues() {
        CustList<MoveTarget> mt_ = new CustList<MoveTarget>();
        noMoveCase(mt_);
        for (EntryCust<String, MoveData> m: bean.getDataBase().getMoves().entryList()) {
            for (TargetCoords t: targets(m.getValue().getTargetChoice().isWithChoice())) {
                mt_.add(new MoveTarget(m.getKey(),t));
            }
        }
        return mt_;
    }

    protected void noMoveCase(CustList<MoveTarget> _mt) {
        for (TargetCoords t: targets(false)) {
            _mt.add(new MoveTarget(DataBase.EMPTY_STRING,t));
        }
    }

    protected CustList<TargetCoords> allValuesTarget() {
        CustList<TargetCoords> mt_ = new CustList<TargetCoords>();
        mt_.addAllElts(targets(false));
        mt_.addAllElts(targets(true));
        return mt_;
    }
    protected CustList<TargetCoords> targets(boolean _valid) {
        CustList<TargetCoords> t_ = new CustList<TargetCoords>();
        if (!_valid) {
            t_.add(TargetCoords.def());
            return t_;
        }
        int mult_ = simulation.getGame().getFight().getMult();
        t_.addAllElts(targetsTeam(Fight.CST_PLAYER,mult_));
        t_.addAllElts(targetsTeam(Fight.CST_FOE,mult_));
        return t_;
    }
    protected static CustList<TargetCoords> targetsTeam(int _cst, int _mult) {
        CustList<TargetCoords> ls_ = new CustList<TargetCoords>();
        for (int i = 0; i < _mult; i++) {
            ls_.add(new TargetCoords(_cst,i));
        }
        return ls_;
    }
    public SimulationBean getBean() {
        return bean;
    }
}
