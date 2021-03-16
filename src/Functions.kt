package com.jrapmund.imad

object Functions {
    fun compareFunction(obj1:Int?, obj2:String?, cond:(val1:Int, val2:Int)->Boolean):Boolean {
        return cond(obj1?:0, (obj2?:"0").toInt())
    }

    fun initializeCompanyDSL() {
        Company {
            store{
                name = "Store 1"
                department {
                    name = "Produce"
                    aisle = 1
                    employee {
                        firstName = "Makaio"
                        lastName = "Matthijs"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Ascanio"
                        lastName = "Maxentius"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Bohumir"
                        lastName = "Thandeka"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Bedros"
                        lastName = "Fran"
                        position = "Manager"
                    }
                }
                department {
                    name = "Beauty"
                    aisle = 2
                    employee {
                        firstName = "Draha"
                        lastName = "Bronislav"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Jayme"
                        lastName = "Elpis"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Martin"
                        lastName = "Wafai"
                        position = "Associate"
                    }
                    employee {
                        firstName = "Sylwia"
                        lastName = "Orvar"
                        position = "Manager"
                    }
                }
            }
        }
    }
}