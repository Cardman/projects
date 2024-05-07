package aiki.gui.components.fight;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.ChosableTargetName;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.listeners.SelectFoeTarget;
import aiki.gui.listeners.SelectPlayerTarget;
import aiki.main.PkNonModalEvent;
import code.gui.AbsPanel;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public class TargetsPanel {

    private final CustList<MiniTargetLabel> foeTargets = new CustList<MiniTargetLabel>();

    private final CustList<MiniTargetLabel> playerTargets = new CustList<MiniTargetLabel>();

    private AbsPanel container;
    private final AbsCompoFactory compoFactory;

    public TargetsPanel(AbsCompoFactory _compoFactory) {
        compoFactory = _compoFactory;
        container = _compoFactory.newLineBox();
    }
    public void setTargets(FacadeGame _facade, Battle _battle) {
        container = compoFactory.newLineBox();
        byte mult_ = _facade.getFight().getMult();
        foeTargets.clear();
        playerTargets.clear();
//        ByteTreeMap<Fighter> teamPl_ = new ByteTreeMap< Fighter>();
//        teamPl_.putAllMap(_facade.getPlayerFrontTeam());
//        teamPl_.putAllMap(_facade.getAllyFrontTeam());
//        teamPl_.putAllMap(_facade.getUnionFrontTeam());
        int i_;
        i_ = IndexConstants.FIRST_INDEX;
        CustList<ChosableTargetName> chosablePl_ = _facade.getFight().getTemp().getChosablePlayerTargets();
        for (ChosableTargetName k: chosablePl_) {
            if (k.getName().isEmpty()) {
                continue;
            }
            MiniTargetLabel target_ = new MiniTargetLabel(compoFactory, k.getKey());
            target_.set(_facade,_battle, k.getName(), i_);
            boolean match_ = k.getChosable() == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new PkNonModalEvent(_battle.getWindow().getModal()),new SelectPlayerTarget(_battle, k.getKey(), i_));
            }
            target_.setSelectable(match_);
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), target_);
            playerTargets.add(target_);
            i_++;
        }
        //TreeMap<Byte,Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        i_ = IndexConstants.FIRST_INDEX;
//        ByteTreeMap<Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        CustList<ChosableTargetName> chosableFoe_ = _facade.getFight().getTemp().getChosableFoeTargets();
        for (ChosableTargetName k: chosableFoe_) {
            if (k.getName().isEmpty()) {
                continue;
            }
            MiniTargetLabel target_ = new MiniTargetLabel(compoFactory, k.getKey());
            target_.set(_facade,_battle, k.getName(), i_);
            boolean match_ = k.getChosable() == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new PkNonModalEvent(_battle.getWindow().getModal()),new SelectFoeTarget(_battle, k.getKey(), i_));
            }
            target_.setSelectable(match_);
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), target_);
            foeTargets.add(target_);
            i_++;
        }
        placeLabels(mult_);
    }

    public void repaintLabelFoe(int _index, Battle _battle) {
        for (MiniTargetLabel l: foeTargets) {
            l.setSelected(false);
        }
        for (MiniTargetLabel l: playerTargets) {
            l.setSelected(false);
        }
        foeTargets.get(_index).setSelected(_index);
        for (MiniTargetLabel l: foeTargets) {
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), l);
        }
        for (MiniTargetLabel l: playerTargets) {
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), l);
        }
    }

    public void repaintLabelPlayer(int _index, Battle _battle) {
        for (MiniTargetLabel l: foeTargets) {
            l.setSelected(false);
        }
        for (MiniTargetLabel l: playerTargets) {
            l.setSelected(false);
        }
        playerTargets.get(_index).setSelected(_index);
        for (MiniTargetLabel l: foeTargets) {
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), l);
        }
        for (MiniTargetLabel l: playerTargets) {
            AbsMetaLabelPk.paintPk(_battle.getWindow().getImageFactory(), l);
        }
    }

    private void placeLabels(byte _mult) {
        if (_mult == 1) {
            container = compoFactory.newGrid(0, 2);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 2) {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX);
            trPlace(foeTargets, IndexConstants.FIRST_INDEX + 1);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX + 1);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 3) {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX+1);
            trPlace(foeTargets, IndexConstants.FIRST_INDEX+2);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX+1);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(playerTargets, IndexConstants.FIRST_INDEX+2);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX);
            trPlace(foeTargets, IndexConstants.FIRST_INDEX+1);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(foeTargets, IndexConstants.FIRST_INDEX+2);
            trPlace(foeTargets, IndexConstants.FIRST_INDEX + 3);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX+1);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            trPlace(playerTargets, IndexConstants.FIRST_INDEX+2);
            trPlace(playerTargets, IndexConstants.FIRST_INDEX + 3);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        }
    }

    public void trPlace(CustList<MiniTargetLabel> _ls, int _i) {
        if (_ls.isValidIndex(_i)) {
            container.add(_ls.get(_i).getPaintableLabel());
        } else {
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        }
    }

    public AbsPanel getContainer() {
        return container;
    }

    public CustList<MiniTargetLabel> getFoeTargets() {
        return foeTargets;
    }

    public CustList<MiniTargetLabel> getPlayerTargets() {
        return playerTargets;
    }
}
