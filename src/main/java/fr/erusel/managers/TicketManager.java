package fr.erusel.managers;

import fr.erusel.Utils.Utils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;
import java.util.EnumSet;

public class TicketManager extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("createticket")) {

            TextInput tickettitle = TextInput.create("tickettitle", "Ticket Subject", TextInputStyle.SHORT)
                    .setPlaceholder("Subject of this ticket")
                    .setMaxLength(100)
                    .setRequired(true)
                    .build();

            TextInput ticketbody = TextInput.create("ticketbody", "Ticket Body", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Your concerns go here")
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create("ticketcreator", "Ticket Creation")
                    .addActionRow(tickettitle)
                    .addActionRow(ticketbody)
                    .build();

            event.replyModal(modal).queue();
        }
        if (event.getComponentId().equals("closeticket")) {
            event.getChannel().delete().queue();
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (!event.getModalId().equals("ticketcreator")) return;

        String title = event.getValue("tickettitle").getAsString();
        String text = event.getValue("ticketbody").getAsString();
        createTicket(event, title, text);

        event.reply("Ticket Created").setEphemeral(true).queue();
    }

    public void createTicket(ModalInteractionEvent event, String title, String text) {

        EnumSet<Permission> permissions = EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND);

        for (Category category : event.getGuild().getCategories()) {
            if (category.getName().equals("Tickets")) {
                TextChannel textChannel = category.createTextChannel("ticket-" + event.getMember().getEffectiveName())
                        .addPermissionOverride(event.getGuild().getPublicRole(), null, permissions)
                        .addMemberPermissionOverride(event.getMember().getIdLong(), permissions, null)
                        .complete();
                Message messageEmbed = textChannel.sendMessageEmbeds(Utils.createEmbed(title, text, Color.GREEN).build()).complete();

                textChannel.editMessageEmbedsById(messageEmbed.getId()).applyMessage(messageEmbed).setActionRow(Button.danger("closeticket", "Close Ticket")).queue();
            }
        }

    }

}
