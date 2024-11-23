package com.pragya.oimspro.machine.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;

public interface MachineConfigurationHistoryService {
    void saveMachineConfigurationHistory(MachineConfigurationHistory machineConfigurationHistory);

    void addNewMachineConfiguration(Machine newMachine,String entitiesUpdated);

    MachineConfigurationHistory getMachineConfigurationHistoryByMachineId(Machine newMachine);

    void updateLastMachineConfiguration(Machine oldMachine, Machine newMachine);
}
