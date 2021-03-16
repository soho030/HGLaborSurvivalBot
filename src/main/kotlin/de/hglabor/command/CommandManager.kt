package de.hglabor.command

import de.hglabor.Manager
import de.hglabor.command.commands.HelpCommand
import de.hglabor.command.commands.InviteCommand
import de.hglabor.command.commands.doc.DocCommand
import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.createApplicationCommand
import dev.kord.core.entity.Guild
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.flow.collect

@KordPreview
object CommandManager {

    val commands = HashMap<String, SlashCommand>()

    fun register(command: SlashCommand) {
        commands[command.name] = command
    }

    suspend fun init() {
        HelpCommand
        DocCommand
        InviteCommand
        cleanupGuilds()
        Manager.client.guilds.collect {
            println("Registering commands for ${it.name}")
        }
        registerOnGuilds()
        Manager.client.on<GuildCreateEvent> {
            this.guild.cleanupCommands()
            this.guild.registerCommands()
        }
        Manager.client.on<InteractionCreateEvent> {
            this.interaction.type
            commands[interaction.command.rootName]?.handleCommand(interaction)
        }
    }

    private suspend fun registerOnGuilds() = Manager.client.guilds.collect { it.registerCommands() }

    private suspend fun cleanupGuilds() = Manager.client.guilds.collect { it.cleanupCommands() }

    private suspend fun Guild.registerCommands() {
        CommandManager.commands.forEach { commandEntry ->
            val command = commandEntry.value
            createApplicationCommand(command.name, command.description) { command.builder.invoke(this) }
        }
    }

    private suspend fun Guild.cleanupCommands() {
        commands.collect { command ->
            if (!CommandManager.commands.containsKey(command.name))
                command.delete()
        }
    }

}