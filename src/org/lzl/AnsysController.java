/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author liangzl2
 */
public class AnsysController {
    private final JProgressBar jpb;
    public AnsysController(JProgressBar jpb){
        this.jpb = jpb;
    }
    
    /**
     * read ansys file
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public DataOfAnsys readAnsysFile(String filename) throws FileNotFoundException, IOException{
        long time = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        DataOfAnsys doa;
        HashMap<Integer,Element> elementlist = new HashMap(10000);
        HashMap<Integer,Node> nodelist = new HashMap(10000);
        HashMap<Integer,GroupOfElement> groupOfElement=new HashMap(10000);
        String elementhead="  ELEMENT="; // 10 length
//        String grouphead=" ***** POST1 ELEMENT NODAL STRESS LISTING *****";
        String grouphead=" ***** POST1"; // 12 length
        File file=new File(filename);
        long l = file.length();
        
        long currentl=0l;
        System.out.println("file size:"+l+" B=="+l/1024/1024+" MB");
//        FileInputStream fis=new FileInputStream(file);
        BufferedReader br=new BufferedReader(new FileReader(file));
        
        System.out.print(((System.currentTimeMillis() - time) / 1000.0) + "sec\t\t");
        System.out.println(runtime.totalMemory() / 1024 + ":" + runtime.freeMemory() / 1024 + ":" + (runtime.totalMemory() - runtime.freeMemory()) / 1024);
        l=new BufferedReader(new FileReader(file)).lines().count();
        System.out.print("Stream<lines> size:"+l+" lines; ---------");
        System.out.print(((System.currentTimeMillis() - time) / 1000.0) + "sec\t\t");
        System.out.println(runtime.totalMemory() / 1024 + ":" + runtime.freeMemory() / 1024 + ":" + (runtime.totalMemory() - runtime.freeMemory()) / 1024);
        
        
//        ProgressMonitorInputStream p=new ProgressMonitorInputStream(null, null, br);
        
        String line = null;
        int group=0;
        boolean isLastFindGroup=false;
        while(true){
            if(isLastFindGroup){
                
                isLastFindGroup=false;
            }else{
                line = br.readLine();
                if (line == null) {break;}
                currentl+=1;
            }
            
            
            int percent = (int) (currentl*100/l);
            SwingUtilities.invokeLater(() -> {
                this.jpb.setValue(percent);
            });
            
            if(line.length()>=12 && line.substring(0, 12).equals(grouphead)){
                //寻找到第一个组：
                GroupOfElement goe=new GroupOfElement();
                /**
                 * 中间可写读取LOAD STEP等的代码
                 */
                
                //<editor-fold defaultstate="collapsed" desc="找到元素的代码">
                while((line = br.readLine())!=null){
                    currentl+=1;

                    if(line.length()>=10 && line.substring(0, 10).equals(elementhead)){
                        //找到元素
                        Element e;//=new Element(elenum, node);
                        String[] cols=line.split("[\\s]+");
                        int elenum = Integer.valueOf(cols[2]);//获取元素号
//                        System.out.println(Arrays.toString(cols)+"--------"+cols[2]);
                        br.readLine();//跳过标题行，读取下一行
                        currentl+=1;
                        int[] nodearry=new int[8];
                        for(int i=0;i<8;i++){
                            //开始读取元素列表
                            line = br.readLine();
                            currentl+=1;
                            String c1=line.substring(0, 8);
                            String c2=line.substring(9, 21);
                            String c3=line.substring(21, 33);
                            String c4=line.substring(33, 45);
                            String c5=line.substring(45, 57);
                            String c6=line.substring(57, 69);
                            String c7=line.substring(69, 81);
                            Node n=new Node(Integer.valueOf(c1.trim()),
                                    Float.valueOf(c2),
                                    Float.valueOf(c3),
                                    Float.valueOf(c4),
                                    Float.valueOf(c5),
                                    Float.valueOf(c6),
                                    Float.valueOf(c7)
                            );
                            nodelist.put(Integer.valueOf(c1.trim()), n);
                            nodearry[i]=Integer.valueOf(c1.trim());
                            
                            
                        //                            System.out.println(n.toString());
                        }
                        e=new Element(elenum,nodearry);//直接对nodearry进行了引用，不懂是否会出问题
                        elementlist.put(elenum, e);
                        goe.addElement(e);
                    }else if(line.length()>=12 && line.substring(0, 12).equals(grouphead)){
                        isLastFindGroup=true;
                        break;
                    }

                }
                //</editor-fold>

                groupOfElement.put(group, goe);
                group++;
                
            }
        }
        doa=new DataOfAnsys(elementlist, nodelist, groupOfElement);
        System.out.println("read: "+currentl+" lines; "+currentl*100/l+" %");
        
        
        
        System.out.print(((System.currentTimeMillis() - time) / 1000.0) + "sec\t\t");
        System.out.println(runtime.totalMemory() / 1024 + ":" + runtime.freeMemory() / 1024 + ":" + (runtime.totalMemory() - runtime.freeMemory()) / 1024);
        br.close();
        System.gc();
        return doa;
    }
}
