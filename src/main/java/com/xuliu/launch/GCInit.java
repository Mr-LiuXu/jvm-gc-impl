package com.xuliu.launch;

import com.xuliu.alg.GCCopying;
import com.xuliu.alg.Mark_Sweep;
import com.xuliu.model.GCRoots;
import com.xuliu.model.HeapObject;
import org.junit.Test;

import java.util.Random;

public class GCInit {

    /***
     * 标记清除算法
     */
    @Test
    public void  test_mark_sweep(){

        Mark_Sweep markSweep = new Mark_Sweep();
        GCRoots root = GCRoots.MethodAreaConstantReferenceObject.setReferenceNumber(2);
        Random rd = new Random();
        root.getReference()[0] = new HeapObject(3, rd.nextInt(1000));
        root.getReference()[1] = new HeapObject(2, rd.nextInt(1000));
        markSweep.mark_phase(root);
        markSweep.getHeap()[root.getReference()[0].position] = root.getReference()[0];
        markSweep.getHeap()[root.getReference()[1].position] = root.getReference()[1];
        markSweep.sweep_phase();

    }

    /**
     * GC复制算法
     */
    @Test
    public void test_GCCopying(){
        GCCopying copy = new GCCopying();
        GCRoots root = GCRoots.MethodAreaConstantReferenceObject.setReferenceNumber(2);
        Random random = new Random();
        root.getReference()[0] = new HeapObject(3,random.nextInt(500));
        root.getReference()[1] = new HeapObject(2,random.nextInt(500));
        copy.getHeap()[root.getReference()[0].position] = root.getReference()[0];
        copy.getHeap()[root.getReference()[1].position] = root.getReference()[1];
        copy.copying(root);
    }





}
