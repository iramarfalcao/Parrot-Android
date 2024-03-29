package com.iramarjunior.parrot.modules.authentication.database

import com.iramarjunior.parrot.core.SessionController
import com.iramarjunior.parrot.modules.authentication.model.SessionAuthentication
import com.iramarjunior.parrot.modules.authentication.model.User
import io.realm.Realm

object AuthenticationDatabase {

    fun saveUser(user: User) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()
        }

        SessionController.user = user
    }

    fun getUser(): User? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(User::class.java)
                .findFirst()?.let { user ->
                    realm.copyFromRealm(user)
                }
        }
    }

    fun clearAppData() {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }

        SessionController.user = null
        SessionController.sessionAuthentication = null
    }

    fun saveSessionAuthentication(sessionAuthentication: SessionAuthentication) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(sessionAuthentication)
            realm.commitTransaction()
        }

        SessionController.sessionAuthentication = sessionAuthentication
    }

    fun getSessionAuthentication(): SessionAuthentication? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(SessionAuthentication::class.java)
                .equalTo(SessionAuthentication::identifier.name, 0.toInt())
                .findFirst()?.let { sessionAuth ->
                    realm.copyFromRealm(sessionAuth)
                }
        }
    }
}