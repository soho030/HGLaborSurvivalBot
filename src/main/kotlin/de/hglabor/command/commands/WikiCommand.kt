package de.hglabor.command.commands

import de.hglabor.command.SlashCommand
import dev.kord.common.Color
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.string
import dev.kord.rest.builder.message.EmbedBuilder

@KordPreview
object WikiCommand : SlashCommand(
    "wiki",
    "Mit diesem Command kannst du das Minecraft Wiki durchsuchen.",
    {
        subCommand("search", "Durchsuche das MineCraft Wiki") {
            string("keyword", "Das keyword nachdem du suchen möchtest")
        }
    }
) {
    override suspend fun handleCommand(interaction: Interaction) {
        val entry = interaction.command.options["keyword"]?.string()
        if (entry != null) {
            interaction.acknowledge().followUp {
                embed {
                    title = "Wikisearch - $entry"
                    description = "Klicke [hier](https://minecraft.gamepedia.com/${entry.toLowerCase()}) um zur Wikipage zu gelangen."
                    val thumb = EmbedBuilder.Thumbnail()
                    thumb.url = interaction.kord.getSelf().avatar.url
                    thumbnail = thumb
                    val foot = EmbedBuilder.Footer()
                    foot.icon = interaction.kord.getSelf().avatar.url
                    foot.text = "HGLaborSurvival Bot"
                    footer = foot
                    color = Color(0, 251, 255)
                }
            }
        }
    }
}