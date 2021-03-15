package de.hglabor.command.commands.doc

import de.hglabor.command.SlashCommand
import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.Interaction

@KordPreview
object DocCommand : SlashCommand(
    name = "docs",
    "Show information about everything in the game."
) {

    val docEntries = listOf<DocEntry>()

    override suspend fun handleCommand(interaction: Interaction) {
        
    }

}