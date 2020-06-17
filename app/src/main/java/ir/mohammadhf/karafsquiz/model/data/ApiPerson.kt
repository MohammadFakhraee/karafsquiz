package ir.mohammadhf.karafsquiz.model.data

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApiPerson(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
) {
    companion object {
        const val TAG = "Person"
        const val SEPARATOR = '-'
    }

    fun isRelatedTo(apiPerson: ApiPerson): Boolean {
        Log.i(TAG, "$firstName is comparing with ${apiPerson.firstName}")
        val thisLastNameList = getLastNameList()
        val otherLastNameList = apiPerson.getLastNameList()
        Log.i(TAG, "$thisLastNameList")
        Log.i(TAG, "$otherLastNameList")
        for (item in thisLastNameList)
            for (otherItem in otherLastNameList)
                if (item == otherItem) return true
        return false
    }

    private fun getLastNameList(): List<String> {
        val thisLastNameList = ArrayList<String>()
        var thisLastName = lastName!!
        while (thisLastName.isNotEmpty()) {
            val separatorPos = thisLastName.indexOfFirst { c -> c == SEPARATOR }
            Log.i(TAG, "$firstName has $lastName")
            thisLastName = if (separatorPos != -1) {
                // We have more than one LastName
                val subStr = thisLastName.substring(0, separatorPos)
                thisLastNameList.add(subStr)
                thisLastName.replace(subStr + SEPARATOR, "")
            } else {
                // We have one LastName
                thisLastNameList.add(thisLastName)
                thisLastName.replace(thisLastName, "")
            }
        }
        return thisLastNameList
    }
}