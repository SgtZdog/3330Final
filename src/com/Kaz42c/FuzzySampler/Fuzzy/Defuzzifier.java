package com.Kaz42c.FuzzySampler.Fuzzy;

public abstract class Defuzzifier implements ISetGetter {
    protected String name;

    private ISetGetter _setGetter;

    public Defuzzifier(ISetGetter setGetter, String name) {
        this.name = name;
        _setGetter = setGetter;
    }

    public DomainMembership[] get() {
        DomainMembership[] input = _setGetter.get();
        return Defuzzify(input);
    }

    abstract protected DomainMembership[] Defuzzify(DomainMembership[] input);

    public ISetGetter get_setGetter() {
        return _setGetter;
    }

    public void set_setGetter(ISetGetter _setGetter) {
        this._setGetter = _setGetter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
