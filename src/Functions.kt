package com.jrapmund.imad

object Functions {
    fun compareFunction(obj1:Int?, obj2:String?, cond:(val1:Int, val2:Int)->Boolean):Boolean {
        return cond(obj1?:0, (obj2?:"0").toInt())
    }
}