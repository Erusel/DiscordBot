package fr.erusel.commands;

import fr.erusel.Utils.Utils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;


public class TicketCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("ticketmessage")) return;
        sendTicketMessage(event);



    }
    public void sendTicketMessage(SlashCommandInteractionEvent event) {
        MessageEmbed ticketEmbed = Utils.createEmbed("Ticket", "Click on the button to create a Ticket \n Read the rules before", Color.RED).build();

        event.replyEmbeds(ticketEmbed)
                        .addActionRow(Button.primary("createticket", "Create a Ticket"))
                .queue();
    }

}
