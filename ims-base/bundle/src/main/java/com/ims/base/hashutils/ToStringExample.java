package com.ims.base.hashutils;

class ToStringExample {
    private double re, im;        
 
    public ToStringExample(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public static void main(String[] args) {
        ToStringExample c1 = new ToStringExample(10, 15);
        ToStringExample c2 = new ToStringExample(10, 15);
        
        System.out.println(c1);
        System.out.println(c1.toString());
        System.out.println(c2);
        c1=c2;
        System.out.println(c1+" "+c2);
    
    }
    

}
  
// Driver class to test the ToStringExample class
