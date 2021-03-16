package com.jrapmund.imad

import kotlinx.serialization.Serializable

@Serializable
class Store(val id:Int, var name:String, var location:String?=null) {
    var departments = mutableListOf<Department>()
        private set

    fun department(inserter:(Department.()->Unit)?) {
        val department = Department("NA", -1)

        if(inserter != null) {
            department.inserter()
        }

        if(department.name != "NA" && department.aisle != -1) {
            departments.add(department)
        } else {
            println("Could not add the department to the store: [$name]." +
                    "\n Missing fields.")
        }
    }


}