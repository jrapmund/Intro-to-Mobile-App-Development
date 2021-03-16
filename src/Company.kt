package com.jrapmund.imad

object Company {

    var stores = mutableListOf<Store>()
        private set

    var numOfStores = 0
        private set

    operator fun invoke(inserter:Company.()->Unit) {
        this.inserter()
    }

    fun store(inserter:(Store.()->Unit)?) {
        val storeContext = Store(numOfStores + 1, "NA", null)

        if (inserter != null) {
            storeContext.inserter()
        }

        if(storeContext.name != "NA") {
            stores.add(storeContext)
            numOfStores++
        } else {
            println("Could not add store to the company.\nMissing values.")
        }
    }
}