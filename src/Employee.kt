package com.jrapmund.imad

import kotlinx.serialization.Serializable

@Serializable
class Employee(var firstName:String, var lastName:String, var position:String)