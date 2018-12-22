package aiki.gui.components.fight;
import java.awt.GridLayout;

import javax.swing.JLabel;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.gui.listeners.SelectFoeTarget;
import aiki.gui.listeners.SelectPlayerTarget;
import code.gui.Panel;
import code.util.BooleanList;
import code.util.CustList;
import code.util.NatTreeMap;

public class TargetsPanel extends Panel {

    private CustList<MiniTargetLabel> foeTargets = new CustList<MiniTargetLabel>();

    private CustList<MiniTargetLabel> playerTargets = new CustList<MiniTargetLabel>();

    public void setTargets(FacadeGame _facade, Battle _battle) {
        byte mult_ = _facade.getFight().getMult();
        foeTargets.clear();
        playerTargets.clear();
        NatTreeMap<Byte,Fighter> teamPl_ = new NatTreeMap<Byte, Fighter>();
//        teamPl_.putAllMap(_facade.getPlayerFrontTeam());
//        teamPl_.putAllMap(_facade.getAllyFrontTeam());
        teamPl_.putAllTreeMap(_facade.getUnionFrontTeam());
        int i_;
        i_ = CustList.FIRST_INDEX;
        BooleanList chosablePl_ = _facade.getFight().getChosablePlayerTargets();
        for (byte k: teamPl_.getKeys()) {
            MiniTargetLabel target_ = new MiniTargetLabel();
            target_.set(_facade, teamPl_.getVal(k).getName(), i_);
            if (chosablePl_.get(k)) {
                target_.addMouseListener(new SelectPlayerTarget(_battle, k, i_));
            }
            target_.setSelectable(chosablePl_.get(k));
            playerTargets.add(target_);
            i_ ++;
        }
        //TreeMap<Byte,Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        i_ = CustList.FIRST_INDEX;
        NatTreeMap<Byte,Fighter> teamFoe_ = _facade.getFoeFrontTeam();
        BooleanList chosableFoe_ = _facade.getFight().getChosableFoeTargets();
        for (byte k: teamFoe_.getKeys()) {
            MiniTargetLabel target_ = new MiniTargetLabel();
            target_.set(_facade, teamFoe_.getVal(k).getName(), i_);
            if (chosableFoe_.get(k)) {
                target_.addMouseListener(new SelectFoeTarget(_battle, k, i_));
            }
            target_.setSelectable(chosableFoe_.get(k));
            foeTargets.add(target_);
            i_ ++;
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
            l.repaint();
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaint();
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
            l.repaint();
        }
        for (MiniTargetLabel l: playerTargets) {
            l.repaint();
        }
    }

    private void placeLabels(byte _mult) {
        removeAll();
        if (_mult == 1) {
            setLayout(new GridLayout(0, 2));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 2) {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX + 1)) {
                add(foeTargets.get(CustList.FIRST_INDEX + 1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX + 1)) {
                add(playerTargets.get(CustList.FIRST_INDEX + 1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 3) {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(foeTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(foeTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(playerTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(playerTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        } else {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(foeTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(foeTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+3)) {
                add(foeTargets.get(CustList.FIRST_INDEX+3));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(playerTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(playerTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+3)) {
                add(playerTargets.get(CustList.FIRST_INDEX+3));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        }
    }
}
