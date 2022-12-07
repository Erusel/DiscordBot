package fr.erusel.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class JoinLeaveListener extends ListenerAdapter {

    public void onGuildMemberJoined(GuildMemberJoinEvent event) throws LoginException {
        String user = event.getMember().getAsMention();

    }
}
