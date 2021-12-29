package com.Kaz42c.FuzzySampler.RulesScene;

import com.Kaz42c.FuzzySampler.FIS;
import com.Kaz42c.FuzzySampler.Fuzzy.DomainMembership;
import com.Kaz42c.FuzzySampler.Fuzzy.ISetGetter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BSetViewController extends MembershipViewController {
    public BSetViewController(RulesSceneController rulesSceneController) {
        super(rulesSceneController);
        label.setText("BSet Name: ");
        deleteButton.setText("Remove BSet");
    }

    @Override
    protected void addNewMembership() {
        rulesSceneController.getModel().addBSet(membershipFunction);
        membershipFunction.set_inputGetter(new ISetGetter() {
            @Override
            public String getName() {
                return "Dumb BSet input";
            }

            @Override
            public void setName(String name) {
                throw new NotImplementedException();
            }

            @Override
            public DomainMembership[] get() {
                DomainMembership[] result = new DomainMembership[FIS.PRECISION];
                for (int i = 0; i < result.length; i++) {
                    result[i] = new DomainMembership(0 + (1) * ((double) i / (FIS.PRECISION - 1)), 1d, "BSet");
                }
                return result;
            }
        });
    }

    @Override
    protected void removeExistingMembership() {
        rulesSceneController.getModel().removeBSet(membershipFunction);
        rulesSceneController.updateAllDropdowns();
    }

    @Override
    public void updateDropdowns() {
    }
}
