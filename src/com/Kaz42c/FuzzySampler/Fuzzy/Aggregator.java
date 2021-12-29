package com.Kaz42c.FuzzySampler.Fuzzy;

import java.util.ArrayList;
import java.util.List;

public abstract class Aggregator implements ISetGetter {
    protected String name;

    protected List<WeightedSetGetter> weightedSetGetters;

    public Aggregator(List<WeightedSetGetter> weightedSetGetters, String name) {
        this.weightedSetGetters = weightedSetGetters;
        this.name = name;
    }

    public DomainMembership[] get() {
        ArrayList<DomainMembership[]> ruleFirings = new ArrayList<>(weightedSetGetters.size());
        for (WeightedSetGetter weightedSetGetter : weightedSetGetters) {
            ruleFirings.add(weightedSetGetter.getSetGetter().get());
        }
        if (ruleFirings.size() == 0) {
            throw new IllegalArgumentException("No rules given to aggregate.");
        }
        DomainMembership[] result = new DomainMembership[ruleFirings.get(0).length];

        for (int index = 0; index < result.length; index++) {
            result[index] = new DomainMembership(ruleFirings.get(0)[index].domain, weightedSetGetters.get(0).weight * ruleFirings.get(0)[index].membership, name);
            for (int i = 1; i < weightedSetGetters.size(); i++) {
                if (Math.abs(result[index].domain - ruleFirings.get(i)[index].domain) > result[index].domain / 10000000000000d) {
                    throw new IllegalArgumentException("Domains must match in input sets.");
                }
                updateDomain(ruleFirings, result, index, i);
            }
        }

        return result;
    }

    abstract void updateDomain(ArrayList<DomainMembership[]> ruleFirings, DomainMembership[] result, int index, int i);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<WeightedSetGetter> getWeightedSetGetters() {
        return weightedSetGetters;
    }

    public void addWeightedSetGetter(WeightedSetGetter weightedSetGetter) {
        weightedSetGetters.add(weightedSetGetter);
    }

    public void removeWeightedSetGetter(WeightedSetGetter weightedSetGetter) {
        weightedSetGetters.remove(weightedSetGetter);
    }
}
