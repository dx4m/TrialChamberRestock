package com.trialrestock.mixin;

import net.minecraft.block.vault.VaultServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VaultServerData.class)
public interface TrialVaultServerDataAccessor {

    @Invoker("getLastFailedUnlockTime")
    long trialrestock$getLastFailedUnlockTime();
    @Invoker("setLastFailedUnlockTime")
    void trialrestock$setLastFailedUnlockTime(long lastFailedUnlockTime);

}
