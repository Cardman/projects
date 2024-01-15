package cards.gui.dialogs.events;
import cards.gui.dialogs.EditorCards;
import cards.gui.panels.CardsScrollableList;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.core.StringUtil;

public class ListenerClickCardsList implements ListSelection {

    private final String formatMessage;

    private final EditorCards editorCards;

    public ListenerClickCardsList(String _formatMessage, EditorCards _edit) {
        formatMessage = _formatMessage;
        editorCards = _edit;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
//        int nombreDeMains_=setterDialog.getPanelsCards().getComponentCount();
        int nbSelectedCards_ = 0;
        for (CardsScrollableList l: editorCards.getAll()) {
            nbSelectedCards_+= l.nombreCartesSelectionnees();
        }
//        for(int j=List.FIRST_INDEX;j<nombreDeMains_;j++) {
//            nbSelectedCards_+=((CardsScrollableList)setterDialog.getPanelsCards().getComponent(j)).nombreCartesSelectionnees();
//        }
        editorCards.getLabelSelectCards().setText(StringUtil.simpleNumberFormat(formatMessage,nbSelectedCards_));
    }
}
