/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.util.HashMap;

/**
 *
 * @author liangzl2
 */
public class DataOfAnsys {
    public HashMap<Integer,Element> elementlist;
    public HashMap<Integer,Node> nodelist;
    public HashMap<Integer,GroupOfElement> groupOfElementlist;

    public DataOfAnsys(HashMap<Integer,Element> elementlist, HashMap<Integer,Node> nodelist, HashMap<Integer,GroupOfElement> goe) {
        this.elementlist = elementlist;
        this.nodelist = nodelist;
        this.groupOfElementlist = goe;
    }
    
}
