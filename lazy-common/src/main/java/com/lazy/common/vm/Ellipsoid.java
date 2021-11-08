package com.lazy.common.vm;

public class Ellipsoid {

    //长半轴
    public double a;
    //短半轴
    public double b;
    //扁率
    public double f;
    //第一偏心率平方
    public double ec;
    //第二偏心率平方
    public double ecc;
    /// <summary>
    ///
    /// </summary>
    /// <param name="a为长半轴"></param>
    /// <param name="Invf为扁率"></param>
    public Ellipsoid(double a,double Invf)
    {
        this.a = a;
        f = 1 / Invf;
        evaluate();
    }
    public Ellipsoid()
    {
        a = 6378137.000;
        f = 1 / 298.3;
        evaluate();
    }
    void evaluate()
    {
        double b = a - a * f;
        ec = 1 - Math.pow(b, 2) / Math.pow(a, 2);
        ecc= ec / (1-ec);
    }
}
