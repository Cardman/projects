package cards.gui.dialogs.events;
import cards.gui.dialogs.SetterSelectedCardList;
import cards.gui.panels.CardsScrollableList;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.StringList;

public class ListenerClickCardsList implements ListSelection {

    private String formatMessage;

    private SetterSelectedCardList setterDialog;

    public ListenerClickCardsList(String _formatMessage,
            SetterSelectedCardList _setterDialog) {
        formatMessage = _formatMessage;
        setterDialog = _setterDialog;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
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
