package aiki.gui;

import code.gui.AbsCustComponent;
import code.mock.*;
import code.util.IdList;
import org.junit.*;

public final class WindowAikiTest extends InitDbGuiAiki {
    @Test
    public void initGame() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        tryClick(window_.getNewGame());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getCommonFrame().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getNickname()));
        assertTrue(tree_.containsObj(window_.getConfirmNewGame()));
        assertTrue(tree_.containsObj(window_.getLabsBegin().get(0).getPaintableLabel()));
    }
    @Test
    public void noChoiceHeros() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        tryClick(window_.getNewGame());
        tryClick(window_.getConfirmNewGame());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getCommonFrame().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getNickname()));
        assertTrue(tree_.containsObj(window_.getConfirmNewGame()));
        assertTrue(tree_.containsObj(window_.getLabsBegin().get(0).getPaintableLabel()));
    }
    @Test
    public void chooseGame() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        tryClick(window_.getNewGame());
        window_.getNickname().setText("__");
        tryClick(window_.getLabsBegin().get(0));
        tryClick(window_.getLabsBegin().get(0));
        tryClick(window_.getLabsBegin().get(0));
        tryClick(window_.getConfirmNewGame());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getCommonFrame().getPane()).getTreeAccessible();
        assertEq(14, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getUp().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getDown().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getLeft().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getRight().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getScene().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSeeBoxes()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSeeEggs()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeam()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItems()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getAttract()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTm()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getGoBack()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getHost()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getGame()));
    }
}
