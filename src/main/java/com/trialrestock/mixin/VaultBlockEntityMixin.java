package com.trialrestock.mixin;

import com.trialrestock.TrialVaultServerDataAccess;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2LongArrayMap;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.block.vault.VaultServerData;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(VaultBlockEntity.class)
public class VaultBlockEntityMixin {

    @Shadow @Final private VaultServerData serverData;

    @Inject(method = "writeData", at = @At("RETURN"))
    void injectWriteData(WriteView view, CallbackInfo ci) {

        TrialVaultServerDataAccess serverDataAccess = (TrialVaultServerDataAccess)serverData;

        for (UUID key : serverDataAccess.trialrestock$getPlayerCooldowns().keySet()) {

            view.putLong("trailrestock$playerCooldowns$" + key.toString(), serverDataAccess.trialrestock$getPlayerCooldowns().getLong(key));
        }

        for (UUID key : serverDataAccess.trialrestock$getPlayerCosts().keySet()) {

            view.putLong("trialrestock$playerCosts$" + key.toString(), serverDataAccess.trialrestock$getPlayerCosts().getInt(key));

        }
    }
    @Inject(method = "readData", at = @At("RETURN"))
    void injectReadData(ReadView view, CallbackInfo ci) {

        TrialVaultServerDataAccess serverDataAccess = (TrialVaultServerDataAccess)serverData;

        Object2LongArrayMap<UUID> cds = serverDataAccess.trialrestock$getPlayerCooldowns();

        view.keys().stream()
                .filter(key -> key.startsWith("trailrestock$playerCooldowns$"))
                .forEach(key -> {
                    UUID uuid = UUID.fromString(key.replace("trailrestock$playerCooldowns$", ""));
                    cds.put(uuid, view.getLong(key, 0));

                });


        serverDataAccess.trialrestock$setPlayerCooldowns(cds);

        Object2IntArrayMap<UUID> csts = serverDataAccess.trialrestock$getPlayerCosts();
        view.keys().stream()
                .filter(key -> key.startsWith("trialrestock$playerCosts$"))
                .forEach(key -> {
                    UUID uuid = UUID.fromString(key.replace("trialrestock$playerCosts$", ""));
                    csts.put(uuid, view.getInt(key, 0));
                });

        serverDataAccess.trialrestock$setPlayerCosts(csts);

    }

}
