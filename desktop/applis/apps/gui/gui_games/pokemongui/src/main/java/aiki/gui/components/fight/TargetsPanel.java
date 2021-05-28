package aiki.gui.components.fight;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.gui.listeners.SelectFoeTarget;
import aiki.gui.listeners.SelectPlayerTarget;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.BooleanList;
import code.util.CustList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public class TargetsPanel {

    private CustList<MiniTargetLabel> foeTargets = new CustList<MiniTargetLabel>();

    private CustList<MiniTargetLabel> playerTargets = new CustList<MiniTargetLabel>();

    private Panel container = Panel.newLineBox();

    public void setTargets(FacadeGame _facade, Battle _battle) {
        byte mult_ = _facade.getFight().getMult();
        foeTargets.clear();
        playerTargets.clear();
        ByteTreeMap<Fighter> teamPl_ = new ByteTreeMap< Fighter>();
//        teamPl_.putAllMap(_facade.getPlayerFrontTeam());
//        teamPl_.putAllMap(_facade.getAllyFrontTeam());
        teamPl_.putAllMap(_facade.getUnionFrontTeam());
        int i_;
        i_ = IndexConstants.FIRST_INDEX;
        IdList<BoolVal> chosablePl_ = _facade.getFight().getChosablePlayerTargets();
        for (byte k: teamPl_.getKeys()) {
            MiniTargetLabel target_ = new MiniTargetLabel();
            target_.set(_facade, teamPl_.getVal(k).getName(), i_);
            boolean match_ = chosablePl_.get(k) == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new SelectPlayerTarget(_battle, k, i_));
            }
            target_.setSelectable(match_);
            playerTargets.add(target_);
            i_++;
        }
        //TreeMap<Byte,Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        i_ = IndexConstants.FIRST_INDEX;
        ByteTreeMap<Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        IdList<BoolVal> chosableFoe_ = _facade.getFight().getChosableFoeTargets();
        for (byte k: teamFoe_.getKeys()) {
            MiniTargetLabel target_ = new MiniTargetLabel();
            target_.set(_facade, teamFoe_.getVal(k).getName(), i_);
            boolean match_ = chosableFoe_.get(k) == BoolVal.TRUE;
            if (match_) {
                target_.addMouseListener(new SelectFoeTarget(_battle, k, i_));
            }
            target_.setSelectable(match_);
            foeTargets.add(target_);
            i_++;
        }
        placeLabels(mult_);
    }

    public void repaintLabelFoe(int _index) {
        for (MiniTargetLabel l: foeTargets) {
            l.setSelected(false);
        }
        for (MiniTargetLabel l: playerTargets) {
            l.setSelected(false);
        }
        foeTargets.get(_index).setSelected(_index);
        for (MiniTargetLabel l: foeTargets) {
            l.repaintLabel();
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaintLabel();
        }
    }

    public void repaintLabelPlayer(int _index) {
        for (MiniTargetLabel l: foeTargets) {
            l.setSelected(false);
        }
        for (MiniTargetLabel l: playerTargets) {
            l.setSelected(false);
        }
        playerTargets.get(_index).setSelected(_index);
        for (MiniTargetLabel l: foeTargets) {
            l.repaintLabel();
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaintLabel();
        }
    }

    private void placeLabels(byte _mult) {
        if (_mult == 1) {
            container = Panel.newGrid(0, 2);
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 2) {
            container = Panel.newGrid(0, 4);
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX + 1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX + 1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX + 1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX + 1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 3) {
            container = Panel.newGrid(0, 4);
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
        } else {
            container = Panel.newGrid(0, 4);
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(IndexConstants.FIRST_INDEX+3)) {
                container.add(foeTargets.get(IndexConstants.FIRST_INDEX+3));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+1)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+1));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+2)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+2));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(IndexConstants.FIRST_INDEX+3)) {
                container.add(playerTargets.get(IndexConstants.FIRST_INDEX+3));
            } else {
                container.add(new TextLabel(DataBase.EMPTY_STRING));
            }
            container.add(new TextLabel(DataBase.EMPTY_STRING));
            container.add(new TextLabel(DataBase.EMPTY_STRING));
        }
    }

    public Panel getContainer() {
        return container;
    }
}
