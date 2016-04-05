package uk.jamierocks.classicserver.util;

import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.stream.NBTInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A utility for NBT.
 */
public final class NBTUtil {

    public static CompoundTag readOrCreatePlayerData(String username) {
        try {
            return (CompoundTag) new NBTInputStream(
                    new FileInputStream(new File("players/" + username + ".nbt"))).readTag();
        } catch (IOException e) {
            e.printStackTrace();
            return new CompoundTag(username, new CompoundMap());
        }
    }
}
