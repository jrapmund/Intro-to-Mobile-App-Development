package com.jrapmund.imad

import kotlinx.serialization.Serializable

@Serializable
class Department(var name:String, var aisle:Int) {

    var products = mutableListOf<Employee>()
        private set

    fun employee(inserter:(Employee.()->Unit)?) {
        var employee = Employee("NA", "NA", "NA")
        if(inserter!=null) {
            employee.inserter()
        }
        if(employee.firstName != "NA" && employee.lastName != "NA" && employee.position != "NA") {
            products.add(employee)
        } else {
            println("Could not add employee to department $name." +
                    "\nMissing fields.")
        }
    }
}