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
public class Node {

    /**
     * The Key of this node
     */
    public int nodenum;
    
    public float SX;
    public float SY;
    public float SZ;
    public float SXY;
    public float SYZ;
    public float SXZ;
    public Node(int nodenum, float SX, float SY, float SZ, float SXY, float SYZ, float SXZ) {
        this.nodenum = nodenum;
        this.SX = SX;
        this.SY = SY;
        this.SZ = SZ;
        this.SXY = SXY;
        this.SYZ = SYZ;
        this.SXZ = SXZ;
    }
    
    @Override
    public String toString(){
        return "Node="+nodenum+
               "\tSX="+SX+
               "\tSY="+SY+
               "\tSZ="+SZ+
               "\tSXY="+SXY+
               "\tSYZ="+SYZ+
               "\tSXZ="+SXZ;
    }
}
