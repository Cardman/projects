package code.util;

import code.util.core.NumberUtil;
import org.junit.Test;


public class NumbersTest extends EquallableExUtil {
    @Test
    public void isValidIndex1() {
        assertTrue(!NumberUtil.isValidIndex(-1,2));
    }
    @Test
    public void isValidIndex2() {
        assertTrue(!NumberUtil.isValidIndex(5,2));
    }
    @Test
    public void isValidIndex3() {
        assertTrue(NumberUtil.isValidIndex(1,2));
    }
    @Test
    public void singleOrEmpty1() {
        Ints res_ = Ints.singleOrEmpty(0);
        assertEq(1, res_.size());
        assertEq(0, res_.get(0));
    }
    @Test
    public void singleOrEmpty2() {
        Ints res_ = Ints.singleOrEmpty(-1);
        assertEq(0, res_.size());
    }
    @Test
    public void getReverseTest() {
        Ints list_ = new Ints();
        list_.add(0);
        list_.add(1);
        CustList<Integer> f_ = list_.getReverse();
        assertEq(2, f_.size());
        assertEq(1, f_.get(0));
        assertEq(0, f_.get(1));
    }
    @Test
    public void containsIndexOf1(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        assertTrue(nbs_.contains(1));
        assertTrue(!nbs_.contains(2));
        assertTrue(nbs_.contains(3));
        assertTrue(nbs_.contains((byte)1));
        assertEq(0,nbs_.indexOf((byte)1));
        assertEq(1,nbs_.indexOf((byte)3));
        assertEq(-1,nbs_.indexOf((byte)2));
    }
    @Test
    public void removeDuplicates1(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void removeDuplicates2(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.removeDuplicates();
        assertEq(1,nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void removeDuplicates3(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void removeDuplicates4(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void removeDuplicates5(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void removeDuplicates6(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void hasDuplicates1(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        assertTrue(!nbs_.hasDuplicates());
    }
    @Test
    public void hasDuplicates2(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        assertTrue(nbs_.hasDuplicates());
    }
    @Test
    public void hasDuplicates3(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        assertTrue(nbs_.hasDuplicates());
    }
    @Test
    public void hasDuplicates4(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        nbs_.add(3);
        assertTrue(nbs_.hasDuplicates());
    }
    @Test
    public void hasDuplicates5(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        assertTrue(nbs_.hasDuplicates());
    }
    @Test
    public void hasDuplicates6(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(3);
        assertTrue(nbs_.hasDuplicates());
    }
    @Test
    public void min1(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        assertEq(1, nbs_.getMinimum(0));
        nbs_ = new Ints();
        nbs_.add(3);
        nbs_.add(1);
        assertEq(1, nbs_.getMinimum(0));
        nbs_ = new Ints();
        nbs_.add(1);
        assertEq(1, nbs_.getMinimum(0));
        nbs_ = new Ints();
        assertEq(0,nbs_.getMinimum(0));
    }
    @Test
    public void max1(){
        Ints nbs_ = new Ints();
        nbs_.add(1);
        nbs_.add(3);
        assertEq(3, nbs_.getMaximum(0));
        nbs_ = new Ints();
        nbs_.add(3);
        nbs_.add(1);
        assertEq(3, nbs_.getMaximum(0));
        nbs_ = new Ints();
        nbs_.add(3);
        assertEq(3, nbs_.getMaximum(0));
        nbs_ = new Ints();
        assertEq(0,nbs_.getMaximum(0));
    }
    @Test
    public void sort1(){
        Ints nbs_ = new Ints();
        nbs_.add(5);
        nbs_.add(1);
        nbs_.add(4);
        nbs_.add(10);
        nbs_.add(8);
        nbs_.sort();
        assertEq(5,nbs_.size());
        assertEq(1, nbs_.first());
        assertEq(4, nbs_.get(1));
        assertEq(5, nbs_.get(2));
        assertEq(8, nbs_.get(3));
        assertEq(10, nbs_.get(4));
    }
    @Test
    public void parseLongZero1Test() {
        assertEq(0, NumberUtil.parseLongZero("0"));
    }
    @Test
    public void parseLongZero2Test() {
        assertEq(1, NumberUtil.parseLongZero("1"));
    }
    @Test
    public void parseLongZero3Test() {
        assertEq(9, NumberUtil.parseLongZero("9"));
    }
    @Test
    public void parseLongZero4Test() {
        assertEq(10, NumberUtil.parseLongZero("10"));
    }
    @Test
    public void parseLongZero5Test() {
        assertEq(-1, NumberUtil.parseLongZero("-1"));
    }
    @Test
    public void parseLongZero6Test() {
        assertEq(-9, NumberUtil.parseLongZero("-9"));
    }
    @Test
    public void parseLongZero7Test() {
        assertEq(-10, NumberUtil.parseLongZero("-10"));
    }
    @Test
    public void parseLongZero8Test() {
        assertEq(0, NumberUtil.parseLongZero(""));
    }
    @Test
    public void parseLongZero9Test() {
        assertEq(0, NumberUtil.parseLongZero("-"));
    }
    @Test
    public void parseInt1Test() {
        assertEq(-1, NumberUtil.parseInt("-1"));
    }
    @Test
    public void parseInt2Test() {
        assertEq(1, NumberUtil.parseInt("1"));
    }
    @Test
    public void parseInt3Test() {
        assertEq(0, NumberUtil.parseInt("10000000000"));
    }
    @Test
    public void parseInt4Test() {
        assertEq(0, NumberUtil.parseInt("-10000000000"));
    }
    @Test
    public void quot1Test() {
        assertEq(0, NumberUtil.quot(0,1));
    }
    @Test
    public void quot2Test() {
        assertEq(1, NumberUtil.quot(4,3));
    }
    @Test
    public void quot3Test() {
        assertEq(2, NumberUtil.quot(4,2));
    }
    @Test
    public void quot4Test() {
        assertEq(0, NumberUtil.quot(1,3));
    }
    @Test
    public void quot5Test() {
        assertEq(-1, NumberUtil.quot(4,-3));
    }
    @Test
    public void quot6Test() {
        assertEq(-2, NumberUtil.quot(4,-2));
    }
    @Test
    public void quot7Test() {
        assertEq(0, NumberUtil.quot(1,-3));
    }
    @Test
    public void quot8Test() {
        assertEq(-2, NumberUtil.quot(-4,3));
    }
    @Test
    public void quot9Test() {
        assertEq(-2, NumberUtil.quot(-4,2));
    }
    @Test
    public void quot10Test() {
        assertEq(-1, NumberUtil.quot(-1,3));
    }
    @Test
    public void quot11Test() {
        assertEq(2, NumberUtil.quot(-4,-3));
    }
    @Test
    public void quot12Test() {
        assertEq(2, NumberUtil.quot(-4,-2));
    }
    @Test
    public void quot13Test() {
        assertEq(1, NumberUtil.quot(-1,-3));
    }
    @Test
    public void quot131Test() {
        assertEq(4, NumberUtil.quot(-23,-7));
    }
    @Test
    public void quot132Test() {
        assertEq(-4, NumberUtil.quot(-23,7));
    }
    @Test
    public void quot133Test() {
        assertEq(-3, NumberUtil.quot(23,-7));
    }

    @Test
    public void mod1Test() {
        assertEq(0, NumberUtil.mod(0,1));
    }
    @Test
    public void mod2Test() {
        assertEq(1, NumberUtil.mod(4,3));
    }
    @Test
    public void mod3Test() {
        assertEq(0, NumberUtil.mod(4,2));
    }
    @Test
    public void mod4Test() {
        assertEq(1, NumberUtil.mod(1,3));
    }
    @Test
    public void mod5Test() {
        assertEq(1, NumberUtil.mod(4,-3));
    }
    @Test
    public void mod6Test() {
        assertEq(0, NumberUtil.mod(4,-2));
    }
    @Test
    public void mod7Test() {
        assertEq(1, NumberUtil.mod(1,-3));
    }
    @Test
    public void mod8Test() {
        assertEq(2, NumberUtil.mod(-4,3));
    }
    @Test
    public void mod9Test() {
        assertEq(0, NumberUtil.mod(-4,2));
    }
    @Test
    public void mod10Test() {
        assertEq(2, NumberUtil.mod(-1,3));
    }
    @Test
    public void mod11Test() {
        assertEq(2, NumberUtil.mod(-4,-3));
    }
    @Test
    public void mod12Test() {
        assertEq(0, NumberUtil.mod(-4,-2));
    }
    @Test
    public void mod13Test() {
        assertEq(2, NumberUtil.mod(-1,-3));
    }
    @Test
    public void mod131Test() {
        assertEq(5, NumberUtil.mod(-23,-7));
    }
    @Test
    public void mod132Test() {
        assertEq(5, NumberUtil.mod(-23,7));
    }
    @Test
    public void mod133Test() {
        assertEq(2, NumberUtil.mod(23,-7));
    }
    @Test
    public void quot14Test() {
        assertEq(0, NumberUtil.quot(0L,1));
    }
    @Test
    public void quot15Test() {
        assertEq(1, NumberUtil.quot(4L,3));
    }
    @Test
    public void quot16Test() {
        assertEq(2, NumberUtil.quot(4L,2));
    }
    @Test
    public void quot17Test() {
        assertEq(0, NumberUtil.quot(1L,3));
    }
    @Test
    public void quot18Test() {
        assertEq(-1, NumberUtil.quot(4L,-3));
    }
    @Test
    public void quot19Test() {
        assertEq(-2, NumberUtil.quot(4L,-2));
    }
    @Test
    public void quot20Test() {
        assertEq(0, NumberUtil.quot(1L,-3));
    }
    @Test
    public void quot21Test() {
        assertEq(-2, NumberUtil.quot(-4L,3));
    }
    @Test
    public void quot22Test() {
        assertEq(-2, NumberUtil.quot(-4L,2));
    }
    @Test
    public void quot23Test() {
        assertEq(-1, NumberUtil.quot(-1L,3));
    }
    @Test
    public void quot24Test() {
        assertEq(2, NumberUtil.quot(-4L,-3));
    }
    @Test
    public void quot25Test() {
        assertEq(2, NumberUtil.quot(-4L,-2));
    }
    @Test
    public void quot26Test() {
        assertEq(1, NumberUtil.quot(-1L,-3));
    }
    @Test
    public void quot27Test() {
        assertEq(3074457345618258603L, NumberUtil.quot(Long.MIN_VALUE,-3));
    }
    @Test
    public void quot28Test() {
        assertEq(4611686018427387904L, NumberUtil.quot(Long.MIN_VALUE,-2));
    }
    @Test
    public void quot29Test() {
        assertEq(-3074457345618258603L, NumberUtil.quot(Long.MIN_VALUE,3));
    }
    @Test
    public void quot30Test() {
        assertEq(-4611686018427387904L, NumberUtil.quot(Long.MIN_VALUE,2));
    }
    @Test
    public void quot31Test() {
        assertEq(-1537228672809129302L, NumberUtil.quot(Long.MIN_VALUE,6));
    }
    @Test
    public void quot32Test() {
        assertEq(1537228672809129302L, NumberUtil.quot(Long.MIN_VALUE,-6));
    }
    @Test
    public void quot33Test() {
        assertEq(-1317624576693539402L, NumberUtil.quot(Long.MIN_VALUE,7));
    }
    @Test
    public void quot34Test() {
        assertEq(1317624576693539402L, NumberUtil.quot(Long.MIN_VALUE,-7));
    }
    @Test
    public void quot35Test() {
        assertEq(-1152921504606846976L, NumberUtil.quot(Long.MIN_VALUE,8));
    }
    @Test
    public void quot36Test() {
        assertEq(1152921504606846976L, NumberUtil.quot(Long.MIN_VALUE,-8));
    }
    @Test
    public void quot37Test() {
        assertEq(-1024819115206086201L, NumberUtil.quot(Long.MIN_VALUE,9));
    }
    @Test
    public void quot38Test() {
        assertEq(1024819115206086201L, NumberUtil.quot(Long.MIN_VALUE,-9));
    }
    @Test
    public void quot39Test() {
        assertEq(-128102389400760776L, NumberUtil.quot(Long.MIN_VALUE,72));
    }
    @Test
    public void quot40Test() {
        assertEq(128102389400760776L, NumberUtil.quot(Long.MIN_VALUE,-72));
    }
    @Test
    public void quot41Test() {
        assertEq(-126347562148695560L, NumberUtil.quot(Long.MIN_VALUE,73));
    }
    @Test
    public void quot42Test() {
        assertEq(126347562148695560L, NumberUtil.quot(Long.MIN_VALUE,-73));
    }
    @Test
    public void quot43Test() {
        assertEq(-124640162660199674L, NumberUtil.quot(Long.MIN_VALUE,74));
    }
    @Test
    public void quot44Test() {
        assertEq(124640162660199674L, NumberUtil.quot(Long.MIN_VALUE,-74));
    }
    @Test
    public void quot45Test() {
        assertEq(-122978293824730345L, NumberUtil.quot(Long.MIN_VALUE,75));
    }
    @Test
    public void quot46Test() {
        assertEq(122978293824730345L, NumberUtil.quot(Long.MIN_VALUE,-75));
    }
    @Test
    public void quot47Test() {
        assertEq(1, NumberUtil.quot(-1,Long.MIN_VALUE));
    }
    @Test
    public void quot48Test() {
        assertEq(0, NumberUtil.quot(1,Long.MIN_VALUE));
    }
    @Test
    public void quot49Test() {
        assertEq(1, NumberUtil.quot(Long.MIN_VALUE,Long.MIN_VALUE));
    }
    @Test
    public void quot50Test() {
        assertEq(0, NumberUtil.quot(0,0));
    }
    @Test
    public void mod14Test() {
        assertEq(0, NumberUtil.mod(0L,1));
    }
    @Test
    public void mod15Test() {
        assertEq(1, NumberUtil.mod(4L,3));
    }
    @Test
    public void mod16Test() {
        assertEq(0, NumberUtil.mod(4L,2));
    }
    @Test
    public void mod17Test() {
        assertEq(1, NumberUtil.mod(1L,3));
    }
    @Test
    public void mod18Test() {
        assertEq(1, NumberUtil.mod(4L,-3));
    }
    @Test
    public void mod19Test() {
        assertEq(0, NumberUtil.mod(4L,-2));
    }
    @Test
    public void mod20Test() {
        assertEq(1, NumberUtil.mod(1L,-3));
    }
    @Test
    public void mod21Test() {
        assertEq(2, NumberUtil.mod(-4L,3));
    }
    @Test
    public void mod22Test() {
        assertEq(0, NumberUtil.mod(-4L,2));
    }
    @Test
    public void mod23Test() {
        assertEq(2, NumberUtil.mod(-1L,3));
    }
    @Test
    public void mod24Test() {
        assertEq(2, NumberUtil.mod(-4L,-3));
    }
    @Test
    public void mod25Test() {
        assertEq(0, NumberUtil.mod(-4L,-2));
    }
    @Test
    public void mod26Test() {
        assertEq(2, NumberUtil.mod(-1L,-3));
    }
    @Test
    public void getAllIndexes1(){
        Ints nbs_ = new Ints();
        assertEq(0,nbs_.getAllIndexes().size());
    }
    @Test
    public void getAllIndexes2(){
        Ints nbs_ = new Ints();
        nbs_.add(2);
        nbs_.add(3);
        nbs_.add(4);
        CustList<Ints> indexes_;
        indexes_ = nbs_.getAllIndexes();
        assertEq(24,indexes_.size());
        assertEq(3, indexes_.get(0).size());
        assertEq(0, indexes_.get(0).get(0));
        assertEq(0, indexes_.get(0).get(1));
        assertEq(0, indexes_.get(0).get(2));
        assertEq(3, indexes_.get(1).size());
        assertEq(0, indexes_.get(1).get(0));
        assertEq(0, indexes_.get(1).get(1));
        assertEq(1, indexes_.get(1).get(2));
        assertEq(3, indexes_.get(2).size());
        assertEq(0, indexes_.get(2).get(0));
        assertEq(0, indexes_.get(2).get(1));
        assertEq(2, indexes_.get(2).get(2));
        assertEq(3, indexes_.get(3).size());
        assertEq(0, indexes_.get(3).get(0));
        assertEq(0, indexes_.get(3).get(1));
        assertEq(3, indexes_.get(3).get(2));
        assertEq(3, indexes_.get(4).size());
        assertEq(0, indexes_.get(4).get(0));
        assertEq(1, indexes_.get(4).get(1));
        assertEq(0, indexes_.get(4).get(2));
        assertEq(3, indexes_.get(5).size());
        assertEq(0, indexes_.get(5).get(0));
        assertEq(1, indexes_.get(5).get(1));
        assertEq(1, indexes_.get(5).get(2));
        assertEq(3, indexes_.get(6).size());
        assertEq(0, indexes_.get(6).get(0));
        assertEq(1, indexes_.get(6).get(1));
        assertEq(2, indexes_.get(6).get(2));
        assertEq(3, indexes_.get(7).size());
        assertEq(0, indexes_.get(7).get(0));
        assertEq(1, indexes_.get(7).get(1));
        assertEq(3, indexes_.get(7).get(2));
        assertEq(3, indexes_.get(8).size());
        assertEq(0, indexes_.get(8).get(0));
        assertEq(2, indexes_.get(8).get(1));
        assertEq(0, indexes_.get(8).get(2));
        assertEq(3, indexes_.get(9).size());
        assertEq(0, indexes_.get(9).get(0));
        assertEq(2, indexes_.get(9).get(1));
        assertEq(1, indexes_.get(9).get(2));
        assertEq(3, indexes_.get(10).size());
        assertEq(0, indexes_.get(10).get(0));
        assertEq(2, indexes_.get(10).get(1));
        assertEq(2, indexes_.get(10).get(2));
        assertEq(3, indexes_.get(11).size());
        assertEq(0, indexes_.get(11).get(0));
        assertEq(2, indexes_.get(11).get(1));
        assertEq(3, indexes_.get(11).get(2));
        assertEq(3, indexes_.get(12).size());
        assertEq(1, indexes_.get(12).get(0));
        assertEq(0, indexes_.get(12).get(1));
        assertEq(0, indexes_.get(12).get(2));
        assertEq(3, indexes_.get(13).size());
        assertEq(1, indexes_.get(13).get(0));
        assertEq(0, indexes_.get(13).get(1));
        assertEq(1, indexes_.get(13).get(2));
        assertEq(3, indexes_.get(14).size());
        assertEq(1, indexes_.get(14).get(0));
        assertEq(0, indexes_.get(14).get(1));
        assertEq(2, indexes_.get(14).get(2));
        assertEq(3, indexes_.get(15).size());
        assertEq(1, indexes_.get(15).get(0));
        assertEq(0, indexes_.get(15).get(1));
        assertEq(3, indexes_.get(15).get(2));
        assertEq(3, indexes_.get(16).size());
        assertEq(1, indexes_.get(16).get(0));
        assertEq(1, indexes_.get(16).get(1));
        assertEq(0, indexes_.get(16).get(2));
        assertEq(3, indexes_.get(17).size());
        assertEq(1, indexes_.get(17).get(0));
        assertEq(1, indexes_.get(17).get(1));
        assertEq(1, indexes_.get(17).get(2));
        assertEq(3, indexes_.get(18).size());
        assertEq(1, indexes_.get(18).get(0));
        assertEq(1, indexes_.get(18).get(1));
        assertEq(2, indexes_.get(18).get(2));
        assertEq(3, indexes_.get(19).size());
        assertEq(1, indexes_.get(19).get(0));
        assertEq(1, indexes_.get(19).get(1));
        assertEq(3, indexes_.get(19).get(2));
        assertEq(3, indexes_.get(20).size());
        assertEq(1, indexes_.get(20).get(0));
        assertEq(2, indexes_.get(20).get(1));
        assertEq(0, indexes_.get(20).get(2));
        assertEq(3, indexes_.get(21).size());
        assertEq(1, indexes_.get(21).get(0));
        assertEq(2, indexes_.get(21).get(1));
        assertEq(1, indexes_.get(21).get(2));
        assertEq(3, indexes_.get(22).size());
        assertEq(1, indexes_.get(22).get(0));
        assertEq(2, indexes_.get(22).get(1));
        assertEq(2, indexes_.get(22).get(2));
        assertEq(3, indexes_.get(23).size());
        assertEq(1, indexes_.get(23).get(0));
        assertEq(2, indexes_.get(23).get(1));
        assertEq(3, indexes_.get(23).get(2));
    }
    @Test
    public void cmpTest() {
        assertTrue(!NumberUtil.eq(1,0));
        assertTrue(NumberUtil.eq(1,1));
    }
    @Test
    public void sub1Test(){
        Ints nbs_ = new Ints();
        nbs_.add(5);
        nbs_.add(1);
        CustList<Integer> out_ = nbs_.sub(1,2);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void sub2Test(){
        Ints nbs_ = new Ints();
        nbs_.add(5);
        nbs_.add(1);
        CustList<Integer> out_ = nbs_.sub(2,1);
        assertEq(0, out_.size());
    }
    @Test
    public void mid1Test(){
        Ints nbs_ = new Ints();
        nbs_.add(5);
        nbs_.add(1);
        CustList<Integer> out_ = nbs_.mid(1,1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void eqSet1Test(){
        Bytes nbs_ = new Bytes();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Bytes nbsTwo_ = new Bytes();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(NumberUtil.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet2Test(){
        Bytes nbs_ = new Bytes();
        nbs_.add((byte) 5);
        Bytes nbsTwo_ = new Bytes();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(!NumberUtil.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet3Test(){
        Bytes nbs_ = new Bytes();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Bytes nbsTwo_ = new Bytes();
        nbsTwo_.add((byte) 5);
        assertTrue(!NumberUtil.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet4Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Shorts nbsTwo_ = new Shorts();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 1);
        assertTrue(NumberUtil.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet5Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        Shorts nbsTwo_ = new Shorts();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 1);
        assertTrue(!NumberUtil.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet6Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Shorts nbsTwo_ = new Shorts();
        nbsTwo_.add((short) 5);
        assertTrue(!NumberUtil.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void removeOneNumber1Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(2);
        assertEq(2, nbs_.size());
        assertEq(5, nbs_.get(0));
        assertEq(1, nbs_.get(1));
    }
    @Test
    public void removeOneNumber2Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(1);
        assertEq(1, nbs_.size());
        assertEq(5, nbs_.get(0));
    }
    @Test
    public void removeOneNumber3Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
        nbs_.removeObj(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
        nbs_.removeObj(1);
        assertEq(0, nbs_.size());
    }
    @Test
    public void removeAllLong1Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(1);
        assertEq(1, nbs_.size());
        assertEq(5, nbs_.get(0));
    }
    @Test
    public void removeAllLong2Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void removeAllLong3Test(){
        Shorts nbs_ = new Shorts();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(2);
        assertEq(2, nbs_.size());
        assertEq(5, nbs_.get(0));
        assertEq(1, nbs_.get(1));
    }
    @Test
    public void indexesOf1Test(){
        Ints nbs_ = new Ints(new CollCapacity(2));
        nbs_.add(5);
        nbs_.add(1);
        Ints out_ = nbs_.indexesOfObj(1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
        assertEq(0, NumberUtil.wrapIntArray().length);
    }
    @Test
    public void indexesOf2Test(){
        Longs nbs_ = new Longs(new CollCapacity(2));
        nbs_.add(5L);
        nbs_.add(1L);
        Longs l_ = new Longs(nbs_);
        Ints out_ = l_.indexesOfObj(1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void indexesOf3Test(){
        Bytes nbs_ = new Bytes(new CollCapacity(2));
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Bytes l_ = new Bytes(nbs_);
        Ints out_ = l_.indexesOfObj(1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void indexesOf4Test(){
        Shorts nbs_ = new Shorts(new CollCapacity(2));
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Shorts l_ = new Shorts(nbs_);
        Ints out_ = l_.indexesOfObj(1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void containsObj1Test(){
        Ints nbs_ = new Ints(new CollCapacity(2));
        nbs_.add(5);
        nbs_.add(1);
        assertTrue(nbs_.containsObj(1));
        assertTrue(!nbs_.containsObj(2));
    }
    @Test
    public void containsObj2Test(){
        Longs nbs_ = new Longs(5L,1L);
        assertTrue(nbs_.containsObj(1));
        assertTrue(!nbs_.containsObj(2));
    }
    @Test
    public void containsObj3Test(){
        Bytes nbs_ = new Bytes((byte)5,(byte)1);
        assertTrue(nbs_.containsObj(1));
        assertTrue(!nbs_.containsObj(2));
    }
    @Test
    public void containsObj4Test(){
        Shorts nbs_ = new Shorts((short)5,(short)1);
        assertTrue(nbs_.containsObj(1));
        assertTrue(!nbs_.containsObj(2));
    }
    @Test
    public void containsObj5Test(){
        Longs nbs_ = new Longs();
        assertTrue(!nbs_.containsObj(1));
        assertTrue(!nbs_.containsObj(2));
    }
    @Test
    public void signum1Test(){
        assertEq(-1,NumberUtil.signum(-2));
    }
    @Test
    public void signum2Test(){
        assertEq(-1,NumberUtil.signum(-1));
    }
    @Test
    public void signum3Test(){
        assertEq(0,NumberUtil.signum(0));
    }
    @Test
    public void signum4Test(){
        assertEq(1,NumberUtil.signum(1));
    }
    @Test
    public void signum5Test(){
        assertEq(1,NumberUtil.signum(2));
    }
}