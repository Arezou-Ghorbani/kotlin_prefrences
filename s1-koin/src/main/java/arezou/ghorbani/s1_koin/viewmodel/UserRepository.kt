package arezou.ghorbani.s1_koin.viewmodel

class UserRepository(private val family: String) : UserInfo {
    override fun userName(): String {
        return "Mohammad $family"
    }
}