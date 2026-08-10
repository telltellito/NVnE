package me.telltellito.nVnE.handler;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.telltellito.nVnE.NVnE;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionHandler implements Listener {

    private final NVnE plugin;

    public NightVisionHandler(NVnE plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    boolean nv = false;

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        String msg = PlainTextComponentSerializer
                .plainText()
                .serialize(event.message());

        Player player = event.getPlayer();

        if (msg.equalsIgnoreCase(".nv")) {

            Bukkit.getScheduler().runTask(plugin, () -> {

                if (!nv) {

                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1999999999, 1, true, false, false));

                    player.sendMessage("NightVision Enabled");

                    nv = true;

                } else {

                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);

                    player.sendMessage("NightVision Disabled");

                    nv = false;
                }

            });
        }
    }
}
