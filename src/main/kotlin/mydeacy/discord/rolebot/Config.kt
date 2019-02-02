package mydeacy.discord.rolebot

class Config {

    companion object {
        @JvmStatic
        val token: String = "BOT Token"
        @JvmStatic
        val message: String = "**リアクションを付けると、自動で役職が割り振られます\n自分に合わせて役職をカスタムできます。**"
        @JvmStatic
        val roleMessage: String = "リアクションを追加すると 役職:%roleが付与されます。"
        @JvmStatic
        val ownerId: Long = 1234567890
        @JvmStatic
        val roles: List<String> = listOf("Fool", "Clever")
        @JvmStatic
        val roleIds: List<Long> = listOf(541172760236523520, 541172840863367178)
        @JvmStatic
        val reaction: List<String> = listOf("✅", "💣")
    }
}