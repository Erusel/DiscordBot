package fr.erusel.commands;

import fr.erusel.Utils.Utils;
import fr.erusel.managers.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;

public class NewsCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("news")) return;
        TextInput newsTitle = TextInput.create("newstitle", "News Title", TextInputStyle.SHORT)
                .setPlaceholder("The Title of the News")
                .setMaxLength(100)
                .build();

        TextInput newsText = TextInput.create("newstext", "Text", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Write your news here")
                .build();

        Modal modal = Modal.create("news", "Create a News")
                .addActionRow(newsTitle)
                .addActionRow(newsText)
                .build();

        event.replyModal(modal).queue();
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (!event.getModalId().equals("news")) return;

        String title = event.getValue("newstitle").getAsString();
        String text = event.getValue("newstext").getAsString();
        sendNews(event.getJDA(), title, text);

        event.reply("News Created").setEphemeral(true).queue();

    }

    public void sendNews(JDA jda, String title, String text) {
        TextChannel newsChannel = jda.getTextChannelById(ConfigManager.NEWS_CHANNEL_ID);
        MessageEmbed newsEmbed = Utils.createEmbed(title, text, Color.GREEN).build();
        newsChannel.sendMessageEmbeds(newsEmbed).queue();
    }



}
