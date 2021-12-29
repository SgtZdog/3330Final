package com.Kaz42c.FuzzySampler.Fuzzy;

public class AndInputOperator extends InputOperator {
    public AndInputOperator(ISetGetter aInputGetter, ISetGetter bInputGetter, String name) {
        super(aInputGetter, bInputGetter, name);
    }

    protected DomainMembership calculateValue(double aInDomain, double aInMembership, double bInMembership) {
        return new DomainMembership(aInDomain, Math.min(aInMembership, bInMembership), name);
    }
}
