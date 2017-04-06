/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author liangzl2
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap hm=new HashMap(10000);
        
//        try {
//            readBufferTest(hm);
//        } catch (IOException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {e.printStackTrace();}
            
            AnsysView av=new AnsysView();
            av.setVisible(true);
        });
        
//        try {
//            DataOfAnsys doa=new AnsysController(null).readAnsysFile("D:\\!CPP\\0405opening\\PRESOL.lis");
//            System.out.println("ElementList.size()=="+doa.elementlist.size());
//            System.out.println("GroupOfElement.size()=="+doa.GroupOfElement.size());
//            System.out.println("nodelist.size()=="+doa.nodelist.size());
//            
//        } catch (IOException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public static void readBufferTest(HashMap hm) throws FileNotFoundException, IOException{
        long time = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        
        
        
        System.out.print(((System.currentTimeMillis() - time) / 1000.0) + "sec\t\t");
        System.out.println(runtime.totalMemory() / 1024 + ":" + runtime.freeMemory() / 1024 + ":" + (runtime.totalMemory() - runtime.freeMemory()) / 1024);
    }
    

}
