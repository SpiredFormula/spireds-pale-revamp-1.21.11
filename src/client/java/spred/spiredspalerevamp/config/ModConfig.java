package spred.spiredspalerevamp.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import spred.spiredspalerevamp.SpiredsPaleRevamp;

@Config(name = SpiredsPaleRevamp.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean PaleGardenFog = true;
}
