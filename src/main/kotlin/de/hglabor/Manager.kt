package de.hglabor

import de.hglabor.command.CommandManager
import de.hglabor.config.ConfigManager
import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.event.gateway.DisconnectEvent
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.flow.collect

@KordPreview
suspend fun main() {
    Manager.start()
}

object Manager {

    lateinit var client: Kord; private set

    @KordPreview
    suspend fun start() {
        println("Starting...")
        client = Kord(
            ConfigManager.discordApplication.token
                ?: error("No token provided"))
        CommandManager.init()
        client.on<ReadyEvent> {
            client.guilds.collect {
                if(it.systemChannel != null) {
                    it.systemChannel!!.createMessage("Bot started successfully")
                }
            }
        }
        client.on<DisconnectEvent> {
            client.guilds.collect {
                if(it.systemChannel != null) {
                    it.systemChannel!!.createMessage("Bot got disconnected from this guild.")
                }
            }
        }
        client.on<GuildCreateEvent> {
            print("Connected to guild ${this.guild.name}")
        }
        client.login()
    }
}

object KordEXT {

    @KordPreview
    suspend fun Interaction.guild(): Guild {
        return kord.getGuild(data.guildId.value!!)!!
    }

    @KordPreview
    suspend fun Interaction.member(): Member {
        return guild().getMember(data.member.value!!.userId)
    }

}