package aiki.gui.components.fight;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import code.gui.AbsGraphicList;
import code.gui.GraphicList;
import code.gui.Panel;
import code.gui.TextLabel;
import code.gui.images.AbstractImageFactory;
import code.util.NatStringTreeMap;

public class BallPanel {

    private final TextLabel title;

    private final AbsGraphicList<BallNumberRate> liste;

    private final FacadeGame facade;

    private final BallRenderer renderer;

    private final Panel container;

    public BallPanel(AbstractImageFactory _fact, int _nb, String _titre, FacadeGame _facade, AbsGraphicList<BallNumberRate> _liste) {
        liste = _liste;
        facade = _facade;
        container = Panel.newBorder();
        container.setLoweredBorder();
        title = new TextLabel(_titre, SwingConstants.CENTER);
        container.add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        renderer = new BallRenderer(_fact,facade);
        liste.setRender(renderer);
        initBalls();
        container.add(liste.self(),BorderLayout.CENTER);
        container.setPreferredSize(new Dimension(100,32*_nb));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initBalls() {
        liste.clear();
        NatStringTreeMap<BallNumberRate> map_ = facade.calculateCatchingRates();
        renderer.setMaxWidth(title,map_);
        for (BallNumberRate b: map_.values()) {
            liste.add(b);
        }
    }

    public BallNumberRate getSelectedBall() {
        return liste.getSelectedValue();
    }

    public Panel getContainer() {
        return container;
    }
}
