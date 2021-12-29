package com.Kaz42c.FuzzySampler.Fuzzy;

public class WeightedSetGetter {
    protected ISetGetter setGetter;
    protected double weight;

    public WeightedSetGetter(ISetGetter setGetter, double weight) {
        this.setGetter = setGetter;
        this.weight = weight;
    }

    public ISetGetter getSetGetter() {
        return setGetter;
    }

    public void setSetGetter(ISetGetter setGetter) {
        this.setGetter = setGetter;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
