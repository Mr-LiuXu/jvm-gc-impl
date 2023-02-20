package com.xuliu.model;

/***
 * @author liuxu
 * @desc 根的对象
 *
 */
public enum GCRoots {

    VMStackReferenceObject,	//虚拟机栈变量引用对象
    MethodAreaStaticReferenceObject, //方法区静态变量引用对象
    MethodAreaConstantReferenceObject, //方法区常量引用对象
    NaticeMethodReferenceObject;	//本地方法引用对象

    public int referenceNumber; //引用对象的个数

    private HeapObject[] reference;//引用的对象

    public GCRoots setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    /**
     * 获取引用对象
     * 使用单例确保正确初始化
     * @return
     */
    public HeapObject[] getReference() {
        if (reference == null){
            reference = new HeapObject[referenceNumber];
        }
        return reference;
    }

    public void setReference(HeapObject[] reference) {
        this.reference = reference;
    }
}
