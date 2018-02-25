package aiki.gui.components.fight;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.gui.listeners.BackFighterSelection;
import aiki.gui.listeners.FighterSelection;
import aiki.gui.listeners.FrontFighterSelection;
import code.gui.CustListModel;
import code.gui.Jl;
import code.util.NatTreeMap;

public class FighterPanel extends JPanel {

    private JLabel title;

    private CustListModel<Fighter> modeleListe = new CustListModel<Fighter>();

    private Jl<Fighter> liste = new Jl<Fighter>(modeleListe);

    private FacadeGame facade;

    public FighterPanel(int _nb, String _titre, FacadeGame _facade, NatTreeMap<Byte,Fighter> _fighters) {
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        title = new JLabel(_titre, SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.setCellRenderer(new FighterRenderer(facade));
        initFighters(_fighters);
        add(new JScrollPane(liste),BorderLayout.CENTER);
        setPreferredSize(new Dimension(150,64*_nb));
    }

    public void initFighters(NatTreeMap<Byte,Fighter> _fighters) {
        modeleListe.clear();
        for (Fighter f: _fighters.values()) {
            modeleListe.addElement(f);
        }
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }
//    public void initFighters(boolean _front) {
//        modeleListe.clear();
//        if (_front) {
//            TreeMap<Byte,Fighter> fronts_ = facade.getPlayerFrontTeam();
//            for (Fighter f: fronts_.values()) {
//                modeleListe.addElement(f);
//            }
//        } else {
//            TreeMap<Byte,Fighter> fronts_ = facade.getPlayerBackTeam();
//            for (Fighter f: fronts_.values()) {
//                modeleListe.addElement(f);
//            }
//        }
//    }

    public void initFighters() {
        modeleListe.clear();
        NatTreeMap<Byte,Fighter> fronts_ = facade.getPlayerTeam();
        for (Fighter f: fronts_.values()) {
            modeleListe.addElement(f);
        }
    }

    public void addListener(Battle _battle, boolean _front) {
        if (_front) {
            liste.addListSelectionListener(new FrontFighterSelection(_battle));
        } else {
            liste.addListSelectionListener(new BackFighterSelection(_battle));
        }
    }

    public void addListener(Battle _battle) {
        liste.addListSelectionListener(new FighterSelection(_battle));
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public void deselect() {
        liste.clearSelection();
    }
}
