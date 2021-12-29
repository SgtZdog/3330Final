package com.Kaz42c.FuzzySampler.Fuzzy;

import java.util.Arrays;

public class CentroidDefuzzifier extends Defuzzifier {

    public CentroidDefuzzifier(ISetGetter setGetter, String name) {
        super(setGetter, name);
    }

    @Override
    protected DomainMembership[] Defuzzify(DomainMembership[] input) {
        return new DomainMembership[]
                {new DomainMembership(Arrays.stream(input).mapToDouble(x -> x.domain * x.membership).sum() /
                        Arrays.stream(input).mapToDouble(x -> x.membership).sum(),
                        1, name)};
    }
}
