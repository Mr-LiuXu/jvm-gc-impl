package com.xuliu.alg;

import com.xuliu.model.GCRoots;
import com.xuliu.model.HeapObject;

/**
 * GC 复制算法
 * @author liuxu
 */
public class GCCopying {

    private HeapObject[] heap = new HeapObject[1000]; //堆
    public int from = 0; //from 空间起始位置
    public int to = 500; //to 空间的起始位
    private  boolean isSwap = false;


    public void copying (GCRoots roots){
        for (int i=0; i<roots.getReference().length; i++){
            HeapObject r = roots.getReference()[i];
            copy(r);
            //将forwarding 指针指向to 空间
            roots.getReference()[i] = heap[to];
            roots.setReference(roots.getReference());
            System.out.println("---------forwarding指向" + to);
            System.out.println();
        }
        sweepFrom(heap,from,to);
        swap(isSwap);
    }
    /**
     * 复制函数
     * @param obj 根指向对象
     */
    private void copy(HeapObject obj){
        //如果未被复制
        if (obj.COPIED == false){
            copy_data(obj);
        }
        if (obj.children != null){
            for (HeapObject child : obj.children) {
                copy(child);
            }
        }
    }
    /**
     * @param obj 复制到 to 空间的存活对象
     */
    private void copy_data(HeapObject obj){
        heap[to]  = (HeapObject) obj.clone();
        to += obj.size;
        obj.COPIED = true;
        System.out.println("第 " + obj.position + " 号对象已复制完成");
    }

    /**
     * 交换 from 空间与 to 空间
     * @param from
     * @param to
     */
    private void swap(boolean isSwap){
        if (isSwap == true){
            from = 500;
            to = 0;
        } else {
            from = 0;
            to = 500;
        }

        System.out.println("from空间交换至 " + from);
    }

    private void sweepFrom(HeapObject[] heap,int from,int to){
        if (isSwap == true){
            from = 500;
            to = 0;
        }else {
            from = 0;
            to = 500;
        }

        for (int i = from;i < to;i++){
            if (heap[i] != null){
                heap[i] = new HeapObject(heap[i].size,heap[i].position);
                System.out.println("=============>已清除第 " + i + " 个位置");
            }
        }
        isSwap = !isSwap;
    }
    public HeapObject[] getHeap(){
        return heap;}
}
