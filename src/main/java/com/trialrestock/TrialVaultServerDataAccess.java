package com.trialrestock;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2LongArrayMap;

import java.util.UUID;

public interface TrialVaultServerDataAccess {

    Object2IntArrayMap<UUID> trialrestock$getPlayerCosts();
    Object2LongArrayMap<UUID> trialrestock$getPlayerCooldowns();
    void trialrestock$setPlayerCooldowns(Object2LongArrayMap<UUID> value);
    void trialrestock$setPlayerCosts(Object2IntArrayMap<UUID> value);

}
