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
import aiki.game.fight.BallNumberRate;
import code.gui.CustListModel;
import code.gui.Jl;
import code.util.NatTreeMap;

public class BallPanel extends JPanel {

    private JLabel title;

    private CustListModel<BallNumberRate> modeleListe = new CustListModel<BallNumberRate>();

    private Jl<BallNumberRate> liste = new Jl<BallNumberRate>(modeleListe);

    private FacadeGame facade;

    private BallRenderer renderer;

    public BallPanel(int _nb, String _titre, FacadeGame _facade) {
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        title = new JLabel(_titre, SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        renderer = new BallRenderer(facade);
        liste.setCellRenderer(renderer);
        initBalls();
        add(new JScrollPane(liste),BorderLayout.CENTER);
        setPreferredSize(new Dimension(100,32*_nb));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initBalls() {
        modeleListe.clear();
        NatTreeMap<String,BallNumberRate> map_ = facade.calculateCatchingRates();
        renderer.setMaxWidth(map_);
        for (BallNumberRate b: map_.values()) {
            modeleListe.addElement(b);
        }
    }

    public BallNumberRate getSelectedBall() {
        return (BallNumberRate) liste.getSelectedValue();
    }

}
