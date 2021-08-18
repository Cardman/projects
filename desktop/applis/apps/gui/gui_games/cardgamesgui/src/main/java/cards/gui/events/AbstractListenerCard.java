package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;

public abstract class AbstractListenerCard extends MouseAdapter {

    private ContainerGame container;

    protected AbstractListenerCard(ContainerGame _container) {
        container = _container;
    }

    protected ContainerGame getContainer() {
        return container;
    }

    protected abstract void affecterCarteSurvolee();
    protected abstract void jeuCarte(boolean _carteSurvolee);
    protected abstract void verifierRegles();
    protected abstract boolean canListen();
    protected abstract boolean playCardExited(MouseEvent _event);
    protected void testEntreeSortie() {
        if(!container.isThreadAnime()&&container.isCarteSortie()&&!container.isCarteEntree()) {
            jeuCarte(true);
        }
    }
    protected boolean clicCarte() {
        return container.getParametres().getJeuCarteClic();
    }
    @Override
    public void mouseEntered(MouseEvent _event) {
        if (!canListen()) {
            return;
        }
        if(!clicCarte()) {
            container.setCarteEntree(true);
            container.setCarteSortie(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }
    @Override
    public void mouseExited(MouseEvent _event) {
        if (!canListen()) {
            return;
        }
        if(!clicCarte()) {
            if (!playCardExited(_event)) {
                return;
            }
            container.setCarteSortie(true);
            container.setCarteEntree(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }
    @Override
    public void mouseReleased(MouseEvent _event) {
        if (!canListen()) {
            return;
        }
        if(container.isaJoueCarte()&&clicCarte()) {
            container.setRaisonCourante(container.getMessages().getVal(WindowCards.ALREADY_PLAYED));
            container.setaJoueCarte(false);
        }
    }
    @Override
    public void mousePressed(MouseEvent _event) {
        if (!canListen()) {
            return;
        }
        if(clicCarte()) {
            jeuCarte(false);
        }
    }
}
