package com.Kaz42c.FuzzySampler.Fuzzy;

public class MamdaniRule extends FuzzyRule {
    public MamdaniRule(ISetGetter aSet, ISetGetter bSet, String name) {
        super(aSet, bSet, name);
    }

    public DomainMembership[] get() {
        DomainMembership[] aIn = _aSet.get();
        if (aIn.length != 1) {
            throw new IllegalArgumentException("Mamdani only works with crisp input. Please provide a SetGetter that returns a single element.");
        }

        double membershipMax = aIn[0].membership;
        DomainMembership[] bSet = _bSet.get();
        DomainMembership[] bPrime = new DomainMembership[bSet.length]; //Trim our output membership down to our input membership as needed.
        for (int index = 0; index < bSet.length; index++) {
            bPrime[index] = new DomainMembership(bSet[index].domain, Math.min(membershipMax, bSet[index].membership), name);
        }

        return bPrime;
    }
}
