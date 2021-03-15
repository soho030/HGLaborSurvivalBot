package de.hglabor

import com.gitlab.kordlib.kordx.emoji.Emojis
import de.hglabor.command.CommandManager
import de.hglabor.config.ConfigManager
import dev.kord.common.Color
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.event.message.ReactionAddEvent
import dev.kord.core.on
import dev.kord.rest.Image
import dev.kord.rest.builder.message.EmbedBuilder
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