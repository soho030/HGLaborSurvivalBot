package de.hglabor.command.commands.doc

import de.hglabor.command.SlashCommand
import de.hglabor.command.commands.doc.entries.HeadEntry
import de.hglabor.command.commands.doc.entries.SpawnEntry
import dev.kord.common.Color
import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.string
import dev.kord.rest.builder.message.EmbedBuilder

val docEntries = listOf<DocEntry>(SpawnEntry, HeadEntry)

@KordPreview
object DocCommand : SlashCommand(
    name = "docs",
    "Show information about everything in the game.",
    {
        subCommand("read", "Zeigt Informationen über bestimmte Dinge") {
            string("entry", "Der Dokumentationseintrag, der angezeigt werden soll") {
                required = true
                for (it in docEntries)
                    choice(it.name, it.name)
            }
        }
    }
) {



    override suspend fun handleCommand(interaction: Interaction) {
        for(docEntry in docEntries) {
            val entry = interaction.command.options["entry"]?.string()
            if (entry != null) {
                if(docEntry.keywords.contains(entry.toLowerCase())) {
                    interaction.acknowledge().followUp {
                        embed {
                            title = "Verfasst von ${docEntry.author}"
                            description = docEntry.description
                            val thumb = EmbedBuilder.Thumbnail()
                            thumb.url = interaction.kord.getSelf().avatar.url
                            thumbnail = thumb
                            val foot = EmbedBuilder.Footer()
                            foot.icon = interaction.kord.getSelf().avatar.url
                            foot.text = "HGLaborSurvival Bot"
                            footer = foot
                            color = Color(0, 251, 255)
                            image = docEntry.image
                            field {
                                name = docEntry.name
                                value = docEntry.text
                            }
                        }
                    }
                }
            }
        }
    }

}