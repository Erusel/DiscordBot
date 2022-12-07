package fr.erusel.listeners;

import fr.erusel.Utils.Utils;
import fr.erusel.managers.ConfigManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class JoinLeaveListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        String user = event.getMember().getAsMention();
        TextChannel textChannel = event.getGuild().getTextChannelById(ConfigManager.JOIN_LEAVE_CHANNEL_ID);
        textChannel.sendMessageEmbeds(Utils.createEmbed("Welcome " + user + " !", "Welcome to the server !", Color.GREEN).build()).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event){
        String user = event.getMember().getAsMention();
        TextChannel textChannel = event.getGuild().getTextChannelById(ConfigManager.JOIN_LEAVE_CHANNEL_ID);
        textChannel.sendMessageEmbeds(Utils.createEmbed("Goodbye " + user + " !", "See you soon !", Color.RED).build()).queue();
    }
}
