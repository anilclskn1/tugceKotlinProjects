package com.tugceozcakir.oop

class Guitar: Instrument, Decoration {
    var brand: String? = null
    var electro: Boolean? = null

    override var room: String
        get() = "saloon"
        set(value) {}

}