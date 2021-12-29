package com.Kaz42c.FuzzySampler.Fuzzy;

public class GaussianMembershipFunction extends MembershipFunction {
    private double _a; //curve peak
    private double _b; //peak center
    private double _c; //standard deviation

    /**
     * @param a           curve peak
     * @param b           peak center
     * @param c           standard deviation
     * @param inputGetter input getter
     */
    public GaussianMembershipFunction(double a, double b, double c, ISetGetter inputGetter, String name) {
        super(inputGetter, name);
        _a = a;
        _b = b;
        _c = c;
    }

    protected double MembershipFunctionSample(double input) {
        return _a * Math.exp(-(Math.pow(input - _b, 2) / (2 * Math.pow(_c, 2))));
    }

    public void set_a(double _a) {
        this._a = _a;
    }

    public void set_b(double _b) {
        this._b = _b;
    }

    public void set_c(double _c) {
        this._c = _c;
    }
}
