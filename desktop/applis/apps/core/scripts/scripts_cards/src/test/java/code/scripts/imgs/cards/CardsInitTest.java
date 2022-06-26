package code.scripts.imgs.cards;

import org.junit.Test;

public class CardsInitTest extends EquallableScriptsCardsUtil {
    @Test
    public void cards() {
        assertNotNullStr(CardsInit.ms());
    }
}