package com.Kaz42c.FuzzySampler.Fuzzy;

public abstract class InputOperator implements ISetGetter {
    protected ISetGetter _aInputGetter;
    protected ISetGetter _bInputGetter;

    protected String name;

    public InputOperator(ISetGetter aInputGetter, ISetGetter bInputGetter, String name) {
        _aInputGetter = aInputGetter;
        _bInputGetter = bInputGetter;
        this.name = name;
    }

    public DomainMembership[] get() {
        DomainMembership[] aIn = _aInputGetter.get();
        DomainMembership[] bIn = _bInputGetter.get();

        if (aIn.length != bIn.length) {
            throw new IllegalArgumentException("Inputs must be of the same length.");
        }

        DomainMembership[] result = new DomainMembership[aIn.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = calculateValue(aIn[i].domain, aIn[i].membership, bIn[i].membership);
        }

        return result;
    }

    abstract protected DomainMembership calculateValue(double aInDomain, double aInMembership, double bInMembership);

    public ISetGetter get_aInputGetter() {
        return _aInputGetter;
    }

    public void set_aInputGetter(ISetGetter _aInputGetter) {
        this._aInputGetter = _aInputGetter;
    }

    public ISetGetter get_bInputGetter() {
        return _bInputGetter;
    }

    public void set_bInputGetter(ISetGetter _bInputGetter) {
        this._bInputGetter = _bInputGetter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
