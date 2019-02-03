package mydeacy.discord.rolebot

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.ReadyEvent
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionEvent
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent
import sx.blah.discord.handle.impl.obj.ReactionEmoji


class EventListener(private val client: IDiscordClient) {

    @EventSubscriber
    fun onReady(event: ReadyEvent) = println("Bot is ready!!")


    @EventSubscriber
    fun onReceiveMessage(event: MessageReceivedEvent) {
        val message = event.message
        val userId = event.author.longID
        val channel = event.channel
        if (!(userId == Config.ownerId && message.content == "!setup")) return
        channel.sendMessage(Config.message)
        Config.roles.forEachIndexed { index: Int, role: String ->
            channel.sendMessage(Config.roleMessage.replace("%role", role))
                .addReaction(ReactionEmoji.of(Config.reaction[index]))
        }
    }

    @EventSubscriber
    fun onAddReaction(event: ReactionAddEvent) = reactionProcess(event)

    @EventSubscriber
    fun onRemoveReaction(event: ReactionRemoveEvent) = reactionProcess(event)

    private fun reactionProcess(event: ReactionEvent) {
        val message = event.message
        val id = message.author.longID
        if (id != client.ourUser.longID) return
        if (event.user.longID == client.ourUser.longID) return
        Config.roles.forEachIndexed { index: Int, role: String ->
            if (Config.roleMessage.replace("%role", role) == message.content) {
                val user = event.user
                if (event is ReactionAddEvent) {
                    user.addRole(client.getRoleByID(Config.roleIds[index]))
                } else {
                    user.removeRole(client.getRoleByID(Config.roleIds[index]))
                }
            }
        }
    }
}