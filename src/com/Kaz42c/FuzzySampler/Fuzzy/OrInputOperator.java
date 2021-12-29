package com.Kaz42c.FuzzySampler.Fuzzy;

public class OrInputOperator extends InputOperator {
    public OrInputOperator(ISetGetter aInputGetter, ISetGetter bInputGetter, String name) {
        super(aInputGetter, bInputGetter, name);
    }

    protected DomainMembership calculateValue(double aInDomain, double aInMembership, double bInMembership) {
        return new DomainMembership(aInDomain, Math.max(aInMembership, bInMembership), name);
    }
}
