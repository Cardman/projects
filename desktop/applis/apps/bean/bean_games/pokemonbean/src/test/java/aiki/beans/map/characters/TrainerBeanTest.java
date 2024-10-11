package aiki.beans.map.characters;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class TrainerBeanTest extends InitDbCharacters {
    @Test
    public void getName1() {
        assertEq(G_L_1,callTrainerBeanGetNameLevelZero(0,12,4));
    }
    @Test
    public void getName2() {
        assertEq(T_L_1,callTrainerBeanGetNameOtherLevel(8,0,12));
    }
    @Test
    public void getName3() {
        assertEq(T_L_2,callTrainerBeanGetNameOtherLevel(8,1,12));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_02,callTrainerBeanMoveGetLevelZero(0,12,4));
    }
    @Test
    public void getMove2() {
        assertEq(NULL_REF,callTrainerBeanMoveGetOtherLevel(8,0,12));
    }
    @Test
    public void getMove3() {
        assertEq(NULL_REF,callTrainerBeanMoveGetOtherLevel(8,1,12));
    }
    @Test
    public void getImage1() {
        assertEq(one(IMG_SINGLE),callTrainerBeanImageGetLevelZero(0,12,4));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_SINGLE),callTrainerBeanImageGetLevelZero(0,12,7));
    }
    @Test
    public void getImage3() {
        assertEq(one(IMG_SINGLE),callTrainerBeanImageGetOtherLevel(8,0,12));
    }
    @Test
    public void getImage4() {
        assertEq(one(IMG_SINGLE),callTrainerBeanImageGetOtherLevel(8,1,12));
    }
    @Test
    public void getImage5() {
        assertEq(one(IMG_SINGLE),callTrainerBeanImageGetOtherLevel(3,1,7));
    }
    @Test
    public void getImageMini1() {
        assertEq(one(IMG_SI),callTrainerBeanImageMiniGetLevelZero(0,12,4));
    }
    @Test
    public void getImageMini2() {
        assertEq(one(IMG_SI),callTrainerBeanImageMiniGetLevelZero(0,12,7));
    }
    @Test
    public void getImageMini3() {
        assertEq(one(IMG_SI),callTrainerBeanImageMiniGetOtherLevel(8,0,12));
    }
    @Test
    public void getImageMini4() {
        assertEq(one(IMG_SI),callTrainerBeanImageMiniGetOtherLevel(8,1,12));
    }
    @Test
    public void getImageMini5() {
        assertEq(one(IMG_SI),callTrainerBeanImageMiniGetOtherLevel(3,1,7));
    }
    @Test
    public void getTeamsRewards1() {
        assertSizeEq(0,callTrainerBeanGetTeamsRewardsLevelZero(8,1,12));
    }
    @Test
    public void getTeamsRewards2() {
        assertSizeEq(2,callTrainerBeanGetTeamsRewardsOtherLevel(3,1,7));
    }
    @Test
    public void getTrMove() {
        assertEq(M_POK_02_TR,callTrainerBeanGetTrMove(0,12,4));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callTrainerBeanClickMove(0,12,4));
    }
    @Test
    public void clickMove2() {
        assertEq(M_POK_02,callTrainerBeanClickMoveId(0,12,4));
    }
}
