package com.Kaz42c.FuzzySampler;

import com.Kaz42c.FuzzySampler.Fuzzy.*;

import java.util.ArrayList;
import java.util.List;

public class FIS {
    public static final int PRECISION = 10;
    private final List<DomainMembership> Inputs = new ArrayList<>();
    private final List<MembershipFunction> ASets = new ArrayList<>();
    private final List<MembershipFunction> BSets = new ArrayList<>();
    private final List<InputOperator> InputOperators = new ArrayList<>();
    private final List<FuzzyRule> Rules = new ArrayList<>();
    private final List<Aggregator> Aggregators = new ArrayList<>();
    private final List<Defuzzifier> Defuzzifiers = new ArrayList<>();

    public List<DomainMembership> getInputs() {
        return Inputs;
    }

    public List<MembershipFunction> getASets() {
        return ASets;
    }

    public List<MembershipFunction> getBSets() {
        return BSets;
    }

    public List<InputOperator> getInputOperators() {
        return InputOperators;
    }

    public List<FuzzyRule> getRules() {
        return Rules;
    }

    public List<Aggregator> getAggregators() {
        return Aggregators;
    }

    public List<Defuzzifier> getDefuzzifiers() {
        return Defuzzifiers;
    }

    public void addInput(DomainMembership input) {
        Inputs.add(input);
    }

    public void removeInput(DomainMembership input) {
        String name = input.getName();
        for (MembershipFunction aSet :
                ASets) {
            if (aSet.get_inputGetter().getName().equals(name)) {
                aSet.set_inputGetter(null);
            }
        }
        Inputs.remove(input);
    }

    public void addASet(MembershipFunction membershipFunction) {
        ASets.add(membershipFunction);
    }

    public void removeASet(MembershipFunction membershipFunction) {
        String name = membershipFunction.getName();
        for (InputOperator inputOperator :
                InputOperators) {
            if (inputOperator.get_aInputGetter().getName().equals(name)) {
                inputOperator.set_aInputGetter(null);
            }
            if (inputOperator.get_bInputGetter().getName().equals(name)) {
                inputOperator.set_bInputGetter(null);
            }
        }
        for (FuzzyRule rule :
                Rules) {
            if (rule.get_aSet().getName().equals(name)) {
                rule.set_aSet(null);
            }
        }
        ASets.remove(membershipFunction);
    }

    public void addBSet(MembershipFunction membershipFunction) {
        BSets.add(membershipFunction);
    }

    public void removeBSet(MembershipFunction membershipFunction) {
        String name = membershipFunction.getName();
        for (FuzzyRule rule :
                Rules) {
            if (rule.get_bSet().getName().equals(name)) {
                rule.set_bSet(null);
            }
        }
        BSets.remove(membershipFunction);
    }

    public void addInputOperator(InputOperator inputOperator) {
        InputOperators.add(inputOperator);
    }

    public void removeInputOperator(InputOperator inputOperator) {
        String name = inputOperator.getName();
        for (FuzzyRule rule :
                Rules) {
            if (rule.get_aSet().getName().equals(name)) {
                rule.set_aSet(null);
            }
        }
        InputOperators.remove(inputOperator);
    }

    public void addRule(FuzzyRule rule) {
        Rules.add(rule);
    }

    public void removeRule(FuzzyRule rule) {
        String name = rule.getName();
        for (Aggregator aggregator :
                Aggregators) {
            for (WeightedSetGetter weightedSetGetter :
                    aggregator.getWeightedSetGetters()) {
                if (weightedSetGetter.getSetGetter().getName().equals(name)) {
                    aggregator.removeWeightedSetGetter(weightedSetGetter);
                }
            }
        }
        Rules.remove(rule);
    }

    public void addAggregator(Aggregator aggregator) {
        Aggregators.add(aggregator);
    }

    public void removeAggregator(Aggregator aggregator) {
        String name = aggregator.getName();
        for (Defuzzifier defuzzifier :
                Defuzzifiers) {
            if (defuzzifier.get_setGetter().getName().equals(name)) {
                defuzzifier.set_setGetter(null);
            }
        }
        Aggregators.remove(aggregator);
    }

    public void addDefuzzifier(Defuzzifier defuzzifier) {
        Defuzzifiers.add(defuzzifier);
    }

    public void removeDefuzzifier(Defuzzifier defuzzifier) {
        Defuzzifiers.remove(defuzzifier);
    }
}
