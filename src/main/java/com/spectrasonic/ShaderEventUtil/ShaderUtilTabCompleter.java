package com.spectrasonic.ShaderEventUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShaderUtilTabCompleter implements TabCompleter {

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

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return List.of("set");
        else if (args.length == 2)
            return SHADERS;
        else if (args.length == 3) {
            List<String> players = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers())
                players.add(p.getName());
            return players;
        }
        return null;
    }
}