package cards.gui.panels;

import code.gui.AbsPanel;
import code.gui.RowGraphicList;
import code.gui.ScrollCustomGraphicList;
import code.gui.initialize.*;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;

/** */
abstract class ScrollableList<T> {
    private final AbstractProgramInfos frames;
    private final AbsPanel container;
    private final ScrollCustomGraphicList<T> liste;
    ScrollableList(AbstractProgramInfos _absCompoFactory, ScrollCustomGraphicList<T> _l) {
        frames = _absCompoFactory;
        container = _absCompoFactory.getCompoFactory().newBorder();
        container.setLoweredBorder();
        liste = _l;
    }
    public void supprimerElts() {
        int i_ = 0;
        RowGraphicList<T> current_ = liste.getRow(0);
        while (current_ != null) {
            RowGraphicList<T> next_ = current_.getNext();
            if (current_.isSelected()) {
                liste.remove(i_,current_);
            } else {
                i_++;
            }
            current_ = next_;
        }
    }
    public IdList<T> valElts() {
        IdList<T> main_=new IdList<T>();
        int taille_=taille();
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            main_.add(liste.get(i));
        }
        return main_;
    }

    public CustList<T> elementsSelection() {
        CustList<T> main_=new CustList<T>();
        for (int i: liste.getSelectedIndexes()) {
            main_.add(liste.get(i));
        }
        return main_;
    }
    public int taille() {
        return liste.size();
    }
    public ScrollCustomGraphicList<T> getListe() {
        return liste;
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
