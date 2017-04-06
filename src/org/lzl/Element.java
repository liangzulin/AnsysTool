/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

/**
 *
 * @author liangzl2
 */
public class Element {

    /**
     * The Key of this Element
     */
    public int elementNum;
    
    public int[] node;
    
    /**
     * Please create a new one.Remember, the node is only a reference
     * @param elenum the no. of element
     * @param node the int array. recommend size is 8.
     */
    public Element(int elenum, int[] node) {
        this.elementNum = elenum;
        this.node = node;
    }
    
}
