package cards.gui.dialogs.events;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.util.StringList;
import cards.gui.dialogs.SetterSelectedCardList;
import cards.gui.panels.CardsScrollableList;

public class ListenerClickCardsList implements ListSelectionListener {

    private String formatMessage;

    private SetterSelectedCardList setterDialog;

    public ListenerClickCardsList(String _formatMessage,
            SetterSelectedCardList _setterDialog) {
        formatMessage = _formatMessage;
        setterDialog = _setterDialog;
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        if(_e.getValueIsAdjusting()) {
            return;
        }
//        int nombreDeMains_=setterDialog.getPanelsCards().getComponentCount();
        setterDialog.setNombreCartesSelectionneesPrecedent(setterDialog.getNombreCartesSelectionnees());
        int nbSelectedCards_ = setterDialog.getNombreCartesSelectionnees();
        for (CardsScrollableList l: setterDialog.getHands(true)) {
            nbSelectedCards_+= l.nombreCartesSelectionnees();
        }
//        for(int j=List.FIRST_INDEX;j<nombreDeMains_;j++) {
//            nbSelectedCards_+=((CardsScrollableList)setterDialog.getPanelsCards().getComponent(j)).nombreCartesSelectionnees();
//        }
        nbSelectedCards_-=setterDialog.getNombreCartesSelectionneesPrecedent();
        setterDialog.setNombreCartesSelectionnees(nbSelectedCards_);
        setterDialog.getLabelSelectCards().setText(StringList.simpleNumberFormat(formatMessage,nbSelectedCards_));
    }
}
