package com.cameraxtutorial.madel.database

import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.query
import io.realm.query.RealmQuery

open class DataModel() : RealmObject {
    var id: Int = 0
    var name: String? = null
    var created: String? = null
    var send : String? = null
}

object DataBase{
    private var id = 0
    private var name = "photo_$id"
    var configuration : RealmConfiguration? = null
    var realm : Realm?  = null

    fun  init() {
        configuration = RealmConfiguration.with(schema = setOf(DataModel::class))
        realm = Realm.open(configuration!!)
    }


    fun deleteData() {
        try {
            realm!!.writeBlocking {
                val query : RealmQuery<DataModel> = this.query()
                delete(query)
            }
            // clearFields()
            Log.d("Status","Data deleted !!!")

        }catch (e:Exception){
            Log.d("Status","Something went Wrong !!!")
        }
    }

    fun updateData(id : Int,_name:String,_created:String,_send:String) {

        try {

            realm!!.query<DataModel>("id = $0",id).first().find()
                ?.also { dataModel ->
                    realm!!.writeBlocking {
                        findLatest(dataModel)?.apply {
                            this.name = _name
                            this.created = _created
                            this.send = _send
                        }
                    }

                }
            Log.d("Status","Data Fetched !!!")
        }catch (e:Exception){
            Log.d("Status","Something went Wrong !!!")
        }
    }

    fun addData(_id:Int = ++id ,_name: String = name, _created: String, _send : String) {

        try {
            val data = DataModel().apply{
                id = _id
                name = _name
                created = _created
                send = _send
            }


            realm!!.writeBlocking { copyToRealm(data) }



            Log.d("Status","Data Inserted !!!")

        }catch (e:Exception){
            Log.d("Status","Something went Wrong !!!")
        }
    }

    fun readData() : List<DataModel>? {

        try {
            return  realm!!.query(DataModel::class).find()
            Log.d("Status","Data Fetched !!!")

        } catch (e: Exception) {
            Log.d("Status","Something went Wrong !!!")
            return null
        }
    }
}