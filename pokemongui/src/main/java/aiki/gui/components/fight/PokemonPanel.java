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
import aiki.gui.listeners.PokemonSelection;
import code.gui.StringJl;
import code.gui.StringListModel;
import code.util.CustList;
import code.util.TreeMap;

public class PokemonPanel extends JPanel {

    private JLabel title;

    private PokemonDataRenderer renderer;

    private StringListModel modeleListe = new StringListModel();

    private StringJl liste = new StringJl(modeleListe);

    private FacadeGame facade;

    private String noEvo;

    public PokemonPanel(int _nb, String _titre, FacadeGame _facade, String _noEvo) {
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        title = new JLabel(_titre, SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noEvo = _noEvo;
        renderer = new PokemonDataRenderer(facade, noEvo);
        liste.setCellRenderer(renderer);
        initEvos();
        add(new JScrollPane(liste),BorderLayout.CENTER);
        setPreferredSize(new Dimension(100,32*(_nb+1)));
    }

    public void setNoEvoMessage(String _message) {
        renderer.setNoEvo(_message);
    }
    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initEvos() {
        modeleListe.clear();
        TreeMap<String,Boolean> map_ = facade.getEvolutions();
        for (String b: map_.getKeys()) {
            modeleListe.addElement(b);
        }
//        liste.setListData(map_.getKeys().toArray(new String[CustList.SIZE_EMPTY]));
        int index_ = CustList.FIRST_INDEX;
        for (String b: map_.getKeys()) {
            if (map_.getVal(b)) {
                liste.setSelectedIndex(index_);
            }
            index_ ++;
        }
        //liste.validate();
    }

    public void addListener(Battle _battle) {
        liste.addListSelectionListener(new PokemonSelection(_battle));
    }

    public String getSelectedEvo() {
        return liste.getSelectedValue();
    }

}
