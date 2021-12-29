package com.Kaz42c.FuzzySampler.Fuzzy;

public class DomainMembership implements ISetGetter {
    public double domain;
    public double membership;
    protected String name;

    public DomainMembership(double domain, double membership, String name) {
        this.domain = domain;
        this.membership = membership;
        this.name = name;
    }

    public DomainMembership[] get() {
        return new DomainMembership[]{this};
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
