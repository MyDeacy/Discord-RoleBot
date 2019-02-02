package mydeacy.discord.rolebot

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient

fun main(args: Array<String>) {
    RoleBot().run()
}

class RoleBot {

    fun run(){
        try {
            val client: IDiscordClient = ClientBuilder().withToken(Config.token).build()
            client.dispatcher.registerListener(EventListener(client))
            client.login()
        }catch(e: Exception){
            println("Cant login....")
        }
    }
}