package com.Kaz42c.FuzzySampler.Fuzzy;

import java.util.ArrayList;
import java.util.List;

public class MaxAggregator extends Aggregator {
    public MaxAggregator(List<WeightedSetGetter> weightedSetGetters, String name) {
        super(weightedSetGetters, name);
    }

    void updateDomain(ArrayList<DomainMembership[]> ruleFirings, DomainMembership[] result, int index, int i) {
        result[index].membership = Math.max(result[index].membership,
                weightedSetGetters.get(i).getWeight() * ruleFirings.get(i)[index].membership);
    }
}
