package fr.erusel.Utils;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Utils {

    public static EmbedBuilder createEmbed(String title, String description, Color color) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setDescription(description);
        embed.setColor(color);
        return embed;
    }


}
