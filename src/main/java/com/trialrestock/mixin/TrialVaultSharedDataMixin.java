package com.trialrestock.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import com.trialrestock.TrialVaultServerDataAccess;
import net.minecraft.block.vault.VaultServerData;
import net.minecraft.block.vault.VaultSharedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import java.util.UUID;
import java.util.function.Predicate;

@Mixin(VaultSharedData.class)
public class TrialVaultSharedDataMixin {

    @ModifyArg(method = "updateConnectedPlayers", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;"))
    private Predicate<UUID> checkCooldowns(Predicate<UUID> predicate, @Local(argsOnly = true) VaultServerData serverData) {

        return uuid -> !(((TrialVaultServerDataAccess)serverData).trialrestock$getPlayerCooldowns().containsKey(uuid));

    }

}
