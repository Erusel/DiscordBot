package fr.erusel;

import fr.erusel.commands.NewsCommand;
import fr.erusel.commands.TicketCommand;
import fr.erusel.managers.ConfigManager;
import fr.erusel.managers.TicketManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // ConfigManager
        ConfigManager configManager = new ConfigManager();
        configManager.loadConfig();

        // Creating the Bot
        JDA jda = JDABuilder.createDefault(ConfigManager.BOT_TOKEN)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing(ConfigManager.BOT_ACTIVITY))
                .addEventListeners(new NewsCommand())
                .addEventListeners(new TicketCommand())
                .addEventListeners(new TicketManager())
                .build();

        jda.updateCommands().addCommands(
            Commands.slash("news", "Send a news in the news channel")
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_CHANNEL, Permission.MODERATE_MEMBERS)),
            Commands.slash("ticketmessage", "Create the ticket message")
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_CHANNEL, Permission.MODERATE_MEMBERS))

        ).queue();

        jda.awaitReady();
    }


}
