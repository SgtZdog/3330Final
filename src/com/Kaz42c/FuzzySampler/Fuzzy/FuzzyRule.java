package com.Kaz42c.FuzzySampler.Fuzzy;

public abstract class FuzzyRule implements ISetGetter {
    protected ISetGetter _aSet;
    protected ISetGetter _bSet;
    protected String name;

    public FuzzyRule(ISetGetter aSet, ISetGetter bSet, String name) {
        this.name = name;
        _aSet = aSet;
        _bSet = bSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ISetGetter get_aSet() {
        return _aSet;
    }

    public void set_aSet(ISetGetter _aSet) {
        this._aSet = _aSet;
    }

    public ISetGetter get_bSet() {
        return _bSet;
    }

    public void set_bSet(ISetGetter _bSet) {
        this._bSet = _bSet;
    }
}
