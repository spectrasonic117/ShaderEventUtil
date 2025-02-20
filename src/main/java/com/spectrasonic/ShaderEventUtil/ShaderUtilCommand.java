package com.spectrasonic.ShaderEventUtil;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ShaderUtilCommand implements CommandExecutor {

    private static final List<String> SHADERS = Arrays.asList(
            "anaglyph", "antialias", "autumnal", "bevel", "bloom", "bloom_color", "bloom_spike",
            "brightness", "checkerboard", "cinematic", "collapse", "color_bleed", "color_blind",
            "color_convolve", "contrast", "deconverge", "depth_outline", "desaturate", "dramatic",
            "film_grain", "fisheye", "flip", "fractal", "ghost", "glass", "glitchy", "halftone",
            "horror", "hyperspace", "kaleidoscope", "life", "love", "mandelbrot", "ntsc",
            "out_of_bounds", "outline", "phosphor", "plants", "retro", "rolling_shutter",
            "scan_pincushion", "sepia", "shareware", "stereogram", "thermal", "toxic_waste",
            "voronoi", "wobble"
    );

    private final Main plugin;

    public ShaderUtilCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 3 || !args[0].equalsIgnoreCase("set")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Uso incorrecto. Usa: /shaderutil set <shader> <player>"));
            return false;
        }

        String shader = args[1];
        String playerName = args[2];

        if (!SHADERS.contains(shader)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Shader no válido."));
            return false;
        }

        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Jugador no encontrado."));
            return false;
        }

        // Envía un mensaje plugin al cliente para que ejecute el comando localmente
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(baos)) {

            dos.writeUTF("soup:set");
            dos.writeUTF(shader);
            player.sendPluginMessage(plugin, "soup:channel", baos.toByteArray());
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Mensaje enviado al cliente de " + player.getName() + "."));
        } catch (IOException e) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Error al enviar el mensaje."));
            e.printStackTrace();
        }
        return true;
    }
}
