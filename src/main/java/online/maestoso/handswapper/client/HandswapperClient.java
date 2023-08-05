package online.maestoso.handswapper.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Arm;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class HandswapperClient implements ClientModInitializer {
    private static KeyBinding swap;
    @Override
    public void onInitializeClient() {
        swap = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.handswapper.swap", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.gameplay"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(swap.wasPressed()) {
                Arm arm = client.options.getMainArm().getValue();
                client.options.getMainArm().setValue(arm == Arm.LEFT ? Arm.RIGHT : Arm.LEFT);
            }
        });
    }
}
