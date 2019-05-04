package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class NumbersTest {
    @Test
    public void eq1Test(){
        assertTrue(Numbers.eq(new Long(1), new Integer(1)));
    }
    @Test
    public void eq2Test(){
        assertTrue(Numbers.eq(new Long(1), new Short((short) 1)));
    }
    @Test
    public void eq3Test(){
        assertTrue(Numbers.eq(new Long(1), new Byte((byte) 1)));
    }
    @Test
    public void eq4Test(){
        assertTrue(Numbers.eq(new Short((short) 1), new Integer(1)));
    }
    @Test
    public void eq5Test(){
        assertTrue(Numbers.eq(new Byte((byte) 1), new Integer(1)));
    }
    @Test
    public void eq6Test(){
        assertTrue(Numbers.eq(new Byte((byte) 1), new Short((short) 1)));
    }
    @Test
    public void getReverseTest() {
        Numbers<Integer> list_ = new Numbers<Integer>();
        list_.add(0);
        list_.add(1);
        CustList<Integer> f_ = list_.getReverse();
        assertEq(2, f_.size());
        assertEq(1, f_.get(0));
        assertEq(0, f_.get(1));
    }
    @Test
    public void containsIndexOf1(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1, nbs_.get(0));
        assertEq(3, nbs_.get(1));
    }
    @Test
    public void removeDuplicates2(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.removeDuplicates();
        assertEq(1,nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void removeDuplicates3(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
    public void min1(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        assertEq(1,(long)nbs_.getMinimum(0));
        nbs_ = new Numbers<Integer>();
        nbs_.add(3);
        nbs_.add(1);
        assertEq(1,(long)nbs_.getMinimum(0));
        nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        assertEq(1,(long)nbs_.getMinimum(0));
        nbs_ = new Numbers<Integer>();
        assertEq(0,nbs_.getMinimum(0));
    }
    @Test
    public void max1(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        assertEq(3,(long)nbs_.getMaximum(0));
        nbs_ = new Numbers<Integer>();
        nbs_.add(3);
        nbs_.add(1);
        assertEq(3,(long)nbs_.getMaximum(0));
        nbs_ = new Numbers<Integer>();
        nbs_.add(3);
        assertEq(3,(long)nbs_.getMaximum(0));
        nbs_ = new Numbers<Integer>();
        assertEq(0,nbs_.getMaximum(0));
    }
    @Test
    public void sort1(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
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
        assertEq(0, Numbers.parseLongZero("0"));
    }
    @Test
    public void parseLongZero2Test() {
        assertEq(1, Numbers.parseLongZero("1"));
    }
    @Test
    public void parseLongZero3Test() {
        assertEq(9, Numbers.parseLongZero("9"));
    }
    @Test
    public void parseLongZero4Test() {
        assertEq(10, Numbers.parseLongZero("10"));
    }
    @Test
    public void parseLongZero5Test() {
        assertEq(-1, Numbers.parseLongZero("-1"));
    }
    @Test
    public void parseLongZero6Test() {
        assertEq(-9, Numbers.parseLongZero("-9"));
    }
    @Test
    public void parseLongZero7Test() {
        assertEq(-10, Numbers.parseLongZero("-10"));
    }
    @Test
    public void parseLongZero8Test() {
        assertEq(0, Numbers.parseLongZero(""));
    }
    @Test
    public void parseLongZero9Test() {
        assertEq(0, Numbers.parseLongZero("-"));
    }
    @Test
    public void parseInt1Test() {
        assertEq(-1, Numbers.parseInt("-1"));
    }
    @Test
    public void parseInt2Test() {
        assertEq(1, Numbers.parseInt("1"));
    }
    @Test
    public void parseInt3Test() {
        assertEq(0, Numbers.parseInt("10000000000"));
    }
    @Test
    public void parseInt4Test() {
        assertEq(0, Numbers.parseInt("-10000000000"));
    }
    @Test
    public void quot1Test() {
        assertEq(0, Numbers.quot(0,1));
    }
    @Test
    public void quot2Test() {
        assertEq(1, Numbers.quot(4,3));
    }
    @Test
    public void quot3Test() {
        assertEq(2, Numbers.quot(4,2));
    }
    @Test
    public void quot4Test() {
        assertEq(0, Numbers.quot(1,3));
    }
    @Test
    public void quot5Test() {
        assertEq(-1, Numbers.quot(4,-3));
    }
    @Test
    public void quot6Test() {
        assertEq(-2, Numbers.quot(4,-2));
    }
    @Test
    public void quot7Test() {
        assertEq(0, Numbers.quot(1,-3));
    }
    @Test
    public void quot8Test() {
        assertEq(-2, Numbers.quot(-4,3));
    }
    @Test
    public void quot9Test() {
        assertEq(-2, Numbers.quot(-4,2));
    }
    @Test
    public void quot10Test() {
        assertEq(-1, Numbers.quot(-1,3));
    }
    @Test
    public void quot11Test() {
        assertEq(2, Numbers.quot(-4,-3));
    }
    @Test
    public void quot12Test() {
        assertEq(2, Numbers.quot(-4,-2));
    }
    @Test
    public void quot13Test() {
        assertEq(1, Numbers.quot(-1,-3));
    }
    @Test
    public void mod1Test() {
        assertEq(0, Numbers.mod(0,1));
    }
    @Test
    public void mod2Test() {
        assertEq(1, Numbers.mod(4,3));
    }
    @Test
    public void mod3Test() {
        assertEq(0, Numbers.mod(4,2));
    }
    @Test
    public void mod4Test() {
        assertEq(1, Numbers.mod(1,3));
    }
    @Test
    public void mod5Test() {
        assertEq(1, Numbers.mod(4,-3));
    }
    @Test
    public void mod6Test() {
        assertEq(0, Numbers.mod(4,-2));
    }
    @Test
    public void mod7Test() {
        assertEq(1, Numbers.mod(1,-3));
    }
    @Test
    public void mod8Test() {
        assertEq(2, Numbers.mod(-4,3));
    }
    @Test
    public void mod9Test() {
        assertEq(0, Numbers.mod(-4,2));
    }
    @Test
    public void mod10Test() {
        assertEq(2, Numbers.mod(-1,3));
    }
    @Test
    public void mod11Test() {
        assertEq(2, Numbers.mod(-4,-3));
    }
    @Test
    public void mod12Test() {
        assertEq(0, Numbers.mod(-4,-2));
    }
    @Test
    public void mod13Test() {
        assertEq(2, Numbers.mod(-1,-3));
    }
    @Test
    public void quot14Test() {
        assertEq(0, Numbers.quot(0L,1));
    }
    @Test
    public void quot15Test() {
        assertEq(1, Numbers.quot(4L,3));
    }
    @Test
    public void quot16Test() {
        assertEq(2, Numbers.quot(4L,2));
    }
    @Test
    public void quot17Test() {
        assertEq(0, Numbers.quot(1L,3));
    }
    @Test
    public void quot18Test() {
        assertEq(-1, Numbers.quot(4L,-3));
    }
    @Test
    public void quot19Test() {
        assertEq(-2, Numbers.quot(4L,-2));
    }
    @Test
    public void quot20Test() {
        assertEq(0, Numbers.quot(1L,-3));
    }
    @Test
    public void quot21Test() {
        assertEq(-2, Numbers.quot(-4L,3));
    }
    @Test
    public void quot22Test() {
        assertEq(-2, Numbers.quot(-4L,2));
    }
    @Test
    public void quot23Test() {
        assertEq(-1, Numbers.quot(-1L,3));
    }
    @Test
    public void quot24Test() {
        assertEq(2, Numbers.quot(-4L,-3));
    }
    @Test
    public void quot25Test() {
        assertEq(2, Numbers.quot(-4L,-2));
    }
    @Test
    public void quot26Test() {
        assertEq(1, Numbers.quot(-1L,-3));
    }
    @Test
    public void mod14Test() {
        assertEq(0, Numbers.mod(0L,1));
    }
    @Test
    public void mod15Test() {
        assertEq(1, Numbers.mod(4L,3));
    }
    @Test
    public void mod16Test() {
        assertEq(0, Numbers.mod(4L,2));
    }
    @Test
    public void mod17Test() {
        assertEq(1, Numbers.mod(1L,3));
    }
    @Test
    public void mod18Test() {
        assertEq(1, Numbers.mod(4L,-3));
    }
    @Test
    public void mod19Test() {
        assertEq(0, Numbers.mod(4L,-2));
    }
    @Test
    public void mod20Test() {
        assertEq(1, Numbers.mod(1L,-3));
    }
    @Test
    public void mod21Test() {
        assertEq(2, Numbers.mod(-4L,3));
    }
    @Test
    public void mod22Test() {
        assertEq(0, Numbers.mod(-4L,2));
    }
    @Test
    public void mod23Test() {
        assertEq(2, Numbers.mod(-1L,3));
    }
    @Test
    public void mod24Test() {
        assertEq(2, Numbers.mod(-4L,-3));
    }
    @Test
    public void mod25Test() {
        assertEq(0, Numbers.mod(-4L,-2));
    }
    @Test
    public void mod26Test() {
        assertEq(2, Numbers.mod(-1L,-3));
    }
    @Test
    public void getAllIndexes1(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        assertEq(0,nbs_.getAllIndexes().size());
    }
    @Test
    public void getAllIndexes2(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(2);
        nbs_.add(3);
        nbs_.add(4);
        CustList<Numbers<Integer>> indexes_;
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
    public void displayTest() {
        Numbers<Integer> list_ = new Numbers<Integer>();
        assertEq("[]",list_.display());
        list_ = new Numbers<Integer>();
        list_.add(1);
        assertEq("[1]",list_.display());
        list_ = new Numbers<Integer>();
        list_.add(0);
        list_.add(1);
        assertEq("[0,1]",list_.display());
        assertEq("1.0",Numbers.toString(1.0f));
        assertEq("1.0",Numbers.toString(1.0));
    }
    @Test
    public void joinTest() {
        Numbers<Integer> list_ = new Numbers<Integer>();
        assertEq("",list_.join(';'));
        assertEq("",list_.join(";"));
        list_ = new Numbers<Integer>();
        list_.add(1);
        assertEq("1",list_.join(';'));
        assertEq("1",list_.join(";"));
        list_ = new Numbers<Integer>();
        list_.add(0);
        list_.add(1);
        assertEq("0;1",list_.join(';'));
        assertEq("0;1",list_.join(";"));
    }
    @Test
    public void cmpTest() {
        assertTrue(Numbers.gt(1,0));
        assertTrue(!Numbers.gt(0,1));
        assertTrue(Numbers.gt(1f,-1));
        assertTrue(!Numbers.gt(-1f,1));
        assertTrue(Numbers.gt(1d,-1));
        assertTrue(!Numbers.gt(-1d,1));
        assertTrue(Numbers.gt(1,-1f));
        assertTrue(!Numbers.gt(-1,1f));
        assertTrue(Numbers.gt(1,-1d));
        assertTrue(!Numbers.gt(-1,1d));
        assertTrue(!Numbers.lt(1,0));
        assertTrue(Numbers.lt(0,1));
        assertTrue(!Numbers.lt(1f,-1));
        assertTrue(Numbers.lt(-1f,1));
        assertTrue(!Numbers.lt(1d,-1));
        assertTrue(Numbers.lt(-1d,1));
        assertTrue(!Numbers.lt(1,-1f));
        assertTrue(Numbers.lt(-1,1f));
        assertTrue(!Numbers.lt(1,-1d));
        assertTrue(Numbers.lt(-1,1d));
        assertTrue(!Numbers.eq(1,0));
        assertTrue(Numbers.eq(1,1));
        assertTrue(!Numbers.eq((Number)1,0));
        assertTrue(Numbers.eq((Number)1,1));
        assertTrue(!Numbers.eq(1f,-1));
        assertTrue(Numbers.eq(-1f,-1));
        assertTrue(!Numbers.eq(1d,-1));
        assertTrue(Numbers.eq(-1d,-1));
        assertTrue(!Numbers.eq(1,-1f));
        assertTrue(Numbers.eq(-1,-1f));
        assertTrue(!Numbers.eq(1,-1d));
        assertTrue(Numbers.eq(-1,-1d));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compare(-1,1));
        assertEq(CustList.EQ_CMP,Numbers.compare(1,1));
        assertEq(CustList.SWAP_SORT,Numbers.compare(1,-1));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1,1));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1,1));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1,-1));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1,1f));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1,1f));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1,-1f));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1,1d));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1,1d));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1,-1d));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1f,1f));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1f,1f));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1f,-1f));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1f,1d));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1f,1d));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1f,-1d));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1d,1f));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1d,1f));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1d,-1f));
        assertEq(CustList.NO_SWAP_SORT,Numbers.compareGene(-1d,1d));
        assertEq(CustList.EQ_CMP,Numbers.compareGene(1d,1d));
        assertEq(CustList.SWAP_SORT,Numbers.compareGene(1d,-1d));
    }
    @Test
    public void sub1Test(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(5);
        nbs_.add(1);
        Numbers<Integer> out_ = nbs_.sub(1,2);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void sub2Test(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(5);
        nbs_.add(1);
        Numbers<Integer> out_ = nbs_.sub(2,1);
        assertEq(0, out_.size());
    }
    @Test
    public void mid1Test(){
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(5);
        nbs_.add(1);
        Numbers<Integer> out_ = nbs_.mid(1,1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
    }
    @Test
    public void eqSet1Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(Numbers.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet2Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(!Numbers.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet3Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        assertTrue(!Numbers.equalsSetBytes(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet4Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Numbers<Short> nbsTwo_ = new Numbers<Short>();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 1);
        assertTrue(Numbers.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet5Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        Numbers<Short> nbsTwo_ = new Numbers<Short>();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 1);
        assertTrue(!Numbers.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void eqSet6Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Numbers<Short> nbsTwo_ = new Numbers<Short>();
        nbsTwo_.add((short) 5);
        assertTrue(!Numbers.equalsSetShorts(nbs_,nbsTwo_));
    }
    @Test
    public void removeAllElementsTest(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Numbers<Short> nbsTwo_ = new Numbers<Short>();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 2);
        nbs_.removeAllElements(nbsTwo_);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void retainAllElementsTest(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        Numbers<Short> nbsTwo_ = new Numbers<Short>();
        nbsTwo_.add((short) 5);
        nbsTwo_.add((short) 2);
        nbs_.retainAllElements(nbsTwo_);
        assertEq(1, nbs_.size());
        assertEq(5, nbs_.get(0));
    }
    @Test
    public void removeOneNumber1Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(2);
        assertEq(2, nbs_.size());
        assertEq(5, nbs_.get(0));
        assertEq(1, nbs_.get(1));
    }
    @Test
    public void removeOneNumber2Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(1);
        assertEq(1, nbs_.size());
        assertEq(5, nbs_.get(0));
    }
    @Test
    public void removeOneNumber3Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeOneNumber(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
        nbs_.removeObj(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void containsAllObj1Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(nbs_.containsAllObj(nbsTwo_));
    }
    @Test
    public void containsAllObj2Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        nbsTwo_.add((byte) 1);
        assertTrue(!nbs_.containsAllObj(nbsTwo_));
    }
    @Test
    public void containsAllObj3Test(){
        Numbers<Byte> nbs_ = new Numbers<Byte>();
        nbs_.add((byte) 5);
        nbs_.add((byte) 1);
        Numbers<Byte> nbsTwo_ = new Numbers<Byte>();
        nbsTwo_.add((byte) 5);
        assertTrue(nbs_.containsAllObj(nbsTwo_));
    }
    @Test
    public void removeAllLong1Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(1);
        assertEq(1, nbs_.size());
        assertEq(5, nbs_.get(0));
    }
    @Test
    public void removeAllLong2Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(5);
        assertEq(1, nbs_.size());
        assertEq(1, nbs_.get(0));
    }
    @Test
    public void removeAllLong3Test(){
        Numbers<Short> nbs_ = new Numbers<Short>();
        nbs_.add((short) 5);
        nbs_.add((short) 1);
        nbs_.removeAllLong(2);
        assertEq(2, nbs_.size());
        assertEq(5, nbs_.get(0));
        assertEq(1, nbs_.get(1));
    }
    @Test
    public void indexesOfTest(){
        Numbers<Integer> nbs_ = new Numbers<Integer>(new CollCapacity(2));
        nbs_.add(5);
        nbs_.add(1);
        Numbers<Integer> out_ = nbs_.indexesOfObj(1);
        assertEq(1, out_.size());
        assertEq(1, out_.get(0));
        assertEq(0, Numbers.wrapIntArray().length);
    }
}