package com.Kaz42c.FuzzySampler.Fuzzy;

public abstract class MembershipFunction implements ISetGetter {
    private final AndInputOperator _tNorm;
    protected String name;
    private ISetGetter _inputGetter;

    protected MembershipFunction(ISetGetter inputGetter, String name) {
        this.name = name;
        _inputGetter = inputGetter;
        _tNorm = new AndInputOperator(_inputGetter,
                new ISetGetter() {
                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public void setName(String name) {
                    }

                    @Override
                    public DomainMembership[] get() {
                        DomainMembership[] input = _inputGetter.get();
                        DomainMembership[] result = new DomainMembership[input.length];
                        for (int index = 0; index < result.length; index++) {
                            result[index] = new DomainMembership(input[index].domain, MembershipFunctionSample(input[index].domain), name);
                        }

                        return result;
                    }
                },
                name);
    }

    public DomainMembership[] get() {
        return _tNorm.get();
    }

    protected abstract double MembershipFunctionSample(double input);

    public ISetGetter get_inputGetter() {
        return _inputGetter;
    }

    public void set_inputGetter(ISetGetter _inputGetter) {
        this._inputGetter = _inputGetter;
        _tNorm.set_aInputGetter(_inputGetter);
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
