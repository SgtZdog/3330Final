package com.Kaz42c.FuzzySampler.Fuzzy;

import java.util.function.Supplier;

public interface ISetGetter extends Supplier<DomainMembership[]> {
    String getName();

    void setName(String name);
}
