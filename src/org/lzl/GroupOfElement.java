/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.util.ArrayList;

/**
 *
 * @author liangzl2
 */
public class GroupOfElement {
    public ArrayList<Element> elelist;
    public GroupOfElement(){
        this.elelist = new ArrayList<>();
    }
    public void addElement(Element e){
        this.elelist.add(e);
    }
}
