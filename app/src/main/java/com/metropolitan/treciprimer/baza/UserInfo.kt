package com.metropolitan.treciprimer.baza

class UserInfo(
    var username: String = "Ne postoji",
    var password: String = "Ne postoji",
    var id: Int = 999
) {
    override fun toString(): String {
        return "UserInfo(username='$username', password='$password', id=$id)"
    }
}