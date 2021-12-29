package com.Kaz42c.FuzzySampler.Fuzzy;

public class TrapezoidalMembershipFunction extends MembershipFunction {

    private double _a; //left limit
    private double _b; //left max
    private double _c; //right max
    private double _d; //right limit

    /**
     * @param a           left limit
     * @param b           left max
     * @param c           right max
     * @param d           right limit
     * @param inputGetter input getter
     */
    public TrapezoidalMembershipFunction(double a, double b, double c, double d, ISetGetter inputGetter, String name) {
        super(inputGetter, name);
        _a = a;
        _b = b;
        _c = c;
        _d = d;
    }

    protected double MembershipFunctionSample(double input) {
        return Math.max(0,
                Math.min(Math.min((input - _a) / (_b - _a), 1d),
                        (_d - input) / (_d - _c)));
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

    public void set_d(double _d) {
        this._d = _d;
    }
}
