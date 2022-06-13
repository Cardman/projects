package aiki.gui.components.fight;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.ChosableTargetName;
import aiki.gui.listeners.SelectFoeTarget;
import aiki.gui.listeners.SelectPlayerTarget;
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
        CustList<ChosableTargetName> chosablePl_ = _facade.getFight().getChosablePlayerTargets();
        for (ChosableTargetName k: chosablePl_) {
            MiniTargetLabel target_ = new MiniTargetLabel(compoFactory);
            target_.set(_facade,_battle, k.getName(), i_);
            boolean match_ = k.getChosable() == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new SelectPlayerTarget(_battle, k.getKey(), i_));
            }
            target_.setSelectable(match_);
            playerTargets.add(target_);
            i_++;
        }
        //TreeMap<Byte,Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        i_ = IndexConstants.FIRST_INDEX;
//        ByteTreeMap<Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        CustList<ChosableTargetName> chosableFoe_ = _facade.getFight().getChosableFoeTargets();
        for (ChosableTargetName k: chosableFoe_) {
            MiniTargetLabel target_ = new MiniTargetLabel(compoFactory);
            target_.set(_facade,_battle, k.getName(), i_);
            boolean match_ = k.getChosable() == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new SelectFoeTarget(_battle, k.getKey(), i_));
            }
            target_.setSelectable(match_);
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
            l.repaintLabel(_battle.getWindow().getImageFactory());
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaintLabel(_battle.getWindow().getImageFactory());
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
            l.repaintLabel(_battle.getWindow().getImageFactory());
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaintLabel(_battle.getWindow().getImageFactory());
        }
    }

    private void placeLabels(byte _mult) {
        if (_mult == 1) {
            container = compoFactory.newGrid(0, 2);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 2) {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX + 1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX + 1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX + 1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX + 1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 3) {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        } else {
            container = compoFactory.newGrid(0, 4);
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+3)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+3));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+3)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+3));
            } else {
                container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            }
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
            container.add(compoFactory.newPlainLabel(DataBase.EMPTY_STRING));
        }
    }

    public AbsPanel getContainer() {
        return container;
    }
}
