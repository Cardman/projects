package aiki.gui.components.fight;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import code.gui.GraphicList;
import code.gui.Panel;
import code.util.NatStringTreeMap;

public class BallPanel extends Panel {

    private JLabel title;

    private GraphicList<BallNumberRate> liste;

    private FacadeGame facade;

    private BallRenderer renderer;

    public BallPanel(int _nb, String _titre, FacadeGame _facade) {
        liste = new GraphicList<BallNumberRate>(false,true);
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        title = new JLabel(_titre, SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        renderer = new BallRenderer(facade);
        liste.setRender(renderer);
        initBalls();
        add(liste.getComponent(),BorderLayout.CENTER);
        setPreferredSize(new Dimension(100,32*_nb));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initBalls() {
        liste.clear();
        NatStringTreeMap<BallNumberRate> map_ = facade.calculateCatchingRates();
        renderer.setMaxWidth(title.getFontMetrics(title.getFont()),map_);
        for (BallNumberRate b: map_.values()) {
            liste.add(b);
        }
    }

    public BallNumberRate getSelectedBall() {
        return liste.getSelectedValue();
    }

}
